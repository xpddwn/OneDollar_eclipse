package com.onedollar.base;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.example.onedollar.R;
import com.onedollar.data.Constants;
import com.onedollar.data.Preferences;
import com.onedollar.net.HttpRequest;

import android.app.Application;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class App extends Application {

	private static final String LOGO_NAME = "ahfbaifenq";

	private static ArrayList<BaseActivity> mActivities = null;

	private static App sInstance = null;

	private NotificationManager mNotificationManager = null;

	@Override
	public void onCreate() {
		super.onCreate();
//		saveLogoToPrivateCacheDir();
		sInstance = this;
		mActivities = new ArrayList<BaseActivity>();
		HttpRequest.setToken(Preferences.getData(Constants.string.TOKEN));
//		startPushService();
		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}

	public static App getInstance() {
		return sInstance;
	}

	public ArrayList<BaseActivity> getActivities() {
		return mActivities;
	}

	public BaseActivity getTopActivity() {
		if (mActivities.size() > 0) {
			return mActivities.get(mActivities.size() - 1);
		}
		return null;
	}

	public void addActivity(BaseActivity activity) {
		mActivities.add(activity);
	}

	public void removeActivity(BaseActivity activity) {
		mActivities.remove(activity);
	}

//	private void saveLogoToPrivateCacheDir() {
//		try {
//			File file = new File(getCacheDir().getAbsolutePath(), LOGO_NAME);
//			FileOutputStream outputStream = new FileOutputStream(file);
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
//					R.drawable.icon);
//			bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//			outputStream.write(baos.toByteArray());
//			outputStream.flush();
//			outputStream.close();
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public String getLogoCachePath() {
		try {
			return getCacheDir().getAbsolutePath() + "//" + LOGO_NAME;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void cleanAllNotification() {
		mNotificationManager.cancel(9876);
		mNotificationManager.cancelAll();
	}
	
}
