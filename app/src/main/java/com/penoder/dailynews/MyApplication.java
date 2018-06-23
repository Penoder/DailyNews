package com.penoder.dailynews;

import android.app.Application;

/**
 * @author Penoder
 * @date 18-4-18.
 */
public class MyApplication extends Application {

    private static MyApplication mApp;

    public static MyApplication getInstance() {
        if (mApp == null) {
            synchronized (MyApplication.class) {
                if (mApp == null) {
                    mApp = new MyApplication();
                }
            }
        }
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private boolean isLogin = false;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
