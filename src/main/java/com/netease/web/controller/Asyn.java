package com.netease.web.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.netease.web.meta.User;
import com.netease.web.serviceimpl.MyServiceImpl;

@Controller
@RequestMapping(path="/api")
public class Asyn {
	@Autowired
	private MyServiceImpl myService;
	
	@ResponseBody
	@RequestMapping(path="/login",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest req,User user,ModelAndView model,HttpSession session){
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		user.setUserName(userName);
		user.setPassword(password);
		if(user.getUserName().equals("buyer")){
			user.setUserType(0);
		}
		if(user.getUserName().equals("seller")){
			user.setUserType(1);
		}
		if(myService.check(userName, password, user.getUserType()) == 0){
			model.addObject("code", 304);
			model.addObject("result", false);
			model.addObject("message", "用户名或密码错误");
		}else{
			model.addObject("code", 200);
			model.addObject("result", true);
			model.addObject("message", "Welcome");
			session = req.getSession();
			if(session.getAttribute("currentUser") == null){
				session.setAttribute("currentUser", user);
			}
		}
		return model;
	}
	
	@RequestMapping(path="/delete",method=RequestMethod.POST)
	public ModelAndView delete(@RequestParam("id")int id,ModelAndView model){
		int a = myService.delete(id);
		if(a == 1){
			model.addObject("code",200);
			model.addObject("result",true);
			model.addObject("message", "删除成功");
		}
		else{
			model.addObject("code",400);
			model.addObject("result",false);
			model.addObject("message", "删除失败");
		}
		return model;
	}
	
	@RequestMapping(path="/buy",method=RequestMethod.POST)
	public ModelAndView buy(@RequestParam("id")int id,ModelAndView model){
		int contentId = id;
		int personId = 1;
		double price = myService.getProduct(id).getPrice();
		long time = new Date().getTime();
		int a = myService.addTrx(contentId, personId, price, time);
		if(a == 1){
			model.addObject("code",200);
			model.addObject("result",true);
			model.addObject("message", "购买成功");
		}else{
			model.addObject("code",400);
			model.addObject("result",false);
			model.addObject("message", "购买失败");
		}
		return model;
	}
}
