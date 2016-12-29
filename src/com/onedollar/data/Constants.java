package com.onedollar.data;

import com.example.onedollar.R;

public class Constants {

	// public static final String SERVER_ADDRESS =
	// "http://www.test.shunshunliuxue.com/account/api";
	public static final String SERVER_ADDRESS_WWW = "http://www.shunshunliuxue.com/";
	public static final String SERVER_ADDRESS = "http://apitest.shunshunliuxue.com/";
	// public static final String SERVER_IMG_ADDRESS =
	// "http://www.shunshunliuxue.com/uploads/avatar/";
	// public static final String SERVER_ADDRESS =
	// "http://apitemp.shunshunliuxue.com/";
	public static final String SERVER_IMG_ADDRESS = "http://cdn.shunshunliuxue.com/avatar/";
	// public static final String URL_SCHOOL_RANK =
	// "http://crptest1.shunshunliuxue.com:3001/uscollege";
	public static final String URL_SCHOOL_RANK = "http://mobile.api.shunshunliuxue.com/uscollege";

	public static final String KEHUBAO_WEB_URL = "http://khbm.shunshunliuxue.com/";

	public static final String WEB_UA = "/ShunshunAPP";

	public static class string {

		public static final String ERROR_MSG = "errorMessage";
		public static final String ERROR_CODE = "errorCode";
		public static final String RESULT_ERROR = "error";
		public static final String REQUEST_STATUS = "status";
		public static final String REQUEST_OK = "01";
		public static final String REQUEST_NO_LOGIN = "00";
		public static final String REQUEST_NO_LOGIN_OUT = "99";
		public static final String RESULT = "result";
		public static final String ENCODE_UTF8 = "utf-8";
		public static final String BUSINESS_DATA = "businessData";
		public static final String CONTENT_TYPE = "content-type";

		public static final String CONTENT = "content";

		public static final String TOKEN = "token";
		public static final String TITLE = "title";
		public static final String COUNT = "count";
		public static final String ID = "id";
		public static final String NAME = "name";
		public static final String IMAGE_URL = "iamge_url";

		public static final String ACCOUNT = "account";
		public static final String USER_INFO = "user_info";
		public static final String USER_ID = "uid";
		public static final String USER_IMG_URL = "avatar_file";
		public static final String USER_NAME = "user_name";
		public static final String ANSWER_COUNT = "answer_count";
		public static final String AGREE_COUNT = "agree_count";
		public static final String FOLLOW_COUNT = "fans_count";
		public static final String VISIT_COUNT = "views_count";

		public static final String QUESTION_ENTIFY = "question_entity";
		public static final String QUESTION_INFO = "question_info";
		public static final String QUESTION_LIST = "question_list";
		public static final String QUESTION_CONTENT = "question_content";
		public static final String QUESTION_ID = "question_id";

		public static final String ANSWER_ENTIFY = "answer_entity";
		public static final String ANSWER_LIST = "answer_list";
		public static final String ANSWER_INFO = "answer_info";
		public static final String ANSWER_ID = "answer_id";
		public static final String ANSWER_CONTENT = "answer_content";

		public static final String MY_QUESTION_LIST = "user_question";

		public static final String ATTACH_ACCESS_KEY = "attach_access_key";

		public static final String COUNSELOR_LIST = "";

		public static final String SIXIN_LIST = "get_message_list";

		public static final String LOGO_PATH_KEY = "advnafvnaifdv";
		public static final String COMUNITY_NEWS_LIST = "activity_list";
		public static final String FENSI_LIST = "get_user_fans";

		public static final String TEXT_ENTIFY = "text_entity";

		public static final String ACTIVITY_TYPE = "activity_type";

		public static final String ACTIVITY_LIST = "activity";

	}

	public static class msg {

		public static final int MSG_NO_LOGIN = 195;// 未登录
		public static final int MSG_NO_LOGIN_OUT = 196;// 登录被踢出

		public static final int MSG_REFRESH = 197;// 刷新数据

		public static final int MSG_NETWORK_EXCEPTION = 200;
		public static final int MSG_AFTER_NETWORK_COMMUNICATION_FAILURE = 201;
		public static final int MSG_AFTER_REQUEST_ERROR = 202;
		public static final int MSG_REGISTER_SUCCESS = 203; // 注册成功
		public static final int MSG_GET_VERIFYCODE_SUCCESS = 204; // 获取验证码成功
		public static final int MSG_LOGIN_SUCCESS = 205; // 登录成功
		public static final int MSG_REQUSET_TOPIC_SUCCESS = 206;// 取话题成功
		public static final int MSG_HOT_QUSTION_SUCCESS = 207;// 取热门问题
		public static final int MSG_QUETION_DETAIL = 208;// 取热门问题
		public static final int MSG_ADD_ANSWER_SUCCESS = 209;// 添加问题回答
		public static final int MSG_FOLLOW_QUESTION_SUCCESS = 210;// 关注/取消
		public static final int MSG_AGREE_ANSWER_SUCCESS = 211;// 点赞
		public static final int MSG_MODIFY_ANSWER_SUCCESS = 212;// 修改答案
		public static final int MSG_PUBLISH_QUESTION_SUCCESS = 213;// 提问
		public static final int MSG_SEARCH_INVITE_USER_SUCCESS = 214;// 邀请搜索
		public static final int MSG_COUNSELOR_DETAIL_SUCCESS = 215;// 顾问详情
		public static final int MSG_COUNSELOR_LIST_SUCCESS = 216;// 顾问列表/找顾问
		public static final int MSG_COUNTRY_INFO = 217;// 国家信息
		public static final int MSG_REQUEST_MY_QUESTIONS_SUCCESS = 218;// 我的提问列表
		public static final int MSG_REQUEST_MY_ANSWERS_SUCCESS = 219;// 我的回答列表
		public static final int MSG_SEARCH_ALL = 220;// 搜索
		public static final int MSG_MY_SIXIN_SUCCESS = 221;// 我的私信
		public static final int MSG_REQUEST_MY_FOLLOW_QUESTIONS_SUCCESS = 222;// 我关注的问题
		public static final int MSG_REQUEST_MY_FOLLOW_TOPIC_SUCCESS = 223;// 我关注的话题
		public static final int MSG_INVITE_USER_SUCCESS = 224;// 邀请回答
		public static final int MSG_MY_COMUNITY_NEWS_SUCCESS = 225;// 我的社区动态
		public static final int MSG_MY_FENSI_SUCCESS = 226;// 我的粉丝
		public static final int MSG_HOT_TOPIC_SUCCESS = 227;// 热门话题列表
		public static final int MSG_MY_FRIENDS_SUCCESS = 228;// 我关注的好友列表

		public static final int MSG_FOLLOW_TOPIC_SUCCESS = 229;// 关注话题/取消

		public static final int MSG_APP_UPDATE = 230;// 版本更新
		public static final int MSG_APP_UPDATE_PROGRESS = 231;// 进度更新
		public static final int MSG_APP_UPDATE_FINISH = 232;// 下载完成
		public static final int MSG_APP_UPDATE_FAILURE = 233;// 下载失败

		public static final int MSG_ADD_ADVICE_SUCCESS = 234;// 意见反馈
		public static final int MSG_ORDER_COUNSELOR_SUCCESS = 235;// 预约咨询
		public static final int MSG_FOCUS_PEOPLE_SUCCESS = 236;// 关注某人
		public static final int MSG_GET_IM_LIST_SUCCESS = 237;// 获取聊天纪录
		public static final int MSG_SEND_MESSAGE_SUCCESS = 238;// 发送消息

		public static final int MSG_CHECK_TOPIC_FLOLLOW_STSTUS_SUCCESS = 239;// 检查是否关注过话题
		public static final int MSG_GET_USERINFO_SUCCESS = 240;// 获取个人信息
		public static final int MSG_GET_COUNSELOR_SERVICE_SUCCESS = 241;// 获取顾问服务经历
		public static final int MAG_GET_COUNSELOR_CASE_SUCCEDD = 242;// 获取顾问服务案例
		public static final int MSG_GET_COUNSELOR_TOPIC_SUCCESS = 243;// 获取顾问擅长话题
		public static final int MSG_GET_PERSONAL_INFO_SUCCESS = 244;// 获取TA的个人信息
		public static final int MSG_GET_PERSONAL_DYNAMIC = 245;// 获取TA的动态
		public static final int MSG_EDIT_PERSONAL_INFO_SUCCESS = 246;// 编辑个人信息
		public static final int MSG_POTENTIAL_PLAN = 247;// 留学规划
		public static final int MSG_COMPLAIT_QUESTION_SUCCESS = 248;// 举报问题
		public static final int MSG_MODIFY_QUESTION_SUCCESS = 249;// 编辑问题
		public static final int MSG_EDIT_USER_EDUCATION_SUCCESS = 250;// 编辑教育经历
		public static final int MSG_NEWS_TASK_SUCCESS = 251;// 分配任务通知
		public static final int MSG_GET_LOCAL_USERINFO_SUCCESS = 253;// 获取自身信息
		public static final int MSG_GET_ANSWER_INFO_SUCCESS = 255;// 获取答案详情
		public static final int MSG_GET_LOCAL_USERINFO_FAIL = 256;// 获取自身信息
		public static final int MSG_PHONE_LOGIN_SUCCESS = 257;// 手机动态登录
		public static final int MSG_VERTIFY_SMSCODE_SUCCESS = 258;// 验证短信验证码
		public static final int MSG_RESET_PASSWORD_SUCCESS = 259;// 重置密码
		public static final int MSG_GET_FIRST_SOURCE_SUCCESS = 260;// 获取今日头条
		public static final int MSG_GET_ACTIVITY_LIST_SUCCESS = 261;// 获取活动列表
		public static final int MSG_ADD_USER_TAG = 262;// 添加/修改感兴趣的TAG
		public static final int MSG_GET_BANNER_LIST_SUCCESS = 263;// 获取banner列表
		public static final int MSG_GET_INFORMATION_LIST_SUCCESS = 264;// 获取资讯列表
		public static final int MSG_GET_SCHOOL_LIST_SUCCESS = 265;// 获取院校列表
		public static final int MSG_GET_STUDENT_CASE_LIST_SUCCESS = 266;// 获取学生案例列表
		public static final int MSG_GET_SCHOOL_DETAIL_SUCCESS = 267;// 获取院校详情
		public static final int MSG_GET_CASEINFO_DETAIL = 268;// 获取案例详情
		public static final int MSG_GET_APPLY_LIST = 269;// 获取首页攻略列表
		public static final int MSG_ASK_SCHOOL = 270;// 单点咨询
		public static final int MSG_GPA_RANK = 272;// GPA百分比
		public static final int MSG_ACTIVITY_SUCCESS_HOME_PAGE = 274;// 首页活动
	}

	public static class action {
		public static final String ACTION_GRT_VERIFYCODE = "account/api/send_sms/";
		public static final String ACTION_REGISTER = "account/api/register_process/";
		public static final String ACTION_LOGIN = "account/api/login_process/";
		public static final String ACTION_LOGOUT = "account/api/logout/";
		public static final String ACTION_GET_TOPIC = "topic/api/topic_catalog/";
		public static final String ACTION_HOT_QUESTION = "question/api/question_list/";
		public static final String ACTION_COMMUNITY_HOT_QUESTION = "question/api/question_list_to/";
		public static final String ACTION_QUESTION_DETAIL = "question/api/question/";
		public static final String ACTION_ADD_ANSWER = "question/api/save_answer/";
		public static final String ACTION_FOLLOW_QUESTION = "question/api/focus/";
		public static final String ACTION_AGREE_ANSWER = "question/api/answer_vote/";
		public static final String ACTION_MODIFY_ANSWER = "question/api/update_answer/";
		public static final String ACTION_PUBLISH_QUESTION = "publish/api/publish_question/";
		public static final String ACTION_SEARCH_INVITE_USER = "search/api/search/";
		public static final String ACTION_COUNSELOR_DETAIL = "consultant/api/advisor/";
		public static final String ACTION_COUNSELOR_LIST = "consultant/api/advisor_list/";
		public static final String ACTION_COUNTRY_INFO = "consultant/api/advisor_list/";
		public static final String ACTION_MY_QUESTION = "people/api/user_question/";
		public static final String ACTION_MY_ANSWER = "people/api/user_answer/";
		public static final String ACTION_SEARCH_ALL = "search/api/search/";
		public static final String ACTION_MY_SIXIN = "inbox/api/get_message_list/";
		public static final String ACTION_MY_FOLLOW_QUESTION = "home/api/focus_question_list/";
		public static final String ACTION_MY_FOLLOW_TOPIC = "home/api/focus_topics_list/";
		public static final String ACTION_INVITE_USER = "question/api/save_invite/";
		public static final String ACTION_SHARE_QUESTION = "m/client/question/?question_id=";
		public static final String ACTION_SHARE_ANSWER = "m/client/question/?answer_id=";
		public static final String ACTION_SHARE_CASE = "m/client/question/?answer_id=";
		public static final String ACTION_MY_COMUNITY_NEWS = "home/api/activity_list/";
		public static final String ACTION_MY_FENSI = "follow/api/get_user_fans/";
		public static final String ACTION_HOT_TOPIC = "topic/api/hot_topics/";
		public static final String ACTION_MY_FRIEND = "follow/api/get_user_friends/";
		public static final String ACTION_FOLLOW_TOPIC = "topic/api/focus_topic/";
		public static final String ACTION_APP_UPDATE = "about/api/version/";
		public static final String ACTION_ADVICE = "about/api/feedback/";
		public static final String ACTION_ORDER_COUNSELOR = "account/api/subscribe_customer_insert/";
		public static final String ACTION_FOCUS_PEOPLE = "follow/api/follow_people/";
		public static final String ACTION_GET_IM = "inbox/api/get_talking/";
		public static final String ACTION_SEND_MESSAGE = "inbox/api/send/";
		public static final String ACTION_CHECK_TOPIC_FLOLLOW_STSTUS = "topic/api/check_focus/";// 检查是否关注过话题
		public static final String ACTION_GET_USERINFO = "account/api/get_profile/";
		public static final String ACTION_GET_COUNSELOR_SERVICE_LIST = "consultant/api/advisor_tags/";
		public static final String ACTION_GET_COUNSELOR_CASE_LIST = "consultant/api/advisor_experience/";
		public static final String ACTION_GET_COUNSELOR_TOPIC_LIST = "consultant/api/reputation_topics/";
		public static final String ACTION_STUDENT_DETAIL = "people/api/space/";// TA的主页
		public static final String ACTION_STUDENT_DYNAMIC = "people/api/user_actions/";// TA的动态
		public static final String ACTION_EDIT_PERSONAL_INFO = "account/api/profile_setting/";
		public static final String ACTION_POTENTIAL_PLAN = "account/api/potential_info_insert/";// 留学规划
		public static final String ACTION_COMPLAIT_QUESTION = "question/api/report_question/";// 举报问题
		public static final String ACTION_MODIFY_QUESTION = "publish/api/modify_question/";// 编辑问题
		public static final String ACTION_EDIT_USER_EDUCATION = "account/api/add_edu/";
		public static final String ACTION_NEWS_TASK_LIST = "home/api/potential_list/";// 分配任务通知
		public static final String ACTION_GET_ANSWER_INFO = "question/api/get_answer_info/";// 获取答案详情
		public static final String ACTION_SEARCH_ADVISOR = "search/api/search_advisor/";// 搜索顾问
		public static final String ACTION_GET_PHONE_VERIFYCODE = "account/api/send_sms_to_user/";// 获取手机动态登录验证码
		public static final String ACTION_USER_PHONE_LOGIN = "account/api/login_process_by_phone/";// 手机动态登录
		public static final String ACTION_VERTIFY_SMSCODE = "account/api/verify_smscode_to/";// 验证短信验证码
		public static final String ACTION_RESET_PASSWORD = "account/api/find_password_reset/";// 重置密码
		public static final String ACTION_GET_FIRST_SOURCE = "home/api/get_recommend/";// 获取今日头条/资讯列表
		public static final String ACTION_GET_ACTIVITY_LIST = "home/api/get_activity/";// 获取活动列表
		public static final String ACTION_ADD_USER_TAG = "account/api/user_add_tag/";// 添加/修改感兴趣的TAG
		public static final String ACTION_GET_BANNER_LIST = "home/api/get_ads/";// 获取banner列表
		public static final String ACTION_GET_SCHOOL_LIST = "";// 获取院校列表
		public static final String ACTION_GET_STUDENT_CASE_LIST = "case/api/get_case_list/";// 获取学生案例列表
		public static final String ACTION_GET_CHOOSE_SCHOOL_RECOMMEND = "question/api/question_list_tag/";// 获取猜你喜欢问答列表
		public static final String ACTION_GET_AMEERICA_COLLGE_LIST = "college/api/get_usa_undergradudates/";// 获取院校列表
		public static final String ACTION_GET_AMEERICA_COLLGE_WORLD = "college/api/get_world_orders/";// 获取院校列表
		public static final String ACTION_GET_MAJORS_RANK = "college/api/get_generic_major_rank_list/";// 获取专业排名列表
		// 美本
		public static final String ACTION_GET_AMEERICA_HIGH_SCHOOL_LIST = "college/api/get_usa_highschools/";// 世界大学排名
		// 美高
		public static final String ACTION_GET_ENGLISH_COLLGE_LIST = "college/api/get_uk_undergradudates/";// 获取院校列表
		// 英本

		public static final String ACTION_SCHOOL_DETAIL = "college/api/get_undergradudate/";// 获取院校详情
																							// //美本
		public static final String ACTION_AMERICA_HIGH_SCHOOL_DETAIL = "college/api/get_highschool/";// 获取院校详情
		// //美高
		public static final String ACTION_SCHOOL_ENGLISH_DETAIL = "college/api/get_uk_undergradudate/";// 获取院校详情
		// //英本

		public static final String ACTION_SCHOOL_COUNSELOR_LIST = "case/api/get_advisorexperiences/";// 成功申请该学校的顾问

		public static final String ACTION_CASE_DETAIL = "case/api/get_advisorexperience/";// 案例详情
		public static final String ACTION_APPLY_LIST = "case/api/get_indexexperiences_list/";// 首页攻略列表
		public static final String ACTION_ASK_SCHOOL = "account/api/consultation/";// 单点咨询
		public static final String ACTION_LOCATION = "account/api/add_users_device/";// 上传地址信息
		public static final String ACTION_GPA_RANK = "case/api/get_gpa_counter/";// GPA百分比
		public static final String ACTION_APP_SUMMARY = "question/api/get_article_pages/";// 申请总结
		public static final String ACTION_OTHER_PLAN = "account/api/study_oversea/";// 另外留学规划
		public static final String ACTION_ACTICITY_HOME_PAGE = "home/api/get_ups/";// 首页活动

	}

	public static class shunshunAction {
		public static final String ACTION_RECEIVE_DATA = "com.shunshunliuxue.ACTION_RECEIVE_DATA";
		public static final String ACTION_BRING_APP_TO_FRONT = "com.shunshunliuxue.ACTION_BRING_APP_FRONT";
		public static final String ACTION_START_ACTIVITY = "com.shunshunliuxue.START_ACTIVITY";
		public static final String ACTION_RECEIVE_NEWS_PUSH = "com.shunshunliuxue.RECEIVE_NEWS_PUSH";
	}

//	public static class URL {
//		public static class banner {
//			public static final String[] URLS = {
//					SERVER_ADDRESS_WWW + "m/about/",
//					SERVER_ADDRESS_WWW + "m/client/servies/",
//					SERVER_ADDRESS_WWW + "m/services/" };
//			public static final int[] IDS = { R.drawable.banner_1,
//					R.drawable.banner_2, R.drawable.banner_3 };
//		}
//
//		public static class setting {
//			public static final String ABOUT_URL = banner.URLS[0];
//			public static final String LAW_URL = Constants.SERVER_ADDRESS
//					+ "m/client/statement/";
//		}
//
//		public static class register {
//			public static final String SERVICE_URL = banner.URLS[2];
//		}
//	}
}
