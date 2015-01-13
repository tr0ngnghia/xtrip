package com.sns.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class SecurityHelper {
	private static byte[] raw = new byte[] { 'M', 'y', 'S', 'N', 'S', 'A', 'p',
			'p', 'l', 'i', 'c', 'a', 't', 'i', 'o', 'n' };
	private static String secretKey = "MySNSApplication";
	private static SecureRandom rnd = new SecureRandom();
	private static IvParameterSpec iv = new IvParameterSpec(
			rnd.generateSeed(16));

	private SecurityHelper() {

	}

	public static String encryptAESAlgorithm(String value) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(value.getBytes());
			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String decryptAESAlgorithm(String encrypted) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes(),
					"AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));
			return new String(original);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String getSecurePassword(String value) {
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(secretKey.getBytes());
			byte[] bytes = md.digest(value.getBytes());

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}
}
