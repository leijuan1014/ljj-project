package com.xiss.service.wxpay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.wxpay.WxpayPlaceOrderResultDao;
import com.xiss.model.wxpay.WxpayPlaceOrderResult;

/** 
* @author leijj
* @since  2017年4月19日 上午10:23:02 
*/
@Service
public class WxpayPlaceOrderResultService {
	@Autowired
	private WxpayPlaceOrderResultDao wxpayPlaceOrderResultDao;
	
	public void insert(WxpayPlaceOrderResult result){
		wxpayPlaceOrderResultDao.insert(result);
	}
	
	public WxpayPlaceOrderResult getById(int id){
		return wxpayPlaceOrderResultDao.getById(id);
	}
	
	public WxpayPlaceOrderResult getByOrderNumber(String orderNumber){
		return wxpayPlaceOrderResultDao.getByOrderNumber(orderNumber);
	}
}
