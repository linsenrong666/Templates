package com.linsr.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.linsr.common.utils.JLog;
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;
import com.linsr.common.utils.contents.ContentsManager;
import com.linsr.common.utils.contents.OnContentUpdateListener;

import java.util.ArrayList;
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
    protected ContentsManager mContentsManager;
    private List<AbstractOnContentUpdateListener> mOnContentUpdateListeners = new ArrayList<>();
    protected boolean mIsActive = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        mContentsManager = ContentsManager.getInstance();
    }

    /**
     * 注册事件监听回调
     * @param listener 监听
     */
    protected void registerOnContentUpdateListener(AbstractOnContentUpdateListener listener) {
        if (mContentsManager.registerOnContentUpdateListener(listener)) {
            mOnContentUpdateListeners.add(listener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIsActive = true;
        JLog.d(TAG, "onResume.called,this:" + getClass().getName());
        JLog.d(TAG, "mOnContentUpdateListeners.size:" + mOnContentUpdateListeners.size());
        for (AbstractOnContentUpdateListener listener : mOnContentUpdateListeners) {
            if (listener.isUpdateHappened()) {
                listener.onContentUpdated(listener.getCachedObjects());
                listener.clearCache();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsActive = false;
        JLog.d(TAG, "onPause.called,this:" + getClass().getName());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (OnContentUpdateListener listener : mOnContentUpdateListeners) {
            mContentsManager.unregisterOnContentUpdateListener(listener);
        }
        mOnContentUpdateListeners.clear();
    }

}
