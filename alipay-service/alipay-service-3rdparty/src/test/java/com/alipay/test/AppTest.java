package com.alipay.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.config.AlipayServerConfig;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
   


    /**
     * Rigourous Test :-)
     */
    public void aaatestApp()
    {
    	//获得初始化的AlipayClient
    	AlipayClient alipayClient = new DefaultAlipayClient(AlipayServerConfig.gatewayUrl, AlipayServerConfig.app_id, AlipayServerConfig.merchant_private_key, "json", AlipayServerConfig.charset, AlipayServerConfig.alipay_public_key, AlipayServerConfig.sign_type);
    	
    	//设置请求参数
    	AlipayTradePayRequest alipayRequest = new AlipayTradePayRequest();
    	alipayRequest.setReturnUrl(AlipayServerConfig.return_url);
    	alipayRequest.setNotifyUrl(AlipayServerConfig.notify_url);
    	
    	//商户订单号，商户网站订单系统中唯一订单号，必填
    	String out_trade_no = "2017081801";//new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
    	//付款金额，必填
    	String total_amount = "0.01";//new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
    	//订单名称，必填
    	String subject = "测试商品";//new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
    	//商品描述，可空
    	String body = "测试商品";//new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
    	
    	alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
    			+ "\"total_amount\":\""+ total_amount +"\"," 
    			+ "\"subject\":\""+ subject +"\"," 
    			+ "\"body\":\""+ body +"\"," 
    			+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
    	
    	//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
    	//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
    	//		+ "\"total_amount\":\""+ total_amount +"\"," 
    	//		+ "\"subject\":\""+ subject +"\"," 
    	//		+ "\"body\":\""+ body +"\"," 
    	//		+ "\"timeout_express\":\"10m\"," 
    	//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
    	//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
    	
    	//请求
    	try {
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			System.out.println("====result=" + result);
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //assertTrue( true );
    }
    
    public void aa() throws UnsupportedEncodingException {
    	String txt = "total_amount=0.01&buyer_id=2088002340337660&trade_no=2017050521001004660203003585&body=becomeVIP&notify_time=2017-05-05 16:07:37&subject=VIP&buyer_logon_id=roc***@yahoo.com.cn&auth_app_id=2016111802958037&charset=utf-8&notify_type=trade_status_sync&invoice_amount=0.01&out_trade_no=android20170505154220976&trade_status=TRADE_SUCCESS&gmt_payment=2017-05-05 15:42:27&version=1.0&point_amount=0.00&&gmt_create=2017-05-05 15:42:26&buyer_pay_amount=0.01&receipt_amount=0.01&app_id=2016111802958037&seller_id=2088221491093746&notify_id=df0fe3406f788f7d9edea26ad0b8e1dl3e&seller_email=1991996789@qq.com";
    	String   mytext   =   URLDecoder.decode(txt,   "utf-8");   
    	System.out.println(mytext);
	}
    public void testApp() throws Exception
    {
    	//获取支付宝POST过来反馈信息
    	Map<String,String> params = new HashMap<String,String>();
    	Map<String,String[]> requestParams = new HashMap<String,String[]>();
    	//{"total_amount"=>"0.01", "buyer_id"=>"2088002340337660", "trade_no"=>"2017050521001004660203003585", "body"=>"becomeVIP", "notify_time"=>"2017-05-05 16:07:37", 
    	//"subject"=>"VIP", "sign_type"=>"RSA", "buyer_logon_id"=>"roc***@yahoo.com.cn", "auth_app_id"=>"2016111802958037", "charset"=>"utf-8", "notify_type"=>"trade_status_sync", 
    	//"invoice_amount"=>"0.01", "out_trade_no"=>"android20170505154220976", "trade_status"=>"TRADE_SUCCESS", "gmt_payment"=>"2017-05-05 15:42:27", "version"=>"1.0", 
    	//"point_amount"=>"0.00", "sign"=>"kI7FRlewdOelt3G+LE0SlWy9KiqkyGqYzcvs9yAzQVT+OZQs0EERbSbxYKlTsuFevtcDUXRQeYFqnxDa7VjCBuMOXtql2Ap//lRAPN/GD0yH3JuTEuR3K4spFVuX7BBpFZdTQT2oy7lzlvSkFpigYPS13icmkAW4GsK/HT3hMvM=", 
    	//"gmt_create"=>"2017-05-05 15:42:26", "buyer_pay_amount"=>"0.01", "receipt_amount"=>"0.01", "fund_bill_list"=>"[{\"amount\":\"0.01\",\"fundChannel\":\"ALIPAYACCOUNT\"}]", 
    	//"app_id"=>"2016111802958037", "seller_id"=>"2088221491093746", "notify_id"=>"df0fe3406f788f7d9edea26ad0b8e1dl3e", "seller_email"=>"1991996789@qq.com", "format"=>:json}
    	requestParams.put("total_amount", new String[]{"0.01"});
    	requestParams.put("buyer_id", new String[]{"2088002340337660"});
    	requestParams.put("trade_no", new String[]{"2017050521001004660203003585"});
    	requestParams.put("body", new String[]{"becomeVIP"});
    	requestParams.put("notify_time", new String[]{"2017-05-05 16:07:37"});
    	requestParams.put("subject", new String[]{"VIP"});
    	requestParams.put("sign_type", new String[]{"RSA"});
    	requestParams.put("buyer_logon_id", new String[]{"roc***@yahoo.com.cn"});
    	requestParams.put("auth_app_id", new String[]{"2016111802958037"});
    	requestParams.put("charset", new String[]{"utf-8"});
    	requestParams.put("notify_type", new String[]{"trade_status_sync"});
    	requestParams.put("invoice_amount", new String[]{"0.01"});
    	requestParams.put("out_trade_no", new String[]{"android20170505154220976"});
    	requestParams.put("trade_status", new String[]{"TRADE_SUCCESS"});
    	requestParams.put("gmt_payment", new String[]{"2017-05-05 15:42:27"});
    	requestParams.put("version", new String[]{"1.0"});
    	requestParams.put("point_amount", new String[]{"0.00"});
    	requestParams.put("sign", new String[]{"kI7FRlewdOelt3G+LE0SlWy9KiqkyGqYzcvs9yAzQVT+OZQs0EERbSbxYKlTsuFevtcDUXRQeYFqnxDa7VjCBuMOXtql2Ap//lRAPN/GD0yH3JuTEuR3K4spFVuX7BBpFZdTQT2oy7lzlvSkFpigYPS13icmkAW4GsK/HT3hMvM="});
    	requestParams.put("gmt_create", new String[]{"2017-05-05 15:42:26"});
    	requestParams.put("buyer_pay_amount", new String[]{"0.01"});
    	requestParams.put("receipt_amount", new String[]{"0.01"});
    	//requestParams.put("fund_bill_list", new String[]{});
    	requestParams.put("app_id", new String[]{"2016111802958037"});
    	requestParams.put("seller_id", new String[]{"2088221491093746"});
    	requestParams.put("notify_id", new String[]{"df0fe3406f788f7d9edea26ad0b8e1dl3e"});
    	requestParams.put("seller_email", new String[]{"1991996789@qq.com"});
    	requestParams.put("format", new String[]{"json"});
    	requestParams.put("", new String[]{});
    	requestParams.put("", new String[]{});
    	for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
    		String name = (String) iter.next();
    		String[] values = (String[]) requestParams.get(name);
    		String valueStr = "";
    		for (int i = 0; i < values.length; i++) {
    			valueStr = (i == values.length - 1) ? valueStr + values[i]
    					: valueStr + values[i] + ",";
    		}
    		//乱码解决，这段代码在出现乱码时使用
    		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
    		params.put(name, valueStr);
    	}
    	
    	boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayServerConfig.alipay_public_key, AlipayServerConfig.charset, AlipayServerConfig.sign_type); //调用SDK验证签名
    	System.out.println(signVerified);
    }
}
