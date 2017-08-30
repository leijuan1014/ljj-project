package com.xiss.api.system;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
* @author leijj
* @since  2017年5月9日 下午5:42:03 
*/
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiss.model.order.enums.TradeNoTypeEnum;

/**
* 短信http接口的java代码调用示例
* 基于Apache HttpClient 4.3
*
* @author songchao
* @since 2015-04-03
*/
@Controller
@RequestMapping("/api")
public class JavaSmsApi {

    //查账户信息的http地址
    private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v2/user/get.json";

    //智能匹配模板发送接口的http地址
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v2/sms/single_send.json";

    //模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "https://sms.yunpian.com/v2/sms/tpl_single_send.json";

    //发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "https://voice.yunpian.com/v2/voice/send.json";

    //绑定主叫、被叫关系的接口http地址
    private static String URI_SEND_BIND = "https://call.yunpian.com/v2/call/bind.json";

    //解绑主叫、被叫关系的接口http地址
    private static String URI_SEND_UNBIND = "https://call.yunpian.com/v2/call/unbind.json";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    public static void main(String[] args) throws IOException, URISyntaxException {
    		JavaSmsApi sms = new JavaSmsApi();
    		sms.sendMemberMsg("17710052758", "豫A111111", "年", "2018-07-25");
    }
    
    @ResponseBody
    @RequestMapping("/sendMsg")
    public ModelMap sendMsg(String mobile, String pin, String tradeNo) throws IOException, URISyntaxException {
    	if (null == mobile || mobile.isEmpty() || null == pin || pin.isEmpty()) {
			return null;
		}
    	String apikey = "173d6d0d1d96a59d7a80530ee6c862c7";

        /**************** 查账户信息调用示例 *****************/
        System.out.println(JavaSmsApi.getUserInfo(apikey));

        /**************** 使用智能匹配模板接口发短信(推荐) *****************/
        //设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
        //发短信调用示例
        // System.out.println(JavaSmsApi.sendSms(apikey, text, mobile));

        /**************** 使用指定模板接口发短信(不推荐，建议使用智能匹配模板接口) *****************/
        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
        long tpl_id = 0;
        if (tradeNo.startsWith(TradeNoTypeEnum.WEEK.name())) {//7天体验运营活动
        	tpl_id = 1803554;
		} else if (tradeNo.startsWith(TradeNoTypeEnum.YEAR.name())){
			tpl_id = 1803552;
		}
        if (tpl_id == 0) 
        	return null;
        
        //设置对应的模板变量值

        String tpl_value = URLEncoder.encode("#code#",ENCODING) +"=" + URLEncoder.encode(pin, ENCODING);
        //模板发送的调用示例
        System.out.println("==============tpl_value=" + tpl_value);
        String result = JavaSmsApi.tplSendSms(apikey, tpl_id, tpl_value, mobile);
        System.out.println("===============sendmsg.result=" + result);
        ModelMap modelMap = new ModelMap("result", result);
    	return modelMap;
    }
    
    /**
     * 【嘻唰唰】尊敬的#licensed_id#的车主，您已经成功办理了嘻唰唰#kind#卡会员，会员有效期至#valid_at#！APP下载地址 http://t.cn/RXo4J7W 客服电话400-852-3369
     * */
    @ResponseBody
    @RequestMapping("/sendMemberMsg")
    public ModelMap sendMemberMsg(String mobile, String licensed_id, String kind, String valid_at) throws IOException, URISyntaxException {
    	if (null == licensed_id || licensed_id.isEmpty() || null == kind || kind.isEmpty() || null == valid_at || valid_at.isEmpty()) {
			return null;
		}
    	String apikey = "173d6d0d1d96a59d7a80530ee6c862c7";

        long tpl_id = 1884380;
        //设置对应的模板变量值
        StringBuilder tpl_value = new StringBuilder();
        tpl_value.append(URLEncoder.encode("#licensed_id#",ENCODING)).append("=").append(URLEncoder.encode(licensed_id, ENCODING));
        tpl_value.append("&").append(URLEncoder.encode("#kind#",ENCODING)).append("=").append(URLEncoder.encode(kind, ENCODING));
        tpl_value.append("&").append(URLEncoder.encode("#valid_at#",ENCODING)).append("=").append(URLEncoder.encode(valid_at, ENCODING));
        //模板发送的调用示例
        System.out.println("==============tpl_value=" + tpl_value);
        String result = JavaSmsApi.tplSendSms(apikey, tpl_id, tpl_value.toString(), mobile);
        System.out.println("===============sendmsg.result=" + result);
        ModelMap modelMap = new ModelMap("result", result);
    	return modelMap;
    }
    /**
     * 【嘻唰唰】欢迎来到彩虹谷，跟我们一起参加“嘻唰唰仲夏火人节狂欢夜”活动，您已选择#travel#方式，请妥善保管好此条短信，参与活动码：#code#，凭此入场参与狂欢夜。
     * */
    @ResponseBody
    @RequestMapping("/sendBurningManMsg")
    public ModelMap sendBurningManMsg(String mobile, String travel, String code) throws IOException, URISyntaxException {
    	if (null == travel || travel.isEmpty() || null == code || code.isEmpty()) {
			return null;
		}
    	String apikey = "173d6d0d1d96a59d7a80530ee6c862c7";

        long tpl_id = 1903192;
        //设置对应的模板变量值
        StringBuilder tpl_value = new StringBuilder();
        tpl_value.append(URLEncoder.encode("#travel#",ENCODING)).append("=").append(URLEncoder.encode(travel, ENCODING));
        tpl_value.append("&").append(URLEncoder.encode("#code#",ENCODING)).append("=").append(URLEncoder.encode(code, ENCODING));
        //模板发送的调用示例
        System.out.println("==============tpl_value=" + tpl_value);
        String result = JavaSmsApi.tplSendSms(apikey, tpl_id, tpl_value.toString(), mobile);
        System.out.println("===============sendmsg.result=" + result);
        ModelMap modelMap = new ModelMap("result", result);
    	return modelMap;
    }
    /**
    * 取账户信息
    *
    * @return json格式字符串
    * @throws java.io.IOException
    */

    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }

    /**
    * 智能匹配模板接口发短信
    *
    * @param apikey apikey
    * @param text   　短信内容
    * @param mobile 　接受的手机号
    * @return json格式字符串
    * @throws IOException
    */

    public static String sendSms(String apikey, String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }

    /**
    * 通过模板发送短信(不推荐)
    *
    * @param apikey    apikey
    * @param tpl_id    　模板id
    * @param tpl_value 　模板变量值
    * @param mobile    　接受的手机号
    * @return json格式字符串
    * @throws IOException
    */

    public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return post(URI_TPL_SEND_SMS, params);
    }

    /**
    * 通过接口发送语音验证码
    * @param apikey apikey
    * @param mobile 接收的手机号
    * @param code   验证码
    * @return
    */

    public static String sendVoice(String apikey, String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(URI_SEND_VOICE, params);
    }

    /**
    * 通过接口绑定主被叫号码
    * @param apikey apikey
    * @param from 主叫
    * @param to   被叫
    * @param duration 有效时长，单位：秒
    * @return
    */

    public static String bindCall(String apikey, String from, String to , Integer duration ) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("from", from);
        params.put("to", to);
        params.put("duration", String.valueOf(duration));
        return post(URI_SEND_BIND, params);
    }

    /**
    * 通过接口解绑绑定主被叫号码
    * @param apikey apikey
    * @param from 主叫
    * @param to   被叫
    * @return
    */
    public static String unbindCall(String apikey, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("from", from);
        params.put("to", to);
        return post(URI_SEND_UNBIND, params);
    }

    /**
    * 基于HttpClient 4.3的通用POST方法
    *
    * @param url       提交的URL
    * @param paramsMap 提交<参数，值>Map
    * @return 提交响应
    */

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
            try {
                HttpPost method = new HttpPost(url);
                if (paramsMap != null) {
                    List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                    for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                        NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                        paramList.add(pair);
                    }
                    method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
                }
                response = client.execute(method);
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseText = EntityUtils.toString(entity, ENCODING);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return responseText;
        }
}