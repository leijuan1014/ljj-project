package com.xiss.model.merchant.enums;
/** 
* @author leijj
* @since  2017年4月10日 下午2:31:13 
*/
public enum CardSaleTypeEnum {
	BY_SCALE("按比例提成"),
	BY_FIXED_MONEY("按固定金额提成");
	
	String value;
	
	private CardSaleTypeEnum(String value){
		this.value = value;
	}
	
    @Override
    public String toString() {
        return this.value;
    }
}
