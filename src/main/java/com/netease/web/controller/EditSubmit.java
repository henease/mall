package com.netease.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netease.web.meta.Product;
import com.netease.web.serviceimpl.MyServiceImpl;
import com.netease.web.utils.CheckSession;
import com.netease.web.utils.PriceTrans;

//该Controller处理编辑提交时的数据
@Controller
public class EditSubmit {
	//自动注入MyServiceImpl
	@Autowired
	private MyServiceImpl myService;
	
	/* 编辑提交时数据处理
	 * 将获得的price由String转为double，并转换为long型存入数据库
	 * 根据请求时的商品id将更改后的属性更新到对应商品表中
	 */
	@RequestMapping(path="/editSubmit",method=RequestMethod.POST)
	public String editSubmit(HttpServletRequest req,HttpSession session,Model model,@RequestParam("id")int id){
		CheckSession.checkUser(session, model, req);
		long price = PriceTrans.transPriceToLong(req.getParameter("price"));
		String title = req.getParameter("title");
		String summary = req.getParameter("summary");
		String image = req.getParameter("image");
		String detail = req.getParameter("detail");
		myService.updateContent(price, title, image, summary, detail,id);
		Product product = myService.getProduct(id);
		model.addAttribute(product);
		return "editSubmit";
	}
}
