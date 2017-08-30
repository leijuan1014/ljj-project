package com.alipay.server;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.alipay.bean.AlipayFundBill;
import com.alipay.bean.AlipayQueryTradeResult;
import com.alipay.bean.AlipayRefundTradeRequest;
import com.alipay.bean.AlipayRefundTradeResult;
import com.alipay.conf.AlipayConfig;
import com.fasterxml.jackson.core.JsonGenerator;

public class AlipayRefundTrade extends AlipayStep {
	private AlipayAccount account;
	private AlipayRefundTradeRequest request;
	
	public AlipayAccount getAccount() {
		return account;
	}

	public void setAccount(AlipayAccount account) {
		this.account = account;
	}

	public AlipayRefundTradeRequest getRequest() {
		return request;
	}

	public void setRequest(AlipayRefundTradeRequest request) {
		this.request = request;
	}
	public AlipayRefundTradeResult run() {
		// SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签     
		 AlipayClient client = new DefaultAlipayClient(AlipayConfig.PAY_URL, account.getAppId(), account.getPrivateKey(), AlipayConfig.FORMAT, AlipayConfig.CHARSET, account.getPublicKey(),AlipayConfig.SIGNTYPE);
		 AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();
			
		AlipayTradeRefundModel model=new AlipayTradeRefundModel();
		model.setOutTradeNo(request.getOrderId());
		model.setTradeNo(request.getAlipayId());
		model.setRefundAmount(request.getRefundAmount());
		model.setRefundReason(request.getRefundReason());
		model.setOutRequestNo(request.getOutRequestNo());
		alipay_request.setBizModel(model);
		
		try {
			AlipayTradeRefundResponse resp = client.execute(alipay_request);
			AlipayRefundTradeResult result = new AlipayRefundTradeResult();
			if (resp.getTradeNo() != null && !resp.getTradeNo().isEmpty())
				result.setAlipayId(resp.getTradeNo());
			if (resp.getOutTradeNo() != null && !resp.getOutTradeNo().isEmpty())
				result.setOrderId(resp.getOutTradeNo());
			if (resp.getBuyerLogonId() != null && !resp.getBuyerLogonId().isEmpty())
				result.setBuyerLogonId(resp.getBuyerLogonId());
			if (resp.getBuyerUserId() != null && !resp.getBuyerUserId().isEmpty())
				result.setBuyerUserId(resp.getBuyerUserId());
			if (resp.getFundChange() != null && !resp.getFundChange().isEmpty())
				result.setFundChange(resp.getFundChange());
			if (resp.getGmtRefundPay() != null)
				result.setGmtRefundPay(resp.getGmtRefundPay());
			if (resp.getRefundDetailItemList() != null && !resp.getRefundDetailItemList().isEmpty())
				result.setRefundDetailItemList(resp.getRefundDetailItemList());
			if (resp.getRefundFee() != null && !resp.getRefundFee().isEmpty())
				result.setRefundFee(resp.getRefundFee());
			if (resp.getSendBackFee() != null && !resp.getSendBackFee().isEmpty())
				result.setSendBackFee(resp.getSendBackFee());
			return result;
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		return null;
	}
	public void run1() {
		AlipayQueryTradeResult qr = null;
		try {
			AlipayTradeQueryRequest req = new AlipayTradeQueryRequest();
			req.setBizContent(getBizContent());
			AlipayTradeQueryResponse resp = new AlipayTradeQueryResponse();//client.execute(req);
			if (resp != null) {
				qr = new AlipayQueryTradeResult();
				if (resp.getTradeNo() != null && !resp.getTradeNo().isEmpty())
					qr.setAlipayId(resp.getTradeNo());
				if (resp.getOutTradeNo() != null && !resp.getOutTradeNo().isEmpty())
					qr.setOrderId(resp.getOutTradeNo());
				if (resp.getBuyerLogonId() != null && !resp.getBuyerLogonId().isEmpty())
					qr.setBuyerLogonId(resp.getBuyerLogonId());
				if (resp.getBuyerUserId() != null && !resp.getBuyerUserId().isEmpty())
					qr.setBuyerUserId(resp.getBuyerUserId());
				if (resp.getTradeStatus() != null && !resp.getTradeStatus().isEmpty())
					qr.setStatus(resp.getTradeStatus());
				if (resp.getTotalAmount() != null && !resp.getTotalAmount().isEmpty())
					qr.setTotalAmount(new BigDecimal(resp.getTotalAmount()));
				if (resp.getReceiptAmount() != null && !resp.getReceiptAmount().isEmpty())
					qr.setReceiptAmount(new BigDecimal(resp.getReceiptAmount()));
				if (resp.getBuyerPayAmount() != null && !resp.getBuyerPayAmount().isEmpty())
					qr.setBuyerPayAmount(new BigDecimal(resp.getBuyerPayAmount()));
				if (resp.getPointAmount() != null && !resp.getPointAmount().isEmpty())
					qr.setPointAmount(new BigDecimal(resp.getPointAmount()));
				if (resp.getInvoiceAmount() != null && !resp.getInvoiceAmount().isEmpty())
					qr.setInvoiceAmount(new BigDecimal(resp.getInvoiceAmount()));
				if (resp.getSendPayDate() != null)
					qr.setPayTime(resp.getSendPayDate());
				if (resp.getAlipayStoreId() != null && !resp.getAlipayStoreId().isEmpty())
					qr.setAlipayStoreId(resp.getAlipayStoreId());
				if (resp.getStoreId() != null && !resp.getStoreId().isEmpty())
					qr.setStoreId(resp.getStoreId());
				if (resp.getTerminalId() != null && !resp.getTerminalId().isEmpty())
					qr.setTerminalId(resp.getTerminalId());
				if (resp.getFundBillList() != null) {
					List<AlipayFundBill> bills = new ArrayList<AlipayFundBill>();
					for (TradeFundBill b : resp.getFundBillList()) {
						AlipayFundBill bill = new AlipayFundBill();
						if (b.getFundChannel() != null && !b.getFundChannel().isEmpty())
							bill.setChannel(b.getFundChannel());
						if (b.getAmount() != null && !b.getAmount().isEmpty())
							bill.setAmount(new BigDecimal(b.getAmount()));
						if (b.getRealAmount() != null && !b.getRealAmount().isEmpty())
							bill.setRealAmount(new BigDecimal(b.getRealAmount()));
						bills.add(bill);
					}
					if (!bills.isEmpty())
						qr.setBills(bills);
				}
				if (resp.getStoreName() != null && !resp.getStoreName().isEmpty())
					qr.setStoreName(resp.getStoreName());
				if (resp.getDiscountGoodsDetail() != null && !resp.getDiscountGoodsDetail().isEmpty())
					qr.setDiscountGoodsDetail(resp.getDiscountGoodsDetail());
				if (resp.getIndustrySepcDetail() != null && !resp.getIndustrySepcDetail().isEmpty())
					qr.setIndustrySpecDetail(resp.getIndustrySepcDetail());
			}
		} catch (Exception e) {
			return;
		}
	}

	@Override
	protected void encodeBizContent(JsonGenerator generator) throws IOException {
		generator.writeStartObject();
		if (request.getOrderId() != null)
			generator.writeStringField("out_trade_no", request.getOrderId());
		if (request.getAlipayId() != null)
			generator.writeStringField("trade_no", request.getAlipayId());
		generator.writeEndObject();
	}

}
