package com.xiss.api.balances;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xiss.model.balances.Balances;
import com.xiss.model.balances.enums.BalanceStatus;
import com.xiss.model.order.SuiteOrders;
import com.xiss.model.order.enums.OrderState;
import com.xiss.model.shops.CardDeals;
import com.xiss.service.balances.BalancesService;
import com.xiss.service.order.SuiteOrdersService;
import com.xiss.service.shops.CardDealsService;
import com.xiss.util.DateUtil;

/** 
* @author leijj
* @since  2017年4月20日 下午4:03:50 
*/
@Controller
@RequestMapping("/test")
public class BalancesJobs {
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private CardDealsService cardDealsService;
	@Autowired
	private SuiteOrdersService suiteOrdersService;
	@Autowired
	private BalancesService balancesService;
	
	/**
	 * 每周一定时计算上周售卡提成方法入口
	 * */
	public void insertBalance() throws ParseException{
		System.out.println("=====执行我====" + DateUtil.getTime());
		String start = DateUtil.getAfterDayDateYMD(-7).concat(" 00:00:00");
		String end = DateUtil.getAfterDayDateYMD(-1).concat(" 00:00:00");
		saveBalance(start, end);
	}
	
	@RequestMapping("/saveOneBalance")
	public void saveOneBalance(int suiteOrderId) throws ParseException {
		SuiteOrders suiteOrders = suiteOrdersService.getSuiteOrderById(suiteOrderId);
		CardDeals cardDeals = cardDealsService.getBySuiteOrderId(suiteOrderId);
		if (suiteOrders != null && suiteOrders.getState() == OrderState.SUCCESS.ordinal() 
				&& cardDeals != null && cardDeals.getCommission() > 0) {
			Balances balances = new Balances();
			balances.setMoney(cardDeals.getCommission());
			balances.setStatus(BalanceStatus.WITHOUTDRAW.ordinal());
			balances.setUserId(cardDeals.getSellerId());
			balances.setRangeStart(cardDeals.getUpdatedAt());
			balances.setRangeEnd(cardDeals.getUpdatedAt());
			balancesService.insert(balances);
		}
	}
	
	@RequestMapping("/saveBalance")
	public void saveBalance(String start, String end) throws ParseException {
		List<Map<String, Object>> list = cardDealsService.sumCommissionGroupByUserId(start, end);
		System.out.println("==========list=" + list);
		if (list != null && list.size() > 0) {
			for (Map<String, Object> map : list) {
				System.out.println("======map=" + map);
				if (map != null) {
					int sellerId = (Integer) map.get("sellerId");
					double sumCommission = (Double) map.get("sumCommission");
					System.out.println("======sellerId=" + sellerId + ",sumCommission=" + sumCommission);
					Balances balances = balancesService.getByUserId(sellerId, start, end);
					if (balances == null) {
						balances = new Balances();
						balances.setCardSaleMoney(sumCommission);
						balances.setMoney(sumCommission);
						balances.setStatus(BalanceStatus.WITHOUTDRAW.ordinal());
						balances.setUserId(sellerId);
						balances.setRangeStart(sdfTime.parse(start));
						balances.setRangeEnd(sdfTime.parse(end));
						balancesService.insert(balances);
					}
				}
			}
		}
	}
	public static void main(String[] args) throws ParseException {
		System.out.println(sdfTime.parse("2017-05-10"));
	}
}