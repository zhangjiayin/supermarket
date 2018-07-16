package cn.xn.cache.common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public final class SignatureUtil {

	private static final Charset CHARSET = Charset.forName("UTF-8");

	private static final String HEX_CHARS = "0123456789abcdef";

	public static String sign(StringBuilder meta, String merKey)
			throws Exception {
		if (null == meta || meta.length() == 0 || isEmpty(merKey)) {
			return null;
		}
		String item = null;
		String key = null;
		String val = null;
		int pos = -1;
		List<String> keys = new ArrayList<String>();
		Map<String, String> vals = new HashMap<String, String>(30);
		StringTokenizer token = new StringTokenizer(
				meta.charAt(0) == '&' ? meta.substring(1) : meta.toString(),
				"&");
		while (token.hasMoreTokens()) {
			item = token.nextToken();
			pos = item.indexOf('=');
			if (pos == -1) {
				continue;
			}
			key = item.substring(0, pos);
			if ("sign".equals(key) || "signType".equals(key)) {
				continue;
			}
			val = item.substring(pos + 1);
			if (isEmpty(val)) {
				continue;
			}
			keys.add(key);
			vals.put(key, val);
			key = null;
			val = null;
		}
		if (keys.size() == 0) {
			return null;
		}
		Collections.sort(keys);
		String ky = null;
		StringBuilder rs = new StringBuilder(meta.length() + merKey.length()
				+ 2);
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			ky = it.next();
			rs.append("&").append(ky).append("=").append(vals.get(ky));
			ky = null;
		}
		String plain = rs.append("&").append(md5Hex(merKey.getBytes(CHARSET)))
				.substring(1);
		return md5Hex(plain.getBytes(CHARSET));
	}

	private static boolean isEmpty(String ori) {
		return null == ori || ori.trim().length() == 0;
	}

	private static String md5Hex(byte[] data) throws Exception {
		byte[] by = MessageDigest.getInstance("MD5").digest(data);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < by.length; i++) {
			sb.append(HEX_CHARS.charAt(by[i] >>> 4 & 0x0F));
			sb.append(HEX_CHARS.charAt(by[i] & 0x0F));
		}
		return sb.toString();
	}

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// �ֽ�����ת��Ϊ ʮ����� ��
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
