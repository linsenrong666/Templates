package com.linsr.common.biz;

import android.app.Application;

import com.linsr.common.base.BaseApplication;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午5:06
 */
public abstract class BasePresenter<V extends IView> implements IPresenter {

    protected V mView;
    protected Application mApplication ;

    public BasePresenter(V IView) {
        mView = IView;
        mApplication = BaseApplication.getInstance();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}
