package com.xiss.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;

public abstract class JsonCodec<T> {
	
	/**
	 * 把当前对象序列化到JSON输出流中。
	 * 
	 * @param generator JSON输出流
	 * @param object 要序列化的对象
	 * @throws IOException
	 */
	public abstract void encode(JsonGenerator generator, T object) throws IOException;
	
	/**
	 * 序列化和反序列化支持的对象类型。
	 * 
	 * @return 对象类型
	 */
	public abstract Class<T> getEncoderClass();
	
	/**
	 * 从JSON输入流的当前位置反序列化出对象。
	 * 
	 * @param parser JSON输入流
	 * @return 反序列化的对象
	 * @throws IOException
	 */
	public abstract T decode(JsonParser parser) throws IOException;
	
	/**
	 * 对象序列化。
	 * 
	 * @param object 要序列化的对象
	 * @return 对象序列化得到的完整JSON文档
	 * @throws IOException
	 */
	public ZeroCopy encode(T object) throws IOException {
		if (object == null)
			return null;
		
		BytesOutputStream bos = new BytesOutputStream();
		JsonGenerator generator = null;
		try {
			generator = new JsonFactory().createGenerator(bos);
			encode(generator, object);
		} finally {
			if (generator != null)
				generator.close();
		}
		
		bos.close();
		return bos.toInputStream();
	}
	
	/**
	 * 对象反序列化。
	 * 
	 * @param json 输入的完整JSON文档
	 * @return 反序列化出的对象
	 * @throws IOException
	 */
	public T decode(ZeroCopy json) throws IOException {
		if (json == null)
			return null;
		
		T object = null;
		JsonParser parser = null;
		try {
			parser = new JsonFactory().createParser(json.asStream());
			parser.nextValue();
			object = decode(parser);
		} finally {
			if (parser != null)
				parser.close();
		}
		
		return object;
	}

}
