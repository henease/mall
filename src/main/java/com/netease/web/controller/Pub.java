package com.netease.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.web.utils.CheckSession;

@Controller
public class Pub {
	@RequestMapping(path="/public",method=RequestMethod.GET)
	public String pub(HttpSession session,HttpServletRequest req,Model model){
		CheckSession.checkUser(session, model, req);
		return "public";
	}
}
