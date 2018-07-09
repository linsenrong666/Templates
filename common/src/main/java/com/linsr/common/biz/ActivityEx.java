package com.linsr.common.biz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.linsr.common.R;
import com.linsr.common.base.BaseActivity;
import com.linsr.common.base.mvp.IView;

/**
 * 和业务相关的activity基类
 *
 * @author Linsr 2018/6/16 下午2:42
 */
public abstract class ActivityEx extends BaseActivity implements IView {

    private View mContentView;

    @Override
    protected void onCreateEx(@Nullable Bundle savedInstanceState) {
        mContentView = LayoutInflater.from(this).inflate(getLayoutId(), getRootContent(), false);
        mContentLayout.addView(mContentView);
        setNoDataLayout();

        init();
        initView();
    }

    protected abstract int getLayoutId();

    protected abstract void init();

    protected abstract void initView();

    protected void setNoDataLayout() {
        View mNoDataView = LayoutInflater.from(this).inflate(R.layout.common_layout_no_data, getRootContent(), false);

        mNoDataLayout.addView(mNoDataView);
    }

    @Override
    public void showNoData(String text) {
        mNoDataLayout.setVisibility(View.VISIBLE);
        mContentView.setVisibility(View.GONE);
    }

    @Override
    public void hideNoData() {
        mNoDataLayout.setVisibility(View.GONE);
        mContentView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading(String text) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String text) {
        mNoDataLayout.setVisibility(View.VISIBLE);
        mContentView.setVisibility(View.GONE);
    }

}
