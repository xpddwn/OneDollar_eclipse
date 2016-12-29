package com.onedollar.dal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.example.onedollar.R;
import com.onedollar.base.App;
import com.onedollar.data.Preferences;

public class UserInfo extends ToString implements Parcelable {

	public static final String SEX_MALE = "1";
	public static final String SEX_FEMALE = "2";

	/**
	 * 头像
	 */
	private String mHeadUrl;
	/**
	 * name
	 */
	private String mName;
	/**
	 * 粉丝数
	 */
	private String mFansNumber;
	/**
	 * 回答被赞数
	 */
	private String mAgreeNumber;
	/**
	 * 被访问数
	 */
	private String mVisitNumber;// 被访问数
	/**
	 * 回答问题数
	 */
	private String mAnswerQuestionNumber;// 回答问题数
	/**
	 * 居住地址
	 */
	private String mAddress;// 居住地址
	/**
	 * 从业年限
	 */
	private String mWorkYears;// 从业年限
	/**
	 * 未知
	 */
	private String mGrade;//
	/**
	 * uid
	 */
	private String mId;
	/**
	 * 类型 顾问/学生
	 */
	private String mUserType;// 类型 顾问/学生
	/**
	 * 获取录取通知书数
	 */
	private String mOfferCount;
	/**
	 * 当前登录用户对this 的关注状态
	 */
	private String mFollowStatus;
	/**
	 * 签名
	 */
	private String mAutograph;
	/**
	 * 手机号
	 */
	private String mPhone;
	/**
	 * sex 1:男 2:女 3:保密
	 */
	private String mSex;
	/**
	 * QQ
	 */
	private String mQQNum = null;
	/**
	 * 生日
	 */
	private String mBirthday = null;
	/**
	 * 顾问介绍
	 */
	private String mCounselorIntroduce = null;
	/**
	 * 邮箱
	 */
	private String mEmailAdress = null;
	/**
	 * 教育经历之学历
	 */
	private String mEducationBackground = null;
	/**
	 * 教育经历之毕业院校
	 */
	private String mEducationSchool = null;
	/**
	 * 教育经历之专业
	 */
	private String mMajor = null;
	/**
	 * 教育经历之入学时间
	 */
	private String mSchoolTim = null;
	/**
	 * 教育经历 id
	 */
	private String mEducationId = null;
	/**
	 * address province
	 */
	private String mProvince = null;
	/**
	 * address city
	 */
	private String mCity = null;
	/**
	 * 资质图片
	 */
	private String mCertificationImageUrl = null;

	/**
	 * 取得生日
	 * 
	 * @return String birthday : 2015-01-02
	 */
	public String getBirthday() {
		return mBirthday;
	}

	/**
	 * 取得生日
	 * 
	 * @return Date
	 */
	public Date getBirthdayDate() {
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",
				Locale.CHINA);
		if (mBirthday != null) {
			try {
				date = simpleDateFormat.parse(mBirthday);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return date;
	}

	public void setBirthday(String birthday) {
		mBirthday = birthday;
	}

	/**
	 * 顾问介绍
	 * 
	 * @return String mCounselorIntroduce
	 */
	public String getCounselorIntroduce() {
		return mCounselorIntroduce;
	}

	public void setCounselorIntroduce(String counselorIntroduce) {
		mCounselorIntroduce = counselorIntroduce;
	}

	public String getEmailAdress() {
		return mEmailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		mEmailAdress = emailAdress;
	}

	/**
	 * 学历
	 * 
	 * @return String mEducationBackground : 本科 研究生 博士
	 */
	public String getEducationBackground() {
		return mEducationBackground;
	}

	public void setEducationBackground(String educationBackground) {
		mEducationBackground = educationBackground;
	}

	public String getId() {
		return mId;
	}

	public void setId(String user_id) {
		this.mId = user_id;
	}

	/**
	 * 类型
	 * 
	 * @return String type : "顾问"/"学生"
	 */
	public String getUserType() {
		return mId;
	}

	public void setUserType(String type) {
		this.mUserType = type;
	}

	public void setHeadUrl(String head_url) {
		mHeadUrl = head_url;
	}

	/**
	 * 头像Url
	 * 
	 * @return String
	 */
	public String getHeadUrl() {
		return mHeadUrl;
	}

	public void setName(String name) {
		mName = name;
	}

	public String getName() {
		return TextUtils.isEmpty(mName) ? "匿名用户" : mName;
	}

	public void setFansNumber(String hornor) {
		mFansNumber = hornor;
	}

	/**
	 * 粉丝数
	 * 
	 * @return String
	 */
	public String getFansNumber() {
		return TextUtils.isEmpty(mFansNumber) ? "0" : mFansNumber;
	}

	public void setAgreeNumber(String zan) {
		mAgreeNumber = zan;
	}

	/**
	 * 被赞n次
	 * 
	 * @return String
	 */
	public String getAgreeNumber() {
		return TextUtils.isEmpty(mAgreeNumber) ? "0" : mAgreeNumber;
	}

	public void setVisitNumber(String visit) {
		mVisitNumber = visit;
	}

	/**
	 * 访问数
	 * 
	 * @return String
	 */
	public String getVisitNumber() {
		return TextUtils.isEmpty(mVisitNumber) ? "0" : mVisitNumber;
	}

	/**
	 * 回答问题数
	 * 
	 * @return String
	 */
	public void setAnswerQuestionNumber(String question) {
		mAnswerQuestionNumber = question;
	}

	public String getAnswerQuestionNumber() {
		return TextUtils.isEmpty(mAnswerQuestionNumber) ? "0"
				: mAnswerQuestionNumber;
	}

	public void setAddress(String address) {
		mAddress = address;
	}

	/**
	 * 居住地址
	 * 
	 * @return String
	 */
	public String getAddress() {
		if (!TextUtils.isEmpty(mAddress)) {
			return mAddress;
		}
		if (!TextUtils.isEmpty(mCity) && !TextUtils.isEmpty(mProvince)) {
			return mProvince + " " + mCity;
		}
		if (!TextUtils.isEmpty(mCity)) {
			return mCity;
		}
		if (!TextUtils.isEmpty(mProvince)) {
			return mProvince;
		}
		return null;
	}

	public void setWorkYears(String work) {
		mWorkYears = work;
	}

	/**
	 * 从业年限
	 * 
	 * @return String
	 */
	public String getWorkYears() {
		return mWorkYears;
	}

	public void setGrade(String grade) {
		mGrade = grade;
	}

	public String getGrade() {
		return mGrade;
	}

	public void setOfferCount(String count) {
		mOfferCount = count;

	}

	/**
	 * 获取通知书数量
	 * 
	 * @return String
	 */
	public String getOfferCount() {
		return TextUtils.isEmpty(mOfferCount) ? "0" : mOfferCount;
	}

	public void setFollowStatus(String followStatus) {
		this.mFollowStatus = followStatus;
	}

	public void setAutograph(String autograph) {
		mAutograph = autograph;
	}

	/**
	 * 签名
	 * 
	 * @return String
	 */
	public String getAutograph() {
		return mAutograph;
	}

	public void setSex(String sex) {
		mSex = sex;
	}

	public String getSex() {
		return mSex;
	}

	public void setPhone(String phone) {
		mPhone = phone;
	}

	public String getPhone() {
		return mPhone;
	}

	public boolean isPhoneEmpty() {
		return TextUtils.isEmpty(mPhone);
	}

	public String getQQNum() {
		return mQQNum;
	}

	public void setQQNum(String qQNum) {
		mQQNum = qQNum;
	}

	/**
	 * 毕业学校
	 * 
	 * @return String
	 */
	public String getEducationSchool() {
		return mEducationSchool;
	}

	public void setEducationSchool(String educationSchool) {
		this.mEducationSchool = educationSchool;
	}

	/**
	 * 专业
	 * 
	 * @return String
	 */
	public String getmMajor() {
		return mMajor;
	}

	public void setMajor(String major) {
		this.mMajor = major;
	}

	/**
	 * 入学时间
	 * 
	 * @return String
	 */
	public String getSchoolTime() {
		return mSchoolTim;
	}

	public void setSchoolTime(String time) {
		this.mSchoolTim = time;
	}

	public void setCity(String city) {
		mCity = city;
	}

	public void setProvince(String province) {
		mProvince = province;
	}

	/**
	 * 教育经历 id
	 */
	public String getEducationId() {
		return mEducationId;
	}

	public void setEducationId(String educationId) {
		this.mEducationId = educationId;
	}

	public boolean isLocalUserFollow() {
		return "1".equals(mFollowStatus);
	}

	public boolean isLocalUser() {
		return mId != null && Preferences.getLocalUserInfo() != null
				&& Preferences.getLocalUserInfo().getId() != null
				&& mId.equals(Preferences.getLocalUserInfo().getId());
	}

	public String getCertificationImageUrl() {
		return mCertificationImageUrl;
	}

	public void setCertificationImageUrl(String certificationImageUrl) {
		mCertificationImageUrl = certificationImageUrl;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mHeadUrl);
		dest.writeString(mName);
		dest.writeString(mFansNumber);
		dest.writeString(mAgreeNumber);
		dest.writeString(mVisitNumber);
		dest.writeString(mAnswerQuestionNumber);
		dest.writeString(mAddress);
		dest.writeString(mWorkYears);
		dest.writeString(mGrade);
		dest.writeString(mId);
		dest.writeString(mUserType);
		dest.writeString(mOfferCount);
		dest.writeString(mFollowStatus);
		dest.writeString(mAutograph);
		dest.writeString(mSex);
		dest.writeString(mPhone);
		dest.writeString(mQQNum);
		dest.writeString(mBirthday);
		dest.writeString(mCounselorIntroduce);
		dest.writeString(mEmailAdress);
		dest.writeString(mEducationBackground);
		dest.writeString(mCounselorIntroduce);
		dest.writeString(mEmailAdress);
		dest.writeString(mEducationBackground);
		dest.writeString(mEducationSchool);
		dest.writeString(mMajor);
		dest.writeString(mSchoolTim);
		dest.writeString(mEducationId);
		dest.writeString(mCertificationImageUrl);
	}

	public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
		public UserInfo createFromParcel(Parcel in) {
			UserInfo userInfo = new UserInfo();
			userInfo.setHeadUrl(in.readString());
			userInfo.setName(in.readString());
			userInfo.setFansNumber(in.readString());
			userInfo.setAgreeNumber(in.readString());
			userInfo.setVisitNumber(in.readString());
			userInfo.setAnswerQuestionNumber(in.readString());
			userInfo.setAddress(in.readString());
			userInfo.setWorkYears(in.readString());
			userInfo.setGrade(in.readString());
			userInfo.setId(in.readString());
			userInfo.setUserType(in.readString());
			userInfo.setOfferCount(in.readString());
			userInfo.setFollowStatus(in.readString());
			userInfo.setAutograph(in.readString());
			userInfo.setSex(in.readString());
			userInfo.setPhone(in.readString());
			userInfo.setQQNum(in.readString());
			userInfo.setBirthday(in.readString());
			userInfo.setEducationSchool(in.readString());
			userInfo.setMajor(in.readString());
			userInfo.setSchoolTime(in.readString());
			userInfo.setEducationId(in.readString());
			userInfo.setCertificationImageUrl(in.readString());
			return userInfo;
		}

		public UserInfo[] newArray(int size) {
			return new UserInfo[size];
		}
	};

	public static void addList(ArrayList<UserInfo> target,
			ArrayList<UserInfo> source) {
		if (source == null) {
			return;
		}
		if (target == null) {
			target = new ArrayList<UserInfo>();
		}
		for (UserInfo map : source) {
			target.add(map);
		}

	}

}
