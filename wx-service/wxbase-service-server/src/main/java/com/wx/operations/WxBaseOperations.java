package com.wx.operations;

import com.wx.base.bean.WxAccessToken;
import com.wx.base.bean.WxTicket;

public interface WxBaseOperations {
	/**
	 * 获取微信基础支持的普通访问令牌。
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	WxAccessToken getAccessToken(String appid, String secret);
	
	/**
	 * 获取微信票据。
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	WxTicket getWxTicket(String appid, String secret); 
	

	/**
	 * 获取微信网页授权的访问令牌，它不同于基础支持的普通访问令牌。
	 * 
	 * @param appid
	 * @param secret
	 * @param code 用户授权码
	 * @return
	 */
	WxAccessToken getAccessToken(String appid, String secret, String code);
	
	/**
	 * 刷新微信网页授权的访问令牌。
	 * 
	 * @param appid
	 * @param refreshToken 刷新令牌
	 * @return
	 */
	WxAccessToken refreshAccessToken(String appid, String refreshToken);

	/**
	 * 验证网页授权的访问令牌是否有效。
	 * 
	 * @param accessToken 访问令牌
	 * @param openId 用户OpenId
	 * @return
	 */
	boolean verifyAccessToken(String accessToken, String openId);
}
