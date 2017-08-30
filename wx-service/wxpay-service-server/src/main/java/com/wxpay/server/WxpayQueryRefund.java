package com.wxpay.server;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import com.wxpay.bean.WxpayAccount;
import com.wxpay.bean.WxpayCoupon;
import com.wxpay.bean.WxpayQueryRefundRequest;
import com.wxpay.bean.WxpayQueryRefundResult;
import com.wxpay.bean.WxpayRefundRecord;
import com.wxpay.bean.WxpaySignature;

public class WxpayQueryRefund extends WxpayStep {
	
	private WxpayQueryRefundRequest request;
	private WxpayAccount account;

	public WxpayQueryRefundRequest getRequest() {
		return request;
	}

	public void setRequest(WxpayQueryRefundRequest request) {
		this.request = request;
	}

	public WxpayAccount getAccount() {
		return account;
	}

	public void setAccount(WxpayAccount account) {
		this.account = account;
	}

	public WxpayQueryRefundResult run() {
		InputStream resultIS = postRun("https://api.mch.weixin.qq.com/pay/refundquery");
		WxpayQueryRefundResult result = (WxpayQueryRefundResult) decodeResponse(resultIS);
		System.out.println("======WxpayQueryRefundResult=" + result);
		return result;
	}

	@Override
	protected void encode(XMLStreamWriter writer) throws Exception {
		WxpaySignature signature = new WxpaySignature();
		
		if (account.getAppId() != null) {
			writeElement(writer, "appid", account.getAppId());
			signature.param("appid", account.getAppId());
		}
		
		if (account.getMerchantId() != null) {
			writeElement(writer, "mch_id", account.getMerchantId());
			signature.param("mch_id", account.getMerchantId());
		}
		
		String deviceInfo = request.getDeviceInfo();
		if (deviceInfo != null && !deviceInfo.isEmpty()) {
			writeElement(writer, "device_info", deviceInfo);
			signature.param("device_info", deviceInfo);
		}

		String nonce = WxpaySignature.genNonce();
		writeElement(writer, "nonce_str", nonce);
		signature.param("nonce_str", nonce);

		String wxpayId = request.getWxpayId();
		if (wxpayId != null && !wxpayId.isEmpty()) {
			writeElement(writer, "transaction_id", wxpayId);
			signature.param("transaction_id", wxpayId);
		}
		
		String orderId = request.getOrderId();
		if (orderId != null && !orderId.isEmpty()) {
			writeElement(writer, "out_trade_no", orderId);
			signature.param("out_trade_no", orderId);
		}
		
		String refundId = request.getRefundId();
		if (refundId != null && !refundId.isEmpty()) {
			writeElement(writer, "out_refund_no", refundId);
			signature.param("out_refund_no", refundId);
		}
		
		String wxpayRefundId = request.getWxpayRefundId();
		if (wxpayRefundId != null && !wxpayRefundId.isEmpty()) {
			writeElement(writer, "refund_id", wxpayRefundId);
			signature.param("refund_id", wxpayRefundId);
		}
		
		// 计算签名
		String sig = signature.sign(account.getKey());
		writeElement(writer, "sign", sig);
		
		// 不要忘了写元素的结束tag
		writer.writeEndElement();
	}

	@Override
	protected Object decode(XMLStreamReader reader) throws Exception {
		if (reader.getEventType() != XMLStreamConstants.START_ELEMENT)
			throw new XMLStreamException("Unexpected event type " + reader.getEventType(), reader.getLocation());
		
		WxpaySignature signature = new WxpaySignature();
		String appId = null, merchantId = null, sign = null;
		WxpayQueryRefundResult qr = new WxpayQueryRefundResult();
		Map<Integer, WxpayRefundRecord> refunds = new TreeMap<Integer, WxpayRefundRecord>();
		Map<Integer, Map<Integer, WxpayCoupon>> refundCoupons = new TreeMap<Integer, Map<Integer, WxpayCoupon>>();
		while (reader.nextTag() == XMLStreamConstants.START_ELEMENT) {
			String name = reader.getLocalName();
			String value = reader.getElementText().trim();
			if (!"sign".equals(name))
				// 除sign外的所有字段都要参与签名的计算
				signature.param(name, value);
			
			if ("appid".equals(name))
				appId = value;
			else if ("mch_id".equals(name))
				merchantId = value;
			else if ("sign".equals(name))
				sign = value;
			else if ("device_info".equals(name))
				qr.setDeviceInfo(value);
			else if ("transaction_id".equals(name))
				qr.setWxpayId(value);
			else if ("out_trade_no".equals(name)) 
				qr.setOrderId(value);
			else if ("total_fee".equals(name)) 
				qr.setTotalFee(BigDecimal.valueOf(Long.parseLong(value)).divide(HUNDRED));
			else if ("fee_type".equals(name))
				qr.setFeeType(value);
			else if ("cash_fee".equals(name))
				qr.setCashFee(BigDecimal.valueOf(Long.parseLong(value)).divide(HUNDRED));
			else if ("refund_account".equals(name))
				qr.setRefundAccount(value);
			else if (name.startsWith("out_refund_no_")) {
				int index = Integer.parseInt(name.substring("out_refund_no_".length()));
				WxpayRefundRecord refund = refunds.get(index);
				if (refund == null) {
					refund = new WxpayRefundRecord();
					refunds.put(index, refund);
				}
				refund.setRefundId(value);
			}
			else if (name.startsWith("refund_id_")) {
				int index = Integer.parseInt(name.substring("refund_id_".length()));
				WxpayRefundRecord refund = refunds.get(index);
				if (refund == null) {
					refund = new WxpayRefundRecord();
					refunds.put(index, refund);
				}
				refund.setWxpayRefundId(value);
			}
			else if (name.startsWith("refund_channel_")) {
				int index = Integer.parseInt(name.substring("refund_channel_".length()));
				WxpayRefundRecord refund = refunds.get(index);
				if (refund == null) {
					refund = new WxpayRefundRecord();
					refunds.put(index, refund);
				}
				refund.setRefundChannel(value);
			}
			else if (name.startsWith("refund_fee_")) {
				int index = Integer.parseInt(name.substring("refund_fee_".length()));
				WxpayRefundRecord refund = refunds.get(index);
				if (refund == null) {
					refund = new WxpayRefundRecord();
					refunds.put(index, refund);
				}
				refund.setRefundFee(BigDecimal.valueOf(Long.parseLong(value)).divide(HUNDRED));
			}
			else if (name.startsWith("coupon_refund_fee_")) {
				int index = Integer.parseInt(name.substring("coupon_refund_fee_".length()));
				WxpayRefundRecord refund = refunds.get(index);
				if (refund == null) {
					refund = new WxpayRefundRecord();
					refunds.put(index, refund);
				}
				refund.setCouponRefundFee(BigDecimal.valueOf(Long.parseLong(value)).divide(HUNDRED));
			}
			else if (name.startsWith("coupon_refund_batch_id_")) {
				String[] indices = name.substring("coupon_refund_batch_id_".length()).split("_");
				if (indices.length >= 2) {
					int index0 = Integer.parseInt(indices[0]);
					int index1 = Integer.parseInt(indices[1]);
					Map<Integer, WxpayCoupon> coupons = refundCoupons.get(index0);
					if (coupons == null) {
						coupons = new TreeMap<Integer, WxpayCoupon>();
						refundCoupons.put(index0, coupons);
					}
					WxpayCoupon coupon = coupons.get(index1);
					if (coupon == null) {
						coupon = new WxpayCoupon();
						coupons.put(index1, coupon);
					}
					coupon.setBatchId(value);
				}
			}
			else if (name.startsWith("coupon_refund_id_")) {
				String[] indices = name.substring("coupon_refund_id_".length()).split("_");
				if (indices.length >= 2) {
					int index0 = Integer.parseInt(indices[0]);
					int index1 = Integer.parseInt(indices[1]);
					Map<Integer, WxpayCoupon> coupons = refundCoupons.get(index0);
					if (coupons == null) {
						coupons = new TreeMap<Integer, WxpayCoupon>();
						refundCoupons.put(index0, coupons);
					}
					WxpayCoupon coupon = coupons.get(index1);
					if (coupon == null) {
						coupon = new WxpayCoupon();
						coupons.put(index1, coupon);
					}
					coupon.setId(value);
				}
			}
			else if (name.startsWith("coupon_refund_fee_")) {
				String[] indices = name.substring("coupon_refund_fee_".length()).split("_");
				if (indices.length >= 2) {
					int index0 = Integer.parseInt(indices[0]);
					int index1 = Integer.parseInt(indices[1]);
					Map<Integer, WxpayCoupon> coupons = refundCoupons.get(index0);
					if (coupons == null) {
						coupons = new TreeMap<Integer, WxpayCoupon>();
						refundCoupons.put(index0, coupons);
					}
					WxpayCoupon coupon = coupons.get(index1);
					if (coupon == null) {
						coupon = new WxpayCoupon();
						coupons.put(index1, coupon);
					}
					coupon.setFee(BigDecimal.valueOf(Long.parseLong(value)).divide(HUNDRED));
				}
			}
			else if (name.startsWith("refund_status_")) {
				int index = Integer.parseInt(name.substring("refund_status_".length()));
				WxpayRefundRecord refund = refunds.get(index);
				if (refund == null) {
					refund = new WxpayRefundRecord();
					refunds.put(index, refund);
				}
				refund.setRefundStatus(value);
			}
			else if (name.startsWith("refund_recv_account_")) {
				int index = Integer.parseInt(name.substring("refund_recv_account_".length()));
				WxpayRefundRecord refund = refunds.get(index);
				if (refund == null) {
					refund = new WxpayRefundRecord();
					refunds.put(index, refund);
				}
				refund.setRefundReceiveAccount(value);
			}

			// 忽略其它元素
		}
		for (Map.Entry<Integer, Map<Integer, WxpayCoupon>> entry : refundCoupons.entrySet()) {
			WxpayRefundRecord refund = refunds.get(entry.getKey());
			Map<Integer, WxpayCoupon> coupons = entry.getValue();
			if (refund != null && !coupons.isEmpty())
				refund.setCoupons(new ArrayList<WxpayCoupon>(coupons.values()));
		}
		if (!refunds.isEmpty())
			qr.setRefunds(new ArrayList<WxpayRefundRecord>(refunds.values()));
		
		// 检查签名
		String sig = signature.sign(account.getKey());
		if (!account.getAppId().equals(appId) ||
				!account.getMerchantId().equals(merchantId) ||
				!sig.equals(sign))
				qr = null;

		return qr;
	}

}
