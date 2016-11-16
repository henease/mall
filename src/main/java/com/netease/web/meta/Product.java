package com.netease.web.meta;


public class Product {
	private Integer id;
	private String title;
	private String summary;
	private String detail;
	private String image;
	private Double price;
	private String isBuy;
	private String isSell;
	private Long buyTime;
	private Double buyPrice;
	
	public Long getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Long buyTime) {
		this.buyTime = buyTime;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(String isBuy) {
		this.isBuy = isBuy;
	}
	public String getIsSell() {
		return isSell;
	}
	public void setIsSell(String isSell) {
		this.isSell = isSell;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
}
