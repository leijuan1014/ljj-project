package com.xiss.model.wxpay;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by leijj on 16/10/10.
 */
public class WxpayPlaceOrderResult implements Serializable {
	//wxpay_place_order_result

	private static final long serialVersionUID = 3463705819286116867L;
	private int id;
	private String orderNumber;
	/** 预支付交易会话标识:微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时*/
    private String prepayId;
    /** 二维码链接:trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付*/
    private String codeUrl;
    
    private String sign;
    private String noncestr;
    private Date createdAt;
	private Date updatedAt;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("WxpayPlaceOrderResult [id=");
		builder.append(id);
		builder.append(", orderNumber=");
		builder.append(orderNumber);
		builder.append(", prepayId=");
		builder.append(prepayId);
		builder.append(", codeUrl=");
		builder.append(codeUrl);
		builder.append(", sign=");
		builder.append(sign);
		builder.append(", noncestr=");
		builder.append(noncestr);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}
}