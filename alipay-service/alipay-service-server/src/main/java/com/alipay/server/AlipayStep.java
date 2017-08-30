package com.alipay.server;

import java.io.IOException;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import static com.alipay.api.AlipayConstants.*;

public abstract class AlipayStep {
	
	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
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
	
	public static Date parseDateTime(String date, TimeZone zone) throws ParseException {
		if (date == null || date.isEmpty())
			return null;
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT);
		sdf.setTimeZone(zone);
		return sdf.parse(date);
	}

	public static void appendParam(StringBuilder builder, Map<String, String> params, 
			String name, String value) throws UnsupportedEncodingException {
		if (value == null || value.isEmpty())
			return;
		
		params.put(name, value);
		if (builder.length() > 0 && builder.charAt(builder.length() - 1) != '&')
			builder.append('&');
		builder.append(name).append("=").append(URLEncoder.encode(value, CHARSET_UTF8));
	}

	public String getBizContent() throws IOException {
		StringWriter writer = new StringWriter();
		JsonGenerator generator = null;
		try {
			generator = new JsonFactory().createGenerator(writer);
			encodeBizContent(generator);
		} finally {
			if (generator != null)
				generator.close();
		}
		
		writer.close();
		return writer.toString();
	}
	
	protected abstract void encodeBizContent(JsonGenerator generator) throws IOException;
	
	public static final String VERSION_1_0 = "1.0";
	
}
