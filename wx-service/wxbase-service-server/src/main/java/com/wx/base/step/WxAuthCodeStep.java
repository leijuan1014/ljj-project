package com.wx.base.step;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.base.bean.WxAccessToken;
import com.wx.base.bean.WxAccessTokenJsonCodec;
import com.wx.grizzly.BytesInputStream;


/** 
* @author leijj
* @since  2017年5月5日 上午9:36:25 
*/
@Service
public class WxAuthCodeStep extends WxStep{
	private static final Logger logger = LoggerFactory.getLogger(WxAuthCodeStep.class);
	public WxAccessToken run(String  appid, String  secret, String code) {
		StringBuilder reqURL = new StringBuilder("https://api.weixin.qq.com/sns/oauth2/access_token?");
		reqURL.append("appid=" + appid);
		reqURL.append("&secret=" + secret);
		reqURL.append("&code=" + code);
		reqURL.append("&grant_type=authorization_code");
		WxAccessToken accessToken = null;
		BytesInputStream result = runGet(reqURL.toString());
		if(result != null) {
			try {
				accessToken = new WxAccessTokenJsonCodec().decode(result);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return accessToken;
	}
}