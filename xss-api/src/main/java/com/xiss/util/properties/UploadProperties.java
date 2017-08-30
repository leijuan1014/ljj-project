package com.xiss.util.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/** 
* @author leijj
* @since  2017年5月4日 下午6:21:09 
*/
public class UploadProperties {
	public static String getBasicImgPath(){
		return getPprVue().getProperty("basedir");
	}
	public static String getBasicImgBrowse(){
		return getPprVue().getProperty("baseurl");
	}
	public static String getShopImgPath(){
		return getBasicImgPath() + getPprVue().getProperty("shop.image.path");
	}
	public static String getShopImgBrowse(){
		return getBasicImgBrowse() + getPprVue().getProperty("shop.image.browse");
	}
	public static String getShopImgDetailPath(){
		return getBasicImgPath() + getPprVue().getProperty("shop.image.detail.path");
	}
	public static String getShopImgDetailBrowse(){
		return getBasicImgBrowse() + getPprVue().getProperty("shop.image.detail.browse");
	}
	public static String getUnionshopImgPath(){
		return getBasicImgPath() + getPprVue().getProperty("unionshop.image.path");
	}
	public static String getUnionshopImgBrowse(){
		return getBasicImgBrowse() + getPprVue().getProperty("unionshop.image.browse");
	}
	public static String getUnionshopImgDetailPath(){
		return getBasicImgPath() + getPprVue().getProperty("unionshop.image.detail.path");
	}
	public static String getUnionshopImgDetailBrowse(){
		return getBasicImgBrowse() + getPprVue().getProperty("unionshop.image.detail.browse");
	}
	/**读取.properties 配置文件
	 * @return
	 * @throws IOException
	 */
	public static Properties getPprVue(){
		InputStream inputStream = UploadProperties.class.getClassLoader().getResourceAsStream("properties/upload.properties");
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
		System.out.println(UploadProperties.getUnionshopImgPath());
	}
}
