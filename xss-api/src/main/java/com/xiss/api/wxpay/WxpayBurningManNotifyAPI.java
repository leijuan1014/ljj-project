package com.xiss.api.wxpay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiss.api.system.JavaSmsApi;
import com.xiss.model.order.SuiteOrders;
import com.xiss.model.system.Users;
import com.xiss.model.wxpay.WxpayAccount;
import com.xiss.model.wxpay.WxpayOrder;
import com.xiss.service.order.SuiteOrdersService;
import com.xiss.service.system.UsersService;
import com.xiss.util.properties.WxPayProperties;
@Controller
@RequestMapping("/api/wxpay")
public class WxpayBurningManNotifyAPI extends WxpayBasicNotify{
	@Autowired
	private UsersService usersService;
	@Autowired
	private SuiteOrdersService suiteOrdersService;
	
	@RequestMapping("/burningManJsapiNotify")
	private void burningManJsapiNotify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WxpayAccount account = WxPayProperties.getJsapiWxpayAccount();
		WxpayOrder order = notify(req, resp, account);
		if(order != null){
			 burningManAfter(order);
			sendResponse(resp, "SUCCESS", "成功");
		} else {
			sendResponse(resp, "FAIL", "微信订单不存在");
		}
	}
	private void burningManAfter(WxpayOrder order) {
		String tradeNo = order.getOrderId();
		System.out.println("===========tradeNo=" + tradeNo);
		SuiteOrders suiteOrders = suiteOrdersService.getSuiteOrderByTradeNo(tradeNo);
		Users users = usersService.getById(suiteOrders.getUserId());
		//发送激活码信息
		JavaSmsApi sms = new JavaSmsApi();
		try {
			sms.sendBurningManMsg(users.getMobile(), order.getAttach(), tradeNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
