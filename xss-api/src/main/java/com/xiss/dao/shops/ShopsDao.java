package com.xiss.dao.shops;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.shops.Shops;

/** 
* @author leijj
* @since  2017年4月10日 下午4:56:30 
*/
public interface ShopsDao {
	public int save(Shops shops);

	public int updateStatus(@Param("id") int id, @Param("status") int status);
	
	public List<Shops> getShops(@Param("status") Integer status, @Param("name") String name, @Param("province") String province, @Param("city") String city, @Param("county") String county, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public Shops getById(int id);
	
	public Shops getByUserId(int userId);
}
