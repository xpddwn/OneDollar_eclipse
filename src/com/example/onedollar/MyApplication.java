package com.example.onedollar;

import android.app.Application;

/**
 * Created by lenovo on 2016/12/21.
 */
public class MyApplication extends Application {
    public static String TAG;
    public static MyApplication myApplication;

    public static MyApplication newInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TAG = this.getClass().getSimpleName();
        myApplication = this;

    }
}
