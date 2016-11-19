package com.netease.web.controller;

import java.util.Iterator;
import java.util.List;

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

//该Controller处理账务的数据
@Controller
public class Account {
	//注入MyServiceImpl
	@Autowired
	private MyServiceImpl myService;
	
	/* 利用checkUser方法验证登录用户
	 * 利用getTrx()方法获取trx表中的product数据，传入List
	 * 对每个从数据库中取出的price进行浮点数转换
	 */
	@RequestMapping(path="/account",method=RequestMethod.GET)
	public String account(HttpServletRequest req,HttpSession session,Model model){
		CheckSession.checkUser(session, model, req);
		List<Product> productList = myService.getTrx();
		Iterator<Product> it = productList.iterator();
		while(it.hasNext()){
			Product product = it.next();
			product.setBuyPrice(PriceTrans.transPriceToDouble(product.getBuyPrice()));
		}
		model.addAttribute(productList);
		return "account";
	}
}
