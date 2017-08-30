package com.xiss.service.shops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.shops.UnionShopsDao;
import com.xiss.model.shops.UnionShop;


/** 
* @author leijj
* @since  2017年4月9日 下午4:14:41 
*/
@Service
public class UnionShopsService {
	@Autowired
	private UnionShopsDao unionShopsDao;
	
	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public int save(UnionShop unionShop){
		return unionShopsDao.save(unionShop);
	}
	public int updateStatus(int id, int status){
		return unionShopsDao.updateStatus(id, status);
	}
	public List<UnionShop> getUnionShops(Integer status, Double chooseLng, Double chooseLat, String name, String category, String province, String city, String county, Integer pageNo, Integer pageSize) {
		return unionShopsDao.getUnionShops(status, chooseLng, chooseLat, name, category, province, city, county, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	public UnionShop getById(int id) {
		return unionShopsDao.getById(id);
	}
	public UnionShop getByUserId(int userId) {
		return unionShopsDao.getByUserId(userId);
	}
}
