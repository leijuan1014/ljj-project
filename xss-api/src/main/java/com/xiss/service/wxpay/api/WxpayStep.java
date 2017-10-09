package com.xiss.service.wxpay.api;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;



public abstract class WxpayStep {
/*
	public WxpayAccount getDefaultAccount1() {
		WxpayAccount account = new WxpayAccount();
		account.setAppId("wx7207aaafb7497ca7");
		account.setCertPath("/root/tool/weixin/wxpay/apiclient_cert.p12");
		account.setKey("1DC11A9531C172C7082663EE1F1B1309");
		account.setMerchantId("1460190602");
		account.setNotifyUrl("https://api.autoxss.com/xiss/api/wxpay/notify");
		return account;
	}
	public WxpayAccount getDefaultAccount() {
		WxpayAccount account = new WxpayAccount();
		account.setAppId("wx46b543acf6dbc7fa");
		//account.setCertPath("/Users/leijj/Documents/weixin/cert_z/apiclient_cert.p12");
		account.setCertPath("/root/tool/weixin/wxpay/apiclient_cert.p12");
		account.setKey("1DC11A9531C172C7082663EE1F1B1309");
		account.setMerchantId("1415297202");
		account.setNotifyUrl("https://api.autoxss.com/xiss/api/wxpay/notify");
		return account;
	}
*/
	public static final BigDecimal HUNDRED = BigDecimal.valueOf(100);
	
	private static final String DATETIME_FORMAT = "yyyyMMddHHmmss";
	
	public static String formatDateTime(Date date) {
		if (date == null)
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.format(date);
	}
	
	public static Date parseDateTime(String date) throws ParseException {
		if (date == null || date.isEmpty())
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		return sdf.parse(date);
	}

	protected static final XMLOutputFactory XOF = new WstxOutputFactory();
	protected static final XMLInputFactory XIF = new WstxInputFactory();
	
	protected abstract void encode(XMLStreamWriter writer) throws Exception;
	
	public InputStream encodeRequest() throws Exception {
		ByteArrayOutputStream  bos = new  ByteArrayOutputStream();
		encodeRequest(bos);
		bos.close();
        InputStream swapStream = new ByteArrayInputStream(bos.toByteArray());
		return swapStream;
	}

	public void encodeRequest(OutputStream os) throws Exception {
		XMLStreamWriter writer = null;
		try {
			writer = XOF.createXMLStreamWriter(os);
			writer.writeStartDocument();
			// 写XML根元素的开始tag
			writer.writeStartElement("xml");
			// 对对象进行XML编码，需要一直输出到该对象的结束tag
			encode(writer);
			writer.writeEndDocument();
			writer.flush();
		} finally {
			if (writer != null)
				writer.close();
		}
	}
	
	protected abstract Object decode(XMLStreamReader reader) throws Exception;
	
	public Object decodeResponse(InputStream is) throws Exception {
		Object object = null;
		XMLStreamReader reader = null;
		try {
			reader = XIF.createXMLStreamReader(is);
			// 游标移到XML文档的根元素
			reader.nextTag();
			object = decode(reader);
			// 一直读到XML文档末尾（END_DOCUMENT）
			while (reader.hasNext())
				reader.next();
		} finally {
			if (reader != null)
				reader.close();
		}
		
		return object;
	}
	
	/**
	 * 输出文本内容的XML元素。
	 * 
	 * @param writer XML输出流
	 * @param localName 元素名
	 * @param text 元素内容
	 * @throws XMLStreamException
	 */
	protected static void writeElement(XMLStreamWriter writer, String localName, String text) throws XMLStreamException {
		writer.writeStartElement(localName);
		writer.writeCharacters(text);
		writer.writeEndElement();
	}
	
	/**
	 * 跳过当前元素，包括其全部内容，一直到对应的end tag。
	 * <br/>先决条件：当前事件是START_ELEMENT
	 * <br/>后置条件：当前事件是对应的END_ELEMENT
	 * 
	 * @param reader
	 * @throws XMLStreamException 如果当前事件不是START_ELEMENT
	 */
	protected static void skipElement(XMLStreamReader reader) throws XMLStreamException {
		if (reader.getEventType() != XMLStreamConstants.START_ELEMENT)
			throw new XMLStreamException("Unexpected event type " + reader.getEventType(),
					reader.getLocation());
        int depth = 1;
        do {
        	int eventType = reader.next();
        	if (eventType == XMLStreamConstants.START_ELEMENT)
        		++depth;
        	else if (eventType == XMLStreamConstants.END_ELEMENT)
        		--depth;
        } while (depth > 0);
	}


}
