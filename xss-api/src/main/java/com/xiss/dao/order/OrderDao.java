package com.xiss.dao.order;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.order.Orders;

/** 
* @author leijj
* @since  2017年4月18日 下午4:32:03 
*/
public interface OrderDao {
	
	public int insert(Orders order);
	
	public Orders getById(long id);
	
	public Orders getByTradeNo(String tradeNo);
	
	public void orderPaid(@Param("tradeNo") String tradeNo, @Param("paymentGateway") int paymentGateway, @Param("totalAmount") double totalAmount, @Param("finishedAt") Date finishedAt);
}