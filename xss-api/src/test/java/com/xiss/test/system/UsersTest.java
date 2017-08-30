package com.xiss.test.system;

import org.springframework.beans.factory.annotation.Autowired;

import com.xiss.model.system.Users;
import com.xiss.service.system.UsersService;

/** 
* @author leijj
* @since  2017年5月18日 下午3:13:52 
*/
//@ContextConfiguration(locations = {"classpath:application.xml"})
public class UsersTest {//extends SpringTestCase{

	@Autowired
	private UsersService usersService;
	//@Test
	public void insertTest() {
		Users users = new Users();
		users.setEmail("13673669302");
		users.setMobile("13673669302");
		usersService.save(users);
	}
}
