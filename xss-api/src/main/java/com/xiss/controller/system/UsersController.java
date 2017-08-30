package com.xiss.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiss.model.system.Users;
import com.xiss.service.system.UsersService;

/** 
* @author leijj
* @since  2017年4月11日 下午2:11:50 
*/
@Controller
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@RequestMapping("/getById/{id}")
	@ResponseBody
	public ModelMap getUsersById(@PathVariable int id) {
		ModelMap model = new ModelMap();
		Users users = usersService.getById(id);
		model.put("users", users);
		return model;
	}
	
	@RequestMapping("/getByMobile/{mobile}")
	@ResponseBody
	public ModelMap getByMobile(@PathVariable String mobile) {
		ModelMap model = new ModelMap();
		Users users = usersService.getByMobile(mobile);
		model.put("users", users);
		return model;
	}
}
