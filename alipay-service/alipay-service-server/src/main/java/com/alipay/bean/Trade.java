package com.alipay.bean;

import java.util.Date;

/**
 * 支付宝支付单,对应支付宝异步通知的结果
 * @author lpf
 */
public class Trade {
    //通知发送时间
    private Date notifyTime;
    //通知类型
    private String notifyType;
    //通知校验ID
    private String notifyId;
    //签名方式
    private String signType;
    //签名
    private String sign;
    //订单号
    private String orderNo;
    //商品名称
    private String subject;
    //支付类型
    private String paymentType;
    //支付宝交易号
    private String tradeNo;
    //交易状态
    private TradeState tradeStatus;
    //交易创建时间
    private Date gmtCreate;
    //交易付款时间
    private Date gmtPayment;
    //交易关闭时间
    private Date gmtClose;
    /**退款状态
     * REFUND_SUCCESS 退款成功：
     * 全额退款情况：trade_status= TRADE_CLOSED，而refund_status=REFUND_SUCCESS
     * 非全额退款情况：trade_status= TRADE_SUCCESS，而refund_status=REFUND_SUCCESS
     *
     * REFUND_CLOSED 退款关闭
     */
    private String refundState;
    //退款时间
    private Date gmtRefund;
    //卖家支付宝账号，可以是email和手机号码。
    private String sellerEmail;
    //买家支付宝账号，可以是Email或手机号码。
    private String buyerEmail;
    //卖家支付宝账户号
    private String sellerId;
    //买家支付宝账户号
    private String buyerId;
    //订单金额
    private Double totalAmount;
    //商家在交易中实际收到的款项，单位为元
    private Double receiptAmount;
    //用户在交易中支付的金额
    private Double buyerPayAmount;
    //是否扫码支付 回传给商户此标识为qrpay时，表示对应交易为扫码支付。目前只有qrpay一种回传值。非扫码支付方式下，目前不会返回该参数。
    private String businessScene;
    //商品描述
    private String body;
    /** 职位最后修改时间，只读属性 */
    private Date lastModifiedDate;

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public TradeState getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(TradeState tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public Date getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(Date gmtClose) {
        this.gmtClose = gmtClose;
    }

    public String getRefundState() {
        return refundState;
    }

    public void setRefundState(String refundState) {
        this.refundState = refundState;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBusinessScene() {
        return businessScene;
    }

    public void setBusinessScene(String businessScene) {
        this.businessScene = businessScene;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Double receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public Double getBuyerPayAmount() {
        return buyerPayAmount;
    }

    public void setBuyerPayAmount(Double buyerPayAmount) {
        this.buyerPayAmount = buyerPayAmount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Trade{");
        sb.append("notifyTime=").append(notifyTime);
        sb.append(", notifyType='").append(notifyType).append('\'');
        sb.append(", notifyId='").append(notifyId).append('\'');
        sb.append(", signType='").append(signType).append('\'');
        sb.append(", sign='").append(sign).append('\'');
        sb.append(", orderNo='").append(orderNo).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", paymentType='").append(paymentType).append('\'');
        sb.append(", tradeNo='").append(tradeNo).append('\'');
        sb.append(", tradeStatus=").append(tradeStatus);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtPayment=").append(gmtPayment);
        sb.append(", gmtClose=").append(gmtClose);
        sb.append(", refundState='").append(refundState).append('\'');
        sb.append(", gmtRefund=").append(gmtRefund);
        sb.append(", sellerEmail='").append(sellerEmail).append('\'');
        sb.append(", buyerEmail='").append(buyerEmail).append('\'');
        sb.append(", sellerId='").append(sellerId).append('\'');
        sb.append(", buyerId='").append(buyerId).append('\'');
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", receiptAmount=").append(receiptAmount);
        sb.append(", buyerPayAmount=").append(buyerPayAmount);
        sb.append(", businessScene='").append(businessScene).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append(", lastModifiedDate=").append(lastModifiedDate);
        sb.append('}');
        return sb.toString();
    }
}
