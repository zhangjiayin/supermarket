package com.linkwee.xoss.thirdsdk.xiaoying.open;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linkwee.xoss.thirdsdk.xiaoying.open.utils.XYCommonUtil;

/**
 * Copyright (c) 2016.  深圳市赢众通金融信息服务股份有限公司  All Rights Reserved.
 */
public class XYOpenSDK {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(XYOpenSDK.class);
	
    private static XYOpenSDK instance = new XYOpenSDK();

    private SSLContext sslContext;
    private KeyManagerFactory keyManagerFactory;
    private TrustManagerFactory trustManagerFactory;
    private String appId;

    public static XYOpenSDK sharedInstance() {
        return instance;
    }

    /**
     * 初始化SDK
     * @param keystorePath 客户端本地p12证书路径
     * @param keystorePwd  客户端本地p12证书密码
     * @param trustedKeystorePath 服务端验证证书路径
     * @param trustedKeystorePwd  服务端验证证书密码
     * @return
     */
    public boolean init(String keystorePath, String keystorePwd, String trustedKeystorePath, String trustedKeystorePwd) {
        try {
            sslContext = SSLContext.getInstance("TLS");

            KeyStore keyStore = KeyStore.getInstance("pkcs12");
            InputStream inputStream = new FileInputStream(keystorePath);
            keyStore.load(inputStream, keystorePwd.toCharArray());
            keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
            keyManagerFactory.init(keyStore, keystorePwd.toCharArray());

            KeyStore trustedKeyStore = KeyStore.getInstance("JKS");
            InputStream inputStream1 = new FileInputStream(trustedKeystorePath);
            trustedKeyStore.load(inputStream1, trustedKeystorePwd.toCharArray());
            trustManagerFactory = TrustManagerFactory.getInstance("SunX509");
            trustManagerFactory.init(trustedKeyStore);

            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
//            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

            sslContext.init(keyManagers, null, new SecureRandom());
            //sslContext.init(keyManagers, new TrustManager[]{tm}, new SecureRandom());

        } catch (NoSuchAlgorithmException sslContextException) {
            System.out.println(sslContextException.getMessage());
            return false;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return false;
        } catch (CertificateException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage());
            return false;
        }

        return true;
    }


    public void setAppId(String appId) {
        this.appId = appId;
    }

//    /**
//     * 发起API请求
//     * @param request
//     * @return http status code 200时的XYResponse
//     * @throws XYResponseException
//     */
//    public XYResponse apiRequest(XYRequest xyRequest) throws XYResponseException {
//        Response okResponse = null;
//
//        try {
//            TrustManager trustManager = trustManagerFactory.getTrustManagers()[0];
//
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManager)
//                    .hostnameVerifier(new OpenSDKHostNameVerifier()).build();
//
//            RequestBody formBody = xyRequest.formBodyBuilder()
//                    .add("appId", appId)
//                    .add("ver", "1.0")
//                    .build();
//
//
//            LOGGER.info("请求地址："+xyRequest.getUrl());
//
//            Request request = new Request.Builder()
//                    .url(xyRequest.getUrl())
//                    .header("User-Agent", "OpenSDK-Java")
//                    .post(formBody)
//                    .build();
//
//            okResponse = client.newCall(request).execute();
//            if (okResponse.code() == 200) {
//                LOGGER.info(okResponse.body().string());
//
//                XYResponse xyResponse = new XYResponse();
//                xyResponse.setRespData(JSON.parseObject(okResponse.body().string()));
//                xyResponse.setResult(0);
//
//                return xyResponse;
//            } else {
//                int code = okResponse.code();
//                LOGGER.warn("http code exception:" + code);
//                XYResponseException xyResponseException = new XYResponseException("http code exception:" + code);
//                xyResponseException.setHttpCode(code);
//                xyResponseException.setHttpBody(okResponse.body().string());
//
//                throw xyResponseException;
//            }
//        } catch (IOException e) {
//            LOGGER.error(e.getMessage());
//
//            XYResponseException xyResponseException = new XYResponseException(e.getMessage());
//            throw xyResponseException;
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
//
//            XYResponseException xyResponseException = new XYResponseException(e.getMessage());
//            throw xyResponseException;
//        }finally {
//            if (okResponse != null) {
//                okResponse.body().close();
//            }
//        }
//    }
    
    /**
     * 发起API请求
     * @param request
     * @return http status code 200时的XYResponse
     * @throws XYResponseException
     */
    public String apiRequestNew(String requestUrl,Object requestObj){
        Response okResponse = null;

        try {
            TrustManager trustManager = trustManagerFactory.getTrustManagers()[0];

            OkHttpClient client = new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManager)
                    .hostnameVerifier(new OpenSDKHostNameVerifier()).build();

            RequestBody formBody = XYCommonUtil.getRequestBuilder(requestObj)
                    .add("appId", appId)
                    .add("ver", "1.0")
                    .build();

            Request request = new Request.Builder()
                    .url(requestUrl)
                    .header("User-Agent", "OpenSDK-Java")
                    .post(formBody)
                    .build();

            okResponse = client.newCall(request).execute();
            String returnStr = okResponse.body().string();
            LOGGER.info("小赢科技请求返回：returnStr={}",returnStr);
            if (okResponse.code() == 200) {
                return returnStr;
            }
        } catch (Exception e) {
            LOGGER.error("小赢科技请求异常",e);
        }finally {
            if (okResponse != null) {
                okResponse.body().close();
            }
        }
        return null;
    }

    class OpenSDKHostNameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            if (hostname.equals(XYConstants.SSLVerifyHostName)) {
                return true;
            }
            return false;
        }
    }
}
