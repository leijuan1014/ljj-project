package com.wxpay.bean;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.wxpay.util.HexEncoder;

/**
 * 微信支付接口签名计算工具。
 * 
 * @author zhy
 *
 */
public class WxpaySignature {
	
	/** 随机数生成器 */
	private static Random RAND;

	static {
		try {
			RAND = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成随机数。
	 * 
	 * @return
	 */
	public static String genNonce() {
		byte[] bytes = new byte[16];
		RAND.nextBytes(bytes);
		return new String(HexEncoder.encode(bytes, true));
	}
	
	private Map<String, String> params = new TreeMap<String, String>();

	/**
	 * 填写参数。
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	public WxpaySignature param(String name, String value) {
		if (value != null && !value.isEmpty())
			// 在计算签名时，忽略空或空值参数
			params.put(name, value);
		return this;
	}
	
	/**
	 * 使用指定秘密计算签名。
	 * 
	 * @param secret 计算签名用的秘密
	 * @return 签名
	 * @throws Exception
	 */
	public String sign(String secret) throws Exception {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : params.entrySet())
			builder.append(entry.getKey()).append('=').append(entry.getValue()).append('&');
		builder.append("key=").append(secret);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(builder.toString().getBytes("UTF-8"));
		return new String(HexEncoder.encode(md.digest(), true));
	}

}
