package com.linkwee.xoss.funds.sdk.ifast.util;

import org.apache.commons.codec.binary.Base64;

public class Base64Utils {

	public static byte[] decrypt(String key) throws Exception {
		return Base64.decodeBase64(key);
	}

	public static String encrypt(byte[] key) throws Exception {
		return Base64.encodeBase64URLSafeString(key);
	}

}
