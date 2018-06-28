package com.linsr.common.base;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity基类
 *
 * @author Linsr 2018/6/16 上午11:17
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public int checkPermission(String permission, int pid, int uid) {
        return super.checkPermission(permission, pid, uid);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
