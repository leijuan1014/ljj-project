package com.xiss.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiss.dao.system.UsersDao;
import com.xiss.model.system.Users;
import com.xiss.util.IdMangler;


/** 
* @author leijj
* @since  2017-4-9
*/
@Service
public class UsersService {
	@Autowired
	private UsersDao usersDao;
	public static void main(String[] args) {
		//PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
		System.out.println(IdMangler.mangle(17710052758l));
		//	umzK2cMC4xwuUrQupSs5
		//  GDQwTMuNZW6Z77k9HAQkHA
		//  BAQAbouNu34IICwg
	}
	/**
	 * @param pd
	 * @throws Exception
	 */
	public int save(Users users) {
		//TODO 默认密码为123456
		if(users.getEncryptedPassword() == null || users.getEncryptedPassword().isEmpty()) 
			users.setEncryptedPassword("123456");
		
		String authenticationToken = String.valueOf(IdMangler.mangle(Long.valueOf(users.getMobile())));
		System.out.println("=====authenticationToken=" + authenticationToken);
		users.setAuthenticationToken(authenticationToken);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
		users.setEncryptedPassword(passwordEncoder.encode(users.getEncryptedPassword()));		
		return usersDao.save(users);
	}
	public int updateInvited(String mobile, String invitationToken, int invitedBy) {
		return usersDao.updateInvited(mobile, invitationToken, invitedBy);
	}
	
	public int updateRoles(String mobile, int roles) {
		return usersDao.updateRoles(mobile, roles);
	}
	public PageInfo<Users> queryByPage(Integer pageNo, Integer pageSize) {
		pageNo = pageNo == null?1:pageNo;
		pageSize = pageSize == null?10:pageSize;
		PageHelper.startPage(pageNo, pageSize);
		List<Users> list = usersDao.queryByPage("");
		PageInfo<Users> page = new PageInfo<Users>(list);
		return page;
	}
	public Users getById(int id) {
		return usersDao.getById(id);
	}
	public Users getByMobile(String mobile) {
		return usersDao.getByMobile(mobile);
	}
	
	public Users getByToken(String token) {
		return usersDao.getByToken(token);
	}
}
