package com.xiss.api.shops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiss.api.BasicAPI;
import com.xiss.model.shops.UnionShop;
import com.xiss.model.shops.enums.ShopStatusEnum;
import com.xiss.model.system.RolesEnum;
import com.xiss.model.system.Users;
import com.xiss.service.shops.UnionShopsService;
import com.xiss.service.system.UsersService;
/** 
* @author leijj
* @since  2017年6月6日 下午4:41:29 
*/
@Controller
@RequestMapping("/api/unionShops")
public class UnionShopsAPI extends BasicAPI{
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private UnionShopsService unionShopsService;
	
	/**
	 * @param chooseLng 用户经度 可空
	 * @param chooseLat 用户纬度 可空
	 * @param name 商户名称搜索条件 可空
	 * @param category 商户分类搜索条件 可空
	 * @param province 省
	 * @param city 市
	 * @param county 区
	 * @param pageNo 当前页码 可空
	 * @param pageSize 查询条数 可空
	 * */
	@RequestMapping("/list")
	@ResponseBody
	public List<UnionShop> getUnionShops(Double chooseLng, Double chooseLat, String name, String category, String province, String city, String county, Integer pageNo, Integer pageSize) {
		if ("全部".equals(category)) category = null;
		List<UnionShop> unionShops = unionShopsService.getUnionShops(ShopStatusEnum.UPLINE.ordinal(), chooseLng, chooseLat, name, category, province, city, county, pageNo, pageSize);
		return unionShops;
	}
	
	@RequestMapping("/all")
	@ResponseBody
	public List<UnionShop> getUnionShops(String type, String name, String category, String province, String city, String county, Integer pageNo, Integer pageSize) {
		if ("全部".equals(category)) category = null;
		Integer status = null;
		if (type != null && !type.isEmpty()) {
			ShopStatusEnum statusEnum = ShopStatusEnum.get(type);
			status = statusEnum.ordinal();
		}
		
		List<UnionShop> unionShops = unionShopsService.getUnionShops(status, null, null, name, category, province, city, county, pageNo, pageSize);
		return unionShops;
	}
	@RequestMapping("/save")
	@ResponseBody
	public ModelMap saveUnionShop(String mobile, String name, String phone, String province, String city, String county, Integer star, String category, Integer status, String profile, String openning, String address, String longitude, String latitude, String image) {
		ModelMap model = new ModelMap();
		if(mobile == null || mobile.isEmpty()
				|| name == null || name.isEmpty())
			return getMessage(false, "参数错误", model); 
		
		Users user = usersService.getByMobile(mobile);
		if (user == null) {
			user = new Users(mobile, mobile, RolesEnum.SHOP_OWNER.ordinal());
			usersService.save(user);
			System.out.println("=====user=" + user);
		}
		if(user == null || user.getId() <= 0)
			return getMessage(false, "创建账号失败", model); 
		UnionShop unionShop = unionShopsService.getByUserId(user.getId());
		if(unionShop != null && unionShop.getId() > 0)
			return getMessage(false, "该手机号码下已有联盟商家", model); 
		unionShop = new UnionShop(user.getId(), name, phone, province, city, county, star, category, status, profile, openning, address, longitude, latitude, image, image);
		unionShopsService.save(unionShop);
		System.out.println("=======unionShop=" + unionShop);
		if(unionShop != null && unionShop.getId() > 0)
			return getMessage(true, "创建联盟商家成功", model); 
		else
			return getMessage(false, "创建联盟商家失败", model); 
	}
	
	@RequestMapping("/getById")
	@ResponseBody
	public ModelMap getById(Integer id) {
		ModelMap model = new ModelMap();
		if(id == null)
			return getMessage(false, "参数错误", model); 
		
		UnionShop unionShop = unionShopsService.getById(id);
		model.put("success", true);
		model.put("unionShop", unionShop);
		return model;
	}
	
	@RequestMapping("/updateStatus")
	@ResponseBody
	public ModelMap updateStatus(Integer id, String type) {
		ModelMap model = new ModelMap();
		if(id == null)
			return getMessage(false, "参数错误", model);
		
		ShopStatusEnum statusEnum = ShopStatusEnum.get(type);
		if(statusEnum == null)
			return getMessage(false, "传入的状态类型错误", model);
		
		int status = statusEnum.ordinal();
		unionShopsService.updateStatus(id, status);
		
		return getMessage(true, "状态修改成功", model);
	}
}
