package com.linsr.common.base.mvp;

import java.lang.ref.WeakReference;

/**
 * Description
 *
 * @author Linsr 2018/6/28 下午3:09
 */
public class BasePresenter<V extends IView> {

    protected V mView;
    protected WeakReference<V> mWeakReference;

    public BasePresenter(){
        mWeakReference = new WeakReference<V>(mView);
    }
    public void onAttach(V v) {
        mView = v;
    }

    public void onDetach() {
        mView = null;
    }

}
