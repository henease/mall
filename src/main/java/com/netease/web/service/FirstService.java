package com.netease.web.service;


import java.util.List;


import com.netease.web.meta.Product;

public interface FirstService {
	//验证用户表中是否存在用户，登录验证
	public int check(String userName,String password,int userType);
	
	//获取所有的product数据，在首页显示所有商品
	public List<Product> getProductList();
	
	//删除指定id的商品
	public int delete(int id);
	
	//发布商品时向商品列表插入新数据
	public int insertContent(long price, String title, String image, String summary, String detail);
	
	//编辑商品时更新商品列表
	public void updateContent(long price, String title, String image, String summary, String detail,int id);
	
	//买家购买商品后向trx表插入一条新数据
	public int addTrx(int contentId,int personId,double price,long time);
	
	//获取trx中的已购买商品信息，显示在账务页面
	public List<Product> getTrx();
	
	//根据id获取对应商品信息，传入show页面
	public Product getProduct(int id);
}
