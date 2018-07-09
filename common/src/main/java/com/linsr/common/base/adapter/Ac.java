package com.linsr.common.base.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.linsr.common.base.BaseActivity;
import com.linsr.common.base.mvp.BasePresenter;
import com.linsr.common.base.mvp.IView;

/**
 * Description
 *
 * @author Linsr 2018/7/9 下午4:47
 */
public class Ac extends BaseActivity implements IView{

    private BasePresenter mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBasePresenter = new BasePresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void showNoData(String text) {

    }

    @Override
    public void hideNoData() {

    }

    @Override
    public void showLoading(String text) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String text) {

    }


}
