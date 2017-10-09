package com.xiss.service.wxpay.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import org.springframework.stereotype.Service;

import com.xiss.model.wxpay.WxpayAccount;
import com.xiss.model.wxpay.WxpayPlaceOrderRequest;
import com.xiss.model.wxpay.WxpayPlaceOrderResult;
import com.xiss.model.wxpay.WxpaySignature;
/** 
* @author leijj
* @since  2017年4月24日 下午3:48:55 
*/
@Service
public class WxpayPlaceOrder extends WxpayStep{
	private static final Logger logger = LoggerFactory.getLogger(WxpayPlaceOrder.class);
	
	private WxpayAccount account;
	private WxpayPlaceOrderRequest request;
	private String sign;
	private String nonce;
	private String notifyUrl;
	public WxpayAccount getAccount() {
		return account;
	}

	public void setAccount(WxpayAccount account) {
		this.account = account;
	}
	public WxpayPlaceOrderRequest getRequest() {
		return request;
	}

	public void setRequest(WxpayPlaceOrderRequest request) {
		this.request = request;
	}
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public WxpayPlaceOrderResult run(WxpayPlaceOrderRequest request, WxpayAccount account, String notifyUrl) throws Exception {
		
		//使用优惠券 TODO
		setAccount(account);
		setNotifyUrl(notifyUrl);
		setRequest(request);
		InputStream stream = encodeRequest();
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
			StringBuilder reqXML = new StringBuilder();
			String text;
			while ((text = bufferedReader.readLine()) != null) {
				reqXML.append(text);
            }
			System.out.println("==========reqXML=" + reqXML);
	        post.setEntity(new ByteArrayEntity(reqXML.toString().getBytes()));
	        response = httpclient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
            	WxpayPlaceOrderResult result = (WxpayPlaceOrderResult) decodeResponse(entity.getContent());
            	System.out.println("======WxpayPlaceOrderResult=" + result);
                return result;
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            try {
            	if(response != null) response.close();
            	if(httpclient != null) httpclient.close();
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        return null;
		/*
		HttpClient client = new HttpClient();
		Request request = client.newRequest("https://api.mch.weixin.qq.com/pay/unifiedorder")
				.method(HttpMethod.POST)
				.header("Content-Type", "text/xml; charset=utf8");
		InputStream stream = encodeRequest();
		request.content(new InputStreamContentProvider(stream))
		.send(new BufferingResponseListener() {
			
			@Override
			public void onComplete(Result result) {
				System.out.println("=============result=" + result);
				if (!result.isSucceeded()) {
					return;
				}
				
				//WxpayQueryOrderResult qr = null;
				try {
					//qr = (WxpayQueryOrderResult) decodeResponse(getContentAsInputStream());
				} catch (Exception e) {
					return;
				}
				
			}
		});
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
		
		String deviceInfo = request.getDeviceInfo();
		if (deviceInfo != null && !deviceInfo.isEmpty()) {
			writeElement(writer, "device_info", deviceInfo);
			signature.param("device_info", deviceInfo);
		}
		
		String nonce = WxpaySignature.genNonce();
		setNonce(nonce);
		writeElement(writer, "nonce_str", nonce);
		signature.param("nonce_str", nonce);
		
		String body = request.getBody();
		if (body != null && !body.isEmpty()) {
			writeElement(writer, "body", body);
			signature.param("body", body);
		}
		/*
		List<WxpayOrderItem> items = request.getItems();
		if (items != null && !items.isEmpty()) {
			BytesOutputStream bos = new BytesOutputStream();
			JsonGenerator generator = null;
			try {
				generator = new JsonFactory().createGenerator(bos);
				generator.writeStartObject();
				generator.writeArrayFieldStart("goods_detail");
				for (WxpayOrderItem item : items) {
					generator.writeStartObject();
					if (item != null) {
						if (item.getId() != null)
							generator.writeStringField("goods_id", item.getId());
						if (item.getWxpayId() != null)
							generator.writeStringField("wxpay_goods_id", item.getWxpayId());
						if (item.getName() != null)
							generator.writeStringField("goods_name", item.getName());
						if (item.getQuantity() > 0)
							generator.writeNumberField("quantity", item.getQuantity());
						if (item.getPrice() != null)
							generator.writeNumberField("price", item.getPrice().multiply(HUNDRED).longValue());
						if (item.getCategory() != null)
							generator.writeStringField("goods_category", item.getCategory());
						if (item.getBody() != null)
							generator.writeStringField("body", item.getBody());
					}
					generator.writeEndObject();
				}
				generator.writeEndArray();
				generator.writeEndObject();
			} finally {
				if (generator != null)
					try { generator.close(); } catch (IOException ioe) {}
			}
			
			bos.close();
			String detail = bos.toString();

			writeElement(writer, "detail", detail);
			signature.param("detail", detail);
		}
		*/
		String attach = request.getAttach();
		if (attach != null && !attach.isEmpty()) {
			writeElement(writer, "attach", attach);
			signature.param("attach", attach);
		}
		
		String orderId = request.getOrderTradeNo();
		if (orderId != null && !orderId.isEmpty()) {
			writeElement(writer, "out_trade_no", orderId);
			signature.param("out_trade_no", orderId);
		}
		
		String feeType = request.getFeeType();
		if (feeType != null && !feeType.isEmpty()) {
			writeElement(writer, "fee_type", feeType);
			signature.param("fee_type", feeType);
		}
		
		//String totalFee = String.valueOf(request.getTotalFee().multiply(HUNDRED).longValue());
		writeElement(writer, "total_fee", String.valueOf(request.getTotalFee()));
		signature.param("total_fee", String.valueOf(request.getTotalFee()));
		
		String clientIp = request.getClientIp();
		if (clientIp != null && !clientIp.isEmpty()) {
			writeElement(writer, "spbill_create_ip", clientIp);
			signature.param("spbill_create_ip", clientIp);
		}
		
		if (request.getStartTime() != null) {
			String startTime = formatDateTime(request.getStartTime());
			writeElement(writer, "time_start", startTime);
			signature.param("time_start", startTime);
		}
		
		if (request.getDueTime() != null) {
			String dueTime = formatDateTime(request.getDueTime());
			writeElement(writer, "time_expire", dueTime);
			signature.param("time_expire", dueTime);
		}
		
		String tag = request.getTag();
		if (tag != null && !tag.isEmpty()) {
			writeElement(writer, "goods_tag", tag);
			signature.param("goods_tag", tag);
		}
		System.out.println("=========最终的notifyUrl=" + notifyUrl);
		if (notifyUrl != null && !notifyUrl.isEmpty()) {
			writeElement(writer, "notify_url", notifyUrl);
			signature.param("notify_url", notifyUrl);
		}
		
		String tradeType = request.getTradeType();
		if (tradeType != null && !tradeType.isEmpty()) {
			writeElement(writer, "trade_type", tradeType);
			signature.param("trade_type", tradeType);
		}
		String openid = request.getOpenid();
		if (openid != null && !openid.isEmpty()) {
			writeElement(writer, "openid", openid);
			signature.param("openid", openid);
		}
		String limitPay = request.getLimitPay();
		if (limitPay != null && !limitPay.isEmpty()) {
			writeElement(writer, "limit_pay", limitPay);
			signature.param("limit_pay", limitPay);
		}
		
		// 计算签名
		String sig = signature.sign(account.getKey());
		setSign(sig);
		System.out.println("============sig=" + sig);
		System.out.println("============nonce_str=" + nonce);
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
		WxpayPlaceOrderResult result = new WxpayPlaceOrderResult();
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
			else if ("sign".equals(name)) {
				sign = value;
				result.setSign(sign);
			} else if ("prepay_id".equals(name)) 
				result.setPrepayId(value);
			else if ("code_url".equals(name))
				result.setCodeUrl(value);
			else if ("nonce_str".equals(name))
				result.setNoncestr(value);
			// 忽略其它元素
		}
		// 检查签名
		String sig = signature.sign(account.getKey());
		if (!account.getAppId().equals(appId) ||
				!account.getMerchantId().equals(merchantId) ||
				!sig.equals(sign)){
			result = null;
		}
		return result;
	}

}
