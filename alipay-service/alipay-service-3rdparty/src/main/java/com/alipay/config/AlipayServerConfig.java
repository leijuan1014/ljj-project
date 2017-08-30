package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayServerConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016111802958037";
	//public static String app_id = "2016032401239733";
	// 商户私钥，您的PKCS8格式RSA2私钥
    //public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQYibLedxOJdPNehEi3rbbAOwHNGzAF5+lQjrCha1CvUMmH6g2yGHC/VDBn2kvS5FVyOCB67BaHVa8hMNngkyV0gt9mm7t88IUIZ0ugyUTLnEAGRzx53epJCXLRfybN4iIK2ObVYqXx+H/Q10UTfjXkRi5MGp+T8Vg2Wvnb5Y7OPdSWdMXSVeNDqfmr21UJbT0/uLIM7IQL7uGkij60EBQmPvnsTkoGFym3yzfjs1dvo12VzKUKXQWPcm/dEDUZ7/S2aidPJf5qYmAFy0NaWLmN5cBCulCLvJV62vBXEqSMlpe5r3fNtHNXe2s9HXGB3PhuD5ydOiC8HqUFzGN4wEZAgMBAAECggEAPYBRniLOYMtkKQZx/jR4F1mRMJB4MmrdqftIJEvkvaT40WhyssMwQWq913wbxfWedrgWScEnYXtV0v+KbLDoBnluQ5Zg4iMO7JxU49chF8aUg8IuviwmoHmxT6EENOm20qoIx8XsPzL5ZU5Ae8Gq2cgIi/m/owY/wT1b5T129MpvNYrBDss2BUgbMQaDOydE2rPgBwqvhJgxWq/vJOiaGak/5/s9lR1+w5b1whw09Ku1JxvDY+cOUyXXTn4DTddwayq3Bq47UlJRaTDYti4vmtzEhfyPmRxBuUFEAii+Ve2yZjJ+ZjZ2Hpvg+eNf/yUQ/zj+zA6L5qL2mdJJxIXtsQKBgQDJrLJ0A+seWrZ6eFyVaWE8+jzGlbvc/mFO3u9/+OmesTY26774qB2epL5elDKNUQ5Vx6jfO3qiGJ56vquFEetkCYBNDSgZgWxDQh0OjF8TaBHxasWs+MNMbIU9NgDqVKSP0hfqXSuwaa89bcoV3OOpUAv+5C42tPUpO6g4h2y2GwKBgQC3RrRCacmCXyZSsF8OVktrc/vbwfpSToRPW0oYJQHlDx+gxKzWFW2hNBKPofKBjfwFeeyypd/9XVwDJIlU1TRxL0IU3WSf6wmOU2DYtUjoz8vt+JEdbB9cf4uyBBr+054TJMVkwphQeoRAdktoSaOWlrd0S0iQ0S70NDiBKT0o2wKBgQCx1o7ajSYE81scbgy+WFm9yrBTMXCi9NJcGWy6vYVNQdmb3pDddmoAHigdALP/N63baJ/6kdmXHPyLIp3fTyjyYwp3Z5WnwB/2w1MaJCAm3cuMxmmgGREm0D9B9eAiwxcGF+6s5RIEk31LPwhH9SvwfrzFPPOfnDzf6lJx3sG3ZwKBgBuA/c41p+rrsomYNDunkZvnhPVer90oMUzja0QiUZ15Xoq22SUrhA88DEkHyafNkfOUb4QDxpB0MTm7gux7LmqJ6AXPj6k8EWwNcSw0UGO3w2fL9KMQtcggz+sAGLkDMua2jvTnCwqQyEYQOpLE8krIBXHrVlF9Qumc6JnhgW8fAoGBAJtpcPC8nLFv2Z4WhKE5+e2T62mFxv5J5D5nGE29a4q83Y51XxYwrVrOsxU0DxDnv7dtLonsD9LLRYTuehMXpz/Icqc0/33aIMwBexV5fBdgidesB1KyRVECSi2M7T7QeG0X3dscD9nuz/qOWFkvLewgcAxX5vYcp1jg+4eUzn6F";
    // 商户私钥，您的PKCS8格式RSA1私钥
	//public static String merchant_private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJydmnneUFTy8WedYtyAbCVIqGoOBthUaLcQu9ucOtKHucLMoUcjXWaKp1EmRhr1tVc7k573q4vxRJ8KQr7SmI8uxbhhKzloSWYvR+kUMUvfYGsUHiNbmCQjZsxW4xD2rGDMRVbJguvlp5gzWeiHs4HrJMiX3Vd4KFMSVcjddCopAgMBAAECgYBCqfMvHKok/qQy7pM+OIvP8vV87yuKuaM5CS5tdDq1koBHosKjfTIsi6hcqmX848mkr/9OEy46CyCiccOnZgz5evrU1eXlBmBlo757zIYnoKgX/nJfif+s2UYKwzMN04CrOLSL4vTBmyAEYcF3pZJMlkvFhWsL4Zazf+I0G4GdYQJBAMuQ118Qd6Wmcri0HxjMfTYRZwspj3poptL47q3KYbIDfDwhFfNUaGM+8dluodODSEbndIsZ0DlMvJ1FbcxLUicCQQDE9Ny+USGHRFreIPiljZaVMgK6Fdp3G3UoBiqD5RBpA6Vpv1aSfJYC3wAMfEJOyJ2zlILN/QAxGvjY21AmWMvAkAGiQfhDW5J6yYTt/SnnykF61kbiqIIlAOHEmbXPYAVbvc2FvnVbghL52FsUZjwaW6QmC2tqY59yHDlu6ziuihRAkA4IF9XjN81IPGUI2k4JGUpFxtX1Cpxjs2QHlG3TGukwXk97VzKWd6V6t6ksDRlJx5psoSkFtkbJUiM49KvTnfZAkEAyUKieW0hcXOpMn62SxhdUTu4RQOk/nsyC1GK+94cixkAEPYYskzCbCau+r/nmbVd7ONlJEGpf/j0tCFSm0yAHQ==";
	public static String merchant_private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJydmnneUFTy8WedYtyAbCVIqGoOBthUaLcQu9ucOtKHucLMoUcjXWaKp1EmRhr1tVc7k573q4vxRJ8KQr7SmI8uxbhhKzloSWYvR+kUMUvfYGsUHiNbmCQjZsxW4xD2rGDMRVbJguvlp5gzWeiHs4HrJMiX3Vd4KFMSVcjddCopAgMBAAECgYBCqfMvHKok/qQy7pM+OIvP8vV87yuKuaM5CS5tdDq1koBHosKjfTIsi6hcqmX848mkr/9OEy46CyCiccOnZgz5evrU1eXlBmBlo757zIYnoKgX/nJfif+s2UYKwzMN04CrOLSL4vTBmyAEYcF3pZJMlkvFhWsL4Zazf+I0G4GdYQJBAMuQ118Qd6Wmcri0HxjMfTYRZwspj3poptL47q3KYbIDfDwhFfNUaGM+8dluodODSEbndIsZ0DlMvJ1FbcxLUicCQQDE9Ny+USGHRFreIPiljZaVMgK6Fdp3G3UoBiqD5RBpA6Vpv1aSfJYC3wAMfEJOyJ2zlILN/QAxGvjY21AmLWMvAkAGiQfhDW5J6yYTt/SnnykF61kbiqIIlAOHEmbXPYAVbvc2FvnVbghL52FsUZjwaW6QmC2tqY59yHDlu6ziuihRAkA4IF9XjN81IPGUI2k4JGUpFxtX1Cpxjs2QHlG3TGukwXk97VzKWd6V6t6ksDRlJx5psoSkFtkbJUiM49KvTnfZAkEAyUKieW0hcXOpMn62SxhdUTu4RQOk/nsyC1GK+94cixkAEPYYskzCbCau+r/nmbVd7ONlJEGpf/j0tCFSm0yAHQ==";
	//public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCQYibLedxOJdPNehEi3rbbAOwHNGzAF5+lQjrCha1CvUMmH6g2yGHC/VDBn2kvS5FVyOCB67BaHVa8hMNngkyV0gt9mm7t88IUIZ0ugyUTLnEAGRzx53epJCXLRfybN4iIK2ObVYqXx+H/Q10UTfjXkRi5MGp+T8Vg2Wvnb5Y7OPdSWdMXSVeNDqfmr21UJbT0/uLIM7IQL7uGkij60EBQmPvnsTkoGFym3yzfjs1dvo12VzKUKXQWPcm/dEDUZ7/S2aidPJf5qYmAFy0NaWLmN5cBCulCLvJV62vBXEqSMlpe5r3fNtHNXe2s9HXGB3PhuD5ydOiC8HqUFzGN4wEZAgMBAAECggEAPYBRniLOYMtkKQZx/jR4F1mRMJB4MmrdqftIJEvkvaT40WhyssMwQWq913wbxfWedrgWScEnYXtV0v+KbLDoBnluQ5Zg4iMO7JxU49chF8aUg8IuviwmoHmxT6EENOm20qoIx8XsPzL5ZU5Ae8Gq2cgIi/m/owY/wT1b5T129MpvNYrBDss2BUgbMQaDOydE2rPgBwqvhJgxWq/vJOiaGak/5/s9lR1+w5b1whw09Ku1JxvDY+cOUyXXTn4DTddwayq3Bq47UlJRaTDYti4vmtzEhfyPmRxBuUFEAii+Ve2yZjJ+ZjZ2Hpvg+eNf/yUQ/zj+zA6L5qL2mdJJxIXtsQKBgQDJrLJ0A+seWrZ6eFyVaWE8+jzGlbvc/mFO3u9/+OmesTY26774qB2epL5elDKNUQ5Vx6jfO3qiGJ56vquFEetkCYBNDSgZgWxDQh0OjF8TaBHxasWs+MNMbIU9NgDqVKSP0hfqXSuwaa89bcoV3OOpUAv+5C42tPUpO6g4h2y2GwKBgQC3RrRCacmCXyZSsF8OVktrc/vbwfpSToRPW0oYJQHlDx+gxKzWFW2hNBKPofKBjfwFeeyypd/9XVwDJIlU1TRxL0IU3WSf6wmOU2DYtUjoz8vt+JEdbB9cf4uyBBr+054TJMVkwphQeoRAdktoSaOWlrd0S0iQ0S70NDiBKT0o2wKBgQCx1o7ajSYE81scbgy+WFm9yrBTMXCi9NJcGWy6vYVNQdmb3pDddmoAHigdALP/N63baJ/6kdmXHPyLIp3fTyjyYwp3Z5WnwB/2w1MaJCAm3cuMxmmgGREm0D9B9eAiwxcGF+6s5RIEk31LPwhH9SvwfrzFPPOfnDzf6lJx3sG3ZwKBgBuA/c41p+rrsomYNDunkZvnhPVer90oMUzja0QiUZ15Xoq22SUrhA88DEkHyafNkfOUb4QDxpB0MTm7gux7LmqJ6AXPj6k8EWwNcSw0UGO3w2fL9KMQtcggz+sAGLkDMua2jvTnCwqQyEYQOpLE8krIBXHrVlF9Qumc6JnhgW8fAoGBAJtpcPC8nLFv2Z4WhKE5+e2T62mFxv5J5D5nGE29a4q83Y51XxYwrVrOsxU0DxDnv7dtLonsD9LLRYTuehMXpz/Icqc0/33aIMwBexV5fBdgidesB1KyRVECSi2M7T7QeG0X3dscD9nuz/qOWFkvLewgcAxX5vYcp1jg+4eUzn6F";											
	//public static String merchant_private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJydmnneUFTy8WedYtyAbCVIqGoOBthUaLcQu9ucOtKHucLMoUcjXWaKp1EmRhr1tVc7k573q4vxRJ8KQr7SmI8uxbhhKzloSWYvR+kUMUvfYGsUHiNbmCQjZsxW4xD2rGDMRVbJguvlp5gzWeiHs4HrJMiX3Vd4KFMSVcjddCopAgMBAAECgYBCqfMvHKok/qQy7pM+OIvP8vV87yuKuaM5CS5tdDq1koBHosKjfTIsi6hcqmX848mkr/9OEy46CyCiccOnZgz5evrU1eXlBmBlo757zIYnoKgX/nJfif+s2UYKwzMN04CrOLSL4vTBmyAEYcF3pZJMlkvFhWsL4Zazf+I0G4GdYQJBAMuQ118Qd6Wmcri0HxjMfTYRZwspj3poptL47q3KYbIDfDwhFfNUaGM+8dluodODSEbndIsZ0DlMvJ1FbcxLUicCQQDE9Ny+USGHRFreIPiljZaVMgK6Fdp3G3UoBiqD5RBpA6Vpv1aSfJYC3wAMfEJOyJ2zlILN/QAxGvjY21AmWMvAkAGiQfhDW5J6yYTt/SnnykF61kbiqIIlAOHEmbXPYAVbvc2FvnVbghL52FsUZjwaW6QmC2tqY59yHDlu6ziuihRAkA4IF9XjN81IPGUI2k4JGUpFxtX1Cpxjs2QHlG3TGukwXk97VzKWd6V6t6ksDRlJx5psoSkFtkbJUiM49KvTnfZAkEAyUKieW0hcXOpMn62SxhdUTu4RQOk/nsyC1GK+94cixkAEPYYskzCbCau+r/nmbVd7ONlJEGpf/j0tCFSm0yAHQ==";
	//	/Users/xiss/Documents/备份/alipay/rsa1/rsa_private_key(1).pem
	// 支付宝公钥,RSA2公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
   // public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCcnZp53lBU8vFnnWLcgGwlSKhqDgbYVGi3ELvbnDrSh7nCzKFHI11miqdRJkYa9bVXO5Oe96uL8USfCkK+0piPLsW4YSs5aElmL0fpFDFL32BrFB4jW5gkI2bMVuMQ9qxgzEVWyYLr5aeYM1noh7OB6yTIl91XeChTElXI3XQqKQIDAQAB";
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA1utFnGkthxMSEpN4EBHh+DFCBwIaJcizizQRuYZkC/bP0EHeQa43bQnGvRxUsqSeUHYVUq6KCmLUg7/PGrtKRpwWGRfNtWAXrNVYWJd/DquQGE3L81WYQ1NeSkd8rgvLPBvdA7VvregHizy+W+7uPiKdMbnXskWKBjo7BX1UgqZU6j5zi3mkLGqYvfI5WqWyaRtPIn+6Oc3stGLnvZ2pBfSYWsF0+fa4dRXxbP/BK2TbC1Z5nSnegncbH9WnJNsYNsEdRykRK//bJT55IGuFQqrtZ8G0YWnMoIoniwBIE/ENGIpnKhI2ZS0zFT+CrwlBp+uFD02P8DTcfsx+6ZFgiwIDAQAB";
	// 支付宝公钥,RSA1公钥
    //public static String alipay_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "https://api.autoxss.com/xisstest/wxpay/notify/pay";//"http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "https://api.autoxss.com/xisstest/wxpay/notify/pay";//"http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "/tmp";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

