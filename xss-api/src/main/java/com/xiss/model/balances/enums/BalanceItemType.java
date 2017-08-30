package com.xiss.model.balances.enums;

/** 
* @author leijj
* @since  2017年4月20日 上午11:08:57 
*/
public enum BalanceItemType {
	CARD_DEALS("售卡提成"),
	SUITE_SALES("套餐销售"),
	CAR_WASH("洗车明细");
	
	String value;
	
	private BalanceItemType(String value){
		this.value = value;
	}
	
    @Override
    public String toString() {
        return this.value;
    }
    
    /**
     * @param ordinal 序列号
     * @return 结果枚举对象
     */
    public static BalanceItemType get(int ordinal) {
        BalanceItemType[] list = values();
        for (BalanceItemType balanceStatus : list) {
            if (balanceStatus.ordinal() == ordinal) {
                return balanceStatus;
            }
        }
        return null;
    }
}
