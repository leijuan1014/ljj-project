package com.xiss.dao.shops;
/** 
* @author leijj
* @since  2017年4月11日 下午4:35:41 
*/

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.shops.CardDeals;

public interface CardDealsDao {
	
	public int insert(CardDeals cardDeals);
	
	public int update(@Param("cardId") int cardId, @Param("tradeNo") String tradeNo, @Param("commission") double commission);
	
	public int updateBalanceId(@Param("id") int id, @Param("balanceId") int balanceId);

	public CardDeals getBySuiteOrderId(int suiteOrderId);
	
	public CardDeals getByTradeNo(String tradeNo);
	
	public List<CardDeals> getBySellerId(@Param("sellerId") int sellerId, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public List<Map<String, Object>> getTotalCommissionSellerId(@Param("sellerId") int sellerId, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public List<Map<String, Object>> getSellRecords(@Param("userId") int userId, @Param("balanceId") Integer balanceId, @Param("state") Integer state, @Param("start") String start, @Param("end")  String end, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public int getSellRecordCount(@Param("userId") int userId, @Param("balanceId") Integer balanceId, @Param("state") Integer state, @Param("start") String start, @Param("end")  String end);
	
	public List<Map<String, Object>> getAllCards(@Param("userId") int userId, @Param("start") String start, @Param("end")  String end, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public List<Map<String, Object>> getPaidCards(@Param("userId") int userId, @Param("start") String start, @Param("end")  String end, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public List<Map<String, Object>> getNoPaidCards(@Param("userId") int userId, @Param("start") String start, @Param("end")  String end, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public List<Map<String, Object>> getCardDealsByUserId(@Param("sellerId") int sellerId, @Param("start") String start, @Param("end")  String end, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public int getCardDealsCountByUserId(@Param("sellerId") int sellerId, @Param("start") String start, @Param("end")  String end);
	
	public double getCardDealsSumCommissionByUserId(@Param("sellerId") int sellerId, @Param("start") String start, @Param("end")  String end);
	
	public List<Map<String, Object>> sumCommissionGroupByUserId(@Param("start") String start, @Param("end")  String end);
}