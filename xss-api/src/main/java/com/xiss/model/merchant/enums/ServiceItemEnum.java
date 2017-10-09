package com.xiss.model.merchant.enums;
/** 
* @author leijj
* @since  2017年4月10日 下午12:25:22 
*/
public enum ServiceItemEnum {
	CAR_WASH("洗车"),
	CARD_SALE("售卡");
	String value;
	
	private ServiceItemEnum(String value) {
		this.value = value;
	}

    @Override
    public String toString() {
        return this.value;
    }
}
