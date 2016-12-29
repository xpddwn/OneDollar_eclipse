package com.onedollar.data;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.onedollar.base.App;
import com.onedollar.dal.UserInfo;
import com.onedollar.dal.UserInfoFactory;
import com.onedollar.net.HttpRequest;
import com.onedollar.util.JsonParser;
import com.onedollar.util.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class Preferences {
	public static final String PREFERENCE_NAME = "global";
	public static final String IS_FIRST = "is_first";
	public static final String IS_CHOOSE_SCHOOL_FIRST = "is_choose_school_first";
	public static final String IS_LOGIN = "dfag";
	public static final String USER_INFO = "wsdf";
	public static final String QUESTION_CACHE = "weirfrfj";
	public static final String TOPIC_CACHE = "topic_cache";
	public static final String HOT_QUESTION_CACHE = "hot_question_cache";
	public static final String COUNSELOR_LIST_CACHE = "counselor_list_cache";
	public static final String COUNTRY_LIST_CACHE = "country_info_cache";
	public static final String MY_QUESTION_CACHE = "my_question_cache";
	public static final String MY_SIXIN_CACHE = "my_sixin_cache";
	public static final String MY_FOLLOW_QUESTION_CACHE = "my_follow_question_cache";
	public static final String MY_FOLLOW_TOPIC_CACHE = "my_follow_topic_cache";
	public static final String MY_COMUNITY_NEWS_CACHE = "my_comunity_news_cache";
	public static final String MY_NEWS_TASK_CACHE = "my_news_task_cache";
	public static final String MY_NEWS_CONSULT_CACHE = "my_news_consult_cache";
	public static final String HOT_TOPIC_CACHE = "hot_topic_cache";
	public static final String MY_FRIEND_CACHE = "my_friend_cache";
	public static final String CHAT_INFO_LIST_CACHE = "chat_info_cache";
	public static final String COUNSELOR_INFO_CACHE = "counselor_info_cache";
	public static final String COUNSELOR_SERVICE_CACHE = "counselor_service_cache";
	public static final String COUNSELOR_SERVICE_CASE_CACHE = "counselor_service_case_cache";
	public static final String COUNSELOR_SERVICE_TOPIC_CACHE = "counselor_service_topic_cache";
	public static final String SOURCE_SHARE_LIST_CACHE = "source_share_list_cache";
	public static final String ACTIVITY_LIST_CACHE = "activity_list_cache";
	public static final String BANNER_LIST_CACHE = "banner_list_cache";
	public static final String APPLY_LIST_CACHE = "first_page_apply_list_cache";
	public static final String INFORMATION_LIST_CACHE = "information_list_cache";
	private static final String USER_INFO_EDUCATION_CACHE = "user_info_education_cache";
	private static final String PUSH_ON_OFF = "push_on_off";
	private static SharedPreferences mPreferences = App.getInstance()
			.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

	public static final String LOCAL_USER_SELECTED_TAG_TYPE = "local_user_selected_tag_type";
	public static final String LOCAL_USER_SELECTED_TAG_COUNTRY = "local_user_selected_tag_country";
	public static final String LOCAL_USER_SELECTED_TAG_PROJECT = "local_user_selected_tag_project";

	private static UserInfo mLocalUserInfo;

	private static void saveIsLogin(boolean login) {
		Editor editor = mPreferences.edit();
		editor.putBoolean(IS_LOGIN, login);
		editor.commit();
	}

	public static boolean getIsLogin() {
		return mPreferences.getBoolean(IS_LOGIN, false);
	}

	private static HashMap<String, Object> getUserInfo() {
		String jsonData = getData(USER_INFO);
		HashMap<String, Object> result = null;
		try {
			result = Util.convertObj2HashMap(JsonParser
					.parserRandomJsonFormat(jsonData));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
		return result;
	}

	private static HashMap<String, Object> getEducationInfo() {
		String jsonData = mPreferences.getString(USER_INFO_EDUCATION_CACHE,
				null);
		HashMap<String, Object> result = null;
		try {
			result = Util.convertObj2HashMap(JsonParser
					.parserRandomJsonFormat(jsonData));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
		return result;
	}

	public static UserInfo getLocalUserInfo() {
		if (mLocalUserInfo == null) {
			HashMap<String, Object> hashMap = getUserInfo();
			if (hashMap == null || hashMap.isEmpty()) {
				return null;
			}
			mLocalUserInfo = UserInfoFactory
					.create(hashMap, getEducationInfo());
		}
		return mLocalUserInfo;
	}

	public static void saveQuestionCache(String questions) {
		Editor editor = mPreferences.edit();
		editor.putString(QUESTION_CACHE, questions);
		editor.commit();
	}

	public static String getQuestionCache() {
		String str = mPreferences.getString(QUESTION_CACHE, null);
		// if (str != null) {
		// str.replace("\"\"", "null");
		// }
		return str;
	}

	public static void saveData(String key, String value) {
		Editor editor = mPreferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getData(String key) {
		String str = mPreferences.getString(key, null);
		// if (str != null) {
		// str.replace("\"\"", "null");
		// }
		return str;
	}

	public static void saveIsFirst(boolean isFirst) {
		Editor editor = mPreferences.edit();
		editor.putBoolean(IS_FIRST, isFirst);
		editor.commit();
	}
	
	public static void saveChooseSchoolIsFirst(boolean isFirst) {
		Editor editor = mPreferences.edit();
		editor.putBoolean(IS_CHOOSE_SCHOOL_FIRST, isFirst);
		editor.commit();
	}
	
	public static boolean getChooseSchoolIsFirst() {
		return mPreferences.getBoolean(IS_CHOOSE_SCHOOL_FIRST, true);
	}


	public static boolean getIsFirst() {
		return mPreferences.getBoolean(IS_FIRST, true);
	}

	public static void localLogin(HashMap<String, Object> hashMap,
			HashMap<String, Object> education) {
		if (hashMap == null || hashMap.size() < 1) {
			return;
		}
		saveIsLogin(true);
		try {
			JSONObject paramValue = new JSONObject();
			Set<String> set = hashMap.keySet();
			for (String keys : set) {
				paramValue.put(keys, hashMap.get(keys));
			}
			saveData(USER_INFO, paramValue.toString());
			mLocalUserInfo = UserInfoFactory.create(hashMap, education);
			paramValue = new JSONObject();
			if (education == null || education.size() < 1) {
				return;
			}

			Set<String> set2 = education.keySet();
			for (String keys : set2) {
				paramValue.put(keys, education.get(keys));
			}
			saveData(USER_INFO_EDUCATION_CACHE, paramValue.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public static void localLogout() {
		saveIsLogin(false);
		saveData(USER_INFO, null);
		mLocalUserInfo = null;
		HttpRequest.setToken(null);
		Preferences.saveData(Constants.string.TOKEN, null);
		App.getInstance().cleanAllNotification();
	}

	public static InputStream getInputStreamFromString(String str) {
		InputStream inputStream = null;
		if (str != null) {
			inputStream = new ByteArrayInputStream(str.getBytes());
		}
		return inputStream;
	}

	public static boolean isLocalUserSelectedTag() {
		if (getLocalUserInfo() == null) {
			return false;
		}
		if (TextUtils.isEmpty(getData(LOCAL_USER_SELECTED_TAG_COUNTRY))
				|| TextUtils.isEmpty(getData(LOCAL_USER_SELECTED_TAG_TYPE))
				|| TextUtils.isEmpty(getData(LOCAL_USER_SELECTED_TAG_PROJECT))) {
			return false;
		}
		return true;
	}

	public static void saveSelectedTag(HashMap<String, Object> hashMap1) {
		HashMap<String, Object> hashMap = Util.getHashMap(hashMap1, "user_tag");
		if (!TextUtils.isEmpty(Util.getValue(hashMap, "interest_country"))) {
			saveData(LOCAL_USER_SELECTED_TAG_COUNTRY,
					Util.getValue(hashMap, "interest_country"));
		}
		if (!TextUtils.isEmpty(Util.getValue(hashMap, "identity"))) {
			saveData(LOCAL_USER_SELECTED_TAG_TYPE,
					Util.getValue(hashMap, "identity"));
		}
		if (!TextUtils.isEmpty(Util.getValue(hashMap, "project"))) {
			saveData(LOCAL_USER_SELECTED_TAG_PROJECT,
					Util.decodeUnicode(Util.getValue(hashMap, "project")));
		}
	}

	public static InputStream getDataInputStream(String key) {
		return getInputStreamFromString(getData(key));
	}

	public static void setPushOnOff(boolean on) {
		Editor editor = mPreferences.edit();
		editor.putBoolean(PUSH_ON_OFF, on);
		editor.commit();
	}

	public static boolean getPushOnOff() {
		return mPreferences.getBoolean(PUSH_ON_OFF, true);
	}
}
