package com.xiss.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/** 
* @author leijj
* @since  2017年7月26日 下午2:40:21 
*/
public class GlobalProperties extends BasicProperties{
	public static String getFilterDomain(){
		return getPprVue().getProperty("filter.domain");
	}

	/**读取.properties 配置文件
	 * @return
	 * @throws IOException
	 */
	public static Properties getPprVue(){
		InputStream inputStream = BasicProperties.class.getClassLoader().getResourceAsStream("properties/global.properties");
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
		System.out.println(GlobalProperties.getFilterDomain());
	}
}
