//package com.common.util;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//import org.junit.Test;
//import org.springframework.security.core.codec.Hex;
//
//public class MD5Util {
//	/**
//	 * Md5加密
//	 * @param password
//	 * @return
//	 */
//	public static String generateMd5Code(String password){
//		char[] encode = null;
//		try {
//			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//			byte[] digest = messageDigest.digest(password.getBytes());
//			encode = Hex.encode(digest);
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		}
//		return new String(encode);
//	}
//	
//	@Test
//	public void query(){
//		System.out.println(generateMd5Code("123456"));
//	}
//}
