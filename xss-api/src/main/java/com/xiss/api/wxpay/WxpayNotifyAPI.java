package com.xiss.api.wxpay;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.xiss.api.system.JavaSmsApi;
import com.xiss.model.balances.Balances;
import com.xiss.model.balances.enums.BalanceStatus;
import com.xiss.model.order.GrowingUsers;
import com.xiss.model.order.PaymentInfo;
import com.xiss.model.order.SuiteOrders;
import com.xiss.model.order.enums.PaymentGateway;
import com.xiss.model.order.enums.TradeNoTypeEnum;
import com.xiss.model.shops.CardDeals;
import com.xiss.model.shops.Cards;
import com.xiss.model.shops.Shops;
import com.xiss.model.system.Car;
import com.xiss.model.system.Users;
import com.xiss.model.wxpay.BankType;
import com.xiss.model.wxpay.WxpayAccount;
import com.xiss.model.wxpay.WxpayCoupon;
import com.xiss.model.wxpay.WxpayOrder;
import com.xiss.model.wxpay.WxpayQueryOrderResult;
import com.xiss.model.wxpay.WxpaySignature;
import com.xiss.model.wxpay.enums.TradeTypeEnum;
import com.xiss.service.balances.BalancesService;
import com.xiss.service.order.GrowingUsersService;
import com.xiss.service.order.SuiteOrdersService;
import com.xiss.service.shops.CardDealsService;
import com.xiss.service.shops.CardsService;
import com.xiss.service.shops.ShopsService;
import com.xiss.service.system.CarsService;
import com.xiss.service.system.UsersService;
import com.xiss.service.wxpay.WxPayOperations;
import com.xiss.service.wxpay.api.WxpayStep;
import com.xiss.util.DateUtil;
import com.xiss.util.RandomNumberGenerator;
import com.xiss.util.properties.WxPayProperties;

/** 
* @author leijj
* @since  2017年4月18日 下午2:43:07 
*/
@Controller
@RequestMapping("/api/wxpay")
public class WxpayNotifyAPI{
	protected static final XMLOutputFactory XOF = new WstxOutputFactory();
	protected static final XMLInputFactory XIF = new WstxInputFactory();
	
	@Autowired
	private WxPayOperations wxpayOperations;
	@Autowired
	private BalancesService balancesService;
	@Autowired
	private CardDealsService cardDealsService;
	@Autowired
	private UsersService usersService;
	@Autowired
	private ShopsService shopsService;
	@Autowired
	private CardsService cardsService;
	@Autowired
	private GrowingUsersService growingUsersService;
	@Autowired
	private CarsService carsService;
	
	public void notify1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("======我是微信支付回调");
		 String inputLine;
	        String notityXml = "";

	        try {
	            while ((inputLine = req.getReader().readLine()) != null) {
	                notityXml += inputLine;
	            }
	            req.getReader().close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }


	        System.out.println("接收到的报文：" + notityXml);
	        sendResponse(resp, "SUCCESS", "成功");
	}
	
	@Autowired
	private SuiteOrdersService suiteOrdersService;

	@RequestMapping("/cardDealJsapiNotify")
	private void cardDealJsapiNotify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WxpayAccount account = WxPayProperties.getJsapiWxpayAccount();
		WxpayOrder order = notify(req, resp, account);
		if(order != null){
			jsapiAfter(order);
			sendResponse(resp, "SUCCESS", "成功");
		} else {
			sendResponse(resp, "FAIL", "微信订单不存在");
		}
	}
	@RequestMapping("/suiteOrderAppNotify")
	private void suiteOrderAppNotify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WxpayAccount account = WxPayProperties.getAppWxpayAccount();
		WxpayOrder order = notify(req, resp, account);
		if(order != null){
			sendResponse(resp, "SUCCESS", "成功");
		} else {
			sendResponse(resp, "FAIL", "微信订单不存在");
		}
	}
	@RequestMapping("/memberJsapiNotify")
	private void memberJsapiNotify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("=======JS支付回调");
		WxpayAccount account = WxPayProperties.getJsapiWxpayAccount();
		WxpayOrder order = notify(req, resp, account);
		if(order != null){
			sendResponse(resp, "SUCCESS", "成功");
		} else {
			sendResponse(resp, "FAIL", "微信订单不存在");
		}
	}
	@RequestMapping("/memberWxAppNotify")
	private void memberWxAppNotify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("=======微信小程序支付回调");
		WxpayAccount account = WxPayProperties.getWxappWxpayAccount();
		WxpayOrder order = notify(req, resp, account);
		if(order != null){
			afterMember(order.getAttach());
			sendResponse(resp, "SUCCESS", "成功");
		} else {
			sendResponse(resp, "FAIL", "微信订单不存在");
		}
	}
	@RequestMapping("/memberAppNotify")
	private void memberAppNotify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("=======APP支付回调");
		WxpayAccount account = WxPayProperties.getAppWxpayAccount();
		WxpayOrder order = notify(req, resp, account);
		if(order != null){
			afterMember(order.getAttach());
			sendResponse(resp, "SUCCESS", "成功");
		} else {
			sendResponse(resp, "FAIL", "微信订单不存在");
		}
	}
	private void afterMember(String attach) {
		//1.增加会员有效期
		if (attach != null && !attach.isEmpty()) {
			JSONObject attachObj = JSONObject.parseObject(attach);
			String licensedId = attachObj.getString("licensedId");
			if (licensedId != null && !licensedId.isEmpty()) {
				Car car = carsService.getByLicensedId(licensedId);
				if (car != null) {
					Date validAt = car.getValidAt();
					if (validAt == null) {
						validAt = new Date();
					}
					String valid = DateUtil.getAfterYearDateYMD(1, validAt);
					System.out.println("======valid=" + valid);
					carsService.updateValid(licensedId, valid);
				}
			}
		}
	}
	private WxpayOrder notify(HttpServletRequest req, HttpServletResponse resp, WxpayAccount account) throws ServletException, IOException {
		String returnCode = null, returnMsg = null;

		// 解析通知内容
		String resultCode = null;
		WxpayQueryOrderResult qr = new WxpayQueryOrderResult();
		WxpaySignature signature = new WxpaySignature();
		String appId = null, merchantId = null, sign = null;
		try {
			XMLStreamReader reader = null;
			try {
				reader = XIF.createXMLStreamReader(req.getInputStream());
				// 游标移到XML文档的根元素
				reader.nextTag();
				
				System.out.println("========reader.getEventType()=" + reader.getEventType());
				if (reader.getEventType() != XMLStreamConstants.START_ELEMENT)
					throw new XMLStreamException("Unexpected event type " + reader.getEventType(), reader.getLocation());

				Map<Integer, WxpayCoupon> coupons = new TreeMap<Integer, WxpayCoupon>();
				while (reader.nextTag() == XMLStreamConstants.START_ELEMENT) {
					String name = reader.getLocalName();
					String value = reader.getElementText().trim();
					System.out.println("=========name=" + name + ",value=" + value);
					if (!"sign".equals(name))
						// 除sign外的所有字段都要参与签名的计算
						signature.param(name, value);
					
					if ("appid".equals(name))
						appId = value;
					else if ("mch_id".equals(name))
						merchantId = value;
					else if ("sign".equals(name))
						sign = value;
					else if ("result_code".equals(name))
						resultCode = value;
					else if ("device_info".equals(name))
						qr.setDeviceInfo(value);
					else if ("openid".equals(name))
						qr.setOpenId(value);
					else if ("is_subscribe".equals(name)) {
						if ("Y".equalsIgnoreCase(value))
							qr.setSubscribed(true);
					}
					else if ("trade_type".equals(name)) 
						qr.setTradeType(value);
					else if ("trade_state".equals(name))
						qr.setTradeState(value);
					else if ("bank_type".equals(name))
						qr.setBankType(value);
					else if ("total_fee".equals(name)) 
						qr.setTotalFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					else if ("fee_type".equals(name)) 
						qr.setFeeType(value);
					else if ("cash_fee".equals(name)) 
						qr.setCashFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					else if ("cash_fee_type".equals(name)) 
						qr.setCashFeeType(value);
					else if ("coupon_fee".equals(name))
						qr.setCouponFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					else if (name.startsWith("coupon_batch_id_")) {
						int index = Integer.parseInt(name.substring("coupon_batch_id_".length()));
						WxpayCoupon coupon = coupons.get(index);
						if (coupon == null) {
							coupon = new WxpayCoupon();
							coupons.put(index, coupon);
						}
						coupon.setBatchId(value);
					}
					else if (name.startsWith("coupon_id_")) {
						int index = Integer.parseInt(name.substring("coupon_id_".length()));
						WxpayCoupon coupon = coupons.get(index);
						if (coupon == null) {
							coupon = new WxpayCoupon();
							coupons.put(index, coupon);
						}
						coupon.setId(value);
					}
					else if (name.startsWith("coupon_fee_")) {
						int index = Integer.parseInt(name.substring("coupon_fee_".length()));
						WxpayCoupon coupon = coupons.get(index);
						if (coupon == null) {
							coupon = new WxpayCoupon();
							coupons.put(index, coupon);
						}
						coupon.setFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					}
					else if ("transaction_id".equals(name)) 
						qr.setWxpayId(value);
					else if ("out_trade_no".equals(name)) 
						qr.setOrderId(value);
					else if ("attach".equals(name)) 
						qr.setAttach(value);
					else if ("time_end".equals(name)) 
						qr.setEndTime(WxpayStep.parseDateTime(value));
					else if ("trade_state_desc".equals(name)) 
						qr.setDescription(value);

					// 忽略其它元素
				}
				if (!coupons.isEmpty())
					qr.setCoupons(new ArrayList<WxpayCoupon>(coupons.values()));
				
				// 一直读到XML文档末尾（END_DOCUMENT）
				while (reader.hasNext())
					reader.next();
			} finally {
				if (reader != null)
					reader.close();
			}
			
			// 检查签名
			String sig = signature.sign(account.getKey());
			System.out.println("===========sig=" + sig + ",sign=" + sign);
			System.out.println("===========appId=" + appId + ",account.getAppId()=" + account.getAppId());
			System.out.println("===========merchantId=" + merchantId + ",account.getMerchantId()=" + account.getMerchantId());
			if (!account.getAppId().equals(appId) ||
					!account.getMerchantId().equals(merchantId) ||
					!sig.equals(sign)) {
				// 签名失败
				returnCode = "FAIL";
				returnMsg = "签名失败";
			}
		} catch (Exception e) {
			returnCode = "FAIL";
			returnMsg = "参数解析错误";
		}
		System.out.println("=======returnCode=" + returnCode + ",returnMsg=" + returnMsg);
		// 如果参数解析或签名错误，报错
		if (returnCode != null) {
			sendResponse(resp, returnCode, returnMsg);
			return null;
		}
		
		if ("SUCCESS".equals(resultCode)) {
			// 支付成功
			// 修改订单状态并发货
			StringBuilder builder = new StringBuilder("微信交易类型：");
			if (qr.getTradeType() != null)
				builder.append(qr.getTradeType());
			builder.append("，付款银行：");
			if (qr.getBankType() != null)
				builder.append(BankType.describe(qr.getBankType()));
			builder.append("，交易号：");
			if (qr.getWxpayId() != null)
				builder.append(qr.getWxpayId());
			
			System.out.println("=========WxpayQueryOrderResult=" + qr);
			boolean status = suiteOrdersService.orderPaidAndDeliver(new PaymentInfo()
					.orderTradeNo(qr.getOrderId())
					.mode(PaymentGateway.WECHATPAY)
					.time(qr.getEndTime())
					.amount(qr.getTotalFee().doubleValue())
					.remark(builder.toString()));
			System.out.println("=============orderPaidAndDeliver.status=" + status);
			// 保存到本地
			WxpayOrder order = qr.toWxpayOrder();
			order.setAppId(account.getAppId());
			wxpayOperations.overwriteWxpayOrder(order);
			return order;
		}
		return null;
	}
	
	private void notifyOld(String tradeType, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("===========微信支付回调开始:tradeType=" + tradeType);

		WxpayAccount account = WxPayProperties.getAppWxpayAccount();//WxPayProperties.getAppWxpayAccount();
		if (TradeTypeEnum.JSAPI.name().equals(tradeType))
			account = WxPayProperties.getJsapiWxpayAccount();
		else if (TradeTypeEnum.APP.name().equals(tradeType)) 
			account = WxPayProperties.getAppWxpayAccount();
		
		System.out.println("=========account=" + account);
		String returnCode = null, returnMsg = null;

		// 解析通知内容
		String resultCode = null;
		WxpayQueryOrderResult qr = new WxpayQueryOrderResult();
		WxpaySignature signature = new WxpaySignature();
		String appId = null, merchantId = null, sign = null;
		try {
			XMLStreamReader reader = null;
			try {
				reader = XIF.createXMLStreamReader(req.getInputStream());
				// 游标移到XML文档的根元素
				reader.nextTag();
				
				System.out.println("========reader.getEventType()=" + reader.getEventType());
				if (reader.getEventType() != XMLStreamConstants.START_ELEMENT)
					throw new XMLStreamException("Unexpected event type " + reader.getEventType(), reader.getLocation());

				Map<Integer, WxpayCoupon> coupons = new TreeMap<Integer, WxpayCoupon>();
				while (reader.nextTag() == XMLStreamConstants.START_ELEMENT) {
					String name = reader.getLocalName();
					String value = reader.getElementText().trim();
					System.out.println("=========name=" + name + ",value=" + value);
					if (!"sign".equals(name))
						// 除sign外的所有字段都要参与签名的计算
						signature.param(name, value);
					
					if ("appid".equals(name))
						appId = value;
					else if ("mch_id".equals(name))
						merchantId = value;
					else if ("sign".equals(name))
						sign = value;
					else if ("result_code".equals(name))
						resultCode = value;
					else if ("device_info".equals(name))
						qr.setDeviceInfo(value);
					else if ("openid".equals(name))
						qr.setOpenId(value);
					else if ("is_subscribe".equals(name)) {
						if ("Y".equalsIgnoreCase(value))
							qr.setSubscribed(true);
					}
					else if ("trade_type".equals(name)) 
						qr.setTradeType(value);
					else if ("trade_state".equals(name))
						qr.setTradeState(value);
					else if ("bank_type".equals(name))
						qr.setBankType(value);
					else if ("total_fee".equals(name)) 
						qr.setTotalFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					else if ("fee_type".equals(name)) 
						qr.setFeeType(value);
					else if ("cash_fee".equals(name)) 
						qr.setCashFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					else if ("cash_fee_type".equals(name)) 
						qr.setCashFeeType(value);
					else if ("coupon_fee".equals(name))
						qr.setCouponFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					else if (name.startsWith("coupon_batch_id_")) {
						int index = Integer.parseInt(name.substring("coupon_batch_id_".length()));
						WxpayCoupon coupon = coupons.get(index);
						if (coupon == null) {
							coupon = new WxpayCoupon();
							coupons.put(index, coupon);
						}
						coupon.setBatchId(value);
					}
					else if (name.startsWith("coupon_id_")) {
						int index = Integer.parseInt(name.substring("coupon_id_".length()));
						WxpayCoupon coupon = coupons.get(index);
						if (coupon == null) {
							coupon = new WxpayCoupon();
							coupons.put(index, coupon);
						}
						coupon.setId(value);
					}
					else if (name.startsWith("coupon_fee_")) {
						int index = Integer.parseInt(name.substring("coupon_fee_".length()));
						WxpayCoupon coupon = coupons.get(index);
						if (coupon == null) {
							coupon = new WxpayCoupon();
							coupons.put(index, coupon);
						}
						coupon.setFee(BigDecimal.valueOf(Long.parseLong(value)).divide(WxpayStep.HUNDRED));
					}
					else if ("transaction_id".equals(name)) 
						qr.setWxpayId(value);
					else if ("out_trade_no".equals(name)) 
						qr.setOrderId(value);
					else if ("attach".equals(name)) 
						qr.setAttach(value);
					else if ("time_end".equals(name)) 
						qr.setEndTime(WxpayStep.parseDateTime(value));
					else if ("trade_state_desc".equals(name)) 
						qr.setDescription(value);

					// 忽略其它元素
				}
				if (!coupons.isEmpty())
					qr.setCoupons(new ArrayList<WxpayCoupon>(coupons.values()));
				
				// 一直读到XML文档末尾（END_DOCUMENT）
				while (reader.hasNext())
					reader.next();
			} finally {
				if (reader != null)
					reader.close();
			}
			
			// 检查签名
			String sig = signature.sign(account.getKey());
			System.out.println("===========sig=" + sig);
			if (!account.getAppId().equals(appId) ||
					!account.getMerchantId().equals(merchantId) ||
					!sig.equals(sign)) {
				// 签名失败
				returnCode = "FAIL";
				returnMsg = "签名失败";
			}
		} catch (Exception e) {
			returnCode = "FAIL";
			returnMsg = "参数解析错误";
		}
		System.out.println("=======returnCode=" + returnCode + ",returnMsg=" + returnMsg);
		// 如果参数解析或签名错误，报错
		if (returnCode != null) {
			sendResponse(resp, returnCode, returnMsg);
			return;
		}
		
		if ("SUCCESS".equals(resultCode)) {
			// 支付成功
			// 修改订单状态并发货
			StringBuilder builder = new StringBuilder("微信交易类型：");
			if (qr.getTradeType() != null)
				builder.append(qr.getTradeType());
			builder.append("，付款银行：");
			if (qr.getBankType() != null)
				builder.append(BankType.describe(qr.getBankType()));
			builder.append("，交易号：");
			if (qr.getWxpayId() != null)
				builder.append(qr.getWxpayId());
			
			System.out.println("=========WxpayQueryOrderResult=" + qr);
			boolean status = suiteOrdersService.orderPaidAndDeliver(new PaymentInfo()
					.orderTradeNo(qr.getOrderId())
					.mode(PaymentGateway.WECHATPAY)
					.time(qr.getEndTime())
					.amount(qr.getTotalFee().doubleValue())
					.remark(builder.toString()));
			System.out.println("=============orderPaidAndDeliver.status=" + status);
			// 保存到本地
			WxpayOrder order = qr.toWxpayOrder();
			order.setAppId(account.getAppId());
			wxpayOperations.overwriteWxpayOrder(order);
			
			//网页端支付目前为购买会员卡，要新增卡片、售卡记录以及生成结算单
			if (TradeTypeEnum.JSAPI.name().equals(tradeType)){
				jsapiAfter(order);
			}
		}
		sendResponse(resp, "SUCCESS", "成功");
	}

	private void jsapiAfter(WxpayOrder order) {
		String tradeNo = order.getOrderId();
		BigDecimal totalFee = order.getTotalFee();
		System.out.println("===========tradeNo=" + tradeNo);
		SuiteOrders suiteOrders = suiteOrdersService.getSuiteOrderByTradeNo(tradeNo);
		Users users = usersService.getById(suiteOrders.getUserId());
		//保存卡信息
		String cid = genCid(tradeNo);//RandomNumberGenerator.getUUId12();
		String pin = genPin(tradeNo);
		int range = 0;
		if (tradeNo.startsWith(TradeNoTypeEnum.WEEK.name())) {//7天体验运营活动
			range = -7;
		} else if (tradeNo.startsWith(TradeNoTypeEnum.YEAR.name())){
			range = 12;
		}
		Cards cards = new Cards();
		cards.setStatus(0);
		cards.setRange(range);
		cards.setPrice(suiteOrders.getPrice());
		cards.setCid(cid);
		cards.setPin(pin);
		if(tradeNo.startsWith(TradeNoTypeEnum.WEEK.name())) {
			GrowingUsers growingUser = growingUsersService.getByMobile(users.getMobile());
			if(growingUser != null)
				cards.setGrowingUserId(growingUser.getId());
		}
		cardsService.insert(cards);
		
		if (tradeNo.startsWith(TradeNoTypeEnum.WEEK.name())) {
			growingUsersService.updateGrowingCardId(users.getMobile(), true);
		}
		//保存售卡信息
		CardDeals cardDeals = cardDealsService.getByTradeNo(tradeNo);
		if (cardDeals != null) {
			double commission = 0;
			Shops shops = shopsService.getByUserId(cardDeals.getSellerId());
			System.out.println("=========shops=" + shops);
			if (shops != null) {
				int cardSaleType = shops.getCardSaleType();
				double cardSaleScale = shops.getCardSaleScale();
				double cardSaleAmount = shops.getCardSaleAmount();
				System.out.println("=========cardSaleScale=" + cardSaleScale + ",cardSaleAmount=" + cardSaleAmount);
				if (cardSaleType == 0 && cardSaleScale > 0) {
					commission = totalFee.multiply(new BigDecimal(cardSaleScale)).doubleValue();
				} else if (cardSaleType == 1 && cardSaleAmount > 0) {
					commission = cardSaleAmount;
				}
				System.out.println("======commission=" + commission);
				if (commission > 0) {
					cardDealsService.update(cards.getId(), tradeNo, commission);
					cardDeals = cardDealsService.getByTradeNo(tradeNo);
					saveBalance(cardDeals);
				}
			}
			
		}
		
		//发送激活码信息
		JavaSmsApi sms = new JavaSmsApi();
		
		System.out.println("===========pin=" + pin);
		try {
			sms.sendMsg(users.getMobile(), pin, tradeNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String genPin(String tradeNo) {
		String pin = RandomNumberGenerator.getUUId12();
		if (tradeNo.startsWith(TradeNoTypeEnum.WEEK.name())) {//7天体验运营活动
			pin = "520".concat(pin.substring(3));
		}
		boolean pinExist = pinExist(pin);
		if (pinExist) {
			return genPin(tradeNo);
		}
		return pin;
	}
	
	private String genCid(String tradeNo) {
		String cid = RandomNumberGenerator.getUUId12();
		if (tradeNo.startsWith(TradeNoTypeEnum.WEEK.name())) {//7天体验运营活动
			cid = "520".concat(cid.substring(3));
		}
		boolean cidExist = cidExist(cid);
		if (cidExist) {
			return genPin(tradeNo);
		}
		return cid;
	}
	private boolean pinExist(String pin) {
		Cards pinCards = cardsService.getByPin(pin);
		if (pinCards != null && pinCards.getPin() != null && !pinCards.getPin().isEmpty())
			return true;
		else
			return false;
			
	}
	
	private boolean cidExist(String cid) {
		Cards pinCards = cardsService.getByCid(cid);
		if (pinCards != null && pinCards.getCid() != null && !pinCards.getCid().isEmpty())
			return true;
		else
			return false;
			
	}
	/**
	 * 保存修改结算单
	 * */
	private void saveBalance(CardDeals cardDeals) {
		if (cardDeals.getBalanceId() > 0 || cardDeals.getCommission() == 0) 
			return;
		
		Date now = new Date();
		String start = DateUtil.getCurWeekStart(now);
		String end = DateUtil.getWeekEndDate(now);
		String next = DateUtil.getNextWeekStart(now);
		Balances balances = balancesService.getByUserId(cardDeals.getSellerId(), start, next);
		
		//BigDecimal cardSaleMoney = new BigDecimal(cardDeals.getCommission());
		if (balances == null) {
			balances = new Balances();
			balances.setCardSaleMoney(cardDeals.getCommission());
			balances.setMoney(cardDeals.getCommission());
			balances.setStatus(BalanceStatus.WITHDRAW_INIT.ordinal());
			balances.setUserId(cardDeals.getSellerId());
			balances.setRangeStart(DateUtil.fomatDate(start));
			balances.setRangeEnd(DateUtil.fomatDate(end));
			balancesService.insert(balances);
			cardDealsService.updateBalanceId(cardDeals.getId(), balances.getId());
		} else {
			System.out.println((balances.getStatus() == BalanceStatus.WITHDRAW_INIT.ordinal()));
			if (balances.getStatus() == BalanceStatus.WITHDRAW_INIT.ordinal())  {
				//money = new BigDecimal(balances.getMoney()).add(cardSaleMoney);
				//cardSaleMoney = new BigDecimal(balances.getCardSaleMoney()).add(cardSaleMoney);
				double cardSaleMoney = cardDeals.getCommission();
				double money = balances.getMoney() + cardSaleMoney;
				cardSaleMoney = balances.getCardSaleMoney() + cardSaleMoney;
				balancesService.updateMoney(balances.getId(), cardSaleMoney, null, money);
				cardDealsService.updateBalanceId(cardDeals.getId(), balances.getId());
			}
		}
	}
	
	private void sendResponse(HttpServletResponse resp, String code, String message) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/xml;charset=utf-8");
		OutputStream os = resp.getOutputStream();
		XMLStreamWriter writer = null;
		try {
			writer = XOF.createXMLStreamWriter(os);
			writer.writeStartDocument();
			
			// 写XML根元素的开始tag
			writer.writeStartElement("xml");
			
			// 对对象进行XML编码，需要一直输出到该对象的结束tag
			if (code != null) {
				writer.writeStartElement("return_code");
				writer.writeCharacters(code);
				writer.writeEndElement();
			}
			if (message != null) {
				writer.writeStartElement("return_msg");
				writer.writeCharacters(message);
				writer.writeEndElement();
			}
			
			writer.writeEndElement();
			
			writer.writeEndDocument();
			writer.flush();
		} catch (XMLStreamException xse) {
			throw new IOException(xse);
		} finally {
			if (writer != null)
				try { writer.close(); } catch (Exception e) {}
			if (os != null)
				os.close();
		}
	}
}