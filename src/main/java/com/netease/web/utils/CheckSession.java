package com.netease.web.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.netease.web.meta.User;

public class CheckSession {
	//获取请求中的session，验证是否含有user信息，判断buyer或seller是否登录
	public static void checkUser(HttpSession session,Model model,HttpServletRequest req){
		session = req.getSession();
		if(session.getAttribute("currentUser") != null){
			User user = (User) session.getAttribute("currentUser");
			model.addAttribute(user);
		}
	}
}
