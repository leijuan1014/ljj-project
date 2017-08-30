package com.xiss.util.encoder;

/**
 * Base64 encoder.
 * <p>
 * The base64 encoder uses a 64-character alphabet consisting of upper- and 
 * lower-case Roman alphabet characters (A-Z, a-z), the numerals (0-9), and the 
 * "+" and "/" symbols. The "=" symbol is also used as a special suffix code.
 * <p>
 * To convert binary data to base64 printable encoding, the first byte is 
 * placed in the most significant eight bits of a 24-bit buffer, the next in 
 * the middle eight, and the third in the least significant eight bits. If 
 * there are fewer than three bytes left to encode (or in total), the remaining 
 * buffer bits will be zero. The buffer is then used, six bits at a time, most 
 * significant first, as indices into the string: 
 * "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", and the 
 * indicated character is output.
 * 
 * @author haiyunzhao
 *
 */
public class Base64Encoder {
	
	// Standard base64 uses '+' and '/' as the characters for 62 and 63, 
	// respectively
	public static final Base64Encoder STANDARD = new Base64Encoder('+', '/');
	
	// Modified base64 for URL uses '-' and '_' as the characters for 62 and 63,
	// respectively
	public static final Base64Encoder URL = 
		new Base64Encoder('-', '_');
	
	// Modified base64 for RegExp uses '!' and '-' as the characters for 62 and
	// 63, respectively
	public static final Base64Encoder REGEXP = 
		new Base64Encoder('!', '-');
	
	// Modified base64 for filename uses '+' and '-' as the characters for 62 
	// 63, respectively.
	public static final Base64Encoder FILENAME = 
		new Base64Encoder('+', '-');
	
	private final byte char62;
	private final byte char63;
	
	protected Base64Encoder(char charFor62, char charFor63) {
		char62 = (byte) charFor62;
		char63 = (byte) charFor63;
	}
	
	/**
	 * Convert a 6-bit number to corresponding base64 character. Assumes the 
	 * input value is non-negative and in the range 0-63.
	 * 
	 * @param value
	 * @return
	 */
	private byte toChar(int value) {
		if (value < 26)
			return (byte) (value + 'A');
		else if (value < 52)
			return (byte) (value - 26 + 'a');
		else if (value < 62)
			return (byte) (value - 52 + '0');
		else if (value == 62)
			return char62;
		else 
			return char63;
	}
	
	/**
	 * Convert a base64 character to its 6-bit number.
	 * 
	 * @param value
	 * @return
	 */
	private byte fromChar(int value) {
		if (value >= 'A' && value <= 'Z')
			return (byte) (value - 'A');
		else if (value >= 'a' && value <= 'z')
			return (byte) (value - 'a' + 26);
		else if (value >= '0' && value <= '9')
			return (byte) (value - '0' + 52);
		else if (value == char62)
			return 62;
		else if (value == char63)
			return 63;
		else 
			return 0;
	}
	
	/**
	 * Base64 encoding without '=' padding.
	 * 
	 * @param input
	 * @return
	 */
	public byte[] encode(byte[] input) {
		return encode(input, false);
	}
	
	/**
	 * If padding is true, then standard base64 encoding is used ('=' padding).
	 * 
	 * If padding is false, then modified base64 encoding is used. Modified 
	 * base64 simply omits the padding and ends immediately after the last 
	 * BASE64 digit containing useful bits (leaving 0-4 unused bits in the last 
	 * base64 digit).
	 * 
	 * @param input
	 * @param padding
	 * @return
	 */
	public byte[] encode(byte[] input, boolean padding) {
		if (input == null || input.length == 0)
			return null;
		
		int len = (input.length / 3) * 4;
		if (input.length % 3 != 0)
			len += padding ? 4 : ((input.length % 3) + 1);
		
		byte[] result = new byte[len];
		for (int i = 0, j = 0; i + 2 < input.length; i += 3, j += 4) {
			result[j] = toChar((input[i] & 0xFC) >> 2);
			result[j + 1] = toChar(((input[i] & 0x03) << 4)
					| ((input[i + 1] & 0xF0) >> 4));
			result[j + 2] = toChar(((input[i + 1] & 0x0F) << 2) 
					| ((input[i + 2] & 0xC0) >> 6));
			result[j + 3] = toChar(input[i + 2] & 0x3F);
		}
		int start = 0;
		switch (input.length % 3) {
		case 1:
			start = padding ? (len - 4) : (len - 2);
			result[start] = toChar((input[input.length - 1] & 0xFC) >> 2);
			result[start + 1] = toChar((input[input.length - 1] & 0x03) << 4);
			if (padding) {
				result[start + 2] = '=';
				result[start + 3] = '=';
			}
			break;
		case 2:
			start = padding ? (len - 4) : (len - 3);
			result[start] = toChar((input[input.length - 2] & 0xFC) >> 2);
			result[start + 1] = toChar(((input[input.length - 2] & 0x03) << 4) 
					| ((input[input.length - 1] & 0xF0) >> 4));
			result[start + 2] = toChar((input[input.length - 1] & 0x0F) << 2);
			if (padding)
				result[start + 3] = '=';
			break;
		default:
			break;
		}

		return result;
	}
	
	/**
	 * Base64 decoding.
	 * 
	 * @param input
	 * @return
	 */
	public byte[] decode(byte[] input) {
		if (input == null || input.length < 2)
			return null;

		int len = (input.length / 4) * 3;
		if (input.length % 4 != 0)
			// it's modified base64 encoding
			len += (input.length % 4) - 1;
		else 
			// standard base64 encoding
			// how many bytes are padded?
			if (input[input.length - 1] == '=')
				len -= (input[input.length - 2] == '=') ? 2 : 1;
		
		byte[] result = new byte[len];
		
		for (int i = 0, j = 0; i + 3 < input.length; i += 4, j += 3) {
			result[j] = (byte) ( (fromChar(input[i]) << 2) 
					| (fromChar(input[i + 1]) >> 4) );
			if (j + 1 < len)
				result[j + 1] = (byte) ( (fromChar(input[i + 1]) << 4) 
						| (fromChar(input[i + 2]) >> 2) );
			if (j + 2 < len)
				result[j + 2] = (byte) ( (fromChar(input[i + 2]) << 6) 
						| fromChar(input[i + 3]) );
		}
		switch (input.length % 4) {
		case 2:
			result[len - 1] = (byte) ( (fromChar(input[input.length - 2]) << 2) 
					| (fromChar(input[input.length - 1]) >> 4) );
			break;
		case 3:
			result[len - 2] = (byte) ( (fromChar(input[input.length - 3]) << 2) 
					| (fromChar(input[input.length - 2]) >> 4) );
			result[len - 1] = (byte) ( (fromChar(input[input.length - 2]) << 4) 
					| (fromChar(input[input.length - 1]) >> 2) );
			break;
		default:
			break;
		}
		
		return result;
	}
	
	public static final void main(String args[]) {
		try {
			String text = "Man is distinguished, not only by his reason, "
				+ "but by this singular passion from other animals, which is a "
				+ "lust of the mind, that by a perseverance of delight in the "
				+ "continued and indefatigable generation of knowledge, "
				+ "exceeds the short vehemence of any carnal pleasure.";
			
			Base64Encoder encoder = Base64Encoder.STANDARD;
			byte[] encoded = encoder.encode(text.getBytes("ASCII"));
			for (int i = 0; i < encoded.length; i++) {
				System.out.print((char) encoded[i]);
				if (i % 76 == 75)
					System.out.println();
			}
			System.out.println();
			
			System.out.println();
			byte[] decoded = encoder.decode(encoded);
			for (int i = 0; i < decoded.length; i++) 
				System.out.print((char) decoded[i]);
			System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
