package com.wx.base.bean;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.wx.grizzly.JsonCodec;

public class WxFailureJsonCodec extends JsonCodec<WxFailure> {

	@Override
	public void encode(JsonGenerator generator, WxFailure object)
			throws IOException {
		generator.writeStartObject();
		if (object != null) {
			if (object.getCode() != 0)
				generator.writeNumberField("errcode", object.getCode());
			if (object.getMsg() != null)
				generator.writeStringField("errmsg", object.getMsg());
		}
		generator.writeEndObject();
	}

	@Override
	public Class<WxFailure> getEncoderClass() {
		return WxFailure.class;
	}

	@Override
	public WxFailure decode(JsonParser parser) throws IOException {
		JsonToken token = parser.getCurrentToken();
		if (token != JsonToken.START_OBJECT)
			throw new JsonParseException("unexpected token " + token, parser.getCurrentLocation());
		WxFailure failure = new WxFailure();
		while ((token = parser.nextValue()) != JsonToken.END_OBJECT) {
			String field = parser.getCurrentName().intern();
			if ("errcode" == field)
				failure.setCode(parser.getValueAsInt());
			else if ("errmsg" == field)
				failure.setMsg(parser.getText());
			else 
				parser.skipChildren();
		}		
		return failure;
	}

}
