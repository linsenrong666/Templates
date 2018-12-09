package com.linsr.common.biz;

import android.content.Context;
import android.view.View;

import com.linsr.common.base.BaseFragment;

/**
 * Description
 *
 * @author Linsr 2018/6/16 下午2:42
 */
public abstract class FragmentEx<P extends IPresenter> extends BaseFragment implements IView {

    protected P mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = bindPresenter();
    }

    protected P bindPresenter() {
        return null;
    }

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }
}
