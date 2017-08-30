package com.alipay.server.service.impl;

import org.springframework.stereotype.Service;

import com.alipay.bean.AlipayAppPayRequest;
import com.alipay.bean.AlipayDirectPayRequest;
import com.alipay.bean.AlipayOrder;
import com.alipay.bean.AlipayQueryTradeRequest;
import com.alipay.bean.AlipayQueryTradeResult;
import com.alipay.bean.AlipayRefundTradeRequest;
import com.alipay.bean.AlipayRefundTradeResult;
import com.alipay.bean.AlipayWapPayRequest;
import com.alipay.server.AlipayAccount;
import com.alipay.server.AlipayDirectPay;
import com.alipay.server.AlipayPrepareForApp;
import com.alipay.server.AlipayPrepareForPage;
import com.alipay.server.AlipayPrepareForWap;
import com.alipay.server.AlipayQueryTrade;
import com.alipay.server.AlipayRefundTrade;
import com.alipay.server.service.AlipayOperations;


/**
 * @author leijj
 */
@Service
public class AlipayOperationsImpl implements AlipayOperations{

    //private static final Logger LOG = LoggerFactory.getLogger("AlipayOperationsCImpl");


	public void prepareDirectPay(AlipayDirectPayRequest request, AlipayAccount account, String notifyUrl) {
		AlipayDirectPay action = new AlipayDirectPay();
		action.setRequest(request);
		action.setAccount(account);
		action.setNotifyUrl(notifyUrl);
		action.run();
	}

    public String preparePayForWap(AlipayWapPayRequest request, AlipayAccount account, String notifyUrl) {
		AlipayPrepareForWap action = new AlipayPrepareForWap();
		action.setRequest(request);
		action.setAccount(account);
		action.setNotifyUrl(notifyUrl);
		return action.run();
    }
    
    public String preparePayForPage(AlipayWapPayRequest request, AlipayAccount account, String notifyUrl) {
    		AlipayPrepareForPage action = new AlipayPrepareForPage();
		action.setRequest(request);
		action.setAccount(account);
		action.setNotifyUrl(notifyUrl);
		return action.run();
    }

	public String preparePayForApp(AlipayAppPayRequest request, AlipayAccount account, String notifyUrl) {
		AlipayPrepareForApp action = new AlipayPrepareForApp();
		action.setRequest(request);
		action.setAccount(account);
		action.setNotifyUrl(notifyUrl);
		return action.run();
	}
	
	public AlipayQueryTradeResult queryTrade(AlipayQueryTradeRequest request, AlipayAccount account) {
		AlipayQueryTrade action = new AlipayQueryTrade();
		action.setAccount(account);
		action.setRequest(request);
		return action.run();
	}
	public AlipayRefundTradeResult refundTrade(AlipayRefundTradeRequest request, AlipayAccount account) {
		AlipayRefundTrade action = new AlipayRefundTrade();
		action.setAccount(account);
		action.setRequest(request);
		return action.run();
	}
	
	public void queryTradeAndProcess(AlipayQueryTradeRequest request, AlipayAccount account, String notifyUrl) {

		// 后续处理
		AlipayQueryTradeResult qr = null;// (AlipayQueryTradeResult) result;
		if (qr != null &&
				("TRADE_SUCCESS".equalsIgnoreCase(qr.getStatus()) ||
						"TRADE_FINISHED".equalsIgnoreCase(qr.getStatus()))) {
			// 支付成功
			// 修改订单状态并发货
			StringBuilder builder = new StringBuilder("支付宝用户号：");
			if (qr.getBuyerUserId() != null)
				builder.append(qr.getBuyerUserId());
			builder.append("，账号：");
			if (qr.getBuyerLogonId() != null)
				builder.append(qr.getBuyerLogonId());
			builder.append("，交易号：");
			if (qr.getAlipayId() != null)
				builder.append(qr.getAlipayId());
			/*
			orderManager.f().c().orderPaidAndDeliver(new Ignore(),
					new PaymentInfo()
					.orderId(Long.parseLong(qr.getOrderId()))
					.mode(PayMode.ALIPAY)
					.time(qr.getPayTime())
					.amount(qr.getTotalAmount())
					.remark(builder.toString()));
			*/
			// 保存支付单
			AlipayOrder order = qr.toAlipayOrder();
			order.setAppId(account.getAppId());
			//overwriteAlipayOrder(new Ignore(), order);
		}
		
	}


}
