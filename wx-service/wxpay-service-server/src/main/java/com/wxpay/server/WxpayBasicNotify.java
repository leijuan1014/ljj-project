package com.wxpay.server;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.springframework.beans.factory.annotation.Autowired;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.wxpay.bean.BankType;
import com.wxpay.bean.WxpayAccount;
import com.wxpay.bean.WxpayCoupon;
import com.wxpay.bean.WxpayOrder;
import com.wxpay.bean.WxpayQueryOrderResult;
import com.wxpay.bean.WxpaySignature;
import com.wxpay.server.service.WxpayOperations;

/** 
* @author leijj
* @since  2017年4月18日 下午2:43:07 
*/
public class WxpayBasicNotify{
	protected static final XMLOutputFactory XOF = new WstxOutputFactory();
	protected static final XMLInputFactory XIF = new WstxInputFactory();
	@Autowired
	private WxpayOperations wxpayOperations;
	public WxpayOrder notify(HttpServletRequest req, HttpServletResponse resp, WxpayAccount account) throws ServletException, IOException {
		String returnCode = null, returnMsg = null;

		// 解析通知内容
		String resultCode = null;
		WxpayQueryOrderResult qr = new WxpayQueryOrderResult();
		WxpaySignature signature = new WxpaySignature();
		String appId = null, merchantId = null, sign = null;
		try {
			XMLStreamReader reader = null;
			try {
				reader = XIF.createXMLStreamReader(req.getInputStream());
				// 游标移到XML文档的根元素
				reader.nextTag();
				
				System.out.println("========reader.getEventType()=" + reader.getEventType());
				if (reader.getEventType() != XMLStreamConstants.START_ELEMENT)
					throw new XMLStreamException("Unexpected event type " + reader.getEventType(), reader.getLocation());

				Map<Integer, WxpayCoupon> coupons = new TreeMap<Integer, WxpayCoupon>();
				while (reader.nextTag() == XMLStreamConstants.START_ELEMENT) {
					String name = reader.getLocalName();
					String value = reader.getElementText().trim();
					System.out.println("=========name=" + name + ",value=" + value);
					if (!"sign".equals(name))
						// 除sign外的所有字段都要参与签名的计算
						signature.param(name, value);
					
					if ("appid".equals(name))
						appId = value;
					else if ("mch_id".equals(name))
						merchantId = value;
					else if ("sign".equals(name))
						sign = value;
					else if ("result_code".equals(name))
						resultCode = value;
					else if ("device_info".equals(name))
						qr.setDeviceInfo(value);
					else if ("openid".equals(name))
						qr.setOpenId(value);
					else if ("is_subscribe".equals(name)) {
						if ("Y".equalsIgnoreCase(value))
							qr.setSubscribed(true);
					}
					else if ("trade_type".equals(name)) 
						qr.setTradeType(value);
					else if ("trade_state".equals(name))
						qr.setTradeState(value);
					else if ("bank_type".equals(name))
						qr.setBankType(value);
					else if ("total_fee".equals(name)) 
						qr.setTotalFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					else if ("fee_type".equals(name)) 
						qr.setFeeType(value);
					else if ("cash_fee".equals(name)) 
						qr.setCashFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					else if ("cash_fee_type".equals(name)) 
						qr.setCashFeeType(value);
					else if ("coupon_fee".equals(name))
						qr.setCouponFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					else if (name.startsWith("coupon_batch_id_")) {
						int index = Integer.parseInt(name.substring("coupon_batch_id_".length()));
						WxpayCoupon coupon = coupons.get(index);
						if (coupon == null) {
							coupon = new WxpayCoupon();
							coupons.put(index, coupon);
						}
						coupon.setBatchId(value);
					}
					else if (name.startsWith("coupon_id_")) {
						int index = Integer.parseInt(name.substring("coupon_id_".length()));
						WxpayCoupon coupon = coupons.get(index);
						if (coupon == null) {
							coupon = new WxpayCoupon();
							coupons.put(index, coupon);
						}
						coupon.setId(value);
					}
					else if (name.startsWith("coupon_fee_")) {
						int index = Integer.parseInt(name.substring("coupon_fee_".length()));
						WxpayCoupon coupon = coupons.get(index);
						if (coupon == null) {
							coupon = new WxpayCoupon();
							coupons.put(index, coupon);
						}
						coupon.setFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					}
					else if ("transaction_id".equals(name)) 
						qr.setWxpayId(value);
					else if ("out_trade_no".equals(name)) 
						qr.setOrderId(value);
					else if ("attach".equals(name)) 
						qr.setAttach(value);
					else if ("time_end".equals(name)) 
						qr.setEndTime(WxpayStep.parseDateTime(value));
					else if ("trade_state_desc".equals(name)) 
						qr.setDescription(value);

					// 忽略其它元素
				}
				if (!coupons.isEmpty())
					qr.setCoupons(new ArrayList<WxpayCoupon>(coupons.values()));
				
				// 一直读到XML文档末尾（END_DOCUMENT）
				while (reader.hasNext())
					reader.next();
			} finally {
				if (reader != null)
					reader.close();
			}
			
			// 检查签名
			String sig = signature.sign(account.getKey());
			System.out.println("===========sig=" + sig + ",sign=" + sign);
			System.out.println("===========appId=" + appId + ",account.getAppId()=" + account.getAppId());
			System.out.println("===========merchantId=" + merchantId + ",account.getMerchantId()=" + account.getMerchantId());
			if (!account.getAppId().equals(appId) ||
					!account.getMerchantId().equals(merchantId) ||
					!sig.equals(sign)) {
				// 签名失败
				returnCode = "FAIL";
				returnMsg = "签名失败";
			}
		} catch (Exception e) {
			returnCode = "FAIL";
			returnMsg = "参数解析错误";
		}
		System.out.println("=======returnCode=" + returnCode + ",returnMsg=" + returnMsg);
		// 如果参数解析或签名错误，报错
		if (returnCode != null) {
			sendResponse(resp, returnCode, returnMsg);
			return null;
		}
		
		if ("SUCCESS".equals(resultCode)) {
			// 支付成功
			// 修改订单状态并发货
			StringBuilder builder = new StringBuilder("微信交易类型：");
			if (qr.getTradeType() != null)
				builder.append(qr.getTradeType());
			builder.append("，付款银行：");
			if (qr.getBankType() != null)
				builder.append(BankType.describe(qr.getBankType()));
			builder.append("，交易号：");
			if (qr.getWxpayId() != null)
				builder.append(qr.getWxpayId());
			
			System.out.println("=========WxpayQueryOrderResult=" + qr);
			/*
			boolean status = suiteOrdersService.orderPaidAndDeliver(new PaymentInfo()
					.orderTradeNo(qr.getOrderId())
					.mode(PaymentGateway.WECHATPAY)
					.time(qr.getEndTime())
					.amount(qr.getTotalFee().doubleValue())
					.remark(builder.toString()));
			System.out.println("=============orderPaidAndDeliver.status=" + status);
			*/
			// 保存到本地
			WxpayOrder order = qr.toWxpayOrder();
			order.setAppId(account.getAppId());
			//wxpayOperations.overwriteWxpayOrder(order);
			return order;
		}
		return null;
	}
	
	public void sendResponse(HttpServletResponse resp, String code, String message) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/xml;charset=utf-8");
		OutputStream os = resp.getOutputStream();
		XMLStreamWriter writer = null;
		try {
			writer = XOF.createXMLStreamWriter(os);
			writer.writeStartDocument();
			
			// 写XML根元素的开始tag
			writer.writeStartElement("xml");
			
			// 对对象进行XML编码，需要一直输出到该对象的结束tag
			if (code != null) {
				writer.writeStartElement("return_code");
				writer.writeCharacters(code);
				writer.writeEndElement();
			}
			if (message != null) {
				writer.writeStartElement("return_msg");
				writer.writeCharacters(message);
				writer.writeEndElement();
			}
			
			writer.writeEndElement();
			
			writer.writeEndDocument();
			writer.flush();
		} catch (XMLStreamException xse) {
			throw new IOException(xse);
		} finally {
			if (writer != null)
				try { writer.close(); } catch (Exception e) {}
			if (os != null)
				os.close();
		}
	}
}