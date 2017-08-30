package com.alipay.server;

import static com.alipay.server.Constants.BillField.*;

import java.math.BigDecimal;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import com.alipay.bean.AlipayFundBill;

public class AlipayFundBillCodec implements Codec<AlipayFundBill> {

	@Override
	public void encode(BsonWriter writer, AlipayFundBill value, EncoderContext encoderContext) {
		writer.writeStartDocument();
		if (value != null) {
			if (value.getChannel() != null)
				writer.writeString(CHANNEL, value.getChannel());
		}
		writer.writeEndDocument();
	}

	@Override
	public Class<AlipayFundBill> getEncoderClass() {
		return AlipayFundBill.class;
	}

	@Override
	public AlipayFundBill decode(BsonReader reader, DecoderContext decoderContext) {
		reader.readStartDocument();
		AlipayFundBill bill = new AlipayFundBill();
		while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
			String name = reader.readName().intern();
			if (CHANNEL == name)
				bill.setChannel(reader.readString());
			else
				reader.skipValue();
		}
		reader.readEndDocument();
		return bill;
	}
	
	public Document encode(AlipayFundBill bill) {
		if (bill == null)
			return null;
		
		Document doc = new Document();
		if (bill.getChannel() != null)
			doc.append(CHANNEL, bill.getChannel());
		return doc;
	}

	public AlipayFundBill decode(Document doc) {
		if (doc == null)
			return null;
		
		AlipayFundBill bill = new AlipayFundBill();
		if (doc.containsKey(CHANNEL))
			bill.setChannel(doc.getString(CHANNEL));
		return bill;
	}
	
}
