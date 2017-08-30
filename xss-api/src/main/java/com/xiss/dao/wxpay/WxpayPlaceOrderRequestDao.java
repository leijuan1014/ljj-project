package com.xiss.dao.wxpay;
/** 
* @author leijj
* @since  2017年4月19日 上午10:23:35 
*/

import com.xiss.model.wxpay.WxpayPlaceOrderRequest;

public interface WxpayPlaceOrderRequestDao {
	
	public int insert(WxpayPlaceOrderRequest wxpayPlaceOrderRequest);
	
	public WxpayPlaceOrderRequest getById(int id);
}
