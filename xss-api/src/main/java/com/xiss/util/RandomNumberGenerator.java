package com.xiss.util;
import java.util.UUID;

public class RandomNumberGenerator {
	public static String getUUId12() {
		return getByUUId(12);
	}
	public static String getUUId9() {
		return getByUUId(9);
	}
	public static String getByUUId(int lenth) {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0     
        // 4 代表长度为4     
        // d 代表参数为正数型
        return machineId + String.format("%0" + (lenth - 1) + "d", hashCodeV);
    }
    public static void main(String[] args) {
    	String uuid = getUUId12();
    	System.out.println(uuid);
        System.out.println(uuid.substring(3));
    }
}