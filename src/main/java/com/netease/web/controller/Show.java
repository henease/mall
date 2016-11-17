package com.netease.web.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netease.web.meta.Product;
import com.netease.web.serviceimpl.MyServiceImpl;
import com.netease.web.utils.CheckSession;

@Controller
public class Show {
	@Autowired
	private MyServiceImpl myService;
	
	@RequestMapping(path="/show",method=RequestMethod.GET)
	public String show(HttpServletRequest req,HttpSession session,Model model,@RequestParam("id")int id) throws UnsupportedEncodingException{
		CheckSession.checkUser(session, model, req);
		Product product = myService.getProduct(id);
		String detail = new String(product.getDetail().getBytes("iso-8859-1"),"UTF-8");
		product.setDetail(detail);
		//product.setPrice((double)(product.getPrice())/100);
		product.setPrice(new BigDecimal(Double.toString(product.getPrice()))
				.divide(new BigDecimal(Integer.toString(100))).doubleValue());
		if(product.getBuyPrice() != null){
			product.setIsBuy("true");
			//product.setBuyPrice((double)(product.getBuyPrice())/100);
			product.setBuyPrice(new BigDecimal(Double.toString(product.getBuyPrice()))
					.divide(new BigDecimal(Integer.toString(100))).doubleValue());
		}
		model.addAttribute(product);
		return "show";
	}
}
