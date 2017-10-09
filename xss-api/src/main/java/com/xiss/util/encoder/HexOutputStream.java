package com.xiss.util.encoder;

import java.io.IOException;
import java.io.OutputStream;

public class HexOutputStream extends OutputStream {
	
	private OutputStream os;
	private char a;
	
	public HexOutputStream(OutputStream os) {
		this(os, false);
	}
	
	public HexOutputStream(OutputStream os, boolean uppercase) {
		this.os = os;
		this.a = uppercase ? 'A' : 'a';
	}

	@Override
	public void write(int b) throws IOException {
		byte by = (byte) ((b >> 4) & 0xf);
		if (by <= 9)
			os.write(by + '0');
		else 
			os.write(by - 10 + a);
		
		by = (byte) (b & 0xf);
		if (by <= 9)
			os.write(by + '0');
		else
			os.write(by - 10 + a);
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if ((off < 0) || (off > b.length) || (len < 0) ||
                   ((off + len) > b.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }
        byte[] buf = new byte[len * 2];
        for (int i = 0; i < len; i++) {
    		byte by = (byte) ((b[off + i] >> 4) & 0xf);
    		if (by <= 9)
    			buf[2 * i] = (byte) (by + '0');
    		else 
    			buf[2 * i] = (byte) (by - 10 + a);
    		
    		by = (byte) (b[off + i] & 0xf);
    		if (by <= 9)
    			buf[2 * i + 1] = (byte) (by + '0');
    		else
    			buf[2 * i + 1] = (byte) (by - 10 + a);
        }
		os.write(buf);
	}

	@Override
	public void flush() throws IOException {
		os.flush();
	}

	@Override
	public void close() throws IOException {
		os.close();
	}
	
}
