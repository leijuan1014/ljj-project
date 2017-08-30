package com.bigdatan.b2c.controller.wxpay;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bigdatan.b2c.WxpayProperties;
import com.bigdatan.b2c.entity.JoinOrder;
import com.bigdatan.b2c.entity.Order;
import com.bigdatan.b2c.service.IOrderItemService;
import com.wxpay.bean.WxpayAccount;
import com.wxpay.bean.WxpayOrder;

/** 
* @author leijj
* @since  2017年4月18日 下午2:43:07 
*/
@Controller
@RequestMapping("/wxpay/notify")
public class WxpayBdShopNotifyAPI extends WxpayBasicNotify{
	@Autowired
	private WxpayProperties wxpayProperties;
	@Autowired
	private IOrderItemService orderItemService;
	@RequestMapping("/pay")
	private void memberJsapiNotify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("=======JS/wxpay/notify/pay支付回调");
		WxpayAccount account = wxpayProperties.getWxpayAccount(wxpayProperties);
		WxpayOrder order = notify(req, resp, account);
		if(order != null){
			afterNotify(order);
			sendResponse(resp, "SUCCESS", "成功");
		} else {
			sendResponse(resp, "FAIL", "微信订单不存在");
		}
	}
	private void afterNotify(WxpayOrder wxpayOrder) {
		System.out.println("=======before.wxpayOrder=" + wxpayOrder);
		try {
			String out = wxpayOrder.getOrderId();
            String paymentSeq = wxpayOrder.getWxpayId();
            // 支付交易成功
            Order order = new Order();
            JoinOrder joinOrder = orderItemService.getJoinOrderByNumber(out);
            String orderNumbers = null;
            if (null != joinOrder) {
                orderNumbers = joinOrder.getOrderNumbers();
                orderItemService.notifyAll(orderNumbers, paymentSeq);
            } else {
                order.setOrderNumber(out);
                order.setPaymentSeq(paymentSeq);
                order.setUpdateTime(new Date());
                // order.setState((byte)2);//更新订单 为支付成功
                order.setPayState((byte) 2);// 更新订单 为支付成功
                orderItemService.updateByPrimaryKeySelective(order);
            }
			//orderItemService.notifyAll(order.getOrderId(), order.getWxpayId());
			System.out.println("=======after.order=" + order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}