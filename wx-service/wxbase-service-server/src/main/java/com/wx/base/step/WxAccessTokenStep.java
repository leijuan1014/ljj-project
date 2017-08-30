package com.wx.base.step;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wx.base.bean.WxAccessToken;
import com.wx.base.bean.WxAccessTokenJsonCodec;
import com.wx.grizzly.BytesInputStream;
@Service
@Transactional
public class WxAccessTokenStep extends WxStep{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	public WxAccessToken run(String  appid, String  secret) {
		StringBuilder reqURL = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?");
		reqURL.append("grant_type=client_credential").append("&appid=").append(appid).append("&secret=").append(secret);
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
