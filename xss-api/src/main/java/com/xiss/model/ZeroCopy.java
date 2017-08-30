package com.xiss.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * ����/���ʱ�㿽�����ڴ����ݡ�һ����һ������byte[]ֱ�ӻ�����ɡ�
 * 
 * @author zhy
 *
 */
public interface ZeroCopy {
	
	/**
	 * ��ǰ���ݳ��ȡ�
	 * 
	 * @return
	 */
	int length();
	
	/**
	 * ��ǰ����ת�����ֽ����顣������Ҫ�������ֽ����鲢�������ݵ����С�
	 * @return
	 */
	byte[] asBytes();
	
	/**
	 * ��Ϊ�������Զ�ȡ�����ݡ��˷������������ݡ����ε��õõ����������Ƕ����ġ�
	 * 
	 * @return
	 */
	InputStream asStream();
	
	/**
	 * �����������������С��˷������ⲻ��Ҫ���ڴ濽����
	 * 
	 * @param os
	 * @throws IOException
	 */
	void writeTo(OutputStream os) throws IOException;

}
