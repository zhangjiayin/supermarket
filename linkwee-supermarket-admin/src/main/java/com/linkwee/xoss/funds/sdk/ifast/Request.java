package com.linkwee.xoss.funds.sdk.ifast;

import java.util.Map;

import com.linkwee.web.model.CimFundInfo;
import com.linkwee.xoss.funds.sdk.ifast.constant.HttpSchema;
import com.linkwee.xoss.funds.sdk.ifast.enums.Method;

public class Request {

    public Request() {
    }

	public Request(Method method, String path,CimFundInfo cimFundInfo) {
		this.method = method;
		this.host = cimFundInfo.getRequestHost();
		this.path = path;
		this.apiKey = cimFundInfo.getApiKey();
		this.privateKey = cimFundInfo.getPrivateKey();
		this.apiVersion = cimFundInfo.getApiVersion();
	}

    public Request(Method method, String host, String path, String apiKey, String privateKey,String apiVersion) {
        this.method = method;
        this.host = HttpSchema.HTTPS+host;
        this.path = path;
        this.apiKey = apiKey;
        this.privateKey = privateKey;
        this.apiVersion = apiVersion;
    }

    private Method method;

    private String host;

    private String path;

    private String apiKey;

    private String privateKey;

    private String apiVersion;

    private Map<String, String> querys;

    private Map<String, String> bodys;

    private String stringBody;

    private byte[] bytesBody;


    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getHost() {
        return host;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

    public Map<String, String> getQuerys() {
        return querys;
    }

    public void setQuerys(Map<String, String> querys) {
        this.querys = querys;
    }

    public Map<String, String> getBodys() {
        return bodys;
    }

    public void setBodys(Map<String, String> bodys) {
        this.bodys = bodys;
    }

    public String getStringBody() {
        return stringBody;
    }

    public void setStringBody(String stringBody) {
        this.stringBody = stringBody;
    }

    public byte[] getBytesBody() {
        return bytesBody;
    }

    public void setBytesBody(byte[] bytesBody) {
        this.bytesBody = bytesBody;
    }
}
