package com.alipay.server;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author lpf
 */
public interface Constants {
	
    interface Field {

    	String ID = "_id";
    	String ORDER_ID = "orderId";
    	String APP_ID = "appId";
    	String BUYER_USER_ID = "buyerUserId";
    	String BUYER_LOGON_ID = "buyerLogonId";
    	String STATUS = "status";
    	String TOTAL_AMOUNT = "totalAmount";
    	String RECEIPT_AMOUNT = "receiptAmount";
    	String BUYER_PAY_AMOUNT = "buyerPayAmount";
    	String POINT_AMOUNT = "pointAmount";
    	String INVOICE_AMOUNT = "invoiceAmount";
    	String PAY_TIME = "payTime";
    	String ALIPAY_STORE_ID = "alipayStoreId";
    	String STORE_ID = "storeId";
    	String TERMINAL_ID = "terminalId";
    	String BILLS = "bills";
    	String STORE_NAME = "storeName";
    	String DISCOUNT_GOODS_DETAIL = "discountGoodsDetail";
    	String INDUSTRY_SPEC_DETAIL = "industrySpecDetail";
    	String CTIME = "ctime";
    	String MTIME = "mtime";
    	
        /**
         * 在save操作中可覆盖的字段。在save操作时，如果某字段有值，
         * 其值会替换旧值；如果某字段无值，旧值会被清除。
         */
        String[] SAVEABLE_FIELDS = {
        		ORDER_ID, BUYER_USER_ID, BUYER_LOGON_ID, STATUS, 
        		TOTAL_AMOUNT, RECEIPT_AMOUNT, BUYER_PAY_AMOUNT, POINT_AMOUNT,
        		INVOICE_AMOUNT, PAY_TIME, ALIPAY_STORE_ID, STORE_ID,
        		TERMINAL_ID, BILLS, STORE_NAME, DISCOUNT_GOODS_DETAIL,
        		INDUSTRY_SPEC_DETAIL
        };

        /** 建有索引的字段 */
        Set<String> INDEXED_FIELDS = new TreeSet<String>(Arrays.asList(new String[]{
        		ID, ORDER_ID, PAY_TIME, CTIME, MTIME
        }));

    }
    
    interface BillField {
    	String CHANNEL = "channel";
    	String AMOUNT = "amount";
    	String REAL_AMOUNT = "realAmount";
    }
    
}
