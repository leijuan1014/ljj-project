package com.wx.base.step;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wx.grizzly.BytesInputStream;

public class WxStep {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 微信get请求公共方法
	 * @param reqUrl	请求URL
	 * */
	public BytesInputStream runGet(String reqUrl) {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse httpResponse = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(reqUrl);
	        httpResponse = httpclient.execute(httpGet);
	        HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
            	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
    			StringBuilder content = new StringBuilder();
    			String text;
    			while ((text = bufferedReader.readLine()) != null)
    				content.append(text);
                
    			bufferedReader.close();
    			return new BytesInputStream(content.toString());
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
        	logger.error(e.getMessage());
        } finally {
            try {
            	if(httpResponse != null) httpResponse.close();
            	if(httpclient != null) httpclient.close();
            } catch (IOException e) {
            	logger.error(e.getMessage());
            }
        }
        return null;
	}
	/**
	 * 微信post请求公共方法
	 * @param reqUrl	请求URL
	 * @param params	请求参数
	 * */
	public BytesInputStream runPost(String reqUrl, String params) {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse httpResponse = null;
        try {
            httpclient = HttpClients.createDefault();
            HttpPost post = new HttpPost(reqUrl);
	        post.setEntity(new ByteArrayEntity(params.getBytes()));
	        httpResponse = httpclient.execute(post);
	        HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
            	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
    			StringBuilder content = new StringBuilder();
    			String text;
    			while ((text = bufferedReader.readLine()) != null)
    				content.append(text);
                
    			bufferedReader.close();
    			return new BytesInputStream(content.toString());
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
        	logger.error(e.getMessage());
        } finally {
            try {
            	if(httpResponse != null) httpResponse.close();
            	if(httpclient != null) httpclient.close();
            } catch (IOException e) {
            	logger.error(e.getMessage());
            }
        }
        return null;
	}
}
