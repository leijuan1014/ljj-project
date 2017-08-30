package com.xiss.util.hash;

import org.getopt.util.hash.FNV1a32;
import org.getopt.util.hash.FNV1a64;

public class FNV1aHash {
	
	public static int hash32(byte[] data) {
		FNV1a32 fnv32_1a = new FNV1a32();
		fnv32_1a.init(data, 0, data.length);
		return (int) fnv32_1a.getHash();
	}
	
	public static int hash32(byte[] data, int offset, int len) {
		FNV1a32 fnv32_1a = new FNV1a32();
		fnv32_1a.init(data, offset, len);
		return (int) fnv32_1a.getHash();
	}
	
	public static long hash64(byte[] data) {
		FNV1a64 fnv64_1a = new FNV1a64();
		fnv64_1a.init(data, 0, data.length);
		return fnv64_1a.getHash();
	}
	
	public static long hash64(byte[] data, int offset, int len) {
		FNV1a64 fnv64_1a = new FNV1a64();
		fnv64_1a.init(data, offset, len);
		return fnv64_1a.getHash();
	}
	

	public static final void main(String args[]) {
		int count = 10000000;
//		byte[] data = "Openfire is a powerful instant messaging (IM) and chat server that implements the XMPP protocol. This document will guide you th".getBytes();
		byte[] data = "Openfire is a powerful instant messaging (IM) and chat server that implements the XMPP protocol. This document will guide you through installing Openfire. For a full list of features and more information, please visit the Openfire website: http://www.igniterealtime.org/projects/openfire/".getBytes();
//		System.out.println(hash32(data));
		System.out.println(hash64(data));
		long beginTime = System.nanoTime();
		for (int i = 0; i < count; i++) {
//			long hash = hash32(data);
			@SuppressWarnings("unused")
			long hash = hash64(data);
			if (i % 1000000 == 0)
				System.out.println(i);
		}
		long endTime = System.nanoTime();
		System.out.println("run " + count + " times in " 
				+ (endTime - beginTime) + " ns, in average "
				+ (endTime - beginTime) / count + " ns");
		System.out.println("hashed " + (data.length * (count + 0.0) / 1024 / 1024) 
				+ " MB, throughput "
				+ data.length * (count + 0.0) / 1024 / 1024 * 1000000000 / (endTime - beginTime) + " MB/second");
	}

}
