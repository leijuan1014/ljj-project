package com.wx.base.step;

import java.io.IOException;

import com.wx.base.bean.WxFailure;
import com.wx.base.bean.WxFailureJsonCodec;
import com.wx.grizzly.BytesInputStream;

public class WxVerifyAccessTokenStep extends WxStep {
	
	public WxFailure run(String accessToken, String openid) {
		StringBuilder reqURL = new StringBuilder("https://api.weixin.qq.com/sns/auth?");
		reqURL.append("access_token=").append(accessToken).append("&openid=").append(openid);
		WxFailure failure = null;
		BytesInputStream result = runGet(reqURL.toString());
		if(result != null) {
			try {
				failure = new WxFailureJsonCodec().decode(result);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return failure;
	}
}
