package com.linsr.main.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.MapView;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.LoginModule;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.Permissions;
import com.linsr.main.R;

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
            JLog.i(TAG, "====有权限啊");
            getRootContent().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Router.startActivity(MainModule.Activity.MAIN);
                    finish();
                }
            }, 1000);
        } else {
            EasyPermissions.requestPermissions(this, "应用需要存储权限",
                    Permissions.REQUEST_STORAGE, Permissions.PERMISSIONS_STORAGE);
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean showTitleView() {
        return false;
    }
}
