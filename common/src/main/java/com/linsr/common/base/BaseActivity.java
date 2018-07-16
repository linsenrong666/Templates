package com.linsr.common.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.linsr.common.R;
import com.linsr.common.dialogs.DialogFactory;
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

    protected DialogFactory mDialogFactory;

    private RelativeLayout mMiddleLayout;
    protected FrameLayout mTopLayout;
    protected FrameLayout mBottomLayout;
    protected FrameLayout mContentLayout;
    protected FrameLayout mNoDataLayout;

    /**
     * 加载layout id
     * @return id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化 view
     */
    protected abstract void initView();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置无title样式
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setOrientation();
        super.onCreate(savedInstanceState);
        init();
        findView();
        onCreateEx(savedInstanceState);
        setContentLayout();
        setNoDataLayout();
        initView();
    }

    protected void onCreateEx(@Nullable Bundle savedInstanceState) {
    }

    private void setContentLayout() {
        View mContentView = LayoutInflater.from(this).inflate(getLayoutId(), getRootContent(), false);
        mContentLayout.addView(mContentView);
    }

    /**
     * 初始化，声明周期很靠前，建议再此进行接收intent参数，初始化变量等操作
     */
    protected void init() {
        TAG = getClass().getSimpleName();
        mContentsManager = ContentsManager.getInstance();
        mDialogFactory = DialogFactory.getInstance();
    }

    /**
     * 设置空数据托底页面，子类可以修改定制
     */
    protected void setNoDataLayout() {
    }

    private void findView() {
        setContentView(R.layout.common_activity_base);
        mTopLayout = (FrameLayout) findViewById(R.id.base_top_layout);
        mMiddleLayout = (RelativeLayout) findViewById(R.id.base_middle_layout);
        mBottomLayout = (FrameLayout) findViewById(R.id.base_bottom_layout);
        mContentLayout = (FrameLayout) findViewById(R.id.base_content_layout);
        mNoDataLayout = (FrameLayout) findViewById(R.id.base_no_data_layout);
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
        JLog.d(TAG, "Activity onResume.called , this: " + getClass().getName());
        JLog.d(TAG, "mOnContentUpdateListeners.size: " + mOnContentUpdateListeners.size());
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
        JLog.d(TAG, "Activity onPause.called , this: " + getClass().getName());
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

    /**
     * 获取根视图
     */
    protected ViewGroup getRootContent() {
        return ((ViewGroup) findViewById(android.R.id.content));
    }

    /**
     * 后退
     */
    public void back() {
        onBackPressed();
    }

    /**
     * 设置屏幕方向
     */
    protected void setOrientation() {
        //默认设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (OnContentUpdateListener listener : mOnContentUpdateListeners) {
            mContentsManager.unregisterOnContentUpdateListener(listener);
        }
        mOnContentUpdateListeners.clear();
    }

    public static void startSelf(Context context, Class c) {
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
    }
}
