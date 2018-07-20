package com.linsr.common.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.linsr.common.R;
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
public abstract class BaseFragment extends Fragment {

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


    protected FrameLayout mContentLayout;
    protected FrameLayout mNoDataLayout;
    private View mContentView;

    @Override
    public void onAttach(Context context) {
        TAG = getClass().getSimpleName();
        super.onAttach(context);
        mActivity = getActivity();
        mContentsManager = ContentsManager.getInstance();

        initArguments(getArguments());
    }

    protected abstract int getLayoutId();

    protected abstract void initArguments(Bundle arguments);

    protected abstract void initView();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JLog.d(TAG, "Fragment onCreate.called, this: " + getClass().getName());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_fragment_base, container, false);
        mContentLayout = (FrameLayout) view.findViewById(R.id.base_content_layout);
        mNoDataLayout = (FrameLayout) view.findViewById(R.id.base_no_data_layout);

        if (mContentView != null) {
            mContentLayout.removeView(mContentView);
            mContentView = null;
        }
        mContentView = inflater.inflate(getLayoutId(), mContentLayout, false);
        mContentLayout.addView(mContentView);

        setNoDataLayout();
        return view;
    }

    private void setNoDataLayout() {

    }

    protected <T extends View> T findViewById(int resId) {
        if (mContentView == null) {
            throw new RuntimeException("content view cannot be null!");
        }
        return mContentView.findViewById(resId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewCreated = true;
        initView();
        lazyLoad();
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
        JLog.d(TAG, "Fragment onVisible " + getClass().getSimpleName());
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
        JLog.d(TAG, "Fragment onInvisible " + getClass().getSimpleName());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
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
        mActivity = null;
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
