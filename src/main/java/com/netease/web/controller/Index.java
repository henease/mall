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

//该Controller处理首页的数据
@Controller
public class Index {
	//自动注入MyServiceImpl
	@Autowired
	private MyServiceImpl myService;
	
	/* 处理首页的数据
	 * 根据left join获取content和trx表的数据，通过buyTime属性来判断商品是否同时存在；
	 * 于content和trx表中，如果存在则表示已买(已卖)，此时设置isBuy和isSell属性，否则
	 * 这两个属性为null；
	 * 将数据库取出的price进行double转换。
	 */
	@RequestMapping(path="/",method=RequestMethod.GET)
	public String indexPage(Model model,HttpSession session,HttpServletRequest req){
		CheckSession.checkUser(session, model, req);
		List<Product> productList = myService.getProductList();
		Iterator<Product> it = productList.iterator();
		while(it.hasNext()){
			Product product = it.next();
			product.setPrice(PriceTrans.transPriceToDouble(product.getPrice()));
			if(product.getBuyTime() != null){
				product.setIsBuy("true");
				product.setIsSell("true");
			}
		}
		model.addAttribute(productList);
		return "index";
	}
}
