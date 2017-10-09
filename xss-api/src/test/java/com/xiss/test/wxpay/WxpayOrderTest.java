package com.xiss.test.wxpay;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.xiss.model.order.PaymentInfo;
import com.xiss.model.order.enums.PaymentGateway;
import com.xiss.model.wxpay.WxpayOrder;
import com.xiss.service.order.SuiteOrdersService;
import com.xiss.service.wxpay.WxPayOperations;
import com.xiss.test.SpringTestCase;

/** 
* @author leijj
* @since  2017年4月26日 下午12:13:20 
*/
//@ContextConfiguration(locations = {"classpath:application.xml"})
public class WxpayOrderTest{// extends SpringTestCase{

	@Autowired
	private WxPayOperations operations;
	@Autowired
	private SuiteOrdersService suiteOrdersService;
	
	//@Test
	public void orderPaidAndDeliverTest() {
		boolean status = suiteOrdersService.orderPaidAndDeliver(new PaymentInfo()
				.orderTradeNo("1495177326045")
				.mode(PaymentGateway.WECHATPAY)
				.time(new Date())
				.amount(0.01)
				.remark("test"));
		System.out.println(status);
	}
	//@Test
	public void insertTest() {
		WxpayOrder order = new WxpayOrder();
		order.setWxpayId("1460190602");
		order.setOpenId("111");
		order.setSubscribed(true);
		order.setOrderId("111");
		order.setDeviceInfo("111");
		order.setTradeType("SUCCESS");
		order.setTradeState("111");
		order.setTotalFee(new BigDecimal(1));
		order.setFeeType("CNY");
		order.setCashFee(new BigDecimal(1));
		order.setCashFeeType("CNY");
		order.setCouponFee(new BigDecimal(0));
		order.setAttach("11");
		order.setEndTime(new Date());
		order.setDescription("111");
		operations.overwriteWxpayOrder(order);
	}

}
