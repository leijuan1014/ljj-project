package com.xiss.dao.wxpay;

import com.xiss.model.wxpay.WxpayPlaceOrderResult;

/** 
* @author leijj
* @since  2017年7月27日 下午12:01:43 
*/
public interface WxpayPlaceOrderResultDao {

	public void insert(WxpayPlaceOrderResult result);
	
	public WxpayPlaceOrderResult getById(int id);
	
	public WxpayPlaceOrderResult getByOrderNumber(String orderNumber);
}
