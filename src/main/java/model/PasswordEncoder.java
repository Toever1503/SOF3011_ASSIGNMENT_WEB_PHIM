package model;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
	private static PasswordEncoder passwordEncoder;
	private MessageDigest digest;

	private PasswordEncoder() {
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static PasswordEncoder getInstance() {
		if (passwordEncoder == null)
			passwordEncoder = new PasswordEncoder();
		return passwordEncoder;
	}

	public String encode(String str) {
		BigInteger hash = new BigInteger(1, digest.digest(str.getBytes()));
		return hash.toString(16);
	}

	public static void main(String[] args) {
		System.out.println(PasswordEncoder.getInstance().encode("1234"));
	}
}
