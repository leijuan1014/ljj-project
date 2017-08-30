package com.xiss.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.system.Users;

/** 
* @author leijj
* @since  2017年4月10日 下午4:56:30 
*/
public interface UsersDao {
	public int save(Users users);
	
	public int updateToken(@Param("mobile") String mobile, @Param("authenticationToken") String authenticationToken);
	
	public int updateInvited(@Param("mobile") String mobile, @Param("invitationToken") String invitationToken, @Param("invitedBy") int invitedBy);
	
	public int updateRoles(@Param("mobile") String mobile, @Param("roles") Integer roles);

	public List<Users> queryByPage(String name);
	
	public Users getById(int id);
	
	public Users getByMobile(String mobile);
	
	public Users getByToken(String authenticationToken);
	
}
