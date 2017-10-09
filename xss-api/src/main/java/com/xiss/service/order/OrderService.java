package com.xiss.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiss.dao.order.OrderDao;
//import com.xiss.dao.order.SuitesDao;
import com.xiss.dao.order.WaresDao;
import com.xiss.model.order.Orders;
import com.xiss.model.order.PaymentInfo;
import com.xiss.model.order.Wares;
import com.xiss.model.order.enums.OrderState;


/** 
* @author leijj
* @since  2017年4月18日 下午2:18:16 
*/
@Service
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	
	//@Autowired
	//private SuitesDao suiteDao;
	@Autowired
	private WaresDao waresDao;
	
	public void insert(Orders order) {
		orderDao.insert(order);
	}
	
	public Orders getOrderById(long id) {
		return orderDao.getById(id);
	}
	
	public Orders getByTradeNo(String tradeNo){
		return orderDao.getByTradeNo(tradeNo);
	}
	
	public Wares getWareById(int id) {
		return waresDao.getById(id);
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
	public Orders orderPaid(PaymentInfo payInfo){
		orderDao.orderPaid(payInfo.getOrderTradeNo(), payInfo.getMode().ordinal(), payInfo.getAmount(), payInfo.getTime());
		return orderDao.getByTradeNo(payInfo.getOrderTradeNo());
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
		Orders order = orderPaid(payInfo);
		if (OrderState.SUCCESS.ordinal() == order.getStatus()) {
			return true;
		}
		return false;
	}
}
