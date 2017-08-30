package com.wx.base.step;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wx.base.bean.WxTicket;
import com.wx.base.bean.WxTicketJsonCodec;
import com.wx.grizzly.BytesInputStream;
@Service
public class WxTicketStep extends WxStep{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public WxTicket run(String accessToken) {
		StringBuilder reqURL = new StringBuilder("https://api.weixin.qq.com/cgi-bin/ticket/getticket?");
		reqURL.append("type=jsapi");
		reqURL.append("&access_token=" + accessToken);
		
		WxTicket ticket = null;
		BytesInputStream result = runGet(reqURL.toString());
		if(result != null) {
			try {
				ticket = new WxTicketJsonCodec().decode(result);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
        return ticket;
	}
}
