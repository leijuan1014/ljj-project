package com.xiss.dao.shops;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.shops.UnionShop;

/** 
* @author leijj
* @since  2017年4月10日 下午4:56:30 
*/
public interface UnionShopsDao {
	public int save(UnionShop unionShop);
	
	public int updateStatus(@Param("id") int id, @Param("status") int status);
	
	public List<UnionShop> getUnionShops(@Param("status") Integer status, @Param("chooseLng") Double chooseLng, @Param("chooseLat") Double chooseLat, @Param("name") String name, @Param("category") String category, @Param("province") String province, @Param("city") String city, @Param("county") String county, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public UnionShop getById(int id);
	
	public UnionShop getByUserId(int userId);
}
