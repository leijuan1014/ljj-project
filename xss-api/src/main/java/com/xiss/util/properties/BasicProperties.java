package com.xiss.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 
* @author leijj
* @since  2017年7月26日 下午2:43:25 
*/
public class BasicProperties {
	/**读取.properties 配置文件
	 * @return
	 * @throws IOException
	 */
	public static Properties getPprVue(){
		InputStream inputStream = BasicProperties.class.getClassLoader().getResourceAsStream("properties/upload.properties");
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

	/**读取redis.properties 配置文件
	 * @return
	 * @throws IOException
	 */
	public static Properties getPprVue(String property){
		InputStream inputStream = WxPayProperties.class.getClassLoader().getResourceAsStream(property);
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
}
