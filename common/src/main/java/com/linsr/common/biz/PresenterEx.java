package com.linsr.common.biz;

import android.app.Application;

import com.linsr.common.base.BaseApplication;
import com.linsr.common.net.NetUtils;
import com.uber.autodispose.AutoDisposeConverter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;

import org.jetbrains.annotations.NotNull;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午5:06
 */
public abstract class PresenterEx<V extends IView> implements IPresenter {

    protected V mView;
    protected Application mApplication;
    private LifecycleOwner mLifecycleOwner;

    public PresenterEx(V IView) {
        mView = IView;
        mApplication = BaseApplication.getInstance();
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        if (null == mLifecycleOwner) {
            throw new NullPointerException("lifecycleOwner == null");
        }
        return NetUtils.bindLifecycle(mLifecycleOwner);
    }

    protected LifecycleOwner getLifecycleOwner() {
        return mLifecycleOwner;
    }

    @Override
    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        mLifecycleOwner = lifecycleOwner;
    }

    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner,
                                   @NotNull Lifecycle.Event event) {

    }

    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onStart(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onResume(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onPause(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onStop(@NotNull LifecycleOwner owner) {

    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {
        mView = null;
    }

}
