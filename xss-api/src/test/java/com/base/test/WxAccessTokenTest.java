package com.base.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wx.Application;
import com.wx.base.bean.WxTicket;
import com.wx.operations.WxBaseOperations;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class WxAccessTokenTest {

	@Autowired
	private WxBaseOperations wxBaseOperations;

    @Test
    public void test() throws Exception {
    	WxTicket wxTicket = wxBaseOperations.getWxTicket("wx46b543acf6dbc7fa", "e7d006686de01c677fd218a7b53e5795");
    	System.out.println("=======wxTicket==" + wxTicket);
    }
}