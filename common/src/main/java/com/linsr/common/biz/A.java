package com.linsr.common.biz;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.linsr.common.base.BasePresenter;
import com.linsr.common.base.BaseRepository;

/**
 * Description
 *
 * @author Linsr 2018/6/28 下午3:58
 */
public class A extends ActivityEx implements Constact.View {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        P p = new P(new BaseRepository(),this);
    }

    @Override
    public void aa() {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void hideNoData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
