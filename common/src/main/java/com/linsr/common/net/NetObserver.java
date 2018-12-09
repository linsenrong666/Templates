package com.linsr.common.net;

import android.nfc.Tag;

import com.linsr.common.biz.IView;
import com.linsr.common.utils.JLog;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Description
 *
 * @author Linsr 2018/8/8 上午10:31
 */
public abstract class NetObserver<T> implements Observer<T> {

    private static final String TAG = NetObserver.class.getSimpleName();

    private IView mIView;
    private boolean mShowLoading;

    public NetObserver(IView iView, boolean showLoading) {
        mIView = iView;
        mShowLoading = showLoading;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        JLog.v(TAG, "net  request onSubscribe ");
        showLoading();
    }

    @Override
    public void onNext(@NonNull T data) {
        JLog.v(TAG, "net  request onNext ");
        onSucceed(data);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        JLog.v(TAG, "net  request onError ");
        hideLoading();
        if (e instanceof ApiException) {
            onFailed((ApiException) e);
        } else {
            onNetError(e);
        }
    }

    @Override
    public void onComplete() {
        JLog.v(TAG, "net  request onComplete ");
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
    public abstract void onFailed(ApiException e);

    /**
     * 网络请求完成
     */
    public void onCompleted() {

    }

    public void onNetError(Throwable throwable) {

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
