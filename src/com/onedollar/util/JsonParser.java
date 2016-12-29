/*********************************************************************
 *                           *
 *********************************************************************/
/**
 * @file   JsonParser.java
 *
 * @author 
 *
 * 负责对通讯数据的解析类
 */

package com.onedollar.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonParser {
	public static final String TAG = "JsonParser";

    /**
     * 转输入流为字符串
     * 
     * @param input
     * @return时
     * @throws Exception
     */
    public static String formatStreamToString(InputStream input)
            throws Exception {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        String va = "";
        if (input != null) {
            try {
                br = new BufferedReader(new InputStreamReader(input, "utf-8"));
                while ((va = br.readLine()) != null) {
                    sb.append(va);
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new Exception(e.getMessage());
            }
        }
//        Log.releaseInfo(TAG, "From server  is:" + sb.toString());
        return sb.toString();
    }

    /****
     * 对任意复杂的json格式数据的解析方法
     * 
     * @param target
     * @return 一个HashMap/ArrayList 混合的复杂对象
     * @throws JSONException
     * @throws UnsupportedEncodingException
     */
    public static Object parserRandomJsonFormat(String target)
            throws JSONException, UnsupportedEncodingException {
        HashMap<String, Object> map = null;
        ArrayList<Object> list = null;
        if (target != null && target.length() > 0) {
            target = target.trim();
            if ("[".equals(String.valueOf(target.charAt(0)))) {// jsonArray对象
                list = new ArrayList<Object>();
                JSONArray jArray = new JSONArray(target);
                parseStartJSONArrayFormat(jArray, list);
                return list;
            } else if ("{".equals(String.valueOf(target.charAt(0)))) {// jsonObject对象
                JSONObject object = new JSONObject(target);
                map = new HashMap<String, Object>();
                parseMutilJSONObjectFormat(object, map);
                return map;
            }
        }
        return null;
    }

    /***
     * 解析数组中包含数组、对象、基本数据的格式 如 [[]] 、[{}]、[1]
     * 
     * @param jsonArray
     * @param list
     * @throws JSONException
     */
    public static void parseStartJSONArrayFormat(JSONArray jsonArray,
            List<Object> list) throws JSONException {
        HashMap<String, Object> map = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONArray array = jsonArray.getJSONArray(i);
                ArrayList<Object> sublist = new ArrayList<Object>();
                parseStartJSONArrayFormat(array, sublist);
                list.add(sublist);
            } catch (Exception e) {
                try {
                    map = new HashMap<String, Object>();
                    JSONObject object = jsonArray.getJSONObject(i);
                    parseMutilJSONObjectFormat(object, map);
                    list.add(map);
                } catch (Exception e1) {
                    list.add(jsonArray.get(i));
                }
            }
        }
    }

    /**
     * 解析json对象中包含的json对象 如{"name":{}}、{"name"：[]}、{"name":"1"}
     * 
     * @param jsonObject
     * @param hashMap
     * @throws JSONException
     */
    public static void parseMutilJSONObjectFormat(JSONObject jsonObject,
            HashMap<String, Object> hashMap) throws JSONException {
        JSONArray nameArray = jsonObject.names();
        for (int k = 0; k < nameArray.length(); k++) {
            String attrName = nameArray.getString(k);
            try {
                JSONArray objarray2 = jsonObject.getJSONArray(attrName);
                ArrayList<Object> sublist = new ArrayList<Object>();
                parseStartJSONArrayFormat(objarray2, sublist);
                hashMap.put(attrName, sublist);
            } catch (JSONException e) {
                try {
                    JSONObject objson2 = jsonObject.getJSONObject(attrName);
                    HashMap<String, Object> submap = new HashMap<String, Object>();
                    try {
                        parseMutilJSONObjectFormat(objson2, submap);
                    } catch (NullPointerException e1) {
                        e1.printStackTrace();
                    }
                    hashMap.put(attrName, submap);
                } catch (Exception e1) {
                    hashMap.put(attrName, jsonObject.get(attrName));
                }
            }
        }
    }
}
