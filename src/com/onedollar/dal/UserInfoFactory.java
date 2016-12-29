package com.onedollar.dal;

import java.util.ArrayList;
import java.util.HashMap;

import android.text.TextUtils;

import com.onedollar.data.Constants;
import com.onedollar.util.Util;

public class UserInfoFactory {

	public static final String[] KEYS = { Constants.string.USER_NAME,
			Constants.string.USER_IMG_URL, Constants.string.FOLLOW_COUNT,
			Constants.string.AGREE_COUNT, Constants.string.VISIT_COUNT,
			Constants.string.ANSWER_COUNT, "region", "work_years", "grade",
			Constants.string.USER_ID, "user_type", "follow_status",
			"signature", "sex", "mobile", "offerCount", "qq", "birthday",
			"personal_description", "common_email", "academic", "education_id" };

	public static UserInfo create(HashMap<String, Object> hashMap, String[] keys) {
		UserInfo userInfo = new UserInfo();
		if (keys == null) {
			return userInfo;
		}
		if (keys.length > 0) {
			userInfo.setName(Util.getValue(hashMap, keys[0]));
		}
		if (keys.length > 1) {
			userInfo.setHeadUrl(Util.getValue(hashMap, keys[1]));
		}

		if (keys.length > 2) {
			userInfo.setFansNumber(Util.getValue(hashMap, keys[2]));
		}
		if (keys.length > 3) {
			userInfo.setAgreeNumber(Util.getValue(hashMap, keys[3]));
		}
		if (keys.length > 4) {
			userInfo.setVisitNumber(Util.getValue(hashMap, keys[4]));
		}
		if (keys.length > 5) {
			userInfo.setAnswerQuestionNumber(Util.getValue(hashMap, keys[5]));
		}
		if (keys.length > 6) {
			userInfo.setAddress(Util.getValue(hashMap, keys[6]));
		}
		if (keys.length > 7) {
			userInfo.setWorkYears(Util.getValue(hashMap, keys[7]));
		}
		if (keys.length > 8) {
			userInfo.setGrade(Util.getValue(hashMap, keys[8]));
		}
		if (keys.length > 9) {
			userInfo.setId(Util.getValue(hashMap, keys[9]));
		}
		if (keys.length > 10) {
			userInfo.setUserType(Util.getValue(hashMap, keys[10]));
		}
		if (keys.length > 11) {
			userInfo.setFollowStatus(Util.getValue(hashMap, keys[11]));
		}
		if (keys.length > 12) {
			userInfo.setAutograph(Util.getValue(hashMap, keys[12]));
		}
		if (keys.length > 13) {
			userInfo.setSex(Util.getValue(hashMap, keys[13]));
		}
		if (keys.length > 14) {
			userInfo.setPhone(Util.getValue(hashMap, keys[14]));
		}
		if (keys.length > 15) {
			userInfo.setOfferCount(Util.getValue(hashMap, keys[15]));
		}
		if (keys.length > 16) {
			userInfo.setQQNum(Util.getValue(hashMap, keys[16]));
		}
		if (keys.length > 17) {
			userInfo.setBirthday(Util.getValue(hashMap, keys[17]));
		}
		if (keys.length > 18) {
			userInfo.setCounselorIntroduce(Util.getValue(hashMap, keys[18]));
		}
		if (keys.length > 19) {
			userInfo.setEmailAdress(Util.getValue(hashMap, keys[19]));
		}
		if (keys.length > 20) {
			userInfo.setEducationBackground(Util.getValue(hashMap, keys[20]));
		}
		if (keys.length > 21) {
			userInfo.setSchoolTime(Util.getValue(hashMap, keys[21]));
		}
		if (keys.length > 22) {
			userInfo.setEducationId(Util.getValue(hashMap, keys[22]));
		}
		userInfo.setProvince(Util.getValue(hashMap, "province"));
		userInfo.setCity(Util.getValue(hashMap, "city"));
		return userInfo;
	}

	public static UserInfo create(HashMap<String, Object> hashMap) {
		return create(hashMap, KEYS);
	}

	public static UserInfo create(HashMap<String, Object> hashMap,
			HashMap<String, Object> education) {
		UserInfo userInfo = create(hashMap, KEYS);
		if (userInfo != null) {
			userInfo.setEducationBackground(Util
					.getValue(education, "academic"));
			userInfo.setEducationSchool(Util.getValue(education, "school_name"));
			userInfo.setSchoolTime(Util.getValue(education, "education_years"));
			userInfo.setMajor(Util.getValue(education, "departments"));
			userInfo.setEducationId(Util.getValue(education, "education_id"));
		}
		return userInfo;
	}

	public static ArrayList<UserInfo> createList(
			ArrayList<HashMap<String, Object>> list, String[] keys) {
		ArrayList<UserInfo> arrayList = new ArrayList<UserInfo>();
		if (list == null) {
			return arrayList;
		}
		for (HashMap<String, Object> hashMap : list) {
			arrayList.add(create(hashMap, keys));
		}
		return arrayList;
	}

	public static ArrayList<UserInfo> createList(
			ArrayList<HashMap<String, Object>> list) {
		return createList(list, KEYS);
	}

	public static UserInfo createSimpleCounslor(
			HashMap<String, Object> userInfoHashMap) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(Util.getValue(userInfoHashMap, "uid"));
		userInfo.setWorkYears(Util.getValue(userInfoHashMap, "service_years"));
		String name = Util.getValue(userInfoHashMap, "user_name");
		if (TextUtils.isEmpty(name)) {
			name = TextUtils.isEmpty(Util.getValue(userInfoHashMap,
					"first_name")) ? "" : Util.getValue(userInfoHashMap,
					"first_name");
			name = TextUtils.isEmpty(name) ? Util.getValue(userInfoHashMap,
					"last_name") : name
					+ Util.getValue(userInfoHashMap, "last_name");
		}
		userInfo.setName(name);
		String address = "";
		address = TextUtils.isEmpty(Util.getValue(userInfoHashMap,
				"address_province")) ? "" : Util.getValue(userInfoHashMap,
				"address_province");
		address = TextUtils.isEmpty(address) ? Util.getValue(userInfoHashMap,
				"address_city") : address + " "
				+ Util.getValue(userInfoHashMap, "address_city");
		address = TextUtils.isEmpty(address) ? Util.getValue(userInfoHashMap,
				"address_district") : address + " "
				+ Util.getValue(userInfoHashMap, "address_district");
		userInfo.setAddress(address);
		userInfo.setOfferCount(Util.getValue(userInfoHashMap, "offer_count"));
		userInfo.setHeadUrl(Util.getValue(userInfoHashMap, "avatar_file"));
		userInfo.setUserType("顾问");
		return userInfo;
	}
}
