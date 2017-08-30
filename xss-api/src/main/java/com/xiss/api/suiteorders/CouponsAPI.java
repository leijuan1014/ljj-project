package com.xiss.api.suiteorders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiss.api.BasicAPI;
import com.xiss.model.order.Coupon;
import com.xiss.model.system.Users;
import com.xiss.service.order.CouponsService;
import com.xiss.service.system.UsersService;

/** 
* @author leijj
* @since  2017年6月5日 上午11:17:17 
*/
@Controller
@RequestMapping("/api/coupon")
public class CouponsAPI extends BasicAPI{
	
	@Autowired
	private UsersService usersService;
	@Autowired
	private CouponsService couponsService;
	
	@RequestMapping("/getCoupons")
	@ResponseBody
	private ModelMap getCoupons(String token) {
		ModelMap modelMap = new ModelMap();
		if (token == null || token.isEmpty()) 
			return getMessage(false, "传入参数错误", modelMap);
		
		Users user = usersService.getByToken(token);
		if (user == null) 
			return getMessage(false, "用户不存在", modelMap);
		/*可使用*/
		List<Coupon> validCoupons = couponsService.getCoupons(user.getId(), 0, true);
		/*已使用*/
		List<Coupon> usedCoupons = couponsService.getCoupons(user.getId(), 1, null);
		/*已过期*/
		List<Coupon> notValidCoupons = couponsService.getCoupons(user.getId(), 0, false);
		modelMap.put("validCoupons", validCoupons);
		modelMap.put("usedCoupons", usedCoupons);
		modelMap.put("notValidCoupons", notValidCoupons);
		return modelMap;
	}
	
	@RequestMapping("/getValidCoupons")
	@ResponseBody
	private ModelMap getValidCoupons(String token) {
		ModelMap modelMap = new ModelMap();
		if (token == null || token.isEmpty()) 
			return getMessage(false, "传入参数错误", modelMap);
		
		Users user = usersService.getByToken(token);
		if (user == null) 
			return getMessage(false, "用户不存在", modelMap);
		/*可使用*/
		List<Coupon> validCoupons = couponsService.getCoupons(user.getId(), 0, true);
		modelMap.put("validCoupons", validCoupons);
		return modelMap;
	}
}
