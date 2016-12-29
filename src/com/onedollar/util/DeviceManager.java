package com.onedollar.util;

import java.lang.reflect.Field;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.onedollar.base.App;

public class DeviceManager {
	
	public static final String TAG = "DeviceManager";
	
	private static Context mContext = App.getInstance();
	
	public static String getDeviceInfo() {
		StringBuffer deviceInfo = new StringBuffer();
		deviceInfo.append("collect device info , start :### \n");
		try {
			PackageManager packageManager = mContext
					.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(App
					.getInstance().getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (packageInfo != null) {
				deviceInfo.append("application version : "
						+ packageInfo.versionName + "\n");
				deviceInfo.append("application versionCode : "
						+ packageInfo.versionCode + "\n");
			}

			// 使用反射来收集设备信息.在Build类中包含各种设备信息,
			// 例如: 系统版本号,设备生产商 等帮助调试程序的有用信息
			// 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段
			Field[] fields = Build.class.getDeclaredFields();

			for (Field field : fields) {
				// setAccessible(boolean flag)
				// 将此对象的 accessible 标志设置为指示的布尔值。
				// 通过设置Accessible属性为true,才能对私有变量进行访问，不然会得到一个IllegalAccessException的异常
				field.setAccessible(true);
				deviceInfo.append("device info fieid  name : "
						+ field.getName() + " , field value : "
						+ field.get(null).toString() + "\n");
			}
		} catch (Exception e) {
			Log.e(TAG, "Error while collect crash info", e);
			e.printStackTrace();
		}
		deviceInfo.append("collect device info , end :### \n");
		return deviceInfo.toString();
	}

}
