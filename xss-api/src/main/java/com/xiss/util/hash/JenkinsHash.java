package com.xiss.util.hash;

public class JenkinsHash {
	
	public static int hash32(byte[] data) {
		return new com.vijaykandy.JenkinsHash().hash32(data);
	}
	
	public static int hash32(byte[] data, int length) {
		return new com.vijaykandy.JenkinsHash().hash32(data, length);
	}
	
	public static long hash64(byte[] data) {
		return new com.vijaykandy.JenkinsHash().hash64(data);
	}

	public static long hash64(byte[] data, int length) {
		return new com.vijaykandy.JenkinsHash().hash64(data, length);
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
