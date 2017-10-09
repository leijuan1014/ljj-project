package com.xiss.util.encoder;

import java.util.Arrays;

public class HexEncoder {
	
	public static byte[] decode(byte[] input) {
		if (input == null || input.length == 0)
			return input;
		int odd = input.length % 2 == 0 ? 0 : 1;
		byte[] output = new byte[(input.length + 1) / 2];
		if (odd == 1) {
			byte b = input[0];
			if (b >= '0' && b <= '9')
				output[0] = (byte) ((b - '0') & 0xf);
			else if (b >= 'a' && b <= 'f')
				output[0] = (byte) ((b - 'a' + 10) & 0xf);
			else if (b >= 'A' && b <= 'F')
				output[0] = (byte) ((b - 'A' + 10) & 0xf);
			else 
				throw new IllegalArgumentException("invalid hexadecimal digit " + (char) b);
		}
		for (int i = 0; i < input.length / 2; i++) {
			byte b = input[2 * i + odd];
			if (b >= '0' && b <= '9')
				output[i + odd] = (byte) (((b - '0') & 0xf) << 4);
			else if (b >= 'a' && b <= 'f')
				output[i + odd] = (byte) (((b - 'a' + 10) & 0xf) << 4);
			else if (b >= 'A' && b <= 'F')
				output[i + odd] = (byte) (((b - 'A' + 10) & 0xf) << 4);
			else 
				throw new IllegalArgumentException("invalid hexadecimal digit " + (char) b);
			
			b = input[2 * i + odd + 1];
			if (b >= '0' && b <= '9')
				output[i + odd] |= (byte) ((b - '0') & 0xf);
			else if (b >= 'a' && b <= 'f')
				output[i + odd] |= (byte) ((b - 'a' + 10) & 0xf);
			else if (b >= 'A' && b <= 'F')
				output[i + odd] |= (byte) ((b - 'A' + 10) & 0xf);
			else 
				throw new IllegalArgumentException("invalid hexadecimal digit " + (char) b);
		}
		return output;
	}
	
	public static byte[] encode(byte[] input) {
		return encode(input, false);
	}
	
	public static byte[] encode(byte[] input, boolean uppercase) {
		if (input == null || input.length == 0)
			return input;
		int a = uppercase ? 'A' : 'a';
		byte[] output = new byte[input.length * 2];
		for (int i = 0; i < input.length; i++) {
			byte b = (byte) ((input[i] >> 4) & 0xf);
			if (b <= 9)
				output[2 * i] = (byte) (b + '0');
			else 
				output[2 * i] = (byte) (b - 10 + a);
			
			b = (byte) (input[i] & 0xf);
			if (b <= 9)
				output[2 * i + 1] = (byte) (b + '0');
			else
				output[2 * i + 1] = (byte) (b - 10 + a);
		}
		return output;
	}
	
	public static final void main(String args[]) {
		byte[] input = new byte[256];
		for (int i = -128; i < 128; i++)
			input[i + 128] = (byte) i;
		byte[] output = encode(input, true);
		System.out.println(Arrays.toString(input));
		System.out.println(new String(output));
		output = Arrays.copyOfRange(output, 1, output.length);
		output[0] = (byte) '1';
		byte[] input2 = decode(output);
		System.out.println(input2[0] == 1);
		System.out.println(Arrays.equals(Arrays.copyOfRange(input, 1, input.length), Arrays.copyOfRange(input2, 1, input2.length)));
	}

}
