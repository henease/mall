package com.netease.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//该Controller处理退出页面
@Controller
public class Logout {
	
	/* 处理退出页面，获取session，验证session是否为空
	 * 为空则利用invalidate方法清空信息，跳转至login页面
	 */
	@RequestMapping(path="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest req,HttpSession session){
		session = req.getSession();
		if(session != null){
			session.invalidate();
		}
		return "redirect:/login";
	}
}
