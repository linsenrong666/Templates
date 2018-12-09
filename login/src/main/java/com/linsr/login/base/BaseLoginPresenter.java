package com.linsr.login.base;

import com.amap.api.location.APSService;
import com.linsr.common.biz.BasePresenter;
import com.linsr.common.biz.IView;
import com.linsr.common.net.Api;
import com.linsr.login.data.LoginApi;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午5:11
 */
public abstract class BaseLoginPresenter<V extends IView> extends BasePresenter<V> {
    protected LoginApi mLoginApi;

    public BaseLoginPresenter(V IView) {
        super(IView);
        mLoginApi = Api.getService(LoginApi.class);
    }
}
