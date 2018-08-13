package com.linsr.common.biz;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linsr.common.base.BaseFragment;
import com.linsr.common.base.mvp.IView;

/**
 * Description
 *
 * @author Linsr 2018/6/16 下午2:42
 */
public abstract class FragmentEx extends BaseFragment implements IView {

    @Override
    public void showNoData() {
        mContentLayout.setVisibility(View.GONE);
        mNoDataLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoData() {
        mContentLayout.setVisibility(View.VISIBLE);
        mNoDataLayout.setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        if (mActivity instanceof ActivityEx) {
            ((ActivityEx) mActivity).showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mActivity instanceof ActivityEx) {
            ((ActivityEx) mActivity).hideLoading();
        }
    }

    @Override
    public void showError(String text) {
        mContentLayout.setVisibility(View.VISIBLE);
        mNoDataLayout.setVisibility(View.GONE);
    }
}
