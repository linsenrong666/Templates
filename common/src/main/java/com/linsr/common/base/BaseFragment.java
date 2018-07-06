package com.linsr.common.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linsr.common.utils.JLog;
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;
import com.linsr.common.utils.contents.ContentsManager;
import com.linsr.common.utils.contents.OnContentUpdateListener;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;
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
    /**
     * 当前页面是否展示
     */
    protected boolean mIsVisible;
    private boolean mLazyLoaded;
    private boolean mIsViewCreated;

    @Override
    public void onAttach(Context context) {
        TAG = getClass().getSimpleName();
        super.onAttach(context);
        mActivity = getActivity();
        mContentsManager = ContentsManager.getInstance();

        initArguments(getArguments());

    }

    protected abstract void initArguments(Bundle arguments);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.d(TAG, "Fragment onCreate.called, this: " + getClass().getName());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewCreated = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisible = isVisibleToUser;
        if (mIsVisible) {
            onVisible();
            lazyLoad();
        } else {
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    private void lazyLoad() {
        if (!mLazyLoaded && mIsViewCreated && mIsVisible) {
            loadData();
            mLazyLoaded = true;
        }
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

    /**
     * 懒加载数据，一般网络请求放在此处，
     * 该方法只会调用一次
     */
    protected void loadData() {
    }

    /**
     * 页面可见
     */
    protected void onVisible() {
        JLog.d(TAG, "mOnContentUpdateListeners.size: " + mOnContentUpdateListeners.size());
        for (AbstractOnContentUpdateListener listener : mOnContentUpdateListeners) {
            if (listener.isUpdateHappened()) {
                listener.onContentUpdated(listener.getCachedObjects());
                listener.clearCache();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 页面隐藏
     */
    protected void onInvisible() {
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        for (OnContentUpdateListener listener : mOnContentUpdateListeners) {
            mContentsManager.unregisterOnContentUpdateListener(listener);
        }
        mOnContentUpdateListeners.clear();
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
