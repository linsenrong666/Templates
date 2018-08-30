package com.linsr.common.biz;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.linsr.common.R;
import com.linsr.common.base.BaseActivity;
import com.linsr.common.base.mvp.IView;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.SystemBarTintManager;
import com.linsr.common.gui.widgets.TitleView;

/**
 * 和业务相关的activity基类
 *
 * @author Linsr 2018/6/16 下午2:42
 */
public abstract class ActivityEx extends BaseActivity implements IView {

    private final Object mLockObject = new Object();

    private volatile Dialog mTransparentDialog;

    protected TitleView mTitleView;

    @Override
    protected void initTopLayout() {
        if (showTitleView()) {
            mTopLayout.setVisibility(View.VISIBLE);
            mTitleView = new TitleView(this);
            mTopLayout.addView(mTitleView);
        }
    }

    protected void initTitleView(int titleTextResId) {
        this.initTitleView(titleTextResId, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }

    protected void initTitleView(int titleTextResId, View.OnClickListener leftClickListener) {
        if (mTitleView == null) {
            JLog.e(TAG, "error: title view has not be initialized.");
            return;
        }
        mTitleView.setTitleText(getString(titleTextResId));
        mTitleView.setOnLeftClickListener(leftClickListener);
    }

    /**
     * @return 是否显示标题栏
     */
    protected boolean showTitleView() {
        return true;
    }

    /**
     * 设置空数据托底页面，子类可以修改定制
     */
    @Override
    protected void setNoDataLayout() {
        View mNoDataView = LayoutInflater.from(this).inflate(R.layout.common_layout_no_data,
                getRootContent(), false);
        mNoDataLayout.addView(mNoDataView);
    }

    @Override
    public void showNoData() {
        mNoDataLayout.setVisibility(View.VISIBLE);
        mContentLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideNoData() {
        mNoDataLayout.setVisibility(View.GONE);
        mContentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        synchronized (mLockObject) {
            hideLoading();
            mTransparentDialog = mDialogFactory.createTransparentProgressDialog(this);
            mDialogFactory.showDialog(mTransparentDialog);
        }
    }

    @Override
    public void hideLoading() {
        synchronized (mLockObject) {
            if (mTransparentDialog != null) {
                mDialogFactory.dismissDialog(mTransparentDialog);
                mTransparentDialog = null;
            }
        }
    }

    @Override
    public void showError(String text) {
        mNoDataLayout.setVisibility(View.VISIBLE);
        mContentLayout.setVisibility(View.GONE);
    }


    protected SystemBarTintManager tintManager;

    @Override
    protected void initSystemBarTint() {
        super.initSystemBarTint();
    }

    private void loadStateBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        tintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);
        // 设置一个状态栏颜色
//        tintManager.setStatusBarTintResource(setSystemBarColor());
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
