package com.xiss.dao.shops;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/** 
* @author leijj
* @since  2017年4月11日 下午4:35:31 
*/
public interface DealsDao {

	/**
	 * 根据商户id查询洗车记录条数
	 * */
	public List<Map<String, Object>> getCountByShopId(int shopId);
	
	/**
	 * 根据商户id，月份查询洗车记录
	 * */
	public List<Map<String, Object>> getDealsByShopId(@Param("shopId") int shopId, @Param("yearMonth") String yearMonth, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	
	public int getCarWashDealsCountByUserId(@Param("shopId") int shopId, @Param("yearMonth") String yearMonth);
		
	
}
