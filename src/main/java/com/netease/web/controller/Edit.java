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

@Controller
public class Edit {
	@Autowired
	private MyServiceImpl myService;
	
	@RequestMapping(path="/edit",method=RequestMethod.GET)
	public String edit(HttpServletRequest req,HttpSession session,Model model,@RequestParam("id")int id) throws UnsupportedEncodingException{
		CheckSession.checkUser(session, model, req);
		Product product = myService.getProduct(id);
		String detail = new String(product.getDetail().getBytes("iso-8859-1"),"UTF-8");
		product.setDetail(detail);
		product.setPrice((double)(product.getPrice())/100);
		model.addAttribute(product);
		return "edit";
	}
}
