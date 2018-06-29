package com.linsr.common.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.linsr.common.utils.JLog;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Activity基类
 *
 * @author Linsr 2018/6/16 上午11:17
 */
public abstract class BaseActivity extends AppCompatActivity implements
        EasyPermissions.PermissionCallbacks {

    protected String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> list) {
        JLog.i(TAG, "===成功获取权限，requestCode:" + requestCode + "，list:" + list.toString());
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> list) {
        JLog.i(TAG, "===获取权限失败，requestCode:" + requestCode + "，list:" + list.toString());
    }
}
