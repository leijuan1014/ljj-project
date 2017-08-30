package com.xiss.test.orders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wx.Application;
import com.wx.base.bean.WxTicket;
import com.wx.operations.WxBaseOperations;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class WxAccessTokenTest {

	@Autowired
	private WxBaseOperations wxBaseOperations;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Autowired
	private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
    	//WxAccessToken accessToken = wxBaseOperations.getAccessToken("wx46b543acf6dbc7fa", "e7d006686de01c677fd218a7b53e5795");
    	//System.out.println(accessToken);
    	WxTicket wxTicket = wxBaseOperations.getWxTicket("wx46b543acf6dbc7fa", "e7d006686de01c677fd218a7b53e5795");
    	System.out.println(wxTicket);
    	/*
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, 360 - 60);
        stringRedisTemplate.opsForValue().set("aaa1", "111", 360, TimeUnit.SECONDS);//.set("aaa1", "111",);
        Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa1"));
        */
    }
}