package com.linkwee.xoss.funds.sdk.ifast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkwee.xoss.funds.sdk.ifast.util.HttpUtil;

public class Client {
	
	private static Logger LOGGER = LoggerFactory.getLogger(Client.class);

	public static Response execute(Request request){
		Response response = new Response();
		try {
			switch (request.getMethod()) {
			case GET:
				return HttpUtil.httpGet(request.getHost(), request.getPath(), request.getQuerys(), request.getApiKey(), request.getPrivateKey(),request.getApiVersion());
			case POST_FORM:
				return HttpUtil.httpPost(request.getHost(), request.getPath(), request.getQuerys(), request.getBodys(), request.getApiKey(), request.getPrivateKey(),request.getApiVersion());
			case POST_STRING:
				return HttpUtil.httpPost(request.getHost(), request.getPath(), request.getQuerys(), request.getStringBody(), request.getApiKey(), request.getPrivateKey(),request.getApiVersion());
			case POST_BYTES:
				return HttpUtil.httpPost(request.getHost(), request.getPath(), request.getQuerys(), request.getBytesBody(), request.getApiKey(), request.getPrivateKey(),request.getApiVersion());
			case POST_JSON:
				return HttpUtil.httpPostJSON(request.getHost(), request.getPath(), request.getQuerys(), request.getStringBody(), request.getApiKey(), request.getPrivateKey(),request.getApiVersion());
			default:
				throw new IllegalArgumentException(String.format("unsupported method:%s", request.getMethod()));
			}	
		} catch (Exception e) {
			LOGGER.error("查询奕丰基金信息异常",e);
		}
		return response;
	}
}
