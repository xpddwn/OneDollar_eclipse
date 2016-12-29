package com.onedollar.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.Header;

import com.example.onedollar.BuildConfig;

public class Log {
	public static boolean DEBUG = BuildConfig.DEBUG;

	public static final String TAG = "Log";
//	private static final Logger gLogbackLogger = LoggerFactory
//			.getLogger(Log.class);

	public static void d(Object tag, Object... msg) {
		if (DEBUG) {
			StringBuilder sb = new StringBuilder();
			for (Object object : msg) {
				sb.append(object);
			}
//			gLogbackLogger.debug(tag + " :: " + sb);
			android.util.Log.d(TAG, tag + " :: " + sb);
		}

	}

	public static void i(Object tag, Object... msg) {
		if (DEBUG) {
			StringBuilder sb = new StringBuilder();
			for (Object object : msg) {
				sb.append(object);
			}
//			gLogbackLogger.info(tag + " :: " + sb);
			android.util.Log.i(TAG, tag + " :: " + sb);
		}
	}

	public static void w(Object tag, Object... msg) {
		if (DEBUG) {
			StringBuilder sb = new StringBuilder();
			for (Object object : msg) {
				sb.append(object);
			}
//			gLogbackLogger.warn(tag + " :: " + sb);
			android.util.Log.w(TAG, tag + " :: " + sb);
		}
	}

	public static void e(Object tag, Object... msg) {
		if (DEBUG) {
			StringBuilder sb = new StringBuilder();
			for (Object object : msg) {
				sb.append(object);
			}
//			gLogbackLogger.error(tag + " :: " + sb);
			android.util.Log.e(TAG, tag + " :: " + sb);
		}
	}

	public static void e(Object tag, Object msg, Throwable tr) {
		if (DEBUG) {
			android.util.Log.e(TAG, tag + " :: " + msg, tr);
			if (null != tr) {
//				gLogbackLogger.error(tag + " :: "
//						+ android.util.Log.getStackTraceString(tr));
			}
			if (null != msg) {
//				gLogbackLogger.error(tag + " :: " + msg + '\n');
			}
		}
	}

	public static void printHeaders(Header[] headers) {
		if (headers != null && headers.length > 0) {
			for (Header h : headers) {
				Log.i(TAG, "header  key  is: " + h.getName() + ",  value is: "
						+ h.getValue());
			}
		}
	}

	public static void printHashMap(HashMap<String, Object> hashMap) {
		if (hashMap != null) {
			Iterator<Map.Entry<String, Object>> iterator = hashMap.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				Log.i(TAG, "hashMap  key  is: " + entry.getKey()
						+ ",  value is: " + entry.getValue());
			}
		}
	}

}
