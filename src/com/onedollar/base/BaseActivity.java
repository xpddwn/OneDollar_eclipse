package com.onedollar.base;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.example.onedollar.R;
import com.onedollar.data.Constants;
import com.onedollar.data.Preferences;
import com.onedollar.image.ImageCache;
import com.onedollar.image.ImageFetcher;
import com.onedollar.util.DialogUtil;
import com.onedollar.util.Util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author yoyo BaseFragmentActivity 基类
 * 
 */
public class BaseActivity extends FragmentActivity implements
		View.OnClickListener, View.OnFocusChangeListener, OnItemClickListener {
	protected String TAG = getClass().getSimpleName();
	protected Dialog mProgressDialog = null;
	protected Dialog mAlertDialog = null;
	protected Handler mHandler = null;
	protected static Handler mLocalHandler = null;

	public static final String IMAGE_CACHE_DIR = "images";

	private static ImageFetcher mImageFetcher = null;

	private EditText mHideInputMethodEditText = null;

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
//			onReceiveBroadData(intent);
		}
	};

	/**
	 * 所有省
	 */
	protected String[] mProvinceDatas;
	/**
	 * key - 省 value - 市
	 */
	protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();

	/**
	 * 当前省的名称
	 */
	protected String mCurrentProviceName;
	/**
	 * 当前市的名称
	 */
	protected String mCurrentCityName;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		// 透明状态栏
		getWindow()
				.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		// 透明导航栏
		// getWindow().addFlags(
		// WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		if (mImageFetcher == null) {
			// Fetch screen height and width, to use as our max size when
			// loading images as this
			// activity runs full screen
			final DisplayMetrics displayMetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
			final int height = displayMetrics.heightPixels;
			final int width = displayMetrics.widthPixels;

			// For this sample we'll use half of the longest width to resize our
			// images. As the
			// image scaling ensures the image is larger than this, we should be
			// left with a
			// resolution that is appropriate for both portrait and landscape.
			// For best image quality
			// we shouldn't divide by 2, but this will use more memory and
			// require a larger memory
			// cache.
			final int longest = (height > width ? height : width) / 2;

			ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams(
					this, IMAGE_CACHE_DIR);
			cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to
														// 25% of app memory

			// The ImageFetcher takes care of loading images into our ImageView
			// children asynchronously
			mImageFetcher = new ImageFetcher(this, longest);
			mImageFetcher.addImageCache(getSupportFragmentManager(),
					cacheParams);
		}
		App.getInstance().addActivity(this);
		IntentFilter intentFilter = new IntentFilter(
				Constants.shunshunAction.ACTION_START_ACTIVITY);
		intentFilter
				.addAction(Constants.shunshunAction.ACTION_RECEIVE_NEWS_PUSH);
		registerReceiver(mBroadcastReceiver, intentFilter);
	}

	public static ImageFetcher getImageFetcher() {
		return mImageFetcher;
	}

	@SuppressLint("HandlerLeak")
	private void newLocalHander() {
		if (mLocalHandler != null)
			return;
		mLocalHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case Constants.msg.MSG_AFTER_NETWORK_COMMUNICATION_FAILURE:
				case Constants.msg.MSG_AFTER_REQUEST_ERROR:
				case Constants.msg.MSG_NETWORK_EXCEPTION:
					dismissProgressDialog();
					String errorMessage = msg.getData().getString(
							Constants.string.ERROR_MSG);
					showToast(errorMessage);
					break;
				case Constants.msg.MSG_NO_LOGIN:
				case Constants.msg.MSG_NO_LOGIN_OUT:
					dismissProgressDialog();
					String errorMessage1 = msg.getData().getString(
							Constants.string.ERROR_MSG);
					showToast(errorMessage1);
					startLoginActivity();
					break;
				default:
					break;
				}
			}
		};

	}

	public void dismissSoftInput(EditText editText) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive() && editText != null
				&& editText.getWindowToken() != null) {
			imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
		}
	}

	public void setHideInputMethodEditText(EditText editText) {
		mHideInputMethodEditText = editText;
	}

	@Override
	public void finish() {
		dismissSoftInput(mHideInputMethodEditText);
		super.finish();
	}

	public boolean checkLogin() {
		if (!Preferences.getIsLogin()) {
			startLoginActivity();
			return false;
		}
		return true;
	}

	public void startLoginActivity() {
//
//		Intent intent = new Intent(this, UserLoginActivity.class);
//		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	protected void onDestroy() {
		// 销毁 mProgressDialog
		if (mProgressDialog != null) {
			if (mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
			mProgressDialog = null;
		}
		if (mAlertDialog != null) {
			if (mAlertDialog.isShowing()) {
				mAlertDialog.dismiss();
			}
			mAlertDialog = null;
		}
		if (mHandler != null) {
			mHandler.removeCallbacks(null);
		}
		App.getInstance().removeActivity(this);
		unregisterReceiver(mBroadcastReceiver);
		super.onDestroy();
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			return super.onKeyDown(keyCode, event);
		}
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 显示 mProgressDialog
	 */
	public void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = Util.createProgressDialog(this);
		}
		dismissProgressDialog();
		mProgressDialog.show();
	}

	/**
	 * 隐藏mProgressDialog
	 */
	public void dismissProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
	}

	public void showToast(int strId) {
		Toast.makeText(App.getInstance(), strId, Toast.LENGTH_SHORT).show();
	}

	public void showToast(String msg) {
		Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT).show();
	}

	public void showAlert(String title, String msg) {
		dismissAlertDialog();
		mAlertDialog = DialogUtil.showAlert(this, title, msg);
		mAlertDialog.show();
	}

	public void showAlert(int title, int msg) {
		dismissAlertDialog();
		mAlertDialog = DialogUtil.showAlert(this, title, msg);
		mAlertDialog.show();
	}

	public void showAlert(String title, int msg) {
		dismissAlertDialog();
		mAlertDialog = DialogUtil.showAlert(this, title, msg);
		mAlertDialog.show();
	}

	public void showAlert(int title, String msg) {
		dismissAlertDialog();
		mAlertDialog = DialogUtil.showAlert(this, title, msg);
		mAlertDialog.show();
	}

	public void showAlert(String msg) {
		dismissAlertDialog();
		mAlertDialog = DialogUtil.showAlert(this, msg);
		mAlertDialog.show();
	}

	public void showAlert(int msg) {
		dismissAlertDialog();
		mAlertDialog = DialogUtil.showAlert(this, msg);
		mAlertDialog.show();
	}

	public void showAlert(String msg,
			DialogInterface.OnClickListener confrimListener) {
		dismissAlertDialog();
		mAlertDialog = DialogUtil.showAlert(this, msg);
		mAlertDialog.show();
	}

	public void showAlert(int msg,
			DialogInterface.OnClickListener confrimListener) {
		dismissAlertDialog();
		mAlertDialog = DialogUtil.showAlert(this, msg);
		mAlertDialog.show();
	}

	public void dismissAlertDialog() {
		if (mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.dismiss();
		}
	}

	@Override
	public void onFocusChange(View arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		// if (getIntent() != null
		// && getIntent().getExtras() != null
		// && getIntent().getExtras().containsKey(
		// "flag_intent_from_push_receiver")) {
		// startActivity(new Intent(this, MyNewsActivity.class));
		// }
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	public void refreshFragment() {

	}

	

	protected void onReceive(Bundle data) {

	}

//	private void processNotifycation(Bundle bundle) {
//		if (bundle == null) {
//			return;
//		}
//		int type = bundle.getInt(Constants.string.ACTIVITY_TYPE);
//
//		INotification notification = NotificationFactory.create(type);
//		if (notification != null
//				&& !App.getInstance()
//						.getTopActivity()
//						.getClass()
//						.getSimpleName()
//						.equals(notification.getTargetActivityClass()
//								.getSimpleName())) {
//			Intent tartgetIntent = new Intent(BaseActivity.this,
//					notification.getTargetActivityClass());
//			Set<String> keys = bundle.keySet();
//			if (keys != null) {
//				for (String key : keys) {
//					if (!Constants.string.ACTIVITY_TYPE.equals(key)) {
//						tartgetIntent.putExtra(key, bundle.getString(key));
//					}
//				}
//			}
//			startActivity(tartgetIntent);
//		} else if (notification != null) {
//			App.getInstance().getTopActivity()
//					.onReceive(notification.getBundle());
//		}
//	}

//	protected void onReceiveBroadData(Intent intent) {
//
//		if (intent != null) {
//			Log.e(TAG, "onReceive", intent.getAction());
//			if (Constants.shunshunAction.ACTION_START_ACTIVITY
//					.equalsIgnoreCase(intent.getAction())
//					&& BaseActivity.this.equals(App.getInstance()
//							.getTopActivity())) {
//				processNotifycation(intent.getExtras());
//			} else if (Constants.shunshunAction.ACTION_RECEIVE_NEWS_PUSH
//					.equalsIgnoreCase(intent.getAction())) {
//				if (BaseActivity.this.equals(App.getInstance().getActivities()
//						.get(0))) {
//
//					for (int i = App.getInstance().getActivities().size(); i > 1; i--) {
//						App.getInstance().getActivities().get(i - 1).finish();
//					}
//					((MainAcitity) App.getInstance().getActivities().get(0))
//							.onClickViewId(R.id.today_news);
//				}
//			}
//		}
//
//	}

	public void setCurrentItem(int position) {
		return;
	}

	public void startRecommendActivity() {
		return;
	}

	protected void fontSizeSet() {
		/** 系统字体大小修改后对应用内部没有影响 */
		Resources resource = getResources();
		Configuration c = resource.getConfiguration();
		c.fontScale = 1.0f;
		resource.updateConfiguration(c, resource.getDisplayMetrics());
	}
}
