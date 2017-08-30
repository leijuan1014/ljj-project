package com.wx.base.bean;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.wx.grizzly.JsonCodec;

public class WxAccessTokenJsonCodec extends JsonCodec<WxAccessToken>{

	@Override
	public void encode(JsonGenerator generator, WxAccessToken object)
			throws IOException {
		generator.writeStartObject();
		if (null != object) {
			if (object.getAccessToken() != null)
				generator.writeStringField("access_token", object.getAccessToken());
			if (object.getExpiresIn() > 0)
				generator.writeNumberField("expires_in", object.getExpiresIn());
			if (object.getExpiresAt() != null)
				generator.writeNumberField("expires_at", object.getExpiresAt().getTime());
			if (object.getRefreshToken() != null)
				generator.writeStringField("refresh_token", object.getRefreshToken());
			if (object.getOpenId() != null)
				generator.writeStringField("openid", object.getOpenId());
			if (object.getScope() != null)
				generator.writeStringField("scope", object.getScope());
			if (object.getUnionId() != null)
				generator.writeStringField("unionid", object.getUnionId());
		}
	}

	@Override
	public Class<WxAccessToken> getEncoderClass() {
		return WxAccessToken.class;
	}

	@Override
	public WxAccessToken decode(JsonParser parser) throws IOException {
		JsonToken token = parser.getCurrentToken();
		if (token != JsonToken.START_OBJECT) {
			throw new JsonParseException("unexpected token" + token, parser.getCurrentLocation());
		}
		
		WxAccessToken wxToken = new WxAccessToken();
		while ((token = parser.nextValue()) != JsonToken.END_OBJECT) {
			String field = parser.getCurrentName().intern();
			if ("access_token" == field) 
				wxToken.setAccessToken(parser.getText());
			else if ("expires_in" == field) 
				wxToken.setExpiresIn(parser.getValueAsInt());
			else if("expires_at" == field)
				wxToken.setExpiresAt(new Date(parser.getValueAsLong()));
			else if ("refresh_token" == field)
				wxToken.setRefreshToken(parser.getText());
			else if ("openid" == field)
				wxToken.setOpenId(parser.getText());
			else if ("scope" == field)
				wxToken.setScope(parser.getText());
			else if ("unionid" == field)
				wxToken.setUnionId(parser.getText());
			else 
				parser.skipChildren();
		}
		return wxToken;
	}
	
}
