package com.xiss.dao.wxpay;

import com.xiss.model.wxpay.WxpayOrder;

/** 
* @author leijj
* @since  2017年4月26日 上午11:34:45 
*/
public interface WxpayOrderDao {
	public WxpayOrder getById(int id);
	
	public void overwriteWxpayOrder(WxpayOrder order);
}
