package com.netease.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//该Controller处理登录页面
@Controller
public class Login {
	
	//不需要传入数据，直接返回login字符串
	@RequestMapping(path="/login",method=RequestMethod.GET)
	public String login(){
		return "login";
	}
}
