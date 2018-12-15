package com.linsr.login.base;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.biz.IView;
import com.linsr.common.net.Api;
import com.linsr.login.data.LoginApi;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午5:11
 */
public abstract class BaseLoginPresenter<V extends IView> extends PresenterEx<V> {
    protected LoginApi mLoginApi;

    public BaseLoginPresenter(V IView) {
        super(IView);
        mLoginApi = Api.getService(LoginApi.class);
    }
}
