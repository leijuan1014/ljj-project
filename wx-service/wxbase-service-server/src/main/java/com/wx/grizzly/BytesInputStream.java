package com.wx.grizzly;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class BytesInputStream extends InputStream implements ZeroCopy {

    /**
     * An array of bytes that was provided
     * by the creator of the stream. Elements <code>buf[0]</code>
     * through <code>buf[count-1]</code> are the
     * only bytes that can ever be read from the
     * stream;  element <code>buf[pos]</code> is
     * the next byte to be read.
     */
    protected byte buf[];

    /**
     * The index of the next character to read from the input stream buffer.
     * This value should always be nonnegative
     * and not larger than the value of <code>count</code>.
     * The next byte to be read from the input stream buffer
     * will be <code>buf[pos]</code>.
     */
    protected int pos;

    /**
     * The currently marked position in the stream.
     * BytesInputStream objects are marked at position zero by
     * default when constructed.  
     * The current buffer position is set to this point by the
     * <code>reset()</code> method.
     * <p>
     * If no mark has been set, then the value of mark is the offset
     * passed to the constructor (or 0 if the offset was not supplied).
     *
     * @since   JDK1.1
     */
    protected int mark = 0;

    /**
     * The index one greater than the last valid character in the input
     * stream buffer.
     * This value should always be nonnegative
     * and not larger than the length of <code>buf</code>.
     * It  is one greater than the position of
     * the last byte within <code>buf</code> that
     * can ever be read  from the input stream buffer.
     */
    protected int count;

    /**
     * Creates a <code>BytesInputStream</code>
     * so that it  uses <code>buf</code> as its
     * buffer array.
     * The buffer array is not copied.
     * The initial value of <code>pos</code>
     * is <code>0</code> and the initial value
     * of  <code>count</code> is the length of
     * <code>buf</code>.
     *
     * @param   buf   the input buffer.
     */
    public BytesInputStream(byte buf[]) {
        this.buf = buf;
        this.pos = 0;
        this.count = buf.length;
    }

    /**
     * Creates <code>BytesInputStream</code>
     * that uses <code>buf</code> as its
     * buffer array. The initial value of <code>pos</code>
     * is <code>offset</code> and the initial value
     * of <code>count</code> is the minimum of <code>offset+length</code>
     * and <code>buf.length</code>.
     * The buffer array is not copied. The buffer's mark is
     * set to the specified offset.
     *
     * @param   buf      the input buffer.
     * @param   offset   the offset in the buffer of the first byte to read.
     * @param   length   the maximum number of bytes to read from the buffer.
     */
    public BytesInputStream(byte buf[], int offset, int length) {
        this.buf = buf;
        this.pos = offset;
        this.count = Math.min(offset + length, buf.length);
        this.mark = offset;
    }
    
    /**
     * Creates <code>BytesInputStream</code>
     * that converts the input <code>string</code> to UTF-8 format 
     * and uses the data as its buffer array. The initial value of 
     * <code>pos</code> is <code>0</code> and the initial value of 
     * <code>count</code> is the encoded length.
     * The buffer array is newly allocated.
     * 
     * @param string the input string
     */
    public BytesInputStream(String string) {
		if (string == null)
			throw new NullPointerException("input string is null");
		if (string.isEmpty()) {
			buf = new byte[0];
			pos = 0;
			count = 0;
			return;
		}
		
		int len = string.length();
		int i = 0;
		char c;
		c = string.charAt(i);
		if ((c & ~0x7f) == 0) {
			buf = new byte[len];
			buf[count++] = (byte) c;
			i++;

			for (; i < len; i++) {
				c = string.charAt(i);
				if ((c & ~0x7f) == 0)
					buf[count++] = (byte) c;
				else {
					byte[] newbytes = new byte[count + (len - count) * 3];
					System.arraycopy(buf, 0, newbytes, 0, count);
					buf = newbytes;
					break;
				}
			}
		}
		else
			buf = new byte[len * 3];
		
		for (; i < len; i++) {
			c = string.charAt(i);
			if ((c & ~0x7f) == 0) {
				buf[count++] = (byte) c;
			}
			else if ((c & ~0x7ff) == 0) {
				buf[count++] = (byte) (0xc0 | (c >> 6));
				buf[count++] = (byte) (0x80 | (c & 0x3f));
			}
			else if ((c & 0xfc00) == 0xd800 &&
					i + 1 < len &&
					((string.charAt(i + 1) & 0xfc00) == 0xdc00)) {
				// surrogate pair
				int s = (c & 0x3ff) << 10;
				s |= string.charAt(i + 1) & 0x3ff;
				s += 0x10000;
				buf[count++] = (byte) (0xf0 | (s >> 18));
				buf[count++] = (byte) (0x80 | ((s >> 12) & 0x3f));
				buf[count++] = (byte) (0x80 | ((s >> 6) & 0x3f));
				buf[count++] = (byte) (0x80 | (s & 0x3f));
				// skip the low surrogate
				i++;
			}
			else {
				buf[count++] = (byte) (0xe0 | (c >> 12));
				buf[count++] = (byte) (0x80 | ((c >> 6) & 0x3f));
				buf[count++] = (byte) (0x80 | (c & 0x3f));
			}
		}
    }

    /**
     * Reads the next byte of data from this input stream. The value
     * byte is returned as an <code>int</code> in the range
     * <code>0</code> to <code>255</code>. If no byte is available
     * because the end of the stream has been reached, the value
     * <code>-1</code> is returned.
     * <p>
     * This <code>read</code> method
     * cannot block.
     *
     * @return  the next byte of data, or <code>-1</code> if the end of the
     *          stream has been reached.
     */
    public int read() {
        return (pos < count) ? (buf[pos++] & 0xff) : -1;
    }

    /**
     * Reads up to <code>len</code> bytes of data into an array of bytes
     * from this input stream.
     * If <code>pos</code> equals <code>count</code>,
     * then <code>-1</code> is returned to indicate
     * end of file. Otherwise, the  number <code>k</code>
     * of bytes read is equal to the smaller of
     * <code>len</code> and <code>count-pos</code>.
     * If <code>k</code> is positive, then bytes
     * <code>buf[pos]</code> through <code>buf[pos+k-1]</code>
     * are copied into <code>b[off]</code>  through
     * <code>b[off+k-1]</code> in the manner performed
     * by <code>System.arraycopy</code>. The
     * value <code>k</code> is added into <code>pos</code>
     * and <code>k</code> is returned.
     * <p>
     * This <code>read</code> method cannot block.
     *
     * @param   b     the buffer into which the data is read.
     * @param   off   the start offset in the destination array <code>b</code>
     * @param   len   the maximum number of bytes read.
     * @return  the total number of bytes read into the buffer, or
     *          <code>-1</code> if there is no more data because the end of
     *          the stream has been reached.
     * @exception  NullPointerException If <code>b</code> is <code>null</code>.
     * @exception  IndexOutOfBoundsException If <code>off</code> is negative,
     * <code>len</code> is negative, or <code>len</code> is greater than
     * <code>b.length - off</code>
     */
    public int read(byte b[], int off, int len) {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }
        if (pos >= count) {
            return -1;
        }
        if (pos + len > count) {
            len = count - pos;
        }
        if (len <= 0) {
            return 0;
        }
        System.arraycopy(buf, pos, b, off, len);
        pos += len;
        return len;
    }

    /**
     * Skips <code>n</code> bytes of input from this input stream. Fewer
     * bytes might be skipped if the end of the input stream is reached.
     * The actual number <code>k</code>
     * of bytes to be skipped is equal to the smaller
     * of <code>n</code> and  <code>count-pos</code>.
     * The value <code>k</code> is added into <code>pos</code>
     * and <code>k</code> is returned.
     *
     * @param   n   the number of bytes to be skipped.
     * @return  the actual number of bytes skipped.
     */
    public long skip(long n) {
        if (pos + n > count) {
            n = count - pos;
        }
        if (n < 0) {
            return 0;
        }
        pos += n;
        return n;
    }

    /**
     * Returns the number of remaining bytes that can be read (or skipped over)
     * from this input stream.
     * <p>
     * The value returned is <code>count&nbsp;- pos</code>,
     * which is the number of bytes remaining to be read from the input buffer.
     *
     * @return  the number of remaining bytes that can be read (or skipped
     *          over) from this input stream without blocking.
     */
    public int available() {
        return count - pos;
    }

    /**
     * Tests if this <code>InputStream</code> supports mark/reset. 
     *
     * @since   JDK1.1
     */
    public boolean markSupported() {
        return false;
    }

    /**
     * Set the current marked position in the stream.
     * BytesInputStream objects are marked at position zero by
     * default when constructed.  
     * <p>
     * If no mark has been set, then the value of the mark is the
     * offset passed to the constructor (or 0 if the offset was not
     * supplied).
     *
     * <p> Note: The <code>readAheadLimit</code> for this class
     *  has no meaning.
     *
     * @since   JDK1.1
     */
    public void mark(int readAheadLimit) {
        throw new UnsupportedOperationException();
    }

    /**
     * Resets the buffer to the marked position.  The marked position
     * is 0 unless another position was marked or an offset was specified
     * in the constructor.
     */
    public void reset() {
        pos = mark;
    }
	public int length() {
		return count - pos;
	}

	public byte[] asBytes() {
		byte[] b = null;
		if (pos == 0 && count == buf.length)
			b = buf;
		else {
			b = new byte[count - pos];
			System.arraycopy(buf, pos, b, 0, b.length);
		}
		return b;
	}

	public InputStream asStream() {
		return new BytesInputStream(buf, pos, count - pos);
	}

	public void writeTo(OutputStream os) throws IOException {
		if (os == null)
			throw new NullPointerException("OutputStream is null");
		os.write(buf, pos, count - pos);
	}
    
}
