package com.xiss.api.wechat;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wx.base.bean.WxAccessToken;
import com.wx.base.bean.WxTicket;
import com.wx.operations.impl.WxBaseOperationsImpl;
import com.xiss.model.wxpay.WxpayAccount;
import com.xiss.util.properties.WxPayProperties;

/** 
* @author leijj
* @since  2017年5月4日 下午7:02:18 
*/
@Controller
@RequestMapping("/api/wechat")
public class WxCommonAPI {
	//@Autowired
	//private WxAuthCodeStep wxAuthCodeStep;
	//@Autowired
	//private WxTicketStep wxTicketStep;
	//@Autowired
	//private WxAccessTokenStep wxAccessTokenStep;
	//@Autowired
	//private WxBaseOperationsImpl wxBaseOperations;
	public static enum API {
		onMenuShareTimeline, onMenuShareAppMessage, onMenuShareQQ, onMenuShareWeibo,onMenuShareQZone, startRecord, stopRecord, onVoiceRecordEnd, playVoice, pauseVoice, stopVoice, onVoicePlayEnd, uploadVoice, downloadVoice, chooseImage, previewImage, uploadImage, downloadImage, translateVoice, getNetworkType, openLocation, getLocation, hideOptionMenu, showOptionMenu, hideMenuItems, showMenuItems, hideAllNonBaseMenuItem, showAllNonBaseMenuItem, closeWindow, scanQRCode, chooseWXPay, openProductSpecificView, addCard, chooseCard, openCard;
	}
	@ResponseBody
	@RequestMapping("/test")
	public JSONObject test() throws Exception {
    	WxTicket wxTicket = null;//wxBaseOperations.getWxTicket("wx46b543acf6dbc7fa", "e7d006686de01c677fd218a7b53e5795");
    	JSONObject object = new JSONObject();
    	object.put("wxTicket", wxTicket);
    	System.out.println("===========wxTicket==="+wxTicket);
    	return object;
    }
	private API[] getApiNames() {
		return API.values();
	}
	private JSONArray getJsApiList(API... jsAPI) {
		JSONArray jsApiList = new JSONArray();
		for (API api : jsAPI) 
			jsApiList.add(api.toString());
		return jsApiList;
	}
	
	/**
	 * 分享功能配置
	 * 
	 * @param callback
	 * @param url
	 * @param jsAPI
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/jsConfig.json")
	private MappingJacksonValue getJSConfig(String callback,String url,  API... jsAPI){
		String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
		long timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		String signature = "";
		try {
			signature = getSignature(url, nonceStr, timestamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsApiList = getJsApiList((null == jsAPI || jsAPI.length == 0) ? getApiNames() : jsAPI);
		JSONObject config = new JSONObject();
		config.put("appId", WxPayProperties.getJsapiWxpayAccount().getAppId());
		config.put("timestamp", timestamp + "");
		config.put("nonceStr", nonceStr);
		config.put("signature", signature);
		config.put("jsApiList", jsApiList);
		MappingJacksonValue result = new MappingJacksonValue(config);
		result.setJsonpFunction(callback);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/jsConfig")
	private JSONObject getJSConfigs(String url,  API... jsAPI){
		String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
		long timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		String signature = "";
		try {
			signature = getSignature(url, nonceStr, timestamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONArray jsApiList = getJsApiList((null == jsAPI || jsAPI.length == 0) ? getApiNames() : jsAPI);
		JSONObject config = new JSONObject();
		config.put("appId", WxPayProperties.getJsapiWxpayAccount().getAppId());
		config.put("timestamp", timestamp + "");
		config.put("nonceStr", nonceStr);
		config.put("signature", signature);
		config.put("jsApiList", jsApiList);
		return config;
	}
	
	/**
	 * 获取签名
	 * 
	 * @param url
	 * @param nonceStr
	 * @param timestamp
	 * @return
	 * @throws Exception
	 */
	public String getSignature(String url, String nonceStr, long timestamp)
			throws Exception {
		WxpayAccount account = WxPayProperties.getJsapiWxpayAccount();
		WxTicket wxTicket = null;//wxBaseOperations.getWxTicket(account.getAppId(), account.getAppSecret());
		System.out.println("====wxTicket=" + wxTicket);
		if (null == wxTicket  || wxTicket.getTicket() == null || wxTicket.getTicket().isEmpty()) {
			return null;
		}
		String jsApiTicket = wxTicket.getTicket();
		String params = "jsapi_ticket=" + jsApiTicket + "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url=" + url;
		String signature = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(params.getBytes(Charsets.UTF_8));
			signature = Hex.encodeHexString(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return signature;
	}
	
	@RequestMapping("/openId")
	public void openId(String code, String url, HttpServletResponse resp) throws Exception {
        StringBuilder urlBuilder = new StringBuilder(url);
		if (url.indexOf("?")  < 0) {
			urlBuilder.append("?weChat.isCallback=true");
		} else {
			urlBuilder.append("&weChat.isCallback=true");
		}
		String prefix = "&";
		if(code != null && !code.isEmpty()){
			urlBuilder.append(prefix).append("weChat.code=").append(URLEncoder.encode(code, "UTF-8"));
			prefix = "&";
		}
		
		WxpayAccount account = WxPayProperties.getJsapiWxpayAccount();
		WxAccessToken token = null;//wxBaseOperations.getAccessToken(account.getAppId(), account.getAppSecret(), code);
		if (token != null) {
			if(null != token.getOpenId() && !token.getOpenId().isEmpty()){
				urlBuilder.append(prefix).append("weChat.weChatOpenId=").append(token.getOpenId());
				prefix = "&";
			}
			
			if(null != token.getUnionId() && !token.getUnionId().isEmpty()){
				urlBuilder.append(prefix).append("weChat.weChatUnionId=").append(token.getUnionId());
				prefix = "&";
			}
		}
		System.out.println("=======获取openid完成：" + urlBuilder.toString());
		resp.sendRedirect(urlBuilder.toString());
	}

}
