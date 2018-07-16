/**
 * 版权声明： 版权所有 违者必究 2012
 * 日    期：12-6-2
 */
package demo.utils;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @描述：签名算法
 *
 * @author Bob
 * @时间  2015年8月21日下午3:05:20
 *
 */
public class SignUtils {
    
    private static final String UTF8 = "UTF-8";

    /**
     * 使用<code>secret</code>对paramValues按以下算法进行签名： <br/>
     * uppercase(hex(sha1(secretkey1value1key2value2...secret))
     *
     * @param paramValues 参数列表
     * @param secret
     * @return
     */
    public static String sign(Map<String, String> paramValues, String secret) {
        return sign(paramValues,null,secret);
    }

    /**
     * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
     * @param paramValues
     * @param ignoreParamNames
     * @param secret
     * @return
     */
    public static String sign(Map<String, String> paramValues, List<String> ignoreParamNames,String secret) {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            if(ignoreParamNames != null && ignoreParamNames.size() > 0){
                for (String ignoreParamName : ignoreParamNames) {
                    paramNames.remove(ignoreParamName);
                }
            }
            Collections.sort(paramNames);
            sb.append(secret);
            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(secret);
            byte[] digest = getMD5Digest(sb.toString());
            return byte2hex(digest);
        } catch (IOException e) {
            throw new IllegalArgumentException("签名错误:",e);
        }
    }

    public static String signByString(String string){
        byte[] digest;
		try {
			digest = getMD5Digest(string);
			 return byte2hex(digest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return null;
    }
    
    private static byte[] getMD5Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes(UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.getMessage());
        }
        return bytes;
    }

    /**
     * 二进制转十六进制字符串
     *
     * @param bytes
     * @return
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }
    
    /**
     * 签名校验
     * @param request
     * @param secret
     * @return
     */
    public static  boolean checkSign(HttpServletRequest request,String secret){
    	HashMap<String, String> params = getRequestParams(request);
    	params.remove("sign");
    	String sign = SignUtils.sign(params, secret);
    	if(!sign.equals(request.getParameter("sign"))){
    		return false;
    	}
    	return true;
    }


    /**
     * HTTP请求参数转化Map对象
     * @param request
     * @return
     */
    public static HashMap<String, String> getRequestParams(HttpServletRequest request) {
    	Map<String, String[]> srcParamMap = request.getParameterMap();
    	HashMap<String, String> destParamMap = new HashMap<String, String>();
    	for (String obj : srcParamMap.keySet()) {
    		String[] values = srcParamMap.get(obj);
    		if (values != null && values.length > 0) {
    			destParamMap.put(obj, values[0]);
    		} else {
    			destParamMap.put(obj, null);
    		}
    	}
    	return destParamMap;
    }
}

