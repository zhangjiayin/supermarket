package com.linkwee.xoss.funds.sdk.ifast.util;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

import com.linkwee.xoss.funds.sdk.ifast.constant.Constants;

public class RSAUtils {

	public static String sign(byte[] data, final String privateKey) throws Exception {
		byte[] keyBytes = Base64Utils.decrypt(privateKey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(Constants.KEY_ALGORITHM);
		PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
		Signature signature = Signature.getInstance(Constants.SIGNATURE_ALGORITHM);
		signature.initSign(privateK);
		signature.update(data);
		return Base64Utils.encrypt(signature.sign());
	}
}