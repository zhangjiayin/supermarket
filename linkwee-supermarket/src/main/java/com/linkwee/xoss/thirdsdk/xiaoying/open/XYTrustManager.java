package com.linkwee.xoss.thirdsdk.xiaoying.open;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Created by bbg on 04/01/2017.
 */
public class XYTrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        System.out.println("checkClientTrusted");
    }

    @Override
    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        System.out.println("checkServerTrusted");
        for (int i = 0; i < x509Certificates.length; i++) {
            System.out.println(x509Certificates[i].getPublicKey().toString());
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
