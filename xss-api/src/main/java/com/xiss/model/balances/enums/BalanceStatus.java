package com.xiss.model.balances.enums;

/** 
* @author leijj
* @since  2017年4月20日 上午11:08:57 
*/
public enum BalanceStatus {
	WITHOUTDRAW("未提现"),//未提现
	WITHDRAWING("提现中"),//提现中
	WITHDRAW_SUCCESS("已提现"),//已提现
	WITHDRAW_INIT("未结算");//未结算
	
	String value;
	
	private BalanceStatus(String value){
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
    public static BalanceStatus get(int ordinal) {
        BalanceStatus[] list = values();
        for (BalanceStatus balanceStatus : list) {
            if (balanceStatus.ordinal() == ordinal) {
                return balanceStatus;
            }
        }
        return null;
    }
}
