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

//异步数据处理的Controller
@Controller
@RequestMapping(path="/api")
public class Asyn {
	//注入MyServiceImpl
	@Autowired
	private MyServiceImpl myService;
	
	/* 登录时的异步处理
	 * 检查用户名是否是buyer或者seller其中之一，如果是就设置对应的userType
	 * 通过select数据时影响的数据条数来判断是否存在用户
	 */
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
	
	/* 删除时的异步处理
	 * 利用delete返回影响的数据条数来判断是否已经删除对应数据
	 */
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
	
	/* 购买商品时的异步处理
	 * 根据请求时的商品id传入对应的contentId和price
	 * 获得购买商品时的long型购买时间
	 */
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
