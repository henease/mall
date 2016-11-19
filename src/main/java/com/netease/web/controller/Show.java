package com.netease.web.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.netease.web.meta.Product;
import com.netease.web.serviceimpl.MyServiceImpl;
import com.netease.web.utils.CheckSession;
import com.netease.web.utils.PriceTrans;

//该Controller处理show页面的数据
@Controller
public class Show {
	//自动注入MyServiceImpl
	@Autowired
	private MyServiceImpl myService;
	
	/* 处理show页面的数据
	 * checkUser验证登录用户；
	 * 根据请求时的id参数从数据库获取对应的product对象；
	 * 对detail属性进行编码转换，避免乱码，对price进行double转换；
	 * 通过left join同时从content和trx表获取数据，
	 * 如果有buyPrice属性值，则设置isBuy，对trx表中的buyPrice进行double转换
	 */
	@RequestMapping(path="/show",method=RequestMethod.GET)
	public String show(HttpServletRequest req,HttpSession session,Model model,@RequestParam("id")int id){
		CheckSession.checkUser(session, model, req);
		Product product = myService.getProduct(id);
		try{
			String detail = new String(product.getDetail().getBytes("iso-8859-1"),"UTF-8");
			product.setDetail(detail);
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		product.setPrice(PriceTrans.transPriceToDouble(product.getPrice()));
		if(product.getBuyPrice() != null){
			product.setIsBuy("true");
			product.setBuyPrice(PriceTrans.transPriceToDouble(product.getBuyPrice()));
		}
		model.addAttribute(product);
		return "show";
	}
}
