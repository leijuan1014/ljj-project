package com.alipay.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author lpf
 */
public class TradeQuery implements Serializable {
    private static final long serialVersionUID = -6296048162765109053L;

    /** 查询orderNo在集合中的支付单。null或empty表示忽略此条件 */
    private Set<String> orderNos;
    /** 查询此时间之前(不含)通知的支付单 */
    private Date notifyTimeBefore;
    /** 查询此时间之后(含)通知的支付单 */
    private Date notifyTimeAfter;
    /** 商品名称 */
    private String subject;
    /** 查询处于这些状态的支付单。null或empty表示忽略此条件 */
    private Set<TradeState> inStates;
    /** 查询不处于这些状态的支付单。null或empty表示忽略此条件 */
    private Set<TradeState> notInStates;
    /** 查询此时间之前(不含)创建的支付单 */
    private Date createBefore;
    /** 查询此时间之后(含)创建的支付单 */
    private Date createAfter;
    /** 查询此时间之前(不含)完成支付的支付单*/
    private Date paymentBefore;
    /** 查询此时间之后(含)完成支付的支付单*/
    private Date paymentAfter;
    /** 查询此时间之前(不含)关闭的支付单 */
    private Date closeBefore;
    /** 查询此时间之后(含)关闭的支付单 */
    private Date closeAfter;
    /**
     * 退款状态
     * REFUND_SUCCESS 退款成功：
     * 全额退款情况：trade_status= TRADE_CLOSED，而refund_status=REFUND_SUCCESS
     * 非全额退款情况：trade_status= TRADE_SUCCESS，而refund_status=REFUND_SUCCESS
     *
     * REFUND_CLOSED 退款关闭
     */
    private String refundState;
    /** 查询此时间之前(不含)退款的支付单 */
    private Date refundBefore;
    /** 查询此时间之后(含)退款的支付单 */
    private Date refundAfter;
    /** 买家支付宝账号，可以是Email或手机号码。*/
    private String buyerEmail;
    /** 买家支付宝账户号 */
    private String buyerId;
    /** 交易金额 */
    private Double totalFee;
    /** 是否扫码支付 回传给商户此标识为qrpay时，表示对应交易为扫码支付。目前只有qrpay一种回传值。非扫码支付方式下，目前不会返回该参数。*/
    private String qrpay;
    /** 返回结果在结果集中的起始偏移 */
    private int offset;
    /** 返回结果条数 */
    private int limit;
    /** 查询此时间之前(不含)修改的支付单 */
    private Date lmDateBefore;
    /** 查询此时间之后(含)修改的支付单 */
    private Date lmDateAfter;
    /** 是否按通知时间排序。正值表示从旧到新，负值表示从新到旧排序，0表示默认排序 */
    private int orderByNotifyTime;
    /** 是否按最后修改时间排序。正值表示从旧到新，负值表示从新到旧排序，0表示默认排序 */
    private int orderByModifyTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<String> getOrderNos() {
        return orderNos;
    }

    public TradeQuery orderNo(String orderNo) {
        if (orderNo == null || orderNo.isEmpty())
            return this;
        if (orderNos == null)
            orderNos = new TreeSet<String>();
        orderNos.add(orderNo);
        return this;
    }

    public Date getNotifyTimeBefore() {
        return notifyTimeBefore;
    }

    public TradeQuery notifyTimeBefore(Date notifyTimeBefore) {
        this.notifyTimeBefore = notifyTimeBefore;
        return this;
    }

    public Date getNotifyTimeAfter() {
        return notifyTimeAfter;
    }

    public TradeQuery notifyTimeAfter(Date notifyTimeAfter) {
        this.notifyTimeAfter = notifyTimeAfter;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public TradeQuery subject(String subject) {
        this.subject = subject;
        return this;
    }

    public Set<TradeState> getInStates() {
        return inStates;
    }

    public TradeQuery inStates(TradeState state) {
        if (state == null)
            return this;
        if (inStates == null)
            inStates = new TreeSet<TradeState>();
        inStates.add(state);
        return this;
    }

    public Set<TradeState> getNotInStates() {
        return notInStates;
    }

    public TradeQuery notInStates(TradeState state) {
        if (state == null)
            return this;
        if (notInStates == null)
            notInStates = new TreeSet<TradeState>();
        notInStates.add(state);
        return this;
    }

    public Date getCreateBefore() {
        return createBefore;
    }

    public TradeQuery createBefore(Date createBefore) {
        this.createBefore = createBefore;
        return this;
    }

    public Date getCreateAfter() {
        return createAfter;
    }

    public TradeQuery createAfter(Date createAfter) {
        this.createAfter = createAfter;
        return this;
    }

    public Date getPaymentBefore() {
        return paymentBefore;
    }

    public TradeQuery paymentBefore(Date paymentBefore) {
        this.paymentBefore = paymentBefore;
        return this;
    }

    public Date getPaymentAfter() {
        return paymentAfter;
    }

    public TradeQuery paymentAfter(Date paymentAfter) {
        this.paymentAfter = paymentAfter;
        return this;
    }

    public Date getCloseBefore() {
        return closeBefore;
    }

    public TradeQuery closeBefore(Date closeBefore) {
        this.closeBefore = closeBefore;
        return this;
    }

    public Date getCloseAfter() {
        return closeAfter;
    }

    public TradeQuery closeAfter(Date closeAfter) {
        this.closeAfter = closeAfter;
        return this;
    }

    public String getRefundState() {
        return refundState;
    }

    public TradeQuery refundState(String refundState) {
        this.refundState = refundState;
        return this;
    }

    public Date getRefundBefore() {
        return refundBefore;
    }

    public TradeQuery refundBefore(Date refundBefore) {
        this.refundBefore = refundBefore;
        return this;
    }

    public Date getRefundAfter() {
        return refundAfter;
    }

    public TradeQuery refundAfter(Date refundAfter) {
        this.refundAfter = refundAfter;
        return this;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public TradeQuery buyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
        return this;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public TradeQuery buyerId(String buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public Double getTotalFee() {
        return totalFee;
    }

    public TradeQuery totalFee(Double totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public String getQrpay() {
        return qrpay;
    }

    public TradeQuery qrpay(String qrpay) {
        this.qrpay = qrpay;
        return this;
    }

    public int getOffset() {
        return offset;
    }

    public TradeQuery offset(int offset) {
        this.offset = offset;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public TradeQuery limit(int limit) {
        this.limit = limit;
        return this;
    }

    public Date getLmDateBefore() {
        return lmDateBefore;
    }

    public TradeQuery lmDateBefore(Date lmDateBefore) {
        this.lmDateBefore = lmDateBefore;
        return this;
    }

    public Date getLmDateAfter() {
        return lmDateAfter;
    }

    public TradeQuery lmDateAfter(Date lmDateAfter) {
        this.lmDateAfter = lmDateAfter;
        return this;
    }

    public int getOrderByNotifyTime() {
        return orderByNotifyTime;
    }

    public TradeQuery orderByNotifyTime(int orderByotifyTime) {
        this.orderByNotifyTime = orderByotifyTime;
        return this;
    }

    public int getOrderByModifyTime() {
        return orderByModifyTime;
    }

    public TradeQuery orderByModifyTime(int orderByModifyTime) {
        this.orderByModifyTime = orderByModifyTime;
        return this;
    }

    /**
     * 本查询条件是否为空，即没有任何子条件。
     *
     * @return 如果没有任何子条件，true，否则false
     */
    public boolean isBlank() {
        return (orderNos == null || orderNos.isEmpty()) &&
                notifyTimeBefore == null && notifyTimeAfter == null &&
                subject == null && (inStates == null || inStates.isEmpty()) &&
                (notInStates == null || notInStates.isEmpty()) &&
                createBefore == null && createAfter == null &&
                paymentBefore == null && paymentAfter == null &&
                closeBefore == null && closeAfter == null &&
                refundState == null && refundBefore == null &&
                refundAfter == null && buyerEmail == null &&
                buyerId == null && totalFee == 0 &&
                qrpay == null && lmDateBefore == null && lmDateAfter == null;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TradeQuery{");
        sb.append("orderNos=").append(orderNos);
        sb.append(", notifyTimeBefore=").append(notifyTimeBefore);
        sb.append(", notifyTimeAfter=").append(notifyTimeAfter);
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", inStates=").append(inStates);
        sb.append(", notInStates=").append(notInStates);
        sb.append(", createBefore=").append(createBefore);
        sb.append(", createAfter=").append(createAfter);
        sb.append(", paymentBefore=").append(paymentBefore);
        sb.append(", paymentAfter=").append(paymentAfter);
        sb.append(", closeBefore=").append(closeBefore);
        sb.append(", closeAfter=").append(closeAfter);
        sb.append(", refundState='").append(refundState).append('\'');
        sb.append(", refundBefore=").append(refundBefore);
        sb.append(", refundAfter=").append(refundAfter);
        sb.append(", buyerEmail='").append(buyerEmail).append('\'');
        sb.append(", buyerId='").append(buyerId).append('\'');
        sb.append(", totalFee=").append(totalFee);
        sb.append(", qrpay='").append(qrpay).append('\'');
        sb.append(", offset=").append(offset);
        sb.append(", limit=").append(limit);
        sb.append(", lmDateBefore=").append(lmDateBefore);
        sb.append(", lmDateAfter=").append(lmDateAfter);
        sb.append(", orderByNotifyTime=").append(orderByNotifyTime);
        sb.append(", orderByModifyTime=").append(orderByModifyTime);
        sb.append('}');
        return sb.toString();
    }
}
