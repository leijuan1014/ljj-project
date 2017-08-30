package com.xiss.dao.balances;
/** 
* @author leijj
* @since  2017年4月19日 上午10:57:56 
* TODO
*/

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.balances.Balances;

public interface BalancesDao {
	
	public Balances getById(int id);
	
	public Balances getByUserId(@Param("userId") int userId, @Param("rangeStart") String rangeStart, @Param("rangeEnd") String rangeEnd);
	
	public List<Map<String, Object>> balancesQuery(@Param("userId") Integer userId, @Param("start") String start, @Param("end") String end, @Param("province") String province, @Param("city") String city, @Param("name") String name, @Param("status") Integer status, @Param("limit") Integer limit, @Param("offset") Integer offset);
	
	public int balancesQueryTotal(@Param("userId") Integer userId, @Param("start") String start, @Param("end") String end, @Param("province") String province, @Param("city") String city, @Param("name") String name, @Param("status") int status);
	
	public int insert(Balances balances);
	
	public int updateMoney(@Param("id") int id, @Param("cardSaleMoney") Double cardSaleMoney, @Param("suiteSaleMoney") Double suiteSaleMoney, @Param("money")  Double money);
	
	/** 申请结算时间*/
	public void apply(@Param("id") int id, @Param("status") int status);
	
	/** 结算时间*/
	public int finish(@Param("id") int id, @Param("status") int status, @Param("receiptImage") String receiptImage);
	
	public int getCardDealsCountByUserId(@Param("sellerId") int sellerId, @Param("start") String start, @Param("end") String end);
	
	public List<Map<String, Object>> getCardDealsByUserId(@Param("sellerId") int sellerId, @Param("start") String start, @Param("end") String end, @Param("limit") Integer limit, @Param("offset") Integer offset);
}
