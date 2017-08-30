package com.wx.operations.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.wx.base.bean.WxAccessToken;
import com.wx.base.bean.WxTicket;
import com.wx.base.step.WxAccessTokenStep;
import com.wx.base.step.WxAuthCodeStep;
import com.wx.base.step.WxTicketStep;
import com.wx.operations.WxBaseOperations;
@Service
public class WxBaseOperationsImpl implements WxBaseOperations{

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private WxAccessTokenStep wxAccessTokenStep;
	@Autowired
	private WxAuthCodeStep wxAuthCodeStep;
	@Autowired
	private WxTicketStep wxTicketStep;
	@Override
	@SuppressWarnings("unchecked")
	public WxAccessToken getAccessToken(String appid, String secret) {
		WxAccessToken accessToken = new WxAccessToken();
		String key = appid.concat(".accessToken");
		boolean exists = redisTemplate.hasKey(key);
        if(exists){
        		accessToken = (WxAccessToken) redisTemplate.opsForValue().get(key);
        } else {
	        	accessToken = wxAccessTokenStep.run(appid, secret);
	        	ValueOperations<String, WxAccessToken> accessTokenOperations = redisTemplate.opsForValue();
	        	accessTokenOperations.set(key, accessToken, accessToken.getExpiresIn(), TimeUnit.SECONDS);
        }
		return accessToken;
	}

	@Override
	@SuppressWarnings("unchecked")
	public WxTicket getWxTicket(String appid, String secret) {
		WxTicket wxTicket = new WxTicket();
		String key = appid.concat(".wxTicket");
		boolean exists = redisTemplate.hasKey(key);
        if(exists){
        	wxTicket = (WxTicket) redisTemplate.opsForValue().get(key);
        } else {
        	WxAccessToken accessToken = getAccessToken(appid, secret);
    		if (accessToken == null || accessToken.getAccessToken() == null ||  accessToken.getAccessToken().isEmpty()) 
    			return null;
        	wxTicket = wxTicketStep.run(accessToken.getAccessToken());
        	ValueOperations<String, WxTicket> tiketOperations = redisTemplate.opsForValue();
        	tiketOperations.set(key, wxTicket, wxTicket.getExpiresIn(), TimeUnit.SECONDS);
        }
		return wxTicket;
	}

	@Override
	@SuppressWarnings("unchecked")
	public WxAccessToken getAccessToken(String appid, String secret, String code) {
		WxAccessToken accessToken = new WxAccessToken();
		String key = appid.concat(".oauth2.accessToken");
		boolean exists = redisTemplate.hasKey(key);
        if(exists){
        	accessToken = (WxAccessToken) redisTemplate.opsForValue().get(key);
        } else {
        	accessToken = wxAuthCodeStep.run(appid, secret, code);
        	ValueOperations<String, WxAccessToken> accessTokenOperations = redisTemplate.opsForValue();
        	accessTokenOperations.set(key, accessToken, accessToken.getExpiresIn(), TimeUnit.SECONDS);
        }
		return accessToken;
	}

	@Override
	public WxAccessToken refreshAccessToken(String appid, String refreshToken) {
		return null;
	}

	@Override
	public boolean verifyAccessToken(String accessToken, String openId) {
		return false;
	}

}
