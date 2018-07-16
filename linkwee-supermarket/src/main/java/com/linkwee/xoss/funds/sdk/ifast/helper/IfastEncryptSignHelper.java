package com.linkwee.xoss.funds.sdk.ifast.helper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.api.response.funds.ifast.EncryptionBaseResponse;
import com.linkwee.web.service.CimFundInfoService;
import com.linkwee.web.service.CrmOrgAcctRelService;

@Component
public class IfastEncryptSignHelper{
	
	private static Logger LOGGER = LoggerFactory.getLogger(IfastEncryptSignHelper.class);
	
	@Resource
	private CimFundInfoService cimFundInfoService;
	@Resource
	private CrmOrgAcctRelService crmOrgAcctRelService;
	
	private static String KEY = "d8eVGiO6Vvy6YH4v";
	
	@PostConstruct
	private void initKey(){
		KEY = StringUtils.isNotBlank(cimFundInfoService.getSelfSecretByOrgNumber("OPEN_IFAST_WEB"))?cimFundInfoService.getSelfSecretByOrgNumber("OPEN_IFAST_WEB"):KEY;
	}

	public String encrypt(String value) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));

			return Base64.encodeBase64String(encrypted);
		} catch (Exception ex) {
			LOGGER.error("奕丰基金数据加密异常", ex);
		}
		return null;
	}

	public String decrypt(String encrypted) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

			return new String(original);
		} catch (Exception ex) {
			LOGGER.error("奕丰基金数据解密异常", ex);
		}
		return null;
	}
	
	public String encryptionBaseData(String userId,String orgNumber){
		EncryptionBaseResponse encryptionBaseResponse = new EncryptionBaseResponse();		
//		CrmOrgAcctRel crmOrgAcctRel = new CrmOrgAcctRel();
//		crmOrgAcctRel.setUserId(userId);
//		crmOrgAcctRel.setOrgNumber(orgNumber);
//		crmOrgAcctRel = crmOrgAcctRelService.selectOne(crmOrgAcctRel);
		encryptionBaseResponse.setUserId(userId);
		
		return encrypt(JSONObject.toJSONString(encryptionBaseResponse));
	}
}
