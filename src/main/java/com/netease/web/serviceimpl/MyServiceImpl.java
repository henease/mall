package com.netease.web.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netease.web.dao.FirstDao;
import com.netease.web.meta.Product;
import com.netease.web.service.FirstService;

@Service
public class MyServiceImpl implements FirstService {
	//向MyServiceImpl注入FirstDao
	@Autowired
	private FirstDao dao;
	
	//验证用户表中是否存在用户，登录验证
	public int check(String userName, String password, int userType) {
		return dao.checkUser(userName, password, userType);
	}
	//通过left join的方式获取对应的product属性，在首页显示所有商品
	public List<Product> getProductList() {
		return dao.getProducts();
	}
	//删除商品
	public int delete(int id) {
		return dao.delete(id);
	}
	//根据id获取对应商品信息，传入show页面
	public Product getProduct(int id) {
		return dao.getProduct(id);
	}
	
	/* 发布商品时向商品列表插入新数据,并拿到该商品的自增主键
	 * 这里执行了两句sql操作，通过声明式的方式将它们合并为一个事务
	 */
	@Transactional
	public int insertContent(long price, String title, String image, String summary, String detail) {
		dao.addContent(price, title, image, summary, detail);
		return dao.getId();
	}
	//编辑商品时更新商品列表
	public void updateContent(long price, String title, String image, String summary, String detail,int id) {
		dao.updateContent(price, title, image, summary, detail,id);
	}
	//买家购买商品后向trx表插入一条新数据
	public int addTrx(int contentId, int personId, double price, long time) {
		return dao.insertTrx(contentId, personId, price, time);
	}
	//通过left join的方式获取trx和content中的已购买商品的属性信息，显示在账务页面
	public List<Product> getTrx() {
		return dao.getTrx();
	}

}
