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
	@Autowired
	private FirstDao dao;

	public int check(String userName, String password, int userType) {
		return dao.checkUser(userName, password, userType);
	}

	public List<Product> getProductList() {
		return dao.getProducts();
	}

	public int delete(int id) {
		return dao.delete(id);
	}

	public Product getProduct(int id) {
		return dao.getProduct(id);
	}
	
	@Transactional
	public int insertContent(long price, String title, String image, String summary, String detail) {
		dao.addContent(price, title, image, summary, detail);
		return dao.getId();
	}

	public void updateContent(long price, String title, String image, String summary, String detail,int id) {
		dao.updateContent(price, title, image, summary, detail,id);
	}

	public int addTrx(int contentId, int personId, double price, long time) {
		return dao.insertTrx(contentId, personId, price, time);
	}

	public List<Product> getTrx() {
		return dao.getTrx();
	}

}
