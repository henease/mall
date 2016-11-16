package com.netease.web.controller;

import java.io.UnsupportedEncodingException;

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
public class PublicSubmit {
	@Autowired
	private MyServiceImpl myService;
	
	@RequestMapping(path="/publicSubmit",method=RequestMethod.POST)
	public String publicSubmit(HttpServletRequest req,HttpSession session,Model model) throws UnsupportedEncodingException{
		CheckSession.checkUser(session, model, req);
		long price = (long)(Double.parseDouble(req.getParameter("price"))*100);
		String title = req.getParameter("title");
		String summary = req.getParameter("summary");
		String image = req.getParameter("image");
		String detail = req.getParameter("detail");
		int a = myService.insertContent(price, title, image, summary, detail);
		Product product = myService.getProduct(a);
		product.setId(a);
		model.addAttribute(product);
		return "publicSubmit";
	}
}
