package com.netease.web.controller;

import java.util.Iterator;
import java.util.List;
import java.math.BigDecimal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.web.meta.Product;
import com.netease.web.serviceimpl.MyServiceImpl;
import com.netease.web.utils.CheckSession;

@Controller
public class Index {
	@Autowired
	private MyServiceImpl myService;
	
	@RequestMapping(path="/",method=RequestMethod.GET)
	public String indexPage(Model model,HttpSession session,HttpServletRequest req){
		CheckSession.checkUser(session, model, req);
		List<Product> productList = myService.getProductList();
		Iterator<Product> it = productList.iterator();
		while(it.hasNext()){
			Product product = it.next();
			//product.setPrice((double)(product.getPrice())/100);
			product.setPrice(new BigDecimal(Double.toString(product.getPrice()))
			.divide(new BigDecimal(Integer.toString(100))).doubleValue());
			if(product.getBuyTime() != null){
				product.setIsBuy("true");
				product.setIsSell("true");
			}
		}
		model.addAttribute(productList);
		return "index";
	}
}
