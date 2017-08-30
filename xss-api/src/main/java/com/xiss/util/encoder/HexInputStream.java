package com.xiss.util.encoder;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

public class HexInputStream extends InputStream {
	
	private InputStream is;

	public HexInputStream(InputStream is) {
		this.is = is;
	}

	@Override
	public int read() throws IOException {
		int b1 = is.read();
		int b2 = is.read();
		if (b1 < 0 || b2 < 0)
			return -1;
		if ((b1 - '0' <= 9 || b1 - 'a' <= 5 || b1 - 'A' <= 5) &&
				(b2 - '0' <= 9 || b2 - 'a' <= 5 || b2 - 'A' <= 5)) {
			int b = 0;
			
			if (b1 - '0' <= 9)
				b = (b1 - '0') << 4;
			else if (b1 - 'a' <= 5)
				b = (b1 - 'a' + 10) << 4;
			else 
				b = (b1 - 'A' + 10) << 4;
			
			if (b2 - '0' <= 9)
				b |= (b2 - '0') & 0xf;
			else if (b2 - 'a' <= 5)
				b |= (b2 - 'a' + 10) & 0xf;
			else
				b |= (b2 - 'A' + 10) & 0xf;
			
			return b;
		}
		throw new IOException("invalid hex sequence " + (char) b1 + (char) b2);
	}
	
    public final void readFully(byte b[]) throws IOException {
        readFully(b, 0, b.length);
    }
	
    public final void readFully(byte b[], int off, int len) throws IOException {
        if (len < 0)
            throw new IndexOutOfBoundsException();
        int n = 0;
        while (n < len) {
            int count = read(b, off + n, len - n);
            if (count < 0)
                throw new EOFException();
            n += count;
        }
    }

	@Override
	public int available() throws IOException {
		return is.available() / 2;
	}

	@Override
	public void close() throws IOException {
		is.close();
	}

}
