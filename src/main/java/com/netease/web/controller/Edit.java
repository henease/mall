package com.netease.web.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netease.web.meta.Product;
import com.netease.web.serviceimpl.MyServiceImpl;
import com.netease.web.utils.CheckSession;
import com.netease.web.utils.PriceTrans;

//该Controller处理编辑页面的数据
@Controller
public class Edit {
	//自动注入MyServiceImpl
	@Autowired
	private MyServiceImpl myService;
	
	/*根据请求时的商品id从数据库获取product数据，
	 * 对detail进行转码，处理数据库中取出的price数据
	 */
	@RequestMapping(path="/edit",method=RequestMethod.GET)
	public String edit(HttpServletRequest req,HttpSession session,Model model,@RequestParam("id")int id){
		CheckSession.checkUser(session, model, req);
		Product product = myService.getProduct(id);
		try{
			String detail = new String(product.getDetail().getBytes("iso-8859-1"),"UTF-8");
			product.setDetail(detail);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		product.setPrice(PriceTrans.transPriceToDouble(product.getPrice()));
		model.addAttribute(product);
		return "edit";
	}
}
