package com.linkwee.xoss.funds.sdk.ifast.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.linkwee.xoss.funds.sdk.ifast.constant.Constants;

public class SignatureHelper {
	
	public static String createSignature(String url, Map<String, String> headersAndParams, String privateKey) throws Exception {
		url = createSortedUrl(url, headersAndParams);
		return RSAUtils.sign(url.getBytes(Constants.ENCODING), privateKey);
	}

	private static String createSortedUrl(String url, Map<String, String> headersAndParams) {
		final String params = createSortedParams(headersAndParams);
		if (StringUtils.isNotBlank(params)){
			if(url.indexOf(Constants.SPE5) != -1){
				url += Constants.SPE3;
			}else{
				url += Constants.SPE5;
			}
		}
		return url + params;
	}

	private static String createSortedParams(final Map<String, String> params) {
		final StringBuffer content = new StringBuffer();
		final List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		for (int i = 0; i < keys.size(); i++) {
			final String key = keys.get(i);
			final String value = params.get(key);
			if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
				if(i != 0){
					content.append(Constants.SPE3);
				}
				content.append(key).append(Constants.SPE4).append(value);
			}
		}
		return content.toString();
	}
}
