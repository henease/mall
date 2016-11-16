package com.netease.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.netease.web.meta.Product;

public interface FirstDao {
	@Insert("insert into content(price,title,icon,abstract,text) values(#{0},#{1},#{2},#{3},#{4})")
	public void addContent(long price,String title,String image,String summary,String detail);
	
	@Update("update content set price=#{0},title=#{1},icon=#{2},abstract=#{3},text=#{4} where id=#{5}")
	public void updateContent(long price,String title,String image,String summary,String detail,int id);
	
	@Select("select max(id) from content")
	public int getId();
	
	@Select("select content.id,trx.time,content.title,content.icon,content.price from content "
			+ "left join trx on trx.contentId = content.id")
	@Results({
		@Result(property="id",column="content.id"),
		@Result(property="title",column="title"),
		@Result(property="image",column="icon"),
		@Result(property="price",column="price"),
		@Result(property="buyTime",column="time")
	})
	public List<Product> getProducts();
	
	@Select("select count(*) from person where userName=#{userName} and password=#{password} and userType=#{userType}")
	public int checkUser(@Param("userName")String userName,@Param("password")String password,@Param("userType")int userType);
	
	@Delete("delete from content where id = #{id}")
	public int delete(@Param("id")int id);
	
	@Select("select trx.price as buyPrice,content.id,content.title,content.abstract,content.text,content.icon,"
			+ "content.price as price from content left join trx on trx.contentId = content.id where "
			+ "content.id = #{id}")
	@Results({
		@Result(property="id",column="content.id"),
		@Result(property="price",column="content.price"),
		@Result(property="summary",column="abstract"),
		@Result(property="title",column="title"),
		@Result(property="image",column="icon"),
		@Result(property="detail",column="text"),
		@Result(property="buyPrice",column="trx.price")
	})
	public Product getProduct(int id);
	
	@Insert("insert into trx(contentId,personId,price,time) values(#{0},#{1},#{2},#{3})")
	public int insertTrx(int contentId,int personId,double price,long time);
	
	@Select("select content.id,content.title,content.icon,trx.price as buyPrice,trx.time as"
			+ " buyTime from trx left join content on trx.contentId = content.id")
	@Results({
		@Result(property="id",column="content.id"),
		@Result(property="title",column="title"),
		@Result(property="image",column="icon"),
		@Result(property="buyPrice",column="price"),
		@Result(property="buyTime",column="time")
	})
	public List<Product> getTrx();
	
}
