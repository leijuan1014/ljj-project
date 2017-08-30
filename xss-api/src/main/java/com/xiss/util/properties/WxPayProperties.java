package com.xiss.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.xiss.model.wxpay.WxpayAccount;


/** 
* @author leijj
* @since  2017年5月4日 下午6:21:09 
*/
public class WxPayProperties {
	/**获取Jedis
	 * @return
	 */
	public static WxpayAccount getAppWxpayAccount(){
		Properties pros = getPprVue();
		WxpayAccount wxpayAccount = new WxpayAccount();
		wxpayAccount.setAppId(pros.getProperty("app.appId"));
		wxpayAccount.setAppSecret( pros.getProperty("app.appSecret"));
		wxpayAccount.setMerchantId(pros.getProperty("app.merchantId"));
		wxpayAccount.setKey(pros.getProperty("app.key"));
		wxpayAccount.setCertPath(pros.getProperty("app.certPath"));
		wxpayAccount.setNotifyUrl(pros.getProperty("app.notifyUrl"));
		return wxpayAccount;
	}
	
	public static WxpayAccount getJsapiWxpayAccount(){
		Properties pros = getPprVue();
		WxpayAccount wxpayAccount = new WxpayAccount();
		wxpayAccount.setAppId(pros.getProperty("jsapi.appId"));
		wxpayAccount.setAppSecret( pros.getProperty("jsapi.appSecret"));
		wxpayAccount.setMerchantId(pros.getProperty("jsapi.merchantId"));
		wxpayAccount.setKey(pros.getProperty("jsapi.key"));
		wxpayAccount.setCertPath(pros.getProperty("jsapi.certPath"));
		wxpayAccount.setNotifyUrl(pros.getProperty("jsapi.notifyUrl"));
		return wxpayAccount;
	}
	public static WxpayAccount getWxappWxpayAccount(){
		Properties pros = getPprVue();
		WxpayAccount wxpayAccount = new WxpayAccount();
		wxpayAccount.setAppId(pros.getProperty("wxapp.appId"));
		wxpayAccount.setAppSecret( pros.getProperty("wxapp.appSecret"));
		wxpayAccount.setMerchantId(pros.getProperty("wxapp.merchantId"));
		wxpayAccount.setKey(pros.getProperty("wxapp.key"));
		wxpayAccount.setCertPath(pros.getProperty("wxapp.certPath"));
		wxpayAccount.setNotifyUrl(pros.getProperty("wxapp.notifyUrl"));
		return wxpayAccount;
	}
	public static String getBaseurl(){
		Properties pros = getPprVue();
		return pros.getProperty("baseurl");
	}
	//火人节门票支付回调（公众号支付）	
	public static String burningManJsapiNotify(){
		Properties pros = getPprVue();
		return getBaseurl().concat(pros.getProperty("burningMan.jsapi.notifyUrl"));
	}	
	//售卡支付回调（公众号支付）	
	public static String cardDealJsapiNotify(){
		Properties pros = getPprVue();
		return getBaseurl().concat(pros.getProperty("cardDeal.jsapi.notifyUrl"));
	}
	//商家套餐销售回调（APP支付）
	public static String suiteOrderAppNotify(){
		Properties pros = getPprVue();
		return getBaseurl().concat(pros.getProperty("suite.app.notifyUrl"));
	}
	//会员卡支付回调（公众号支付）
	public static String memberJsapiNotify(){
		Properties pros = getPprVue();
		return getBaseurl().concat(pros.getProperty("member.jsapi.notifyUrl"));
	}
	//会员卡支付回调（APP支付）
	public static String memberWxAppNotify(){
		Properties pros = getPprVue();
		return getBaseurl().concat(pros.getProperty("member.wxapp.notifyUrl"));
	}
	//会员卡支付回调（APP支付）
	public static String memberAppNotify(){
		Properties pros = getPprVue();
		return getBaseurl().concat(pros.getProperty("member.app.notifyUrl"));
	}
	//会员卡临时支付回调（APP支付）
	public static String memberJsapiTmpNotify(){
		Properties pros = getPprVue();
		return getBaseurl().concat(pros.getProperty("member.jsapi.tmp.notifyUrl"));
	}
	//网上商城支付回调
	public static String bdshopJsapiNotify(){
		Properties pros = getPprVue();
		return getBaseurl().concat(pros.getProperty("bdshop.jsapi.notifyUrl"));
	}
	
	/**读取redis.properties 配置文件
	 * @return
	 * @throws IOException
	 */
	public static Properties getPprVue(){
		InputStream inputStream = WxPayProperties.class.getClassLoader().getResourceAsStream("properties/wxpay.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			//读取配置文件出错
			e.printStackTrace();
		}
		return p;
	}
	
	public static void main(String[] args) {
		System.out.println(WxPayProperties.cardDealJsapiNotify());
	}
}
