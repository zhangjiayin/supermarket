package com.openpltsdk.xyb.security;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.openpltsdk.xyb.entity.Message;
import com.openpltsdk.xyb.entity.ServiceData;
import com.openpltsdk.xyb.util.CryptException;



/**
 * 
 * 项目名称：xyb-third4toobei-sdk
 * 类名称：MessageCrypt
 * 类描述：加解密
 * @author zenggang
 * @date 创建时间：2017年1月11日 下午2:03:36
 * 修改人：zenggang
 * 修改时间：2017年1月11日 下午2:03:36
 * 修改备注：
 * @version V1.3.1
 * 
 */
public class MessageCrypt {
	private static Charset CHARSET = Charset.forName("utf-8");
	
	private String aesKey = "";

	private String token = "";
	
	private String appId = "";
	
	
	/**
	 * encryptMsg(加密)
	 * @param sd 数据实体
	 * @return Message 交互实体
	 * @author zenggang
	 * @Exception 异常对象
	 * @date 创建时间：2017年1月11日 上午11:31:07
	 * @version V1.3.1
	*/
	public Message encryptMsg(ServiceData sd) throws Throwable {
		String nonce = getRandomStr();
		Message msg = new Message();
		msg.setTimestamp(System.currentTimeMillis() / 1000);
		msg.setNonce(nonce);
		
		Gson gson = new Gson();
		byte[] jsonBytes = gson.toJson(sd).getBytes(CHARSET);
		byte[] networkBytesOrder = getNetworkBytesOrder(jsonBytes.length);
		byte[] appIdBytes = appId.getBytes(CHARSET);
		ByteGroup bg = new ByteGroup();
		bg.addBytes(nonce.getBytes(CHARSET));
		bg.addBytes(networkBytesOrder);
		bg.addBytes(jsonBytes);
		bg.addBytes(appIdBytes);
		byte[] padBytes = PKCS7Encoder.encode(bg.size());
		bg.addBytes(padBytes);
		// 获得最终的字节流, 未加密
		byte[] original = bg.toBytes();
		
		try {
			byte[] reqKey = getRequestKey(msg.getTimestamp());
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(reqKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(reqKey, 0, 16));
			cipher.init(Cipher.ENCRYPT_MODE, key_spec, iv);
			byte[] encrypted = cipher.doFinal(original);
			String data = Base64.encodeBase64String(encrypted);
			msg.setData(data);
			String signature = SHA1.getSHA1(token, msg);
			msg.setSignature(signature);
		}catch(CryptException e) {
			throw e;
		}catch(Exception e) {
			throw new CryptException(CryptException.ENCRYPT_AES_ERROR);
		}
		
		return msg;
	}
	
	/**
	 * decryptMsg(解密)
	 * @param msg 交互实体
	 * @return ServiceData 数据实体
	 * @author zenggang
	 * @Exception 异常对象
	 * @date 创建时间：2017年1月11日 上午11:31:46
	 * @version V1.3.1
	*/
	public ServiceData decryptMsg(Message msg) throws Throwable {
		
		String signature = SHA1.getSHA1(token, msg);
		if (!signature.equals(msg.getSignature())) {
			throw new CryptException(CryptException.VALIDATE_SIGNATURE_ERROR);
		}
		try {
			byte[] reqKey = getRequestKey(msg.getTimestamp());
			Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
			SecretKeySpec key_spec = new SecretKeySpec(reqKey, "AES");
			IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(reqKey, 0, 16));
			cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);
			byte[] encrypted = Base64.decodeBase64((msg.getData()));
			byte[] original = cipher.doFinal(encrypted);
			
			// 去除补位字符
			byte[] bytes = PKCS7Encoder.decode(original);
			// 分离16位随机字符串,网络字节序和AppId
			byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);
			int dataLength = recoverNetworkBytesOrder(networkOrder);
			String json = new String(Arrays.copyOfRange(bytes, 20, 20 + dataLength), CHARSET);
			String appId = new String(Arrays.copyOfRange(bytes, 20 + dataLength, bytes.length),
					CHARSET);
			
			if (!appId.equals(this.appId)) {
				throw new CryptException(CryptException.VALIDATE_APPID_ERROR);
			}
			
			JsonParser parser = new JsonParser();
			JsonObject root = parser.parse(json).getAsJsonObject();
			String service = root.get("service").getAsString();
			return new ServiceData(service, root.get("body"));
		}catch(CryptException e) {
			throw e;
		}catch(Exception e) {
			throw new CryptException(CryptException.DECRYPT_AES_ERROR);
		}
	}
	
	private byte[] getRequestKey(long timestamp) throws Throwable {
		String a = String.format("%s%d", aesKey, timestamp);
			MessageDigest md5;
			try {
				md5 = MessageDigest.getInstance("MD5");
				return md5.digest(a.getBytes(CHARSET));
			} catch (NoSuchAlgorithmException e) {
				throw new CryptException(CryptException.ENCRYPT_AES_ERROR);
			}
		
	}
	
	// 
	/**
	 * getNetworkBytesOrder(生成4个字节的网络字节序)
	 * @author zenggang
	 * @Exception 异常对象
	 * @date 创建时间：2017年1月11日 上午11:49:53
	 * @version V1.3.1
	*/
	byte[] getNetworkBytesOrder(int sourceNumber) {
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (sourceNumber & 0xFF);
		orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
		orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
		orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
		return orderBytes;
	}
	
	/**
	 * recoverNetworkBytesOrder(还原4个字节的网络字节序)
	 * @author zenggang
	 * @Exception 异常对象
	 * @date 创建时间：2017年1月11日 上午11:49:27
	 * @version V1.3.1
	*/
	int recoverNetworkBytesOrder(byte[] orderBytes) {
		int sourceNumber = 0;
		for (int i = 0; i < 4; i++) {
			sourceNumber <<= 8;
			sourceNumber |= orderBytes[i] & 0xff;
		}
		return sourceNumber;
	}

	/**
	 * getRandomStr(随机生成16位字符串)
	 * @return String
	 * @author zenggang
	 * @Exception 异常对象
	 * @date 创建时间：2017年1月10日 下午4:03:23
	 * @version V1.3.1
	*/
	String getRandomStr() {
		StringBuffer base = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789");
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 16; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 初始化MessageCrypt.
	 *
     * @param appId 应用编号
     * @param aesKey 密钥
     * @param token 密钥
	 # @date 创建时间：2016年3月10日 下午4:03:01
	 * @version V1.3.1
	 */
	public MessageCrypt(String appId,String aesKey,String token){
	    this.appId = appId;
	    this.aesKey = aesKey;
	    this.token = token;
	}
}
