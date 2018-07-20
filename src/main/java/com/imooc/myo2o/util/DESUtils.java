package com.imooc.myo2o.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DESUtils {

	private static Key key;
	//设置密钥key
	private static String KEY_STR = "myKey";
	private static String CHARSETNAME = "UTF-8";
	private static String ALGORITHM = "DES"; 

	static {
		try {
			//生成DES算法对象
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			//运用SHA1安全策略
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			//设置上密钥种子
			secureRandom.setSeed(KEY_STR.getBytes());
			//初始化基于SHA1的算法对象
			generator.init(secureRandom);
			//生成密钥对象
			key = generator.generateKey();
			generator = null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 获取加密后的信息
	 * @param str
	 * @return
	 */

	public static String getEncryptString(String str) {
		@SuppressWarnings("restriction")
		//基于BASE64编码，接收byte[]并转化成string
		BASE64Encoder base64encoder = new BASE64Encoder();
		try {
			byte[] bytes = str.getBytes(CHARSETNAME);
			//获取加密对象
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			//初始化密码信息
			cipher.init(Cipher.ENCRYPT_MODE, key);
			//机密==加密
			byte[] doFinal = cipher.doFinal(bytes);
			//返回string
			return base64encoder.encode(doFinal);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	/**
	 * 获取解密后的信息
	 * @param str
	 * @return
	 */

	public static String getDecryptString(String str) {
		BASE64Decoder base64decoder = new BASE64Decoder();
		try {
			byte[] bytes = base64decoder.decodeBuffer(str);
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] doFinal = cipher.doFinal(bytes);
			return new String(doFinal, CHARSETNAME);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getEncryptString("wx202abfb5363f2c4d"));
		System.out.println(getEncryptString("d6fe30fa048e82ffe69fdccc74e02119"));
		System.out.println(getDecryptString("WnplV/ietfQ="));
		System.out.println(getDecryptString("QAHlVoUc49w="));
	}

}