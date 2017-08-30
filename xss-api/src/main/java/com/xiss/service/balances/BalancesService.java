package com.xiss.service.balances;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.balances.BalancesDao;
import com.xiss.model.balances.Balances;

/** 
* @author leijj
* @since  2017年4月19日 上午10:57:47 
*/
@Service
public class BalancesService {
	@Autowired
	private BalancesDao balancesDao;
	
	public Balances getById(int id){
		return balancesDao.getById(id);
	}
	
	public Balances getByUserId(int userId, String rangeStart, String rangeEnd){
		return balancesDao.getByUserId(userId, rangeStart, rangeEnd);
	}
	
	public int balancesQueryTotal(Integer userId, String start, String end, String province, String county, String name, int status) {
		return balancesDao.balancesQueryTotal(userId, start, end, province, county, name, status);
	}
	
	public List<Map<String, Object>> balancesQueryByUserId(Integer userId) {
		return balancesQuery(userId, null, null, null, null, null, null, null, null);
	}
	
	public List<Map<String, Object>> balancesQuery(Integer userId, String start, String end, String province, String county, String name, Integer status, Integer limit, Integer offset) {
		return balancesDao.balancesQuery(userId, start, end, province, county, name, status, limit, offset);
	}

	public int getCardDealsCountByUserId(int sellerId, String start, String end){
		return balancesDao.getCardDealsCountByUserId(sellerId, start, end);
	}
	
	public List<Map<String, Object>> getCardDealsByUserId(int sellerId, String start, String end, Integer limit, Integer offset){
		return balancesDao.getCardDealsByUserId(sellerId, start, end, limit, offset);
	}
	
	public int insert(Balances balances) {
		return balancesDao.insert(balances);
	}
	public int updateMoney(int id, Double cardSaleMoney, Double suiteSaleMoney,  Double money) {
		return balancesDao.updateMoney(id,  cardSaleMoney, suiteSaleMoney,  money);
	}
	/** 申请提现*/
	public void apply(int id, int status) {
		balancesDao.apply(id, status);
	}
	
	/** 结算处理*/
	public void finish(int id, int status, String receiptImage) {
		balancesDao.finish(id, status, receiptImage);
	}
}