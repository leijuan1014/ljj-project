package com.xiss.service.shops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.shops.ShopsDao;
import com.xiss.model.shops.Shops;
import com.xiss.model.shops.UnionShop;


/** 
* @author leijj
* @since  2017年4月9日 下午4:14:41 
*/
@Service
public class ShopsService {
	@Autowired
	private ShopsDao shopsDao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public int save(Shops shops){
		return shopsDao.save(shops);
	}
	public int updateStatus(int id, int status){
		return shopsDao.updateStatus(id, status);
	}
	public Shops getById(int id) {
		return shopsDao.getById(id);
	}
	public Shops getByName(String name) {
		List<Shops> shops = getShops(null, name, null, null, null, null, null);
		if (shops != null && shops.size() > 0) {
			return shops.get(0);
		}
		return null;
	}
	
	public Shops getByUserId(int userId) {
		return shopsDao.getByUserId(userId);
	}
	
	public List<Shops> getShops(Integer status, String name, String province, String city, String county, Integer pageNo, Integer pageSize) {
		return shopsDao.getShops(status, name, province, city, county, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	/**
	 * 根据商户id、查询月份查询详细洗车记录
	 * */
	/*public PageInfo<Map<String, Object>> getCardDealsByUserId(int userId, String yearMonth,Integer pageNo, Integer pageSize) {
		return getPageInfoWithMap(shopsDao.getCardDealsByUserId(userId, yearMonth), pageNo, pageSize);
	}
	
	public int getCarWashDealsCountByUserId(int shopId, String yearMonth){
		return shopsDao.getCarWashDealsCountByUserId(shopId, yearMonth);
	}
	
	public int getCardDealsCountByUserId(int userId, String yearMonth){
		return shopsDao.getCardDealsCountByUserId(userId, yearMonth);
	}
	public double getCardDealsSumCommissionByUserId(int userId, String yearMonth){
		return shopsDao.getCardDealsSumCommissionByUserId(userId, yearMonth);
	}*/
}
