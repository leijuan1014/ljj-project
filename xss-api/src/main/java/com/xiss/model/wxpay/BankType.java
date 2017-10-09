package com.xiss.model.wxpay;

/** 
 * 银行卡类型码及其描述。
 * 
 * @author zhy
 *
 */
public enum BankType {
	ICBC_DEBIT("工商银行(借记卡)"),
	ICBC_CREDIT("工商银行(信用卡)"),
	ABC_DEBIT("农业银行(借记卡)"),
	ABC_CREDIT("农业银行(信用卡)"),
	PSBC_DEBIT("邮政储蓄银行(借记卡)"),
	PSBC_CREDIT("邮政储蓄银行(信用卡)"),
	CCB_DEBIT("建设银行(借记卡)"),
	CCB_CREDIT("建设银行(信用卡)"),
	CMB_DEBIT("招商银行(借记卡)"),
	CMB_CREDIT("招商银行(信用卡)"),
	BOC_DEBIT("中国银行(借记卡)"),
	BOC_CREDIT("中国银行(信用卡)"),
	COMM_DEBIT("交通银行(借记卡)"),
	SPDB_DEBIT("浦发银行(借记卡)"),
	SPDB_CREDIT("浦发银行(信用卡)"),
	GDB_DEBIT("广发银行(借记卡)"),
	GDB_CREDIT("广发银行(信用卡)"),
	CMBC_DEBIT("民生银行(借记卡)"),
	CMBC_CREDIT("民生银行(信用卡)"),
	PAB_DEBIT("平安银行(借记卡)"),
	PAB_CREDIT("平安银行(信用卡)"),
	CEB_DEBIT("光大银行(借记卡)"),
	CEB_CREDIT("光大银行(信用卡)"),
	CIB_DEBIT("兴业银行(借记卡)"),
	CIB_CREDIT("兴业银行(信用卡)"),
	CITIC_DEBIT("中信银行(借记卡)"),
	CITIC_CREDIT("中信银行(信用卡)"),
	BOSH_DEBIT("上海银行(借记卡)"),
	BOSH_CREDIT("上海银行(信用卡)"),
	CRB_DEBIT("华润银行(借记卡)"),
	HZB_DEBIT("杭州银行(借记卡)"),
	HZB_CREDIT("杭州银行(信用卡)"),
	BSB_DEBIT("包商银行(借记卡)"),
	BSB_CREDIT("包商银行(信用卡)"),
	CQB_DEBIT("重庆银行(借记卡)"),
	SDEB_DEBIT("顺德农商行(借记卡)"),
	SZRCB_DEBIT("深圳农商银行(借记卡)"),
	HRBB_DEBIT("哈尔滨银行(借记卡)"),
	BOCD_DEBIT("成都银行(借记卡)"),
	GDNYB_DEBIT("南粤银行(借记卡)"),
	GDNYB_CREDIT("南粤银行(信用卡)"),
	GZCB_DEBIT("广州银行(借记卡)"),
	GZCB_CREDIT("广州银行(信用卡)"),
	JSB_DEBIT("江苏银行(借记卡)"),
	JSB_CREDIT("江苏银行(信用卡)"),
	NBCB_DEBIT("宁波银行(借记卡)"),
	NBCB_CREDIT("宁波银行(信用卡)"),
	NJCB_DEBIT("南京银行(借记卡)"),
	JZB_DEBIT("晋中银行(借记卡)"),
	KRCB_DEBIT("昆山农商(借记卡)"),
	LJB_DEBIT("龙江银行(借记卡)"),
	LNNX_DEBIT("辽宁农信(借记卡)"),
	LZB_DEBIT("兰州银行(借记卡)"),
	WRCB_DEBIT("无锡农商(借记卡)"),
	ZYB_DEBIT("中原银行(借记卡)"),
	ZJRCUB_DEBIT("浙江农信(借记卡)"),
	WZB_DEBIT("温州银行(借记卡)"),
	XAB_DEBIT("西安银行(借记卡)"),
	JXNXB_DEBIT("江西农信(借记卡)"),
	NCB_DEBIT("宁波通商银行(借记卡)"),
	NYCCB_DEBIT("南阳村镇银行(借记卡)"),
	NMGNX_DEBIT("内蒙古农信(借记卡)"),
	SXXH_DEBIT("陕西信合(借记卡)"),
	SRCB_CREDIT("上海农商银行(信用卡)"),
	SJB_DEBIT("盛京银行(借记卡)"),
	SDRCU_DEBIT("山东农信(借记卡)"),
	SRCB_DEBIT("上海农商银行(借记卡)"),
	SCNX_DEBIT("四川农信(借记卡)"),
	QLB_DEBIT("齐鲁银行(借记卡)"),
	QDCCB_DEBIT("青岛银行(借记卡)"),
	PZHCCB_DEBIT("攀枝花银行(借记卡)"),
	ZJTLCB_DEBIT("浙江泰隆银行(借记卡)"),
	TJBHB_DEBIT("天津滨海农商行(借记卡)"),
	WEB_DEBIT("微众银行(借记卡)"),
	YNRCCB_DEBIT("云南农信(借记卡)"),
	WFB_DEBIT("潍坊银行(借记卡)"),
	WHRC_DEBIT("武汉农商行(借记卡)"),
	ORDOSB_DEBIT("鄂尔多斯银行(借记卡)"),
	XJRCCB_DEBIT("新疆农信银行(借记卡)"),
	ORDOSB_CREDIT("鄂尔多斯银行(信用卡)"),
	CSRCB_DEBIT("常熟农商银行(借记卡)"),
	JSNX_DEBIT("江苏农商行(借记卡)"),
	GRCB_CREDIT("广州农商银行(信用卡)"),
	GLB_DEBIT("桂林银行(借记卡)"),
	GDRCU_DEBIT("广东农信银行(借记卡)"),
	GDHX_DEBIT("广东华兴银行(借记卡)"),
	FJNX_DEBIT("福建农信银行(借记卡)"),
	DYCCB_DEBIT("德阳银行(借记卡)"),
	DRCB_DEBIT("东莞农商行(借记卡)"),
	CZCB_DEBIT("稠州银行(借记卡)"),
	CZB_DEBIT("浙商银行(借记卡)"),
	CZB_CREDIT("浙商银行(信用卡)"),
	GRCB_DEBIT("广州农商银行(借记卡)"),
	CSCB_DEBIT("长沙银行(借记卡)"),
	CQRCB_DEBIT("重庆农商银行(借记卡)"),
	CBHB_DEBIT("渤海银行(借记卡)"),
	BOIMCB_DEBIT("内蒙古银行(借记卡)"),
	BOD_DEBIT("东莞银行(借记卡)"),
	BOD_CREDIT("东莞银行(信用卡)"),
	BOB_DEBIT("北京银行(借记卡)"),
	BNC_DEBIT("江西银行(借记卡)"),
	BJRCB_DEBIT("北京农商行(借记卡)"),
	AE_CREDIT("AE(信用卡)"),
	GYCB_CREDIT("贵阳银行(信用卡)"),
	JSHB_DEBIT("晋商银行(借记卡)"),
	JRCB_DEBIT("江阴农商行(借记卡)"),
	JNRCB_DEBIT("江南农商(借记卡)"),
	JLNX_DEBIT("吉林农信(借记卡)"),
	JLB_DEBIT("吉林银行(借记卡)"),
	JJCCB_DEBIT("九江银行(借记卡)"),
	HXB_DEBIT("华夏银行(借记卡)"),
	HXB_CREDIT("华夏银行(信用卡)"),
	HUNNX_DEBIT("湖南农信(借记卡)"),
	HSB_DEBIT("徽商银行(借记卡)"),
	HSBC_DEBIT("恒生银行(借记卡)"),
	HRXJB_DEBIT("华融湘江银行(借记卡)"),
	HNNX_DEBIT("河南农信(借记卡)"),
	HKBEA_DEBIT("东亚银行(借记卡)"),
	HEBNX_DEBIT("河北农信(借记卡)"),
	HBNX_DEBIT("湖北农信(借记卡)"),
	HBNX_CREDIT("湖北农信(信用卡)"),
	GYCB_DEBIT("贵阳银行(借记卡)"),
	GSNX_DEBIT("甘肃农信(借记卡)"),
	JCB_CREDIT("JCB(信用卡)"),
	MASTERCARD_CREDIT("MASTERCARD(信用卡)"),
	VISA_CREDIT("VISA(信用卡)");
	
	private final String description;
	
	BankType(String description) {
		this.description = description;
	}
	
	public String description() {
		return description;
	}
	
	/**
	 * 银行卡类型码转成文字描述。如果遇到不认识的银行卡类型码，直接返回银行卡类型码。
	 * 
	 * @param name 银行类型码，如"ICBC_DEBIT"
	 * @return 银行卡类型描述
	 */
	public static String describe(String name) {
		if (name == null || name.isEmpty())
			return "";
		try {
			return BankType.valueOf(name).description();
		} catch (Exception e) {
			return name;
		}
	}
	
}
