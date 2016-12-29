package com.onedollar.dal;

import java.lang.reflect.Field;

import android.util.Log;

public class ToString {

	public String toString() {

		Log.e("ToString", "tostring(");
		StringBuffer deviceInfo = new StringBuffer();
		Field[] fields = this.getClass().getDeclaredFields();

		try {
			for (Field field : fields) {
				// setAccessible(boolean flag)
				// 将此对象的 accessible 标志设置为指示的布尔值。
				// 通过设置Accessible属性为true,才能对私有变量进行访问，不然会得到一个IllegalAccessException的异常
				field.setAccessible(true);
				deviceInfo.append(field.getName() + "="
						+ field.get(this).toString() + ",");
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return deviceInfo.toString();
	}
}
