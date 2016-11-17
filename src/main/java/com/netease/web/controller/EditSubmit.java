package com.netease.web.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
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
public class EditSubmit {
	@Autowired
	private MyServiceImpl myService;
	
	@RequestMapping(path="/editSubmit",method=RequestMethod.POST)
	public String editSubmit(HttpServletRequest req,HttpSession session,Model model,@RequestParam("id")int id) throws UnsupportedEncodingException{
		CheckSession.checkUser(session, model, req);
		//long price = (long)(Double.parseDouble(req.getParameter("price"))*100);
		long price = new BigDecimal(req.getParameter("price"))
				.multiply(new BigDecimal(Integer.toString(100))).longValue();
		String title = req.getParameter("title");
		String summary = req.getParameter("summary");
		String image = req.getParameter("image");
		String detail = req.getParameter("detail");
		myService.updateContent(price, title, image, summary, detail,id);
		Product product = myService.getProduct(id);
		model.addAttribute(product);
		return "editSubmit";
	}
}
