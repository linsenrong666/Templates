package com.linsr.news;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.utils.JLog;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

@Route(path = "/news/main")
public class MainActivity extends ActivityEx {

    static final int RC_CAMERA_AND_LOCATION = 1;

    @AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // Already have permission, do the thing
            // ...
            JLog.i(TAG, "有权限！");
        } else {
            JLog.e(TAG, "没权限！");
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this, getString(R.string.app_name),
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.news_activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ARouter.getInstance().build("/news/news").navigation();
//                RouterCenter.startActivity(BookModule.Activity.MAIN);

                Intent intent = new Intent();
//                intent.setClassName("com.linsr.books", "com.linsr.books.MainActivity");
//                startActivity(intent);


                try {
                    intent = new Intent(Intent.ACTION_MAIN);
                    ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
                    intent.addCategory(Intent.CATEGORY_LAUNCHER);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setComponent(cmp);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        methodRequiresTwoPermission();
    }
}
