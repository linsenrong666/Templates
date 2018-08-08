package com.linsr.common.net;

import com.linsr.common.base.mvp.IView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Description
 *
 * @author Linsr 2018/8/8 上午10:31
 */
public abstract class NetObserver<T> implements Observer<T> {

    private IView mIView;
    private boolean mShowLoading;

    public NetObserver(IView iView, boolean showLoading) {
        mIView = iView;
        mShowLoading = showLoading;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        showLoading();
    }

    @Override
    public void onNext(@NonNull T data) {
        onSucceed(data);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailed(e);
    }

    @Override
    public void onComplete() {
        hideLoading();
        onCompleted();
    }

    /**
     * 网络请求成功
     * @param data data
     */
    public abstract void onSucceed(T data);

    /**
     * 网络请求失败
     * @param e error
     */
    public abstract void onFailed(Throwable e);

    /**
     * 网络请求完成
     */
    public void onCompleted() {

    }

    private void hideLoading() {
        if (mShowLoading) {
            if (mIView != null) {
                mIView.hideLoading();
            }
        }
    }

    private void showLoading() {
        if (mShowLoading) {
            if (mIView != null) {
                mIView.showLoading();
            }
        }
    }
}
