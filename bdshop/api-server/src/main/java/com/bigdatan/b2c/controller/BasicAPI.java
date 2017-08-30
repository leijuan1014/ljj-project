package com.bigdatan.b2c.controller;

import org.springframework.ui.ModelMap;

/** 
* @author leijj
* @since  2017年6月5日 下午2:33:24 
*/
public class BasicAPI {
	public ModelMap getMessage(boolean success, String message, ModelMap modelMap) {
		modelMap.put("success", success);
		modelMap.put("message", message);
		return modelMap;
	}
	public ModelMap getCodeMessage(boolean code, String message, ModelMap modelMap) {
		modelMap.put("success", code);
		modelMap.put("return_code", code);
		modelMap.put("message", message);
		return modelMap;
	}
}
