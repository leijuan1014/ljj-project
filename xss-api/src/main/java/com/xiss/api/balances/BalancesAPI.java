package com.xiss.api.balances;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiss.model.balances.Balances;
import com.xiss.model.balances.enums.BalanceStatus;
import com.xiss.model.order.SuiteOrders;
import com.xiss.model.order.Suites;
import com.xiss.model.shops.Shops;
import com.xiss.service.balances.BalancesService;
import com.xiss.service.order.SuiteOrdersService;
import com.xiss.service.order.SuitesService;
import com.xiss.service.shops.ShopsService;
import com.xiss.util.DateUtil;

/** 
* @author leijj
* @since  2017年4月19日 上午10:58:09 
*/
@Controller
@RequestMapping("/api/balances")
public class BalancesAPI {
	@Autowired
	private BalancesService balancesService;
	@Autowired
	private SuitesService suitesService;
	@Autowired
	private ShopsService shopsService;
	@Autowired
	private SuiteOrdersService suiteOrdersService;
	
	/**提现申请*/
	@RequestMapping("/apply")
	@ResponseBody
	public ModelMap apply(int id) {
		ModelMap modelMap = new ModelMap();
		balancesService.apply(id, BalanceStatus.WITHDRAWING.ordinal());
		Balances balances = balancesService.getById(id);
		if (balances.getStatus() > 0) {
			modelMap.put("success", true);
			modelMap.put("message", "success");
		} else {
			modelMap.put("success", false);
			modelMap.put("message", "false");
		}
		return modelMap;
	}
	
	/**
	 * 保存修改结算单
	 * */
	@RequestMapping("/save")
	@ResponseBody
	private ModelMap saveBalance(String tradeNo) {
		ModelMap modelMap = new ModelMap();
		SuiteOrders suiteOrders = suiteOrdersService.getSuiteOrderByTradeNo(tradeNo);
		if (suiteOrders == null) {
			modelMap.put("success", false);
			modelMap.put("message", "订单不存在");
			return modelMap;
		} else if(suiteOrders.getBalanceId() > 0) {
			modelMap.put("success", false);
			modelMap.put("message", "已更新到结算单");
			return modelMap;
		}
		int userId = 0;
		Suites suites = suitesService.getSuiteById(suiteOrders.getSuiteId());
		if(suites != null){
			Shops shops = shopsService.getById(suites.getShopId());
			userId = shops.getUserId();
		}
		Date now = new Date();
		String start = DateUtil.getCurWeekStart(now);
		String end = DateUtil.getWeekEndDate(now);
		String next = DateUtil.getNextWeekStart(now);
		Balances balances = balancesService.getByUserId(userId, start, next);
		double suiteSaleMoney = suiteOrders.getPrice();
		//BigDecimal suiteSaleMoney = new BigDecimal(suiteOrders.getPrice());
		//BigDecimal money = new BigDecimal(0);
		if (suiteOrders.getQuantity() > 0) {
			suiteSaleMoney = suiteSaleMoney*suiteOrders.getQuantity();//suiteSaleMoney.multiply(new BigDecimal(suiteOrders.getQuantity()));
		}
		if (balances == null) {
			balances = new Balances();
			balances.setSuiteSaleMoney(suiteSaleMoney);
			balances.setMoney(suiteSaleMoney);
			balances.setStatus(BalanceStatus.WITHDRAW_INIT.ordinal());
			balances.setUserId(userId);
			balances.setRangeStart(DateUtil.fomatDate(start));
			balances.setRangeEnd(DateUtil.fomatDate(end));
			balancesService.insert(balances);
			suiteOrdersService.updateBalanceId(suiteOrders.getId(), balances.getId());
		} else {
			if (balances.getStatus() == BalanceStatus.WITHDRAW_INIT.ordinal())  {
				double money = balances.getMoney() + suiteSaleMoney;
				suiteSaleMoney = balances.getSuiteSaleMoney() + suiteSaleMoney;
				balancesService.updateMoney(balances.getId(), null, suiteSaleMoney, money);
				suiteOrdersService.updateBalanceId(suiteOrders.getId(), balances.getId());
			}
		}
		modelMap.put("success", true);
		return modelMap;
	}
}
