package com.wxpay.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxpay.bean.WxpayAccount;
import com.wxpay.bean.WxpayCoupon;
import com.wxpay.bean.WxpayQueryOrderRequest;
import com.wxpay.bean.WxpayQueryOrderResult;
import com.wxpay.bean.WxpaySignature;

public class WxpayQueryOrder extends WxpayStep {
	private static final Logger logger = LoggerFactory.getLogger(WxpayQueryOrder.class);
	
	private WxpayQueryOrderRequest request;
	
	private WxpayAccount account;
	
	public WxpayQueryOrderRequest getRequest() {
		return request;
	}

	public void setRequest(WxpayQueryOrderRequest request) {
		this.request = request;
	}

	public WxpayAccount getAccount() {
		return account;
	}

	public void setAccount(WxpayAccount account) {
		this.account = account;
	}
	public WxpayQueryOrderResult run() {
		InputStream resultIS = postRun("https://api.mch.weixin.qq.com/pay/orderquery");
		WxpayQueryOrderResult result = (WxpayQueryOrderResult) decodeResponse(resultIS);
		System.out.println("======WxpayQueryOrderResult=" + result);
		return result;
	}
	public WxpayQueryOrderResult run1(String orderTradeNo, String wxpayId, WxpayAccount account) throws Exception {
		WxpayQueryOrderRequest request = new WxpayQueryOrderRequest();
		request.setOrderId(orderTradeNo);
		request.setWxpayId(wxpayId);
		setRequest(request);
		setAccount(account);
		InputStream stream = encodeRequest();
		CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/pay/orderquery");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
			StringBuilder reqXML = new StringBuilder();
			String text;
			while ((text = bufferedReader.readLine()) != null) {
				reqXML.append(text);
            }
	        post.setEntity(new ByteArrayEntity(reqXML.toString().getBytes()));
	        CloseableHttpResponse response = httpclient.execute(post);
	        try {
	            HttpEntity entity = response.getEntity();
	            if (entity != null) {
	            	WxpayQueryOrderResult result = (WxpayQueryOrderResult) decodeResponse(entity.getContent());
	                return result;
	            }
	            EntityUtils.consume(entity);
	        } finally {
	            response.close();
	        }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        return null;
		/*
		try {
			HttpClient client = (HttpClient) continua.ctxget(HttpScope.CLIENT);
			Request request = client.newRequest("https://api.mch.weixin.qq.com/pay/orderquery")
					.method(HttpMethod.POST)
					.header("Content-Type", "text/xml; charset=utf8");
			
			ZeroCopy data = encodeRequest();
			Number timeout = (Number) continua.ctxget(HttpScope.TIMEOUT);
			if (timeout != null)
				request.timeout(timeout.longValue(), TimeUnit.MILLISECONDS);
			request.content(new InputStreamContentProvider(data.asStream()))
			.send(new BufferingResponseListener() {

				@Override
				public void onComplete(Result result) {
					if (!result.isSucceeded()) {
						continua.failed(result.getFailure());
						return;
					}
					
					WxpayQueryOrderResult qr = null;
					try {
						qr = (WxpayQueryOrderResult) decodeResponse(getContentAsInputStream());
					} catch (Exception e) {
						continua.failed(e);
						return;
					}
					if (qr == null)
						continua.failed(getContentAsString());
					else
						continua.completed(qr);
				}
				
			});
		} catch (Exception e) {
			continua.failed(e);
			return;
		}
		*/
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
		
		String nonce = WxpaySignature.genNonce();
		writeElement(writer, "nonce_str", nonce);
		signature.param("nonce_str", nonce);
		
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
		WxpayQueryOrderResult qr = new WxpayQueryOrderResult();
		Map<Integer, WxpayCoupon> coupons = new TreeMap<Integer, WxpayCoupon>();
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
				qr.setTotalFee(BigDecimal.valueOf(Long.parseLong(value)).divide(HUNDRED));
			else if ("fee_type".equals(name)) 
				qr.setFeeType(value);
			else if ("cash_fee".equals(name)) 
				qr.setCashFee(BigDecimal.valueOf(Long.parseLong(value)).divide(HUNDRED));
			else if ("cash_fee_type".equals(name)) 
				qr.setCashFeeType(value);
			else if ("coupon_fee".equals(name))
				qr.setCouponFee(BigDecimal.valueOf(Long.parseLong(value)).divide(HUNDRED));
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
				coupon.setFee(BigDecimal.valueOf(Long.parseLong(value)).divide(HUNDRED));
			}
			else if ("transaction_id".equals(name)) 
				qr.setWxpayId(value);
			else if ("out_trade_no".equals(name)) 
				qr.setOrderId(value);
			else if ("attach".equals(name)) 
				qr.setAttach(value);
			else if ("time_end".equals(name)) 
				qr.setEndTime(parseDateTime(value));
			else if ("trade_state_desc".equals(name)) 
				qr.setDescription(value);

			// 忽略其它元素
		}
		if (!coupons.isEmpty())
			qr.setCoupons(new ArrayList<WxpayCoupon>(coupons.values()));
		
		// 检查签名
		String sig = signature.sign(account.getKey());
		if (!account.getAppId().equals(appId) ||
				!account.getMerchantId().equals(merchantId) ||
				!sig.equals(sign))
				qr = null;

		return qr;
	}

}
