package com.linsr.login.login;

import com.linsr.common.model.BasePojo;
import com.linsr.common.net.Api;
import com.linsr.common.net.NetObserver;
import com.linsr.common.net.RxHelper;
import com.linsr.common.utils.ToastUtils;
import com.linsr.login.data.LoginApi;

/**
 * Description
 *
 * @author Linsr 2018/8/8 上午9:52
 */
public class LoginPresenter implements LoginContact.Presenter {

    private LoginContact.View mView;
    private LoginApi mLoginApi;

    public LoginPresenter() {
        mLoginApi = Api.getService(LoginApi.class);
    }

    @Override
    public void login(String userName, String password) {
        mLoginApi.login(userName, password)
                .compose(RxHelper.<BasePojo>handleResponse())
                .subscribe(new NetObserver<BasePojo>(mView, true) {

                    @Override
                    public void onSucceed(BasePojo data) {

                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }

                });
    }

}