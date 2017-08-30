package com.alipay.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
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
import com.alipay.bean.AlipayOrder;

/**
 * @author lpf
 */
public class AlipayNotifyServlet2 extends HttpServlet {

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

            System.out.println("publickey=============="+account.getPublicKey());
            boolean signVerified = AlipaySignature.rsaCheckV1(params, account.getDirectPayPublicKey(), AlipayConstants.CHARSET_UTF8);
            if (!signVerified) {
                // 签名验证失败
                if (LOG.isWarnEnabled())
                    LOG.warn("AlipayNotifyServlet2 signature error");
                return false;
            }

            if (!params.containsKey("out_trade_no") ||
                    !params.containsKey("total_fee") ||
                    !params.containsKey("trade_status")) {
                if (LOG.isWarnEnabled())
                    LOG.warn("AlipayNotifyServlet2 required parameter missing");
                return true;
            }

            String orderId = params.get("out_trade_no");
            /*Order order = orderOperations.getOrder(Long.parseLong(orderId));
            if (order == null) {
                if (LOG.isWarnEnabled())
                    LOG.warn("AlipayNotifyServlet2 order not found");
                return true;
            }

            BigDecimal totalAmount = new BigDecimal(params.get("total_fee"));
            if (order.getTotalAmount().compareTo(totalAmount) != 0) {
                if (LOG.isWarnEnabled())
                    LOG.warn("AlipayNotifyServlet2 incorrect total fee");
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
                if (params.get("buyer_email") != null)
                    builder.append(params.get("buyer_email"));
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
                LOG.warn("AlipayNotifyServlet2 error", e);
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

    private AlipayOrder toAlipayOrder(Map<String, String> params) {
        AlipayOrder order = new AlipayOrder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String value = entry.getValue();
            if (value == null || value.isEmpty())
                continue;
            String name = entry.getKey();
            if ("trade_no".equals(name))
                order.setAlipayId(value);
            else if ("out_trade_no".equals(name))
                order.setOrderId(value);
            else if ("buyer_id".equals(name))
                order.setBuyerUserId(value);
            else if ("buyer_email".equals(name))
                order.setBuyerLogonId(value);
            else if ("trade_status".equals(name))
                order.setStatus(value);
            else if ("total_fee".equals(name))
                order.setTotalAmount(new BigDecimal(value));
            else if ("gmt_payment".equals(name))
                try { order.setPayTime(AlipayStep.parseDateTime(value)); } catch (Exception e) {}
        }
        return order;
    }


}
