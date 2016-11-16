package com.netease.web.service;


import java.util.List;


import com.netease.web.meta.Product;

public interface FirstService {
	public int check(String userName,String password,int userType);
	public List<Product> getProductList();
	public int delete(int id);
	public int insertContent(long price, String title, String image, String summary, String detail);
	public void updateContent(long price, String title, String image, String summary, String detail,int id);
	public int addTrx(int contentId,int personId,double price,long time);
	public List<Product> getTrx();
	public Product getProduct(int id);
}
