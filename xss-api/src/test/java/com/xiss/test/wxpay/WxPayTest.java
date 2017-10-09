package com.xiss.test.wxpay;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.SSLContext;

//import org.junit.Test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.util.InputStreamContentProvider;
import org.eclipse.jetty.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xiss.util.md5.MD5Util;

/**
 * 微信接口相关交互
 *
 */
public class WxPayTest{// extends SpringTestCase{

    private static final Logger logger = LoggerFactory.getLogger(WxPayTest.class);
    
	//@Autowired
	//private WxBaseOperations wxBaseOperations;
	
    private static CloseableHttpClient getMchIdSSLClient(String certPath,String key) throws Exception{
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        FileInputStream instream = new FileInputStream(new File(certPath));
        char[] keyArray = key.toCharArray();
        try {
            keyStore.load(instream, keyArray);
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, keyArray).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,null);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    private static void append(StringBuilder sb,String nodeName,Object value){
        sb.append("<").append(nodeName).append(">").append(value).append("</").append(nodeName).append(">");
    }

    /*
    //微信支付结果通知回调
    public void callback(HttpServletRequest request, HttpServletResponse response, String appid, String mch_id, String nonce_str, String product_id, String time_stamp, String sign){
        String inputLine;
        String notityXml = "";

        try {
            while ((inputLine = request.getReader().readLine()) != null) {
                notityXml += inputLine;
            }
            request.getReader().close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("接收到的报文：" + notityXml);
    }*/


   // @Test
    public void unifiedorder1() {
    	System.out.println("=======微信支付统一下单开始");
        String mchId = "1460190602";//"1252644101";
        String mchSecret = "1DC11A9531C172C7082663EE1F1B1309";//"c1698f2afb874b169b05e2ce0dc76e48";
        String appId = "wx7207aaafb7497ca7";//"wx4dadc9d2978c1ae4";
        CloseableHttpClient httpclient = null;
        try {
            //httpclient = getMchIdSSLClient("/home/dreamjobs/uploader/wx/apiclient_cert.p12", "1252644101");
            httpclient = HttpClients.createDefault();//getMchIdSSLClient("/Users/leijj/Documents/weixin/cert_z/apiclient_cert.p12", "1252644101");
            String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
            Calendar ca = Calendar.getInstance();
            Date time_start = ca.getTime();
            System.out.println("time_start=" + time_start);
            Map<String,Object> params = new TreeMap<String, Object>();
            params.put("appid",	 appId);//公众账号ID
            params.put("mch_id", mchId);//商户号
            params.put("device_info", "WEB");//设备号:终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
            params.put("nonce_str", nonceStr);//随机字符串
            params.put("body", "测试-会员充值");//商品描述
            params.put("detail", "");//商品详情
            params.put("attach", "嘻唰唰");//附加数据
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            params.put("out_trade_no", mchId+"test"+format.format(new Date()));//商户订单号
            params.put("fee_type", "CNY");//货币类型
            params.put("total_fee", 1);//总金额
            params.put("spbill_create_ip", "118.190.114.150");//终端IP
            params.put("time_start", time_start.getTime());//交易起始时间
            ca.add(Calendar.MINUTE, 10);
            Date time_expire = ca.getTime();
            System.out.println("time_expire=" + time_expire);
            //params.put("time_expire", time_expire.getTime());//交易结束时间
            //params.put("goods_tag", "WXG");//商品标记，代金券或立减优惠功能的参数
            params.put("notify_url", "http://118.190.114.150:8080/xiss/api/wxpay/notify");//通知地址:接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
            params.put("trade_type", "NATIVE");//交易类型
            params.put("product_id", "1");//商品ID
            //params.put("limit_pay" , "");//指定支付方式
            //params.put("openid", "oqFQ6wHntHvOjAhT91VPr24LGFXY");//用户标识

            StringBuilder signBuilder = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            for (Entry<String, Object> entry : params.entrySet()) {
                if (null==entry.getValue() || entry.getValue().toString().isEmpty()) {
                    continue;
                }
                signBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                append(sb, entry.getKey(),entry.getValue());
            }
            signBuilder.append("key=").append(mchSecret);
            System.out.println("*******************");
            String signStr = signBuilder.toString();
            System.out.println("参数值:"+signStr);
            String sign = MD5Util.md5Encode(signStr).toUpperCase();//encoder.encodePassword(signBuilder.toString(), null).toUpperCase();
            System.out.println("======sign="+sign);
            System.out.println("*******************");
            append(sb, "sign",sign);
            String reqXML = "<xml>"+sb.toString()+"</xml>";
            
            HttpClient client = new HttpClient();
    		Request request = client.newRequest("https://api.mch.weixin.qq.com/pay/unifiedorder")
    				.method(HttpMethod.POST)
    				.header("Content-Type", "text/xml; charset=utf8");
    		request.content(new InputStreamContentProvider(new ByteArrayInputStream(reqXML.getBytes())), "text/plain").send();
    		/*
    		request.content(new InputStreamContentProvider(new ByteArrayInputStream(reqXML.getBytes())))
    		.send(new BufferingResponseListener() {
    			@Override
    			public void onComplete(Result result) {
    				System.out.println("=============result=" + result);
    				if (!result.isSucceeded()) {
    					return;
    				}
    				
    				//WxpayQueryOrderResult qr = null;
    				try {
    					//qr = (WxpayQueryOrderResult) decodeResponse(getContentAsInputStream());
    				} catch (Exception e) {
    					return;
    				}
    				
    			}
    		});
            */
            /*
            HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
            append(sb, "sign",sign);
            String reqXML = "<xml>"+sb.toString()+"</xml>";
            post.setEntity(new ByteArrayEntity(reqXML.getBytes()));
            System.out.println("executing request" + post.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("===========" + response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    //System.out.println(entity.getContent());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println("返回结果" + text);
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
            */
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        System.out.println("=======微信支付统一下单截至");
	}
    /**
     * 微信支付统一下单接口
     */
    //@Test
    public void unifiedorder() {
        System.out.println("=======微信支付统一下单开始");
        String mchId = "1460190602";//"1252644101";
        String mchSecret = "1DC11A9531C172C7082663EE1F1B1309";//"c1698f2afb874b169b05e2ce0dc76e48";
        String appId = "wx7207aaafb7497ca7";//"wx4dadc9d2978c1ae4";
        CloseableHttpClient httpclient = null;
        try {
            //httpclient = getMchIdSSLClient("/home/dreamjobs/uploader/wx/apiclient_cert.p12", "1252644101");
            httpclient = HttpClients.createDefault();//getMchIdSSLClient("/Users/leijj/Documents/weixin/cert_z/apiclient_cert.p12", "1252644101");
            String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
            Calendar ca = Calendar.getInstance();
            Date time_start = ca.getTime();
            System.out.println("time_start=" + time_start);
            Map<String,Object> params = new TreeMap<String, Object>();
            params.put("appid",	 appId);//公众账号ID
            params.put("mch_id", mchId);//商户号
            params.put("device_info", "WEB");//设备号:终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
            params.put("nonce_str", nonceStr);//随机字符串
            params.put("body", "测试-会员充值");//商品描述
            params.put("detail", "");//商品详情
            params.put("attach", "嘻唰唰");//附加数据
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            params.put("out_trade_no", mchId+"test"+format.format(new Date()));//商户订单号
            params.put("fee_type", "CNY");//货币类型
            params.put("total_fee", 1);//总金额
            params.put("spbill_create_ip", "118.190.114.150");//终端IP
            params.put("time_start", time_start.getTime());//交易起始时间
            ca.add(Calendar.MINUTE, 10);
            Date time_expire = ca.getTime();
            System.out.println("time_expire=" + time_expire);
            //params.put("time_expire", time_expire.getTime());//交易结束时间
            //params.put("goods_tag", "WXG");//商品标记，代金券或立减优惠功能的参数
            params.put("notify_url", "http://118.190.114.150:8080/xiss/api/wxpay/notify");//通知地址:接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数
            params.put("trade_type", "NATIVE");//交易类型
            params.put("product_id", "1");//商品ID
            //params.put("limit_pay" , "");//指定支付方式
            //params.put("openid", "oqFQ6wHntHvOjAhT91VPr24LGFXY");//用户标识

            StringBuilder signBuilder = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            for (Entry<String, Object> entry : params.entrySet()) {
                if (null==entry.getValue() || entry.getValue().toString().isEmpty()) {
                    continue;
                }
                signBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                append(sb, entry.getKey(),entry.getValue());
            }
            signBuilder.append("key=").append(mchSecret);
            System.out.println("*******************");
            String signStr = signBuilder.toString();
            System.out.println("参数值:"+signStr);
            String sign = MD5Util.md5Encode(signStr).toUpperCase();//encoder.encodePassword(signBuilder.toString(), null).toUpperCase();
            System.out.println("======sign="+sign);
            System.out.println("*******************");
            HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
            append(sb, "sign",sign);
            String reqXML = "<xml>"+sb.toString()+"</xml>";
            post.setEntity(new ByteArrayEntity(reqXML.getBytes()));
            System.out.println("executing request" + post.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("===========" + response.getStatusLine());
                if (entity != null) {
                    System.out.println("Response content length: " + entity.getContentLength());
                    //System.out.println(entity.getContent());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println("返回结果" + text);
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        System.out.println("=======微信支付统一下单截至");
    }

    //@Test
    public void wxOrderquery(){
        System.out.println("=======微信支付查询订单开始");
        String mchId = "1460190602";//"1252644101";
        String mchSecret = "1DC11A9531C172C7082663EE1F1B1309";//"c1698f2afb874b169b05e2ce0dc76e48";
        String appId = "wx7207aaafb7497ca7";//"wx4dadc9d2978c1ae4";
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
            Calendar ca = Calendar.getInstance();
            Date time_start = ca.getTime();
            System.out.println("time_start=" + time_start);
            Map<String,Object> params = new TreeMap<String, Object>();
            params.put("appid", appId);//公众账号ID
            params.put("mch_id", mchId);//商户号
            params.put("nonce_str", nonceStr);//随机字符串
            params.put("transaction_id", "4000642001201609022894995551");//微信的订单号，优先使用
            //params.put("out_trade_no", "1266591401test20160902144730");//商户订单号
            StringBuilder signBuilder = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            for (Entry<String, Object> entry : params.entrySet()) {
                if (null==entry.getValue() || !entry.getValue().toString().isEmpty()) {
                    continue;
                }
                signBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                append(sb, entry.getKey(),entry.getValue());
            }
            signBuilder.append("key=").append(mchSecret);
            System.out.println("*******************");
            String signStr = signBuilder.toString();
            System.out.println("参数值:"+signStr);
            String sign = MD5Util.md5Encode(signStr).toUpperCase();
            System.out.println("======sign="+sign);
            System.out.println("*******************");
            HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/pay/orderquery");
            append(sb, "sign",sign);
            String reqXML = "<xml>"+sb.toString()+"</xml>";
            post.setEntity(new ByteArrayEntity(reqXML.getBytes()));
            System.out.println("executing request" + post.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println(text);
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        System.out.println("=======微信支付查询订单截至");
    }

    //@Test
    public void wxPayRefund(){
        System.out.println("=======微信支付退款开始");
        String mchId = "1460190602";//"1252644101";
        String mchSecret = "1DC11A9531C172C7082663EE1F1B1309";//"c1698f2afb874b169b05e2ce0dc76e48";
        String appId = "wx7207aaafb7497ca7";//"wx4dadc9d2978c1ae4";
        CloseableHttpClient httpclient = null;
        try {
            httpclient = getMchIdSSLClient("/Users/xiss/Documents/project/xissdoc/wxpay/cert/apiclient_cert.p12", "1460190602");
            String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
            Calendar ca = Calendar.getInstance();
            Date time_start = ca.getTime();
            System.out.println("time_start=" + time_start);
            Map<String,Object> params = new TreeMap<String, Object>();
            params.put("appid", appId);//公众账号ID
            params.put("mch_id", mchId);//商户号
            params.put("device_info", "WEB");
            params.put("nonce_str", nonceStr);//随机字符串
            params.put("transaction_id", "4000642001201609022894995551");//微信的订单号，优先使用
            params.put("out_trade_no", "");//商户订单号
            params.put("out_refund_no", "4000642001201609022894995551");
            params.put("total_fee", 1);
            params.put("refund_fee", 1);
            params.put("refund_fee_type", "");
            params.put("op_user_id", "1");


            StringBuilder signBuilder = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            for (Entry<String, Object> entry : params.entrySet()) {
                if (null==entry.getValue() || !entry.getValue().toString().isEmpty()) {
                    continue;
                }
                signBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                append(sb, entry.getKey(),entry.getValue());
            }
            signBuilder.append("key=").append(mchSecret);
            System.out.println("*******************");
            String signStr = signBuilder.toString();
            System.out.println("参数值:"+signStr);
            String sign = MD5Util.md5Encode(signStr).toUpperCase();
            System.out.println("======sign="+sign);
            System.out.println("*******************");
            HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            append(sb, "sign",sign);
            String reqXML = "<xml>"+sb.toString()+"</xml>";
            post.setEntity(new ByteArrayEntity(reqXML.getBytes()));
            System.out.println("executing request:" + post.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                System.out.println("======entity=" + entity);
                if (entity != null) {
                    System.out.println("======entity.getContent()=" + entity.getContent());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println(text);
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        System.out.println("=======微信支付退款截至");
    }

    //@Test
    public void wxPayShorturl(){
        System.out.println("=======微信支付退款开始");
        String mchId = "1460190602";//"1252644101";
        String mchSecret = "1DC11A9531C172C7082663EE1F1B1309";//"c1698f2afb874b169b05e2ce0dc76e48";
        String appId = "wx7207aaafb7497ca7";//"wx4dadc9d2978c1ae4";
        CloseableHttpClient httpclient = null;
        try {
            httpclient = getMchIdSSLClient("/Users/xiss/Documents/project/xissdoc/wxpay/cert/apiclient_cert.p12", "1460190602");
            String nonceStr = UUID.randomUUID().toString().replaceAll("-", "");
            Calendar ca = Calendar.getInstance();
            Date time_start = ca.getTime();
            System.out.println("time_start=" + time_start);
            Map<String,Object> params = new TreeMap<String, Object>();
            params.put("appid", appId);//公众账号ID
            params.put("mch_id", mchId);//商户号
            Date now = new Date();
            String long_url = "weixin://wxpay/bizpayurl?sign=6A59BFBBA2031EB651D2C9C13E941A9C&appid=wx4dadc9d2978c1ae4&mch_id=1266591401&product_id=1&time_stamp="+now.getTime()
                    +"&nonce_str=f0a81623cae2458c9a8ffc549b125522";
            params.put("long_url", long_url);
            params.put("nonce_str", nonceStr);//随机字符串


            StringBuilder signBuilder = new StringBuilder();
            StringBuilder sb = new StringBuilder();
            for (Entry<String, Object> entry : params.entrySet()) {
                if (null==entry.getValue() || !entry.getValue().toString().isEmpty()) {
                    continue;
                }
                signBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                append(sb, entry.getKey(),entry.getValue());
            }
            signBuilder.append("key=").append(mchSecret);
            System.out.println("*******************");
            String signStr = signBuilder.toString();
            System.out.println("参数值:"+signStr);
            String sign = MD5Util.md5Encode(signStr).toUpperCase();
            System.out.println("======sign="+sign);
            System.out.println("*******************");
            HttpPost post = new HttpPost("https://api.mch.weixin.qq.com/tools/shorturl");
            append(sb, "sign",sign);
            String reqXML = "<xml>"+sb.toString()+"</xml>";
            post.setEntity(new ByteArrayEntity(reqXML.getBytes()));
            System.out.println("executing request:" + post.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    System.out.println("======entity.getContent()=" + entity.getContent());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
                    String text;
                    while ((text = bufferedReader.readLine()) != null) {
                        System.out.println(text);
                    }
                }
                EntityUtils.consume(entity);
            } finally {
                response.close();
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        System.out.println("=======微信支付退款截至");
    }
    public static void main(String[] args){
        System.out.println("yrdy");
    }
}