package com.netease.web.utils;

import java.math.BigDecimal;

public class PriceTrans {
    //为了消除浮点数截断的误差，将价格从数据库取出时进行转换	
	public static double transPriceToDouble(double price){
		return new BigDecimal(Double.toString(price))
				.divide(new BigDecimal(Integer.toString(100))).doubleValue();
	}
	//为了消除浮点数截断的误差，将价格存入数据库时进行转换
	public static long transPriceToLong(String price){
		return new BigDecimal(price)
				.multiply(new BigDecimal(Integer.toString(100))).longValue();
	}
}
