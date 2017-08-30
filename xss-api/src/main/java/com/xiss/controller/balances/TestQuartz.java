package com.xiss.controller.balances;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author leijj
* @since  2017年4月20日 下午4:10:40 
*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
  
/** 
 * 测试定时器类 
 * @author javaw 
 * 
 */  
public class TestQuartz {  
        
       public static Logger logger = LoggerFactory.getLogger(TestQuartz.class);    
       public void TestMethod(){  
    	   SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
            logger.info("Auto Execute TestMethod start! Date={}", format.format(new Date()));  
            logger.info("**********测试跑批类************");  
            logger.info("Auto Execute TestMethod end! Date={}", format.format(new Date()))	;
      }  
}  
