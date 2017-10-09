package com.xiss.service.wxpay;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.wxpay.WxpayOrderDao;
import com.xiss.dao.wxpay.WxpayPlaceOrderRequestDao;
import com.xiss.model.wxpay.WxpayOrder;
import com.xiss.model.wxpay.WxpayPlaceOrderRequest;
import com.xiss.model.wxpay.WxpayPlaceOrderResult;
import com.xiss.model.wxpay.WxpayQueryOrderRequest;
import com.xiss.model.wxpay.WxpayQueryOrderResult;
import com.xiss.model.wxpay.WxpayQueryRefundRequest;
import com.xiss.model.wxpay.WxpayQueryRefundResult;
import com.xiss.model.wxpay.WxpayRefundRequest;


/** 
* @author leijj
* @since  2017年4月18日 下午2:18:47 
*/
@Service
public class WxPayOperations {
	@Autowired
	private WxpayPlaceOrderRequestDao wxpayPlaceOrderRequestDao;
	
	@Autowired
	private WxpayOrderDao wxpayOrderDao;
	
	/**
	 * 使用默认微信支付商户账号下单，成功后返回预支付交易会话标识。
	 * 
	 * @param request 微信支付下单请求
	 * @return 预支付交易会话标识
	 */
	WxpayPlaceOrderResult placeOrder(WxpayPlaceOrderRequest request){
		return null;
	}
	WxpayPlaceOrderResult getPlaceOrder(String orderId){
		return null;
	}
	/**
	 * 使用默认微信支付商户账号下单，成功后返回供App调起微信支付用的参数。
	 * 
	 * @param request 微信支付下单请求
	 * @return App调起微信支付用的参数
	 */
	Map<String, String> preparePayForApp(WxpayPlaceOrderRequest request){
		return null;
	}
	
	/**
	 * 使用默认微信支付商户账号查询支付订单状态。
	 * 
	 * @param request 微信支付订单查询请求
	 * @return 微信支付订单详情
	 */
	WxpayQueryOrderResult queryOrder(WxpayQueryOrderRequest request){
		return null;
	}
	
	/**
	 * 使用默认微信支付商户账号查询商户订单的支付状态。如果支付已成功，更新商户订单状态为已支付并进行后续处理。
	 * 
	 * @param request 微信支付订单查询请求
	 * @return 微信支付订单详情
	 */
	WxpayQueryOrderResult queryOrderAndProcess(WxpayQueryOrderRequest request){
		return null;
	}
	
	/**
	 * 使用默认微信支付商户账号关闭关联的支付订单。
	 * 
	 * @param orderId 商户订单号，非微信支付订单号
	 * @return 是否成功关闭支付订单
	 */
	boolean closeOrder(String orderId){
		return false;
	}
	
	/**
	 * 使用默认微信支付商户账号申请退款，成功后返回微信支付的退款单号。
	 * 
	 * @param request 微信支付退款申请
	 * @return 微信支付退款单号
	 */
	String askForRefund(WxpayRefundRequest request){
		return null;
	}
	
	/**
	 * 使用默认微信支付商户账号查询退款记录。
	 * 
	 * @param request 微信支付退款记录查询请求
	 * @return 微信支付退款记录
	 */
	WxpayQueryRefundResult queryRefund(WxpayQueryRefundRequest request){
		return null;
	}
	
	/**
	 * 获取本地保存的指定微信支付订单。
	 * 
	 * @param wxpayId 微信支付订单号
	 * @return 本地保存的微信支付订单
	 */
	WxpayOrder getWxpayOrderByWxpayId(String wxpayId){
		return null;
	}
	
	/**
	 * 获取本地保存的指定微信支付订单。
	 * 
	 * @param orderId 商户订单号
	 * @return 本地保存的微信支付订单
	 */
	WxpayOrder getWxpayOrderByOrderId(String orderId){
		return null;
	}
	/**
	 * 保存微信支付订单，覆盖其原内容。
	 * 
	 * @param order 微信支付订单
	 */
	public void overwriteWxpayOrder(WxpayOrder order){
		wxpayOrderDao.overwriteWxpayOrder(order);
	}
}
