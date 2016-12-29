package com.onedollar.net;

import android.content.Context;
import android.text.TextUtils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import com.onedollar.data.Constants;
import com.example.onedollar.R;
import com.onedollar.util.JsonParser;
import com.onedollar.util.Log;
import com.onedollar.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class HttpRequest {

	public static final String TAG = "HttpRequest";
	private static String mCookie = null;

	public HttpRequest() {

	}

	public static void setToken(String token) {
		mCookie = token;
	}

	private static String BULETOOTH_MAC = getBlueMacValue();

	/***
	 * �???��?????驱�??�? �???��?��??线�??�?�???��?��??
	 * 
	 * @return
	 */
	private static String getBlueMacValue() {
		if (BULETOOTH_MAC == null) {
			// BULETOOTH_MAC = .getDeviceBluetoothAddress();
		}
		return BULETOOTH_MAC;
	}

	/***
	 * �???��?��?�代???信�?? ?????��??�?认�?????头�?��??
	 * 
	 * @return
	 */
	public String getUserAgentInfo(Context context) {

		return null;
	}

	/**
	 * �???��?��??信�?????di??? ?????��?��??认�??�??????��?? �? user-agent=Android_4.0/TDRMPC_Pad_2.0.0.0/LG778;
	 * di= wifiMAC/BLUETOOTH/CPU/UUID/800*600;
	 * 
	 * @param context
	 * @return
	 */
	public String getDeviceDiInfo(Context context) {
		return null;
	}

	/****
	 * ???�????Post请�??头中?????????
	 * 
	 * @param httpPost
	 * @param context
	 */
	private void setPostHeadParam(HttpPost httpPost, Context context) {
		if (mCookie != null) {
			httpPost.setHeader(HttpManager.COOKIE, mCookie);
		}
		// httpPost.setHeader(Constants.string.CONTENT_TYPE,
		// "application/json");
		// httpPost.setHeader(Constants.string.TOKEN, "application/json");
	}

	/****
	 * ???�????Get请�??头中?????????
	 * 
	 * @param httpPost
	 * @param context
	 */
	private void setGetHeadParam(HttpGet httpGet, Context context) {
		if (mCookie != null) {
			httpGet.setHeader(HttpManager.COOKIE, mCookie);
		}
		// httpGet.setHeader(Constants.string.CONTENT_TYPE, "application/json");
		// httpGet.setHeader(Constants.string.TOKEN, "application/json");
	}

	/**
	 * ??????get 请�?????�? 交�??
	 * 
	 * @param context
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public HttpResponse requestGet(Context context, String url)
			throws Exception {
		HttpGet httpGet = new HttpGet(url);
		setGetHeadParam(httpGet, context);
		HttpResponse response = HttpManager.execute(httpGet, context);
		return response;
	}

	public HttpResponse requestGetWithSign(Context context, String url)
			throws Exception {
		HttpGet httpGet = new HttpGet(url);
		setGetHeadParam(httpGet, context);
		HttpResponse response = HttpManager.execute(httpGet, context);
		return response;
	}

	public HttpResponse requestPost(Context context,
			HashMap<String, String> hashMap, String url) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		setPostHeadParam(httpPost, context);
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
				Util.getBasicNameValuePairListSerializableData(hashMap),
				HttpManager.ENCODE);
		Log.i(TAG, "to new work params : ",
				Util.getBasicNameValuePairListSerializableData(hashMap));
		httpPost.setEntity(entity);
		return HttpManager.execute(httpPost, context);
	}

	public HttpResponse requestPostWithSign(Context context,
			HashMap<String, String> hashMap, String url) throws Exception {
		HttpPost httpPost = new HttpPost(url);
		setPostHeadParam(httpPost, context);
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(
				Util.getBasicNameValuePairListSerializableDataWithSign(hashMap),
				HttpManager.ENCODE);
		Log.i(TAG, "to new work params with sign  : ",
				Util.getBasicNameValuePairListSerializableDataWithSign(hashMap));
		httpPost.setEntity(entity);
		return HttpManager.execute(httpPost, context);
	}

	public static void requestServer(final Context context, final String url,
			final HashMap<String, String> parma, final INetworkDelegate callback) {
		new HttpRequest()
				.requestServerHttpClient(context, url, parma, callback);
	}

	public static void requestServerWithSign(final Context context,
			final String url, final HashMap<String, String> parma,
			final INetworkDelegate callback) {
		new HttpRequest().requestServerHttpClientWithSign(context, url, parma,
				callback);
	}

	public void requestServerHttpClient(final Context context,
			final String url, HashMap<String, String> parma,
			final INetworkDelegate callback) {
		if (parma == null) {
			parma = new HashMap<String, String>();
		}
		if (mCookie != null) {
			parma.put(Constants.string.TOKEN, mCookie);
		}

		// use to return when no network
		if (!Util.isNetworkAvailable(context)) {
			if (null != callback) {
				callback.didFailureLoad(context.getResources().getString(
						R.string.no_network_connection_toast));
			}
			return;
		}

		callback.didStartLoad();// �?�????�?

		final HashMap<String, String> parmaFinal = parma;
		new Thread() {
			public void run() {
				InputStream fis = null;
				try {
					Log.i(TAG, "to net work url : ", url);
					HttpResponse response = null;
					if (parmaFinal != null && parmaFinal.size() > 0) {// post请�??
						response = requestPost(context, parmaFinal, url);
					} else {
						response = requestGet(context, url);// GET请�??
					}
					if (null != response) {
						Log.i(TAG, "from net work getStatusCode : ", response
								.getStatusLine().getStatusCode());
						if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

							fis = response.getEntity().getContent();
							callback.didFinishedLoad(fis);
						} else {
							callback.didFailureLoad(

							context.getResources().getString(
									R.string.server_exception));
						}
					} else {
						callback.didFailureLoad(

						context.getResources().getString(
								R.string.server_exception));
					}
				} catch (Exception e) {
					e.printStackTrace();
					String errStr = e.getMessage();
					errStr = !TextUtils.isEmpty(errStr) ? "request server exception reason: "
							+ errStr
							: "request server exception reason is null ";
					// Log.i(TAG, errStr);
					// Log.i(TAG, errStr, e);
					callback.didFailureLoad(context.getResources().getString(
							R.string.server_exception));
				} finally {
					if (null != fis) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						fis = null;
					}
				}
			}
		}.start();

	}

	public void requestIgnoreResponse(final Context context, final String url,
			HashMap<String, String> parma) {
		if (parma == null) {
			parma = new HashMap<String, String>();
		}
		if (mCookie != null) {
			parma.put(Constants.string.TOKEN, mCookie);
		}
		if (!Util.isNetworkAvailable(context)) {
			return;
		}
		final HashMap<String, String> parmaFinal = parma;
		new Thread() {
			public void run() {
				InputStream fis = null;
				try {
					Log.i(TAG, "to net work url : ", url);
					if (parmaFinal != null && parmaFinal.size() > 0) {// post请�??
						HttpResponse response = requestPostWithSign(context,
								parmaFinal, url);
						Log.i(TAG, "from net work getStatusCode : ", response
								.getStatusLine().getStatusCode());
						if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
							fis = response.getEntity().getContent();
							String str = JsonParser.formatStreamToString(fis);
							Log.i(TAG, "from server data is :" + str);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (null != fis) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						fis = null;
					}
				}
			}
		}.start();
	}

	public void requestServerHttpClientWithSign(final Context context,
			final String url, HashMap<String, String> parma,
			final INetworkDelegate callback) {
		if (parma == null) {
			parma = new HashMap<String, String>();
		}
		if (mCookie != null) {
			parma.put(Constants.string.TOKEN, mCookie);
		}

		// use to return when no network
		if (!Util.isNetworkAvailable(context)) {
			if (null != callback) {
				callback.didFailureLoad(context.getResources().getString(
						R.string.no_network_connection_toast));
			}
			return;
		}

		callback.didStartLoad();// �?�????�?

		final HashMap<String, String> parmaFinal = parma;
		new Thread() {
			public void run() {
				InputStream fis = null;
				try {
					Log.i(TAG, "to net work url : ", url);
					HttpResponse response = null;
					if (parmaFinal != null && parmaFinal.size() > 0) {// post请�??
						response = requestPostWithSign(context, parmaFinal, url);
					} else {
						response = requestGetWithSign(context, url);// GET请�??
					}
					if (null != response) {
						Log.i(TAG, "from net work getStatusCode : ", response
								.getStatusLine().getStatusCode());
						if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

							fis = response.getEntity().getContent();
							callback.didFinishedLoad(fis);
						} else {
							callback.didFailureLoad(

							context.getResources().getString(
									R.string.server_exception));
						}
					} else {
						callback.didFailureLoad(

						context.getResources().getString(
								R.string.server_exception));
					}
				} catch (Exception e) {
					e.printStackTrace();
					String errStr = e.getMessage();
					errStr = !TextUtils.isEmpty(errStr) ? "request server exception reason: "
							+ errStr
							: "request server exception reason is null ";
					// Log.i(TAG, errStr);
					// Log.i(TAG, errStr, e);
					callback.didFailureLoad(context.getResources().getString(
							R.string.server_exception));
				} finally {
					if (null != fis) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
						fis = null;
					}
				}
			}
		}.start();

	}
}
