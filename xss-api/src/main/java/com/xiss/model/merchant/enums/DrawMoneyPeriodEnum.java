package com.xiss.model.merchant.enums;
/** 
* @author leijj
* @since  2017年4月10日 下午2:40:17 
*/
public enum DrawMoneyPeriodEnum {
	BY_MONTH("月提"),
	BY_WEEK("周提");
	
	String value;
	private DrawMoneyPeriodEnum(String value) {
		this.value = value;
	}
	
    @Override
    public String toString() {
        return this.value;
    }
}
