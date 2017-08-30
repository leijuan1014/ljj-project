package com.wx.base.step;

import java.io.IOException;

import com.wx.base.bean.WxAccessToken;
import com.wx.base.bean.WxAccessTokenJsonCodec;
import com.wx.grizzly.BytesInputStream;

public class WxRefreshAccessTokenStep extends WxStep {
	
	public WxAccessToken run(String appid, String refreshToken) {
		StringBuilder reqURL = new StringBuilder("https://api.weixin.qq.com/sns/oauth2/refresh_token?");
		reqURL.append("grant_type=refresh_token").append("&appid=").append(appid).append("&refresh_token=").append(refreshToken);
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