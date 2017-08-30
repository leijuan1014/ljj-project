package com.xiss.service.shops;
/** 
* @author leijj
* @since  2017年4月11日 下午4:36:28 
*/

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.shops.DealsDao;

@Service
public class DealsService{

	@Autowired
	private DealsDao dealsDao;
	
	public List<Map<String, Object>> getCountByShopId(int shopId) {
		return dealsDao.getCountByShopId(shopId);
	}
	
	/**
	 * 根据商户id、查询月份查询详细洗车记录
	 * */
	public List<Map<String, Object>> getDealsByShopId(int shopId, String yearMonth,Integer pageNo, Integer pageSize) {
		return dealsDao.getDealsByShopId(shopId, yearMonth, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
		//return getPageInfoWithMap(dealsDao.getDealsByShopId(shopId, yearMonth), pageNo, pageSize);
	}
	
	public int getCarWashDealsCountByUserId(int shopId, String yearMonth){
		return dealsDao.getCarWashDealsCountByUserId(shopId, yearMonth);
	}
}
