package com.base.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxpay.Application;
import com.wxpay.bean.WxpayAccount;
import com.wxpay.bean.WxpayPlaceOrderRequest;
import com.wxpay.server.service.WxpayOperations;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class WxpayTest {

	@Autowired
	private WxpayOperations operations;
	@Test
	public void testGet() {
		System.out.println(operations);
	}
	public static WxpayAccount getJSAPIWxpayAccount() {
		WxpayAccount wxpayAccount = new WxpayAccount();
		wxpayAccount.setAppId("wx46b543acf6dbc7fa");
		wxpayAccount.setAppSecret( "e7d006686de01c677fd218a7b53e5795");
		wxpayAccount.setMerchantId("1415297202");
		wxpayAccount.setKey("1DC11A9531C172C7082663EE1F1B1309");
		return wxpayAccount;
	}
	public static WxpayAccount getAppWxpayAccount() {
		WxpayAccount wxpayAccount = new WxpayAccount();
		wxpayAccount.setAppId("wx7207aaafb7497ca7");
		wxpayAccount.setAppSecret( "");
		wxpayAccount.setMerchantId("1460190602");
		wxpayAccount.setKey("1DC11A9531C172C7082663EE1F1B1309");
		return wxpayAccount;
	}
	//@Test
	public void testPlaceOrder() {
		WxpayPlaceOrderRequest request = new WxpayPlaceOrderRequest();
		request.setDeviceInfo("APP");
		request.setBody("测试");
		request.setAttach("测试");
		request.setOrderTradeNo("201708250001");
		request.setFeeType("CNY");
		request.setTotalFee(1);
		//placeOrderRequest.setClientIp("");
		request.setStartTime(new Date());
		request.setTradeType("APP");
		
		request.setLimitPay("no_credit");
		operations.placeOrder(getAppWxpayAccount(), "https://api.autoxss.com/xisstest/api/wxpayMember/memberJsapiNotify", request);
	}

}
