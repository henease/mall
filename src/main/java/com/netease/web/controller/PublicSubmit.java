package com.netease.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netease.web.meta.Product;
import com.netease.web.serviceimpl.MyServiceImpl;
import com.netease.web.utils.CheckSession;
import com.netease.web.utils.PriceTrans;

//该Controller处理发布提交时的数据
@Controller
public class PublicSubmit {
	//自动注入MyServiceImpl
	@Autowired
	private MyServiceImpl myService;
	
	/* 获取price数据，将String转换为long型存入数据库；
	 * 获取其他对应的属性；
	 * 插入数据时获取该数据的自增id,将其设置到product的id属性中。
	 */
	@RequestMapping(path="/publicSubmit",method=RequestMethod.POST)
	public String publicSubmit(HttpServletRequest req,HttpSession session,Model model){
		CheckSession.checkUser(session, model, req);
		long price = PriceTrans.transPriceToLong(req.getParameter("price"));
		String title = req.getParameter("title");
		String summary = req.getParameter("summary");
		String image = req.getParameter("image");
		String detail = req.getParameter("detail");
		int a = myService.insertContent(price, title, image, summary, detail);
		Product product = myService.getProduct(a);
		product.setId(a);
		model.addAttribute(product);
		return "publicSubmit";
	}
}
