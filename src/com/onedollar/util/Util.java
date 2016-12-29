package com.onedollar.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.onedollar.R;
import com.onedollar.base.App;
import com.onedollar.dal.UserInfo;
import com.onedollar.data.Constants;
import com.onedollar.data.Preferences;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.example.onedollar.MainActivity;

public class Util {

	public static final String TAG = "util";

	@SuppressLint("InflateParams")
	public static ProgressDialog createProgressDialog(Activity activity) {
		ProgressDialog dialog = new ProgressDialog(activity);
		dialog.show();
		// dialog.setCancelable(false);
		View view = LayoutInflater.from(activity).inflate(
				R.layout.progress_dialog, null);
		dialog.getWindow().setContentView(view);
		return dialog;
	}

	public static boolean isNetworkAvailable(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {

				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {

					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static boolean isNetworkAvailable() {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) App
					.getInstance().getSystemService(
							Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {

				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {

					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public static void HashMapCopy(HashMap<String, Object> source,
			HashMap<String, Object> target) {
		if (source != null && source.size() > 0 && target != null) {
			Iterator<Map.Entry<String, Object>> iterator = source.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, Object> entry = iterator.next();
				target.put(entry.getKey(), entry.getValue());
			}
		}
	}

	public static boolean isExistSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}

	public static String decodeUnicode(String theString) {
		if (TextUtils.isEmpty(theString)) {
			return null;
		}
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	public static String getFormSerializableData(HashMap<String, String> hashMap) {

		if (hashMap != null && hashMap.size() > 0) {
			StringBuffer result = new StringBuffer();
			// 表单参数与get形式一样
			Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				result.append(entry.getKey()).append("=")
						.append(entry.getValue());
				if (iterator.hasNext()) {
					result.append("&");
				}
			}
			return result.toString();
		}
		return null;
	}

	public static List<BasicNameValuePair> getBasicNameValuePairListSerializableData(
			HashMap<String, String> hashMap) {
		if (hashMap != null && hashMap.size() > 0) {
			ArrayList<BasicNameValuePair> result = new ArrayList<BasicNameValuePair>();
			// 表单参数与get形式一样
			Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				result.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
			return result;
		}
		return null;
	}

	public static List<BasicNameValuePair> getBasicNameValuePairListSerializableDataWithSign(
			HashMap<String, String> hashMap) {
		if (hashMap != null && hashMap.size() > 0) {
			ArrayList<BasicNameValuePair> result = new ArrayList<BasicNameValuePair>();
			String str = null;
			// 表单参数与get形式一样
			Iterator<Map.Entry<String, String>> iterator = hashMap.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				result.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
				if (TextUtils.isEmpty(str)) {
					str = entry.getKey() + "=" + entry.getValue();
				} else {
					str = str + "&" + entry.getKey() + "=" + entry.getValue();
				}
			}
			MD5.sign(result, str);
			return result;
		}
		return null;
	}

	public static boolean hasKey(HashMap<String, Object> hashMap, String key) {
		if (hashMap == null || hashMap == JSONObject.NULL
				|| !hashMap.containsKey(key)) {
			return false;
		}
		if (hashMap.get(key) == null || hashMap.get(key) == JSONObject.NULL) {
			return false;
		}
		return true;
	}

	public static String getValue(HashMap<String, Object> hashMap, String key) {
		if (hasKey(hashMap, key)) {
			return hashMap.get(key).toString();
		}
		return null;
	}

	public static HashMap<String, Object> getHashMap(
			HashMap<String, Object> hashMap, String key) {
		return convertObj2HashMap(getObject(hashMap, key));
	}

	public static Object getObject(HashMap<String, Object> hashMap, String key) {
		if (hasKey(hashMap, key)) {
			return hashMap.get(key);
		}
		return null;
	}

	public static ArrayList<HashMap<String, Object>> getHashMapList(
			HashMap<String, Object> hashMap, String key) {
		return convertObj2List(getObject(hashMap, key));

	}

	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> convertObj2HashMap(Object object) {
		if (object == null || object == JSONObject.NULL
				|| object.toString().equals("")) {
			return null;
		}
		HashMap<String, Object> hashMap = null;
		try {
			hashMap = (HashMap<String, Object>) object;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return hashMap;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<HashMap<String, Object>> convertObj2List(
			Object object) {
		ArrayList<HashMap<String, Object>> hashMaps = null;

		if (object == null || object == JSONObject.NULL
				|| object.toString().equals("")) {
			return null;
		}
		try {
			hashMaps = (ArrayList<HashMap<String, Object>>) object;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return hashMaps;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<String> convertObj2StringList(Object object) {

		if (object == null || object == JSONObject.NULL
				|| object.toString().equals("")) {
			return null;
		}
		ArrayList<String> strings = null;
		try {
			strings = (ArrayList<String>) object;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return strings;
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<HashMap<String, Object>>> convertObj2ListList(
			Object object) {

		if (object == null) {
			return null;
		}
		if (object.toString().equals("")) {
			return null;
		}
		try {
			return (ArrayList<ArrayList<HashMap<String, Object>>>) object;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	public static void addList(ArrayList<HashMap<String, Object>> target,
			ArrayList<HashMap<String, Object>> source) {
		if (source == null) {
			return;
		}
		if (target == null) {
			target = new ArrayList<HashMap<String, Object>>();
		}
		for (HashMap<String, Object> map : source) {
			target.add(map);
		}

	}

	public static boolean isLocalUser(HashMap<String, Object> other) {
		UserInfo local = Preferences.getLocalUserInfo();
		if (local == null || other == null) {
			return false;
		}
		if (local.getId() == null) {
			return false;
		}
		return local.getId().equals(
				other.get(Constants.string.USER_ID).toString());
	}

	public static boolean isLocalUser(String userID) {
		UserInfo local = Preferences.getLocalUserInfo();
		if (local == null || userID == null) {
			return false;
		}
		return local.getId().equals(userID);
	}

	/** 执行Linux命令，并返回执行结果。 */
	public static String exec(String[] args) {
		String result = "";
		ProcessBuilder processBuilder = new ProcessBuilder(args);
		Process process = null;
		InputStream errIs = null;
		InputStream inIs = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int read = -1;
			process = processBuilder.start();
			errIs = process.getErrorStream();
			while ((read = errIs.read()) != -1) {
				baos.write(read);
			}
			baos.write("\n".getBytes());
			inIs = process.getInputStream();
			while ((read = inIs.read()) != -1) {
				baos.write(read);
			}
			byte[] data = baos.toByteArray();
			result = new String(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (errIs != null) {
					errIs.close();
				}
				if (inIs != null) {
					inIs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (process != null) {
				process.destroy();
			}
		}
		return result;
	}

	/***
	 * 调用系统的安装界面
	 * 
	 * @param context
	 * @param file
	 *            安装apk的文件路径2
	 */
	public static void installAPKBySystemAPI(Context context, File file) {

		Uri uri = Uri.fromFile(file);
		Intent startGameIntent = new Intent(Intent.ACTION_VIEW, uri);
		startGameIntent.setData(uri);
		// startGameIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
		startGameIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startGameIntent.setClassName("com.android.packageinstaller",
				"com.android.packageinstaller.PackageInstallerActivity");
		context.startActivity(startGameIntent);
	}

	public static boolean isAppRunning() {
		// 判断应用是否在运行
		ActivityManager am = (ActivityManager) App.getInstance()
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> list = am.getRunningTasks(100);
		boolean isAppRunning = false;
		String MY_PKG_NAME = "com.shunshunliuxue";
		for (RunningTaskInfo info : list) {
			if (info.topActivity.getPackageName().equals(MY_PKG_NAME)
					|| info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {

				isAppRunning = true;
				break;
			}
		}
		return isAppRunning;
	}

	public static void moveAppToFront(Context context) {
		if (isAppRunning()) {
			// 判断应用是否在运行
			ActivityManager am = (ActivityManager) App.getInstance()
					.getSystemService(Context.ACTIVITY_SERVICE);
			List<RunningTaskInfo> list = am.getRunningTasks(100);
			String MY_PKG_NAME = "com.shunshunliuxue";
			for (RunningTaskInfo info : list) {
				if (info.topActivity.getPackageName().equals(MY_PKG_NAME)
						|| info.baseActivity.getPackageName().equals(
								MY_PKG_NAME)) {
					am.moveTaskToFront(info.id, 0);
					break;
				}
			}
			return;
		}
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setClass(context, MainActivity.class);
		context.startActivity(intent);
	}

	public static void setTitleBarTextViewEnabled(TextView textView,
			boolean enable) {
		if (textView == null) {
			return;
		}
		if (enable) {
			textView.setTextColor(App.getInstance().getResources()
					.getColor(R.color.white));
		} else {
			textView.setTextColor(App.getInstance().getResources()
					.getColor(R.color.text_view_unclickable));
		}
		textView.setEnabled(enable);
	}

	public static boolean checkDeviceHasNavigationBar(Context context) {
		boolean hasNavigationBar = false;
		Resources rs = context.getResources();
		int id = rs
				.getIdentifier("config_showNavigationBar", "bool", "android");
		if (id > 0) {
			hasNavigationBar = rs.getBoolean(id);
		}
		try {
			Class<?> systemPropertiesClass = Class
					.forName("android.os.SystemProperties");
			Method m = systemPropertiesClass.getMethod("get", String.class);
			String navBarOverride = (String) m.invoke(systemPropertiesClass,
					"qemu.hw.mainkeys");
			if ("1".equals(navBarOverride)) {
				hasNavigationBar = false;
			} else if ("0".equals(navBarOverride)) {
				hasNavigationBar = true;
			}
		} catch (Exception e) {
			Log.w(TAG, e);
		}
		Log.e(TAG, "#hasNavigationBar#", hasNavigationBar);
		return hasNavigationBar;
	}

	public static int getNavigationBarHeight(Context context) {
		int navigationBarHeight = 0;
		Resources rs = context.getResources();
		int id = rs.getIdentifier("navigation_bar_height", "dimen", "android");
		if (id > 0 && checkDeviceHasNavigationBar(context)) {
			navigationBarHeight = rs.getDimensionPixelSize(id);
		}
		return navigationBarHeight;
	}
	/** 1234567890 转为 12.3亿*/
	public static String getMoney(String value) {
		if (TextUtils.isEmpty(value)) {
			return " 暂无";
		}
		String target = null;
		if (value.contains(".")) {
			target = value.split("\\.")[0];
		} else {
			target = value;
		}
		if (target.length() > 12) {
			return target.substring(0, target.length() - 12) + "."
					+ target.charAt(target.length() - 12) + "万亿";
		} else if (target.length() > 8) {
			return target.substring(0, target.length() - 8) + "."
					+ target.charAt(target.length() - 8) + "亿";
		} else if (target.length() > 4) {
			return target.substring(0, target.length() - 4) + "."
					+ target.charAt(target.length() - 4) + "万";
		}
		return target;
	}
}
