package com.wx.base.bean;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.wx.grizzly.JsonCodec;


public class WxTicketJsonCodec extends JsonCodec<WxTicket>{

	@Override
	public void encode(JsonGenerator generator, WxTicket object)
			throws IOException {
		generator.writeStartObject();
		if (null != object) {
			generator.writeStringField("ticket", object.getTicket());
			generator.writeNumberField("expires_in", object.getExpiresIn());
			if (object.getExpiresAt() != null)
				generator.writeNumberField("expires_at", object.getExpiresAt().getTime());
		}
	}

	@Override
	public Class<WxTicket> getEncoderClass() {
		return WxTicket.class;
	}

	@Override
	public WxTicket decode(JsonParser parser) throws IOException {
		JsonToken token = parser.getCurrentToken();
		if (token != JsonToken.START_OBJECT) {
			throw new JsonParseException("unexpected wxTicket" + token, parser.getCurrentLocation());
		}
		
		WxTicket wxTicket = new WxTicket();
		while ((token = parser.nextValue()) != JsonToken.END_OBJECT) {
			String field = parser.getCurrentName().intern();
			if ("ticket" == field) {
				wxTicket.setTicket(parser.getText());
			} else if ("expires_in" == field) {
				wxTicket.setExpiresIn(parser.getValueAsInt());
			} else if("expires_at" == field){
				wxTicket.setExpiresAt(new Date(parser.getValueAsLong()));
			} else {
				parser.skipChildren();
			}
		}
		return wxTicket;
	}
	
}
