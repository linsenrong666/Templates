package com.linsr.main.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.linsr.common.biz.ActivityEx;
import com.linsr.common.biz.config.AppConfig;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.LoginModule;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.Permissions;
import com.linsr.common.utils.PrefsUtils;
import com.linsr.main.R;
import com.linsr.main.app.Constants;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Description
 *
 * @author Linsr 2018/7/16 下午2:45
 */
public class SplashActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_splash_screen;
    }

    @Override
    protected void onCreateEx(@Nullable Bundle savedInstanceState) {
        super.onCreateEx(savedInstanceState);
        requestPermissions();
    }

    @AfterPermissionGranted(Permissions.REQUEST_STORAGE)
    private void requestPermissions() {
        if (EasyPermissions.hasPermissions(this, Permissions.PERMISSIONS_STORAGE)) {
            getRootContent().postDelayed(new Runnable() {
                @Override
                public void run() {
                    start();
                }
            }, 1000);
        } else {
            EasyPermissions.requestPermissions(this, "应用需要存储权限",
                    Permissions.REQUEST_STORAGE, Permissions.PERMISSIONS_STORAGE);
        }
    }

    private void start() {
        if (AppConfig.getInstance().isLoggedIn()) {
            Router.startActivity(MainModule.Activity.MAIN);
        } else {
            Router.startActivity(LoginModule.Activity.LOGIN);
        }
        finish();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean showTitleView() {
        return false;
    }
}
