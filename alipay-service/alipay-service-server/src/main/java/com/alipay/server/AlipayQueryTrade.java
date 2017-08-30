package com.alipay.server;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.TradeFundBill;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.bean.AlipayFundBill;
import com.alipay.bean.AlipayQueryTradeRequest;
import com.alipay.bean.AlipayQueryTradeResult;
import com.alipay.conf.AlipayConfig;
import com.fasterxml.jackson.core.JsonGenerator;

public class AlipayQueryTrade extends AlipayStep {
	private AlipayAccount account;
	private AlipayQueryTradeRequest request;
	
	public AlipayAccount getAccount() {
		return account;
	}

	public void setAccount(AlipayAccount account) {
		this.account = account;
	}

	public AlipayQueryTradeRequest getRequest() {
		return request;
	}

	public void setRequest(AlipayQueryTradeRequest request) {
		this.request = request;
	}
	public AlipayQueryTradeResult run() {
		AlipayClient client = new DefaultAlipayClient(AlipayConfig.PAY_URL, account.getAppId(), account.getPrivateKey(), AlipayConfig.FORMAT, AlipayConfig.CHARSET, account.getPublicKey(),AlipayConfig.SIGNTYPE);
		AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
		 
		AlipayTradeQueryModel model=new AlipayTradeQueryModel();
	    model.setOutTradeNo(request.getOrderId());
	    alipayRequest.setBizModel(model);
	     
		AlipayQueryTradeResult qr = null;
		try {
			AlipayTradeQueryResponse resp = client.execute(alipayRequest);
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
			return qr;
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
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
