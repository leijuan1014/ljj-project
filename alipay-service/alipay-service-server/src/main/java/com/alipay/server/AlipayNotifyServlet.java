package com.alipay.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.bean.AlipayFundBill;
import com.alipay.bean.AlipayOrder;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;


public class AlipayNotifyServlet extends HttpServlet {
	
	public static final Logger LOG = LoggerFactory.getLogger(AlipayNotifyServlet.class.getSimpleName());

	private static final long serialVersionUID = -6045067508012460984L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean ok = process(req);
		sendResponse(resp, ok ? "success" : "failure");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean ok = process(req);
		sendResponse(resp, ok ? "success" : "failure");
	}
	
	/**
	 * 处理支付结果异步通知。仅当通知签名验证不过时返回false，其它情况都返回true。
	 * 
	 * @param req
	 * @return 仅当通知签名验证不过时返回false，其它情况都返回true。
	 * @throws IOException
	 */
	private boolean process(HttpServletRequest req) {
		try {
			AlipayAccount account = AlipayNotifyServletBuddy.INSTANCE.getAccount();
			//OrderOperations orderOperations = AlipayNotifyServletBuddy.INSTANCE.getOrderOperations();
			//OrderManager orderManager = AlipayNotifyServletBuddy.INSTANCE.getOrderManager();
			//AlipayOperations alipayOperations = AlipayNotifyServletBuddy.INSTANCE.getAlipayOperations();
			
			// 验证签名
			Map<String, String[]> map = req.getParameterMap();
			Map<String, String> params = new TreeMap<String, String>();
			for (Map.Entry<String, String[]> entry : map.entrySet()) {
				String[] values = entry.getValue();
				if (values.length == 1) 
					params.put(entry.getKey(), values[0]);
			}
			if (LOG.isWarnEnabled())
				LOG.warn("AlipayNotifyServlet parameters {}", params);

			boolean signVerified = AlipaySignature.rsaCheckV1(params, account.getPublicKey(), AlipayConstants.CHARSET_UTF8);
			if (!signVerified) {
				// 签名验证失败
				if (LOG.isWarnEnabled())
					LOG.warn("AlipayNotifyServlet signature error");
				return false;
			}
			
			if (!params.containsKey("app_id") ||
					!params.containsKey("out_trade_no") ||
					!params.containsKey("total_amount") ||
					!params.containsKey("trade_status")) {
				if (LOG.isWarnEnabled())
					LOG.warn("AlipayNotifyServlet required parameter missing");
				return true;
			}
			

			String appId = params.get("app_id");
			if (!account.getAppId().equals(appId)) {
				// 应用唯一标识不匹配
				if (LOG.isWarnEnabled())
					LOG.warn("AlipayNotifyServlet wrong app_id");
				return true;
			}

			String orderId = params.get("out_trade_no");
			/*Order order = orderOperations.getOrder(Long.parseLong(orderId));
			if (order == null) {
				if (LOG.isWarnEnabled())
					LOG.warn("AlipayNotifyServlet order not found");
				return true;
			}
			
			BigDecimal totalAmount = new BigDecimal(params.get("total_amount"));
			if (order.getTotalAmount().compareTo(totalAmount) != 0) {
				if (LOG.isWarnEnabled())
					LOG.warn("AlipayNotifyServlet incorrect total amount");
				return true;
			}
			
			Date payTime = new Date();
			if (params.containsKey("gmt_payment")) 
				payTime = AlipayStep.parseDateTime(params.get("gmt_payment"));
			
			String status = params.get("trade_status");
			if ("TRADE_SUCCESS".equalsIgnoreCase(status) ||
					"TRADE_FINISHED".equalsIgnoreCase(status)) {
				// 支付成功
				// 修改订单状态并发货
				StringBuilder builder = new StringBuilder("支付宝用户号：");
				if (params.get("buyer_id") != null)
					builder.append(params.get("buyer_id"));
				builder.append("，账号：");
				if (params.get("buyer_logon_id") != null)
					builder.append(params.get("buyer_logon_id"));
				builder.append("，交易号：");
				if (params.get("trade_no") != null)
					builder.append(params.get("trade_no"));
				orderManager.f().c().orderPaidAndDeliver(new Ignore(),
						new PaymentInfo()
						.orderId(order.getId())
						.mode(PayMode.ALIPAY)
						.time(payTime)
						.amount(totalAmount)
						.remark(builder.toString()));
				
				// 保存支付单
				AlipayOrder alipayOrder = toAlipayOrder(params);
				alipayOperations.overwriteAlipayOrder(alipayOrder);
			}
			*/
		} catch (Exception e) {
			if (LOG.isWarnEnabled())
				LOG.warn("AlipayNotifyServlet error", e);
		}
		return true;
	}
	
	private void sendResponse(HttpServletResponse resp, String text) throws IOException {
		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter writer = resp.getWriter();
		writer.print(text);
		writer.close();
	}
		
	private static AlipayOrder toAlipayOrder(Map<String, String> params) {
		AlipayOrder order = new AlipayOrder();
		for (Map.Entry<String, String> entry : params.entrySet()) {
			String value = entry.getValue();
			if (value == null || value.isEmpty())
				continue;
			String name = entry.getKey();
			if ("app_id".equals(name))
				order.setAppId(value);
			else if ("trade_no".equals(name))
				order.setAlipayId(value);
			else if ("out_trade_no".equals(name))
				order.setOrderId(value);
			else if ("buyer_id".equals(name))
				order.setBuyerUserId(value);
			else if ("buyer_logon_id".equals(name))
				order.setBuyerLogonId(value);
			else if ("trade_status".equals(name))
				order.setStatus(value);
			else if ("total_amount".equals(name))
				order.setTotalAmount(new BigDecimal(value));
			else if ("receipt_amount".equals(name))
				order.setReceiptAmount(new BigDecimal(value));
			else if ("buyer_pay_amount".equals(name))
				order.setBuyerPayAmount(new BigDecimal(value));
			else if ("point_amount".equals(name))
				order.setPointAmount(new BigDecimal(value));
			else if ("invoice_amount".equals(name))
				order.setInvoiceAmount(new BigDecimal(value));
			else if ("gmt_payment".equals(name)) 
				try { order.setPayTime(AlipayStep.parseDateTime(value)); } catch (Exception e) {}
			else if ("fund_bill_list".equals(name))
				try { order.setBills(parseBills(value)); } catch (Exception e) {}
		}
		return order;
	}
	
	private static final List<AlipayFundBill> parseBills(String json) throws JsonParseException, IOException {
		if (json == null)
			return null;
		
		List<AlipayFundBill> bills = new ArrayList<AlipayFundBill>();
        JsonParser parser = null;
        try {
            parser = new JsonFactory().createParser(json);
            JsonToken token = parser.nextValue();
            if (token != JsonToken.START_ARRAY)
            	parser.skipChildren();
            else {
            	while ((token = parser.nextValue()) != JsonToken.END_ARRAY) {
            		if (token != JsonToken.START_OBJECT)
            			throw new JsonParseException("unexpected token " + token, parser.getCurrentLocation());
            		AlipayFundBill bill = new AlipayFundBill();
            		while ((token = parser.nextValue()) != JsonToken.END_OBJECT) {
            			String field = parser.getCurrentName();
            			if ("fundChannel".equals(field))
            				bill.setChannel(parser.getValueAsString());
            			else if ("amount" == field)
            				bill.setAmount(new BigDecimal(parser.getValueAsString()));
            				parser.skipChildren();
            		}		
            		bills.add(bill);
            	}
            }
        } finally {
            if (parser != null)
                parser.close();
        }
        return bills;
	}
	
}
