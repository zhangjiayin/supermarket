package com.linkwee.xoss.funds.sdk.ifast.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.linkwee.web.service.impl.IfastFundServiceImpl;
import com.linkwee.xoss.funds.sdk.ifast.Response;
import com.linkwee.xoss.funds.sdk.ifast.constant.Constants;
import com.linkwee.xoss.funds.sdk.ifast.constant.ContentType;
import com.linkwee.xoss.funds.sdk.ifast.constant.HttpHeader;
import com.linkwee.xoss.funds.sdk.ifast.constant.SystemHeader;

public class HttpUtil {

	private static Logger LOGGER = LoggerFactory.getLogger(IfastFundServiceImpl.class);
	
	public static Response httpGet(final String host, final String path, final Map<String, String> querys, final String apiKey, final String privateKey, final String apiVersion) throws Exception {
		
		LOGGER.info("奕丰基金httpGet请求  path={} querys={}",new Object[]{path,JSONObject.toJSONString(querys)});
		
		final String urlPath = initSignUrl(path, querys);
		final Map<String, String> headers = initialBasicHeader(urlPath, apiKey, privateKey, apiVersion);

		HttpGet get = new HttpGet(host + initUrl(path, querys));
		get.setConfig(initQuestCinfig());
		for (Map.Entry<String, String> e : headers.entrySet()) {
			get.addHeader(e.getKey(), MessageDigestUtil.utf8ToIso88591(e.getValue()));
		}
		final HttpClientConnectionManager hccm = createConnManager();
		final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(hccm).build();
		return convert(httpClient.execute(get));
	}

	public static Response httpPost(final String host, final String path, final Map<String, String> querys, final Map<String, String> bodys, final String apiKey, final String privateKey, final String apiVersion) throws Exception {
		final String urlPath = initSignUrl(path, querys);
		final Map<String, String> headers = initialBasicHeader(urlPath, apiKey, privateKey, apiVersion);
		headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_FORM);


		final HttpClientConnectionManager hccm = createConnManager();
		final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(hccm).build();

		HttpPost post = new HttpPost(host + initUrl(path, querys));
		post.setConfig(initQuestCinfig());
		for (Map.Entry<String, String> e : headers.entrySet()) {
			post.addHeader(e.getKey(), MessageDigestUtil.utf8ToIso88591(e.getValue()));
		}

		UrlEncodedFormEntity formEntity = buildFormEntity(bodys);
		if (formEntity != null) {
			post.setEntity(formEntity);
		}

		return convert(httpClient.execute(post));
	}

	public static Response httpPost(final String host, final String path, final Map<String, String> querys, final String body, final String apiKey, final String privateKey, final String apiVersion) throws Exception {
		final String urlPath = initSignUrl(path, querys);
		final Map<String, String> headers = initialBasicHeader(urlPath, apiKey, privateKey, apiVersion);

		final HttpClientConnectionManager hccm = createConnManager();
		final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(hccm).build();

		HttpPost post = new HttpPost(host + initUrl(path, querys));
		for (Map.Entry<String, String> e : headers.entrySet()) {
			post.addHeader(e.getKey(), MessageDigestUtil.utf8ToIso88591(e.getValue()));
		}

		if (StringUtils.isNotBlank(body)) {
			post.setEntity(new StringEntity(body, Constants.ENCODING));

		}

		return convert(httpClient.execute(post));
	}

	public static Response httpPostJSON(final String host, final String path, final Map<String, String> querys, final String body, final String apiKey, final String privateKey, final String apiVersion) throws Exception {
		
		LOGGER.info("奕丰基金httpPostJSON请求  path={} querys={}  body={}",new Object[]{path,JSONObject.toJSONString(querys),body});
		
		final String urlPath = initSignUrl(path, querys);
		final Map<String, String> headers = initialBasicHeader(urlPath, apiKey, privateKey, apiVersion);

		headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_JSON);

		final HttpClientConnectionManager hccm = createConnManager();
		final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(hccm).build();

		HttpPost post = new HttpPost(host + initUrl(path, querys));
		for (Map.Entry<String, String> e : headers.entrySet()) {
			post.addHeader(e.getKey(), MessageDigestUtil.utf8ToIso88591(e.getValue()));
		}

		if (StringUtils.isNotBlank(body)) {
			post.setEntity(new StringEntity(body, Constants.ENCODING));

		}

		return convert(httpClient.execute(post));
	}

	public static Response httpPost(final String host, final String path, final Map<String, String> querys, final byte[] bodys, final String apiKey, final String privateKey, final String apiVersion) throws Exception {
		final String urlPath = initSignUrl(path, querys);
		final Map<String, String> headers = initialBasicHeader(urlPath, apiKey, privateKey, apiVersion);

		final HttpClientConnectionManager hccm = createConnManager();
		final CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(hccm).build();

		HttpPost post = new HttpPost(host + initUrl(path, querys));
		for (Map.Entry<String, String> e : headers.entrySet()) {
			post.addHeader(e.getKey(), MessageDigestUtil.utf8ToIso88591(e.getValue()));
		}

		if (bodys != null) {
			post.setEntity(new ByteArrayEntity(bodys));
		}

		return convert(httpClient.execute(post));
	}

	private static UrlEncodedFormEntity buildFormEntity(Map<String, String> formParam) throws UnsupportedEncodingException {
		if (formParam != null) {
			List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

			for (String key : formParam.keySet()) {
				nameValuePairList.add(new BasicNameValuePair(key, formParam.get(key)));
			}
			UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, Constants.ENCODING);
			formEntity.setContentType(ContentType.CONTENT_TYPE_FORM);
			return formEntity;
		}

		return null;
	}

	private static String initSignUrl(final String path,final Map<String, String> querys) {
		final StringBuilder sbUrl = new StringBuilder();
		if (!StringUtils.isBlank(path)) {
			sbUrl.append(path);
		}
		if (null != querys) {
			final List<String> keys = new ArrayList<String>(querys.keySet());
			Collections.sort(keys);
			StringBuilder sbQuery = new StringBuilder();
			for (int i = 0; i < keys.size(); i++) {
				final String key = keys.get(i);
				if (0 < sbQuery.length()) {
					sbQuery.append(Constants.SPE3);
				}
				if (StringUtils.isBlank(key) && StringUtils.isNotBlank(querys.get(key))) {
					sbQuery.append(querys.get(key));
				}
				if (StringUtils.isNotBlank(key)) {
					sbQuery.append(key);
					if (!StringUtils.isBlank(querys.get(key))) {
						sbQuery.append(Constants.SPE4);
						sbQuery.append(querys.get(key));
					}
				}
			}
			if (0 < sbQuery.length()) {
				sbUrl.append(Constants.SPE5).append(sbQuery);
			}
		}

		return sbUrl.toString();
	}

	private static String initUrl(final String path,final Map<String, String> querys) throws UnsupportedEncodingException {
		final StringBuilder sbUrl = new StringBuilder();
		if (!StringUtils.isBlank(path)) {
			sbUrl.append(path);
		}
		if (null != querys) {
			final List<String> keys = new ArrayList<String>(querys.keySet());
			Collections.sort(keys);
			StringBuilder sbQuery = new StringBuilder();
			for (int i = 0; i < keys.size(); i++) {
				final String key = keys.get(i);
				if (0 < sbQuery.length()) {
					sbQuery.append(Constants.SPE3);
				}
				if (StringUtils.isBlank(key) && StringUtils.isNotBlank(querys.get(key))) {
					sbQuery.append(URLEncoder.encode(querys.get(key), Constants.ENCODING));
				}
				if (StringUtils.isNotBlank(key)) {
					sbQuery.append(key);
					if (!StringUtils.isBlank(querys.get(key))) {
						sbQuery.append(Constants.SPE4);
						sbQuery.append(URLEncoder.encode(querys.get(key), Constants.ENCODING));
					}
				}
			}
			if (0 < sbQuery.length()) {
				sbUrl.append(Constants.SPE5).append(sbQuery);
			}
		}

		return sbUrl.toString();
	}

	private static Map<String, String> initialBasicHeader(final String url, final String apiKey, final String privateKey, final String apiVersion) throws Exception {
		final Map<String, String> headers = new HashMap<String, String>();
		headers.put(SystemHeader.X_API_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
		headers.put(SystemHeader.X_API_KEY, apiKey);
		headers.put(SystemHeader.X_API_VERSION, apiVersion);
		headers.put(SystemHeader.X_API_SIGNATURE, SignatureHelper.createSignature(url, headers, privateKey));
		return headers;
	}

	private static Response convert(HttpResponse response) throws IOException {
		Response res = new Response();

		if (null != response) {
			res.setStatusCode(response.getStatusLine().getStatusCode());
			for (Header header : response.getAllHeaders()) {
				res.setHeader(header.getName(), MessageDigestUtil.iso88591ToUtf8(header.getValue()));
			}

			res.setContentType(res.getHeader("Content-Type"));
			res.setBody(readStreamAsStr(response.getEntity().getContent()));
		} else {
			res.setStatusCode(500);
		}
		
		LOGGER.info("奕丰基金请求数据返回  statusCode={} body={}",new Object[]{res.getStatusCode(),res.getBody()});
		
		return res;
	}

	public static String readStreamAsStr(InputStream is) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		WritableByteChannel dest = Channels.newChannel(bos);
		ReadableByteChannel src = Channels.newChannel(is);
		ByteBuffer bb = ByteBuffer.allocate(4096);

		while (src.read(bb) != -1) {
			bb.flip();
			dest.write(bb);
			bb.clear();
		}
		src.close();
		dest.close();

		return new String(bos.toByteArray(), Constants.ENCODING);
	}

	private static HttpClientConnectionManager createConnManager() {
		PoolingHttpClientConnectionManager connManager = null;
		try {
			final SSLContext sslc = createIgnoreVerifySSL();
			final SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslc, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			final Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslsf).build();
			connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return connManager;
	}

	private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("SSLv3");
		X509TrustManager trustManager = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		sc.init(null, new TrustManager[]{trustManager}, null);
		return sc;
	}

	private static RequestConfig initQuestCinfig() {
		return RequestConfig.custom().setConnectTimeout(100000000).setConnectionRequestTimeout(100000000).setSocketTimeout(100000000).build();
	}

}