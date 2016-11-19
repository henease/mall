package com.netease.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.web.utils.CheckSession;

//该Controller处理发布页面
@Controller
public class Pub {
	
	//该页面不需要传入数据，只需要验证用户登录后返回public字符串
	@RequestMapping(path="/public",method=RequestMethod.GET)
	public String pub(HttpSession session,HttpServletRequest req,Model model){
		CheckSession.checkUser(session, model, req);
		return "public";
	}
}
