package com.onedollar.net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import android.content.Context;
import android.net.wifi.WifiManager;

/**
 * 
 * @author yoyo
 */
public class HttpManager {
	private static DefaultHttpClient sClient;
	private static boolean sNeedProxy = false;
	private static String sHostname = "";
	private static int sHostport = 0;
	public static final String ENCODE = "UTF-8";
	public static final String COOKIE = "Cookie";
	public static final String SET_COOKIE = "Set-Cookie";
	public static final String DOWN_FILE_CONTENT = "Content-Disposition";
	public static final String DOWN_FILE_SIZE = "Size";
	static final String HTTP = "http";
	static final String HTTPS = "https";
	static final int CONN_PER_ROUTE_BEAN = 12;
	static final int MAX_TOTAL_CONNECTIONS = 20;
	static final int CONNECTION_TIME_OUT = 3 * 60 * 1000;
	static final int SOCKET_TIME_OUT = 3 * 60 * 1000;
	static final int SOCKET_BUFFER_SIZE = 8 * 1024;;
	static final int HTTP_PROXY_PORT = 80;
	static final int HTTPS_PROXY_PORT = 443;

	static {
		initClient();
	}

	private static void initClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);
			SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, ENCODE);
			HttpProtocolParams.setUseExpectContinue(params, true);
			//HttpProtocolParams.setUserAgent(params, useragent);
			// HttpProtocolParams.setUserAgent(params, getUserAgent(con));

			ConnPerRoute connPerRoute = new ConnPerRouteBean(
					CONN_PER_ROUTE_BEAN);
			ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
			ConnManagerParams.setMaxTotalConnections(params,
					MAX_TOTAL_CONNECTIONS);

			HttpConnectionParams.setStaleCheckingEnabled(params, false);
			HttpConnectionParams.setConnectionTimeout(params,
					CONNECTION_TIME_OUT);
			HttpConnectionParams.setSoTimeout(params, SOCKET_TIME_OUT);
			HttpConnectionParams
					.setSocketBufferSize(params, SOCKET_BUFFER_SIZE);

			HttpClientParams.setRedirecting(params, false);
			ConnManagerParams.setMaxTotalConnections(params, 200);
			ConnPerRouteBean localConnPerRouteBean = new ConnPerRouteBean(20);
			HttpHost localHttpHost = new HttpHost("localhost", 80);
			HttpRoute localHttpRoute = new HttpRoute(localHttpHost);
			localConnPerRouteBean.setMaxForRoute(localHttpRoute, 5);
			ConnManagerParams.setMaxConnectionsPerRoute(params,
					localConnPerRouteBean);

			SchemeRegistry schReg = new SchemeRegistry(); 
			schReg.register(new Scheme(HTTP, PlainSocketFactory
					.getSocketFactory(), HTTP_PROXY_PORT));
			schReg.register(new Scheme(HTTPS, sf, HTTPS_PROXY_PORT));

			ClientConnectionManager conMgr = new ThreadSafeClientConnManager(
					params, schReg);
			sClient = new DefaultHttpClient(conMgr, params);
			sClient.getCookieSpecs().register("easy", new CookieSpecFactory() {
				public CookieSpec newInstance(HttpParams params) {
					return new BrowserCompatSpec() {
						@Override
						public void validate(Cookie cookie, CookieOrigin origin)
								throws MalformedCookieException {
							// Oh, I am easy
						}
					};
				}
			});
			sClient.getParams()
					.setParameter(ClientPNames.COOKIE_POLICY, "easy");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private static void initProxy(Context con) throws Exception {
		// ??��??�?认�??�?代�??主�??ip
		String proxyHost = android.net.Proxy.getDefaultHost();
		// ??��??代�????��????????为空设置?????????�?代�??
		sNeedProxy = (proxyHost != null);
		// ??��??�????wifi�????�?就�?????�?设置代�??�?
		if (((WifiManager) con.getSystemService(Context.WIFI_SERVICE))
				.isWifiEnabled()) {
			sNeedProxy = false;
		}
		if (sNeedProxy) {// �???????�?代�??
			sHostname = android.net.Proxy.getDefaultHost();// 代�??主�?��??
			sHostport = android.net.Proxy.getDefaultPort();// 代�??主�?��?????
			HttpHost proxy = new HttpHost(sHostname, sHostport);// 代�??主�?��?�象
			sClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					proxy);// 请�??对象设置代�??
		} else {
			sHostname = "";
			sHostport = 0;
		}
	}

	/**
	 * ??��???????��?��??�???????get请�??
	 * 
	 * @param get
	 *            HttpGet类�??�?�?对象
	 * @return HttpResponse 对�????????�?�????�?头�??信�?????�?�?
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws UnrecoverableKeyException
	 * @throws CertificateException
	 * @throws KeyStoreException
	 * @see BTCHttpConnection#httpSend�?�?
	 */
	public static HttpResponse execute(HttpGet get, Context con)
			throws Exception {
		initProxy(con);
		HttpResponse response;
		if (sNeedProxy) {
			response = sClient.execute(new HttpHost(sHostname, sHostport), get);
		} else {
			response = sClient.execute(get);
		}
		return response;
	}

	public static HttpResponse execute(HttpPost post, Context con)
			throws Exception {
		initProxy(con);
		HttpResponse response;
		if (sNeedProxy) {
			response = sClient
					.execute(new HttpHost(sHostname, sHostport), post);
		} else {
			response = sClient.execute(post);
		}
		return response;
	}
}

class SSLSocketFactoryEx extends SSLSocketFactory {

	SSLContext sslContext = SSLContext.getInstance("TLS");

	public SSLSocketFactoryEx(KeyStore truststore)
			throws NoSuchAlgorithmException, KeyManagementException,
			KeyStoreException, UnrecoverableKeyException {
		super(truststore);

		TrustManager tm = new X509TrustManager() {

			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {

			}

			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] chain, String authType)
					throws java.security.cert.CertificateException {

			}
		};

		sslContext.init(null, new TrustManager[] { tm }, null);
	}

	@Override
	public Socket createSocket(Socket socket, String host, int port,
			boolean autoClose) throws IOException, UnknownHostException {
		return sslContext.getSocketFactory().createSocket(socket, host, port,
				autoClose);
	}

	@Override
	public Socket createSocket() throws IOException {
		return sslContext.getSocketFactory().createSocket();
	}
}
