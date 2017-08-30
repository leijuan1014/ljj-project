package com.wx.grizzly;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 输入/输出时零拷贝的内存内容。一般由一个或多个byte[]直接或间接组成。
 * 
 * @author zhy
 *
 */
public interface ZeroCopy {
	
	/**
	 * 当前内容长度。
	 * 
	 * @return
	 */
	int length();
	
	/**
	 * 当前内容转换成字节数组。可能需要分配新字节数组并拷贝内容到其中。
	 * @return
	 */
	byte[] asBytes();
	
	/**
	 * 作为输入流以读取其内容。此方法不拷贝内容。两次调用得到的输入流是独立的。
	 * 
	 * @return
	 */
	InputStream asStream();
	
	/**
	 * 将内容输出到输出流中。此方法避免不必要的内存拷贝。
	 * 
	 * @param os
	 * @throws IOException
	 */
	void writeTo(OutputStream os) throws IOException;

}
