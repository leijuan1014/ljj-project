package com.wxpay.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;



public abstract class WxpayStep {
	private static final Logger logger = LoggerFactory.getLogger(WxpayStep.class);
	
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
	public InputStream postRun(String url) {
		
		InputStream stream = encodeRequest();
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
			StringBuilder reqXML = new StringBuilder();
			String text;
			while ((text = bufferedReader.readLine()) != null) {
				reqXML.append(text);
            }
	        post.setEntity(new ByteArrayEntity(reqXML.toString().getBytes()));
	        response = httpclient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return entity.getContent();
            }
            EntityUtils.consume(entity);
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            try {
            	if(response != null) response.close();
            	if(httpclient != null) httpclient.close();
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        return null;
	}
	
	protected abstract void encode(XMLStreamWriter writer) throws Exception;
	
	public InputStream encodeRequest() {
		ByteArrayOutputStream  bos = new  ByteArrayOutputStream();
		try {
			encodeRequest(bos);
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public Object decodeResponse(InputStream is) {
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
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (XMLStreamException e) {
					logger.info(e.getMessage());
				}
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
			throw new XMLStreamException("Unexpected event type " + reader.getEventType(), reader.getLocation());
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
