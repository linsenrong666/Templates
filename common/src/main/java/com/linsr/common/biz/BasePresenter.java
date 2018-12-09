package com.linsr.common.biz;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午5:06
 */
public abstract class BasePresenter<V extends IView> implements IPresenter {

    protected V mIView;

    public BasePresenter(V IView) {
        mIView = IView;
    }

    @Override
    public void onDestroy() {
        mIView = null;
    }
}
