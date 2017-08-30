package com.xiss.service.shops;
/** 
* @author leijj
* @since  2017年4月11日 下午4:35:59 
*/

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.shops.CardDealsDao;
import com.xiss.model.shops.CardDeals;

@Service
public class CardDealsService{

	@Autowired
	private CardDealsDao cardDealsDao;
	
	public int insert(CardDeals cardDeals) {
		return cardDealsDao.insert(cardDeals);
	}
	public int update(int cardId, String tradeNo, double commission) {
		return cardDealsDao.update(cardId, tradeNo, commission);
	}
	public int updateBalanceId(int id, int balanceId) {
		return cardDealsDao.updateBalanceId(id, balanceId);
	}
	public CardDeals getBySuiteOrderId(int suiteOrderId) {
		return cardDealsDao.getBySuiteOrderId(suiteOrderId);
	}
	public CardDeals getByTradeNo(String tradeNo) {
		return cardDealsDao.getByTradeNo(tradeNo);
	}
	public List<CardDeals> getBySellerId(int sellerId, Integer pageNo, Integer pageSize){
		return cardDealsDao.getBySellerId(sellerId, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	
	public List<Map<String, Object>> getTotalCommissionSellerId(int sellerId, Integer pageNo, Integer pageSize) {
		return cardDealsDao.getTotalCommissionSellerId(sellerId, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	
	public List<Map<String, Object>> getSellRecords(int userId, Integer balanceId, Integer state, String start, String end, Integer pageNo, Integer pageSize) {
		return cardDealsDao.getSellRecords(userId, balanceId, state, start, end, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	
	public int getSellRecordCount(int userId, Integer balanceId, Integer state, String start, String end) {
		return cardDealsDao.getSellRecordCount(userId, balanceId, state, start, end);
	}
	
	public List<Map<String, Object>> getAllCards(int userId, String start, String end, Integer pageNo, Integer pageSize) {
		return cardDealsDao.getAllCards(userId, start, end, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	
	public List<Map<String, Object>> getPaidCards(int userId, String start, String end, Integer pageNo, Integer pageSize) {
		return cardDealsDao.getPaidCards(userId, start, end, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	
	public List<Map<String, Object>> getNoPaidCards(int userId, String start, String end, Integer pageNo, Integer pageSize) {
		return cardDealsDao.getNoPaidCards(userId, start, end, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	/**
	 * 根据商户id、查询月份查询详细洗车记录
	 * */
	public List<Map<String, Object>> getCardDealsByUserId(int userId, String start, String end,Integer pageNo, Integer pageSize) {
		return cardDealsDao.getCardDealsByUserId(userId, start, end, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	public int getCardDealsCountByUserId(int userId, String start, String end){
		return cardDealsDao.getCardDealsCountByUserId(userId, start, end);
	}
	public double getCardDealsSumCommissionByUserId(int userId, String start, String end){
		return cardDealsDao.getCardDealsSumCommissionByUserId(userId, start, end);
	}
	/**
	 * 分商户查询提成总额
	 * */
	public List<Map<String, Object>> sumCommissionGroupByUserId(String start, String end){
		return cardDealsDao.sumCommissionGroupByUserId(start, end);
	}
}
