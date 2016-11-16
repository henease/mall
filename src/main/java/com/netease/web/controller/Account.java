package com.netease.web.controller;

import java.util.Iterator;
import java.util.List;

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
public class Account {
	@Autowired
	private MyServiceImpl myService;
	
	@RequestMapping(path="/account",method=RequestMethod.GET)
	public String account(HttpServletRequest req,HttpSession session,Model model){
		CheckSession.checkUser(session, model, req);
		List<Product> productList = myService.getTrx();
		Iterator<Product> it = productList.iterator();
		while(it.hasNext()){
			Product product = it.next();
			product.setBuyPrice((double)(product.getBuyPrice())/100);
		}
		model.addAttribute(productList);
		return "account";
	}
}
