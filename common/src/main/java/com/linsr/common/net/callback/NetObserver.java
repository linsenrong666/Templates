package com.linsr.common.net.callback;

import com.linsr.common.biz.IView;
import com.linsr.common.net.exception.ApiException;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.ToastUtils;

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
    private boolean mShowToast;

    public NetObserver(IView iView) {
        this(iView, false, false);
    }

    public NetObserver(IView iView, boolean showLoading) {
        this(iView, showLoading, false);
    }

    public NetObserver(IView iView, boolean showLoading, boolean showToast) {
        mIView = iView;
        mShowLoading = showLoading;
        mShowToast = showToast;
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
        printFailedToast(e);
        onFailed(e);
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
    public abstract void onFailed(Throwable e);

    /**
     * 网络请求完成
     */
    public void onCompleted() {

    }

    private void printFailedToast(Throwable e) {
        if (mShowToast && e instanceof ApiException) {
            ToastUtils.show(e.getMessage());
        }
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
