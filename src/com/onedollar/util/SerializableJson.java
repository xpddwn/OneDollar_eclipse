package com.onedollar.util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Set;

public class SerializableJson {
	public static final String TAG = "JASON";

	public static final String TRADESTATUS = "01"; // 通讯成功的状态值
	public static final String TRADESATUSFAILRUE = "02";// 通讯失败的状态值

	/***
	 * 通过返回的下载路径地址，得到需要上传的参数
	 * 
	 * @param absolutePath
	 * @return
	 */
	public static HashMap<String, String> getDownloadParamsByPath(
			String absolutePath) {
		absolutePath = absolutePath.substring(absolutePath.indexOf("?") + 1);
		String[] vas = absolutePath.split("&");
		HashMap<String, String> paramMap = new HashMap<String, String>();
		for (String va : vas) {
			int index = va.indexOf("=");
			if (index != -1) {
				paramMap.put(va.substring(0, index), va.substring(index + 1));
			}
		}
		return paramMap;
	}

	/**
	 * 通过参数的map值得到 上传到服务端的固定json格式的参数
	 * 
	 * @param map
	 * @return
	 */
	public static String getJsonPararam(HashMap<String, String> map) {
		JSONObject result = new JSONObject();
		JSONObject Local = new JSONObject();
		try {
			Local.put("Locale", "ZH_cn");

			Set<String> set = map.keySet();
			JSONObject paramValue = new JSONObject();
			for (String keys : set) {
				paramValue.put(keys, map.get(keys));
			}
			result.put("header", Local);
			result.put("body", paramValue);
			String string = "{\"user_name\":\"shunshun\",\"password\":\"000000\",\"user_phone\":\"15210832541\",\"code\":\"0123\"}";
			Log.i(TAG, "To network is:" + string);
			// 上传数据加密 TODO
			return string;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
