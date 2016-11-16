package com.netease.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.netease.web.meta.User;

public class CheckSession {
	public static void checkUser(HttpSession session,Model model,HttpServletRequest req){
		session = req.getSession();
		if(session.getAttribute("currentUser") != null){
			User user = (User) session.getAttribute("currentUser");
			model.addAttribute(user);
		}
	}
}
