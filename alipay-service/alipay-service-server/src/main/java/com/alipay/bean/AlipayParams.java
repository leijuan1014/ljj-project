package com.alipay.bean;

/**
 * 业务相关参数
 * @author lpf
 */
public class AlipayParams {
    //内部订单号
    private String orderNo;
    //商品名称
    private String subject;
    //交易金额 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。
    private Double totalFee;
    //卖家支付宝用户号 当签约账号就是收款账号时，seller_id的值与partner的值相同
    private String sellerId;
    //买家支付宝用户号  非必填
    private String buyerId;
    //买家支付宝账号  非必填
    private String buyerEmail;
    //买家支付宝账号别名 非必填
    private String buyerAccountName;
    //商品描述 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
    private String body;
    //商品展示网址  收银台页面上，商品展示的超链接。
    private String showUrl;
    //默认支付方式  取值范围：creditPay（信用支付） directPay（余额支付）如果不设置，默认识别为余额支付。
    private String paymethod;
    //防钓鱼时间戳 通过时间戳查询接口获取的加密支付宝系统时间戳。如果已申请开通防钓鱼时间戳验证，则此字段必填。
    private String antiPhishingKey;
    //客户端IP 用户在创建交易时，该用户当前所使用机器的IP。如果商户申请后台开通防钓鱼IP地址检查选项，此字段必填，校验用。
    private String exterInvokeIp;
    //公用回传参数  如果用户请求时传递了该参数，则返回给商户时会回传该参数
	private String extraCommonParam;
    //超时时间 设置未付款交易的超时时间，一旦超时，该笔交易就会自动被关闭。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。该参数数值不接受小数点，如1.5h，可转换为90m。
    private String itBPay;
    //快捷登录授权令牌 如果开通了快捷登录产品，则需要填写；如果没有开通，则为空
    private String token;
    /**
     * 扫码支付方式  扫码支付的方式，支持前置模式和跳转模式。
     *
     * 前置模式是将二维码前置到商户的订单确认页的模式。需要商户在自己的页面中以iframe方式请求支付宝页面。具体分为以下3种：
        0：订单码-简约前置模式，对应iframe宽度不能小于600px，高度不能小于300px；
        1：订单码-前置模式，对应iframe宽度不能小于300px，高度不能小于600px；
        3：订单码-迷你前置模式，对应iframe宽度不能小于75px，高度不能小于75px。
        4：订单码-可定义宽度的嵌入式二维码，商户可根据需要设定二维码的大小。

      跳转模式下，用户的扫码界面是由支付宝生成的，不在商户的域名下。
       2：订单码-跳转模式
     */

    private String qrPayMode;
    //商户自定二维码宽度  当qr_pay_mode=4时，该参数生效。
    private Integer qrcodeWidth;
    //是否需要买家实名认证  T表示需要买家实名认证；不传或者传其它值表示不需要买家实名认证。
    private String needBuyerRealnamed;
    //商品类型  1表示实物类商品  0表示虚拟类商品 如果不传，默认为实物类商品。
    private String goodsType;
    /** 支付宝服务器主动通知商户网站里指定的页面http路径。 */
    private String notifyUrl;
    /** 支付宝处理完请求后，当前页面自动跳转到商户网站里指定页面的http路径*/
    private String returnUrl;
    //字符编码格式 目前支持 gbk 或 utf-8
    private String charset;
    /** 签名方式 */
    private String signType;
    /** 调用的支付宝方法*/
    private String method;
    /** 支付宝交易号 */
    private String tradeNo;
    /** 退款金额 */
    private double refundAmount;
    /** 退款原因 */
    private String refundReson;
    /** 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。 */
    private String requestNo;

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

    public Double getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Double totalFee) {
        this.totalFee = totalFee;
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

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerAccountName() {
        return buyerAccountName;
    }

    public void setBuyerAccountName(String buyerAccountName) {
        this.buyerAccountName = buyerAccountName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    public String getAntiPhishingKey() {
        return antiPhishingKey;
    }

    public void setAntiPhishingKey(String antiPhishingKey) {
        this.antiPhishingKey = antiPhishingKey;
    }

    public String getExterInvokeIp() {
        return exterInvokeIp;
    }

    public void setExterInvokeIp(String exterInvokeIp) {
        this.exterInvokeIp = exterInvokeIp;
    }

    public String getExtraCommonParam() {
        return extraCommonParam;
    }

    public void setExtraCommonParam(String extraCommonParam) {
        this.extraCommonParam = extraCommonParam;
    }

    public String getItBPay() {
        return itBPay;
    }

    public void setItBPay(String itBPay) {
        this.itBPay = itBPay;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getQrPayMode() {
        return qrPayMode;
    }

    public void setQrPayMode(String qrPayMode) {
        this.qrPayMode = qrPayMode;
    }

    public Integer getQrcodeWidth() {
        return qrcodeWidth;
    }

    public void setQrcodeWidth(Integer qrcodeWidth) {
        this.qrcodeWidth = qrcodeWidth;
    }

    public String getNeedBuyerRealnamed() {
        return needBuyerRealnamed;
    }

    public void setNeedBuyerRealnamed(String needBuyerRealnamed) {
        this.needBuyerRealnamed = needBuyerRealnamed;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReson() {
        return refundReson;
    }

    public void setRefundReson(String refundReson) {
        this.refundReson = refundReson;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }
}
