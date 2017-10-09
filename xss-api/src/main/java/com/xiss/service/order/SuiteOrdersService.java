package com.xiss.service.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.order.SuiteOrdersDao;
import com.xiss.dao.order.SuitesDao;
//import com.xiss.dao.order.SuitesDao;
import com.xiss.dao.order.WaresDao;
import com.xiss.model.order.Coupon;
import com.xiss.model.order.CouponUser;
import com.xiss.model.order.PaymentInfo;
import com.xiss.model.order.SuiteOrders;
import com.xiss.model.order.Suites;
import com.xiss.model.order.Wares;
import com.xiss.model.order.enums.OrderState;
import com.xiss.model.order.enums.PaymentGateway;
import com.xiss.model.order.enums.PlatformEnum;
import com.xiss.model.system.Users;


/** 
* @author leijj
* @since  2017年4月18日 下午2:18:16 
*/
@Service
public class SuiteOrdersService {
	@Autowired
	private SuiteOrdersDao suiteOrdersDao;
	
	@Autowired
	private SuitesDao suiteDao;
	
	@Autowired
	private WaresDao waresDao;
	@Autowired
	private CouponsService couponsService;
	@Autowired
	private CouponUsersService couponUsersService;
	
	public void insert(SuiteOrders suiteOrders) {
		suiteOrdersDao.insert(suiteOrders);
	}
	
	public SuiteOrders saveOrder(Users users, Suites suites, Integer couponUserId, String deviceInfo, Integer quantity, String payType){
		double deductible = 0;
		if (couponUserId != null && couponUserId > 0) {
			CouponUser couponUser = couponUsersService.getById(couponUserId);
			if (couponUser != null && couponUser.getStatus() == 0) {
				Coupon coupon = couponsService.getById(couponUser.getCouponId());
				long now = new Date().getTime();
				if (coupon != null && coupon.getValidStart().getTime() < now && coupon.getValidEnd().getTime() > now) {
					deductible = coupon.getDeductible();
				}
			}
		}
		SuiteOrders suiteOrders = new SuiteOrders();
		suiteOrders.setUserId(users.getId());
		suiteOrders.setSuiteId(suites.getId());
		int couponId = 0;
		double price = suites.getSalePrice();
		if (suites.getSalePrice() - deductible > 0) {
			if (couponUserId != null) couponId = couponUserId;
			price = suites.getSalePrice() - deductible;
		}
		suiteOrders.setCouponId(couponId);
		suiteOrders.setPrice(price);
		if (null == quantity || quantity == 0)
			quantity = 1;
		
		suiteOrders.setQuantity(quantity);
		suiteOrders.setPaymentGateway(PaymentGateway.WECHATPAY.ordinal());
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String tradeNo = sf.format(new Date());
		/*
		if (TradeNoTypeEnum.WEEK.name().equals(payType)) {//周卡
			tradeNo = TradeNoTypeEnum.WEEK.name().concat(tradeNo);
		} else if (TradeNoTypeEnum.YEAR.name().equals(payType)) {//年卡
			tradeNo = TradeNoTypeEnum.YEAR.name().concat(tradeNo);
		} else if (TradeNoTypeEnum.BURNING_MAN.name().equals(payType)) {//火人节
			tradeNo = TradeNoTypeEnum.BURNING_MAN.name().concat(tradeNo);
		} else if(deviceInfo != null && !deviceInfo.isEmpty()){
			tradeNo = deviceInfo.concat(tradeNo);
		}
		*/
		if(payType != null && !payType.isEmpty()) {
			tradeNo = payType.concat(users.getMobile().substring(7)).concat(tradeNo);
		} else if(deviceInfo != null && !deviceInfo.isEmpty()){
			tradeNo = deviceInfo.concat(tradeNo);
		}
		suiteOrders.setTradeNo(tradeNo);
		if (null != deviceInfo) {
			if (PlatformEnum.ANDROID.name().equals(deviceInfo.toUpperCase())) {
				suiteOrders.setPlatform(PlatformEnum.ANDROID.ordinal());
			} else if (PlatformEnum.IOS.name().equals(deviceInfo.toUpperCase())) {
				suiteOrders.setPlatform(PlatformEnum.IOS.ordinal());
			}
		}
		suiteOrders.setState(OrderState.CREATED.ordinal());
		suiteOrdersDao.insert(suiteOrders);
		return suiteOrders;
	}
	public SuiteOrders getSuiteOrderById(int id) {
		return suiteOrdersDao.getById(id);
	}
	
	public SuiteOrders getSuiteOrderByTradeNo(String tradeNo){
		return suiteOrdersDao.getByTradeNo(tradeNo);
	}
	
	public Wares getWareById(int id) {
		return waresDao.getById(id);
	}
	public Suites getSuiteById(int id) {
		return suiteDao.getById(id);
	}
	/**
	 * 检查订单状态，如果是支付中（PAYING），则改为已支付（PAID），记录支付信息，及相关的账户记账。
	 * 其逻辑等同下列代码：
	 * <pre>
	 * Order order = orderOperations.setPaymentInfo(payInfo);
	 * if (order != null) {
	 *     // 更新订单状态
	 * }
	 * return order;
	 * </pre>
	 * 
	 * @param payInfo 支付信息
	 * @return 如果订单状态是支付中，状态修改后的订单，否则null
	 */
	public SuiteOrders suiteOrderPaid(PaymentInfo payInfo){
		suiteOrdersDao.suiteOrderPaid(payInfo.getOrderTradeNo(), payInfo.getMode().ordinal(), OrderState.SUCCESS.ordinal());
		return suiteOrdersDao.getByTradeNo(payInfo.getOrderTradeNo());
	}
	/**
	 * 检查订单状态，如果是支付中（PAYING），则改为已支付（PAID），并进行发货处理。
	 * 其逻辑等同于下列代码：
	 * <pre>
	 * Order order = orderPaid(payInfo);
	 * if (order != null)
	 *     return deliverOrderAfterPaid(payInfo.getOrderId());
	 * else
	 *     return false;
	 * </pre>
	 * 
	 * @param payInfo 支付信息
	 * @return 是否发货成功
	 */
	public boolean orderPaidAndDeliver(PaymentInfo payInfo){
		SuiteOrders suiteOrders = suiteOrderPaid(payInfo);
		System.out.println("======payInfo=" +payInfo+"==========orderPaidAndDeliver.suiteOrders=" + suiteOrders);
		if (suiteOrders !=null && OrderState.SUCCESS.ordinal() == suiteOrders.getState()) {
			return true;
		}
		return false;
	}
	
	public List<SuiteOrders> getByUserId(int userId, Integer state, Integer pageNo, Integer pageSize ) {
		return suiteOrdersDao.getByUserId(userId, state, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	
	public List<Map<String, Object>> getByShopUserToken(String shopUserToken, Integer state, Integer balanceId, Integer pageNo, Integer pageSize){
		return suiteOrdersDao.getByShopUserToken(shopUserToken, state, balanceId, pageSize != null && pageSize > 0 ? pageSize * (pageNo - 1) : null, pageSize);
	}
	
	public int updateBalanceId(int id, int balanceId) {
		return suiteOrdersDao.updateBalanceId(id, balanceId);
	}
	
	public int updateVerify(String tradeNo, int state) {
		return suiteOrdersDao.updateVerify(tradeNo, state);
	}
}
