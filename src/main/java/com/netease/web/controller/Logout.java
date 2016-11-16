package com.netease.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Logout {
	@RequestMapping(path="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest req,HttpSession session){
		session = req.getSession();
		if(session != null){
			session.invalidate();
		}
		return "redirect:/login";
	}
}
