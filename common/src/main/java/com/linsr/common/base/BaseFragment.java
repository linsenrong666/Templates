package com.linsr.common.base;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.linsr.common.utils.JLog;
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;
import com.linsr.common.utils.contents.ContentsManager;
import com.squareup.leakcanary.RefWatcher;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Fragment 基类
 *
 * @author Linsr 2018/6/16 上午11:18
 */
public abstract class BaseFragment extends Fragment implements EasyPermissions.PermissionCallbacks {

    protected Activity mActivity;
    protected String TAG;
    protected ContentsManager mContentsManager;
    private List<AbstractOnContentUpdateListener> mOnContentUpdateListeners = new ArrayList<>();
    protected boolean mIsActive = true;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG = getClass().getSimpleName();
        mActivity = getActivity();
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
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = BaseApplication.getInstance().getRefWatcher();
        if (null != refWatcher) {
            refWatcher.watch(this);
        } else {
            JLog.e(TAG, "Error:RefWatcher is null!");
        }
    }


}
