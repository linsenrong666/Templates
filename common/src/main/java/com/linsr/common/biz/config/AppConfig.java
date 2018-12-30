package com.linsr.common.biz.config;

import android.app.Application;
import android.text.TextUtils;

import com.linsr.common.biz.ApplicationEx;
import com.linsr.common.utils.PrefsUtils;

/**
 * Description
 *
 * @author Linsr 2018/12/15 下午4:23
 */
public class AppConfig implements UserInfoKey {

    private Application mApplication;

    private AppConfig() {
        mApplication = ApplicationEx.getInstance();
    }

    private volatile static AppConfig mInstance;

    public static AppConfig getInstance() {
        if (mInstance == null) {
            synchronized (AppConfig.class) {
                if (mInstance == null) {
                    mInstance = new AppConfig();
                }
            }
        }
        return mInstance;
    }

    public boolean isLoggedIn() {
        return !TextUtils.isEmpty(PrefsUtils.getSharedString(mApplication, UserInfoKey.TOKEN));
    }

    public void login(String token, String userId) {
        PrefsUtils.putSharedString(mApplication, UserInfoKey.TOKEN, token);
        PrefsUtils.putSharedString(mApplication, UserInfoKey.USER_ID, userId);
    }

    public void logout() {
        PrefsUtils.putSharedString(mApplication, UserInfoKey.TOKEN, "");
        PrefsUtils.putSharedString(mApplication, UserInfoKey.USER_ID, "");
    }

}
