package com.xiss.util;

import java.nio.ByteBuffer;
import java.util.Random;

import com.xiss.util.encoder.Base64Encoder;
import com.xiss.util.hash.JenkinsHash;

/**
 * 混淆数值ID，使其不易被看出。但同时能读取未混淆的数值ID。混淆后的值区分大小写。
 * 
 * @author zhy
 *
 */
public class IdMangler {
	
	/** 默认的magic数 */
	private static final int MAGIC = 0x6bb9e3f2;
	/** 用于生成随机magic数的RNG */
	private static final Random RANDOM = new Random(System.currentTimeMillis());
	
	private static void weave(byte[] data) {
		int half = data.length / 2, stride = data.length - 1;
		int high, low;
		for (int i = 0; i < half; i++) {
			high = (data[i] & 0x03) |
				   ((data[i + stride] & 0x0f) << 2) |
				   ((data[i] & 0x0c) << 4);
			low = (data[i] & 0xc0) |
				  ((data[i + stride] & 0xf0) >> 2) | 
				  ((data[i] & 0x30) >> 4);
			data[i] = (byte) high;
			data[i + stride] = (byte) low;
			stride -= 2;
		}
	}
	
	private static void unweave(byte[] data) {
		int half = data.length / 2, stride = data.length - 1;
		int high, low;
		for (int i = 0; i < half; i++) {
			low = ((data[i] & 0x3c) >> 2) | 
				  ((data[i + stride] & 0x3c) << 2);
			high = ((data[i] & 0xc0) >> 4) | 
				   (data[i] & 0x03) |
				   (data[i + stride] & 0xc0) | 
				   ((data[i + stride] & 0x03) << 4);
			data[i] = (byte) high;
			data[i + stride] = (byte) low;
			stride -= 2;
		}
	}
	
	/** 
	 * 对输入字符串进行混淆，使其原值不能被一眼看出。
	 * 
	 * @param data
	 * @return
	 */
	public static String weave(String data) {
		if (data == null || data.isEmpty())
			return data;
		byte[] bytes = data.getBytes();
		weave(bytes);
		return new String(Base64Encoder.URL.encode(bytes));
	}
	
	/**
	 * 从混淆后的字符串解出原始字符串。
	 * 
	 * @param data
	 * @return
	 */
	public static String unweave(String data) {
		if (data == null || data.isEmpty())
			return data;
		byte[] bytes = Base64Encoder.URL.decode(data.getBytes());
		unweave(bytes);
		return new String(bytes);
	}
	
	/**
	 * 对ID进行混淆，使用默认的magic数。
	 * 
	 * @param id 要混淆的ID
	 * @return 混淆后的ID
	 */
	public static String mangle(long id) {
		return mangle(id, false);
	}
	
	/**
	 * 对ID进行混淆，可使用随机的magic数。
	 * 
	 * @param id 要混淆的ID
	 * @param randomMagic 是否使用随机的magic数，如为false，使用默认的magic数
	 * @return 混淆后的ID
	 */
	public static String mangle(long id, boolean randomMagic) {
		return mangle(id, randomMagic ? RANDOM.nextInt() : MAGIC);
	}
	
	/**
	 * 对ID进行混淆，使用指定的magic数。
	 * 
	 * @param id 要混淆的ID
	 * @param magic 使用的magic数
	 * @return 混淆后的ID
	 */
	public static String mangle(long id, int magic) {
		if (id == 0)
			return "0";
		// 如果id可表示成32位整数
		int high = (int) (id >> 32);
		boolean is4Bytes = high == 0;
		byte[] bytes = new byte[is4Bytes ? 12 : 16];
		ByteBuffer buf = ByteBuffer.wrap(bytes);
		if (is4Bytes)
			buf.putInt((int) id);
		else
			buf.putLong(id);
		// 加上magic数
		buf.putInt(magic);
		int hash = JenkinsHash.hash32(bytes, is4Bytes ? 8 : 12);
		buf.putInt(hash);
		weave(bytes);
		return new String(Base64Encoder.URL.encode(bytes));
	}

    private static long strictDemangle(String mangledId, boolean checkMagic, int magic) {
        if (mangledId == null || mangledId.isEmpty() || mangledId.equals("0"))
            return 0;
        long id = 0;
        if (mangledId.length() == 22 || mangledId.length() == 16) {
            byte[] bytes = Base64Encoder.URL.decode(mangledId.getBytes());
            if (bytes.length == 16 || bytes.length == 12) {
                unweave(bytes);
                ByteBuffer buf = ByteBuffer.wrap(bytes);
                id = bytes.length == 16 ? buf.getLong() : buf.getInt() & 0xffffffffL;
                int mag = buf.getInt();
                if (checkMagic && mag != magic)
                    return 0;
                int hash = buf.getInt();
                if (hash == JenkinsHash.hash32(bytes, bytes.length - 4))
                    return id;
            }
        }
        return id;
    }

	private static long demangle(String mangledId, boolean checkMagic, int magic) {
        long id = strictDemangle(mangledId, checkMagic, magic);
        if (id == 0) {
            try {
                id = Long.parseLong(mangledId);
            } catch (NumberFormatException nfe) {
                // Ignore
            }
        }
		return id;
	}

    /**
	 * 从混淆后的ID中解读出ID，不关心magic数的值。
	 * 
	 * @param mangledId 混淆后的ID，或未混淆ID（的字符串表示）
	 * @return 解读出的ID
	 */
    public static long strictDemangle(String mangledId) {
        return strictDemangle(mangledId, false, 0);
    }

	public static long demangle(String mangledId) {
		return demangle(mangledId, false, 0);
	}
	
	/**
	 * 从混淆后的ID中解读出ID。解读出的magic数必须与指定值匹配，如不匹配，返回0。
	 * 
	 * @param mangledId 混淆后的ID，或未混淆ID（的字符串表示）
	 * @param magic 指定的magic数
	 * @return 解读出的ID
	 */
    public static long strictDemangle(String mangledId, int magic) {
        return strictDemangle(mangledId, true, magic);
    }

	public static long demangle(String mangledId, int magic) {
		return demangle(mangledId, true, magic);
	}
	
	public static void main(String args[]) {
		long uid = 1;
		System.out.println("uid=" + uid);
		String encoded = mangle(uid, true);
		System.out.println("encoded=" + encoded);
		long decoded = demangle(encoded);
		System.out.println("decoded=" + decoded);
		decoded = demangle(String.valueOf(uid));
		System.out.println("decoded=" + decoded);
		

		Random random = new Random(System.currentTimeMillis());
		int count = 1000;
		long beginTime = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			uid = random.nextLong();
			encoded = mangle(uid);
			decoded = demangle(encoded);
			if (uid != decoded)
				throw new AssertionError();
			decoded = demangle(Long.toString(uid));
			if (uid != decoded)
				throw new AssertionError();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Run " + count + " times in " + (endTime - beginTime) + " milliseconds, in average " + ((endTime - beginTime + 0.0) / count) + " ms/run.");
	
		uid = random.nextInt();
		System.out.println("uid=" + uid);
		encoded = mangle(uid);
		System.out.println("encoded=" + encoded);
		decoded = demangle(encoded);
		System.out.println("decoded=" + decoded);
		decoded = demangle(String.valueOf(uid));
		System.out.println("decoded=" + decoded);
		
		String guid = "phone:13581530351";
		String woven = weave(guid);
		System.out.println(woven);
		System.out.println(unweave(woven));
	}

}
