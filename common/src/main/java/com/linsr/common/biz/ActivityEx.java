package com.linsr.common.biz;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.linsr.common.R;
import com.linsr.common.base.BaseActivity;
import com.linsr.common.base.mvp.IView;
import com.linsr.common.widgets.TitleView;

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
    public void showNoData(String text) {
        mNoDataLayout.setVisibility(View.VISIBLE);
        mContentLayout.setVisibility(View.GONE);
    }

    @Override
    public void hideNoData() {
        mNoDataLayout.setVisibility(View.GONE);
        mContentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading(String text) {
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

}
