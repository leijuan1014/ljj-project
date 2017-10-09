package com.xiss.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xiss.model.order.SuiteOrders;

/** 
* @author leijj
* @since  2017年4月18日 下午4:32:03 
*/
public interface SuiteOrdersDao {
	
	public int insert(SuiteOrders suiteOrders);
	
	public SuiteOrders getById(Integer id);
	
	public SuiteOrders getByTradeNo(String tradeNo);
	
	public List<SuiteOrders> getByUserId(@Param("userId") int userId, @Param("state") int state, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public List<Map<String, Object>> getByShopUserToken(@Param("shopUserToken") String shopUserToken, @Param("state") Integer state, @Param("balanceId") Integer balanceId, @Param("offset") Integer offset, @Param("limit") Integer limit);
	
	public void suiteOrderPaid(@Param("tradeNo") String tradeNo, @Param("paymentGateway") int paymentGateway, @Param("state") int state);

	public int updateBalanceId(@Param("id") int id, @Param("balanceId") int balanceId);
	
	public int updateVerify(@Param("tradeNo") String tradeNo, @Param("state") int state);
}