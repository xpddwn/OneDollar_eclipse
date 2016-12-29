package com.onedollar.net;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.onedollar.R;
import com.onedollar.base.App;
import com.onedollar.data.Constants;
import com.onedollar.data.Preferences;
import com.onedollar.util.JsonParser;
import com.onedollar.util.Log;
import com.onedollar.util.Util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

public class NetworkDelegateImplNew implements INetworkDelegate {
	public static String TAG = "INetworkDelegateImpl";
	public static int DEFAULT_WHAT = -1;
	private HashMap<String, Object> mHashMap = null;
	private int mEndMessageWhat = DEFAULT_WHAT;
	private int mExceptionMessageWhat = Constants.msg.MSG_NETWORK_EXCEPTION;
	private int mNetCommunicationFailureMessageWhat = Constants.msg.MSG_AFTER_NETWORK_COMMUNICATION_FAILURE;
	private int mFailureType = DEFAULT_WHAT;
	private Handler mHandler = null;
	private Context mContext = null;
	private String mListKey = null;
	private CommunicationEndCallBack mEndCallBack = null;
	private boolean isDataArrayList = false;
	private String mSaveKey = null;

	public NetworkDelegateImplNew(Handler handler,
			HashMap<String, Object> hashMap) {
		if (hashMap == null) {
			Log.e(TAG,
					"INetworkDelegateImpl constructure,hashMap can't be null or ");
			return;
		}
		if (handler == null) {
			Log.e(TAG,
					"INetworkDelegateImpl constructure,handler can't be null or ");
			return;
		}
		isDataArrayList = false;
		mHandler = handler;
		mHashMap = hashMap;
		mContext = App.getInstance();
	}

	public NetworkDelegateImplNew(Handler handler) {
		if (handler == null) {
			Log.e(TAG,
					"INetworkDelegateImpl constructure,handler can't be null ");
			return;
		}
		isDataArrayList = true;
		mHandler = handler;
		mContext = App.getInstance();
	}

	public void setEndMessageWhat(int endWhat) {
		mEndMessageWhat = endWhat;
	}

	public void setFailureType(int type) {
		mFailureType = type;
	}

	public void setListKey(String key) {
		mListKey = key;
	}

	public void setEndCallBack(CommunicationEndCallBack callBack) {
		mEndCallBack = callBack;
	}

	public void setSaveKey(String key) {
		mSaveKey = key;
	}

	private void end() {
		if (mEndCallBack != null)
			mEndCallBack.onEnd();
	}

	private void sendMessage2UIThread(int what, int messageId) {
		if (mHandler != null && what != -1) {

			Message msg = mHandler.obtainMessage(what);
			if (mFailureType != DEFAULT_WHAT) {
				msg.arg1 = mFailureType;
			}
			if (messageId != DEFAULT_WHAT) {
				msg.getData().putString(Constants.string.ERROR_MSG,
						mContext.getResources().getString(messageId));
			}
			mHandler.sendMessage(msg);
		}
	}

	private void sendEmptyMeassage2UIThread(int what, String error) {
		Message msg = mHandler.obtainMessage(what);
		if (error != null && error.length() > 0) {
			msg.getData().putString(Constants.string.ERROR_MSG, error);
		}
		mHandler.sendMessage(msg);
	}

	private void sendEmptyMeassage2UIThread(int what, Object obj) {
		Message msg = mHandler.obtainMessage(what);
		msg.obj = obj;
		mHandler.sendMessage(msg);
	}

	private void sendMessage2UIThread(int what, String message) {
		if (mHandler != null && what != -1) {

			Message msg = mHandler.obtainMessage(what);
			if (mFailureType != DEFAULT_WHAT) {
				msg.arg1 = mFailureType;
			}
			if (message != null) {
				msg.getData().putString(Constants.string.ERROR_MSG, message);
			}
			mHandler.sendMessage(msg);
		}
	}

	@Override
	public void didStartLoad() {
		// sendMessage2UIThread(mStartMessageWhat, DEFAULT_WHAT);
	}

	@Override
	public void didFinishedLoad(Object resposeResult) {

		end();
		try {
			String jsonStr = JsonParser
					.formatStreamToString((InputStream) resposeResult);

			// jsonStr = jsonStr.replace("null", "\"\"");
			Log.i(TAG, "from server data is :" + jsonStr);
			HashMap<String, Object> map = Util.convertObj2HashMap(JsonParser
					.parserRandomJsonFormat(jsonStr));
			if (mEndMessageWhat == Constants.msg.MSG_LOGIN_SUCCESS) {

				try {
					HashMap<String, Object> resultHeader = Util
							.convertObj2HashMap(Util.getObject(map, "header"));

					if (Util.hasKey(resultHeader, Constants.string.TOKEN)) {
						HttpRequest.setToken(resultHeader.get(
								Constants.string.TOKEN).toString());
						Preferences.saveData(Constants.string.TOKEN,
								resultHeader.get(Constants.string.TOKEN)
										.toString());
					}
				} catch (Exception e) {
					Log.e(TAG, "error get token ", e);
				}
			}

			final HashMap<String, Object> resultBody = Util
					.convertObj2HashMap(Util.getObject(map,
							Constants.string.RESULT));
			// resultBody.put(Constants.string.REQUEST_STATUS, "01");

			String status = Util.getValue(resultBody,
					Constants.string.REQUEST_STATUS);
			HashMap<String, Object> error = Util.convertObj2HashMap(Util
					.getObject(resultBody, Constants.string.RESULT_ERROR));
			String errorCode = Util
					.getValue(error, Constants.string.ERROR_CODE);
			String errorMessage = Util.getValue(error,
					Constants.string.ERROR_MSG);
			if (TextUtils.isEmpty(errorMessage)) {
				errorMessage = mContext.getResources().getString(
						R.string.server_exception);
			}
			if (Constants.string.REQUEST_OK.equals(status)) {
				if (mSaveKey != null) {
					Preferences.saveData(mSaveKey, jsonStr);
				}
				HashMap<String, Object> bussnessDataHashMap = Util
						.convertObj2HashMap(Util.getObject(resultBody,
								Constants.string.BUSINESS_DATA));
				if (isDataArrayList) {
					ArrayList<HashMap<String, Object>> arrayList = Util
							.convertObj2List(Util.getObject(
									bussnessDataHashMap, mListKey));

					sendEmptyMeassage2UIThread(mEndMessageWhat, arrayList);
				} else {
					Util.HashMapCopy(bussnessDataHashMap, mHashMap);
					mHashMap.put(Constants.string.ERROR_MSG, errorMessage);
					sendEmptyMeassage2UIThread(mEndMessageWhat, null);
				}
			} else if (Constants.string.REQUEST_NO_LOGIN.equals(status)) {
				sendEmptyMeassage2UIThread(mEndMessageWhat,
						R.string.user_no_login);
				Preferences.localLogout();

			} else if (Constants.string.REQUEST_NO_LOGIN_OUT.equals(status)) {
				sendEmptyMeassage2UIThread(mEndMessageWhat,
						R.string.user_no_login_out);
				Preferences.localLogout();
			} else {// �?�?

				Log.e(TAG, "errorCode : " + errorCode);
				Log.e(TAG, "errorMessage : " + errorMessage);
				sendEmptyMeassage2UIThread(
						Constants.msg.MSG_AFTER_REQUEST_ERROR, errorMessage);
			}
		} catch (Exception e) {
			sendMessage2UIThread(mExceptionMessageWhat,
					R.string.server_exception);
			Log.e(TAG, e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void didFailureLoad(String errorMessage) {
		end();
		sendMessage2UIThread(mNetCommunicationFailureMessageWhat, errorMessage);

	}

	@Override
	public void didLoading(String progress) {

	}

	public static interface CommunicationEndCallBack {
		public void onEnd();

	}
}
