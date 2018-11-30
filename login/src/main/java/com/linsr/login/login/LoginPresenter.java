package com.linsr.login.login;

import com.linsr.common.net.Api;
import com.linsr.common.net.NetObserver;
import com.linsr.common.net.NetUtils;
import com.linsr.common.utils.JLog;
import com.linsr.login.data.LoginApi;
import com.linsr.login.data.model.LoginPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/8 上午9:52
 */
public class LoginPresenter implements LoginContact.Presenter {

    private LoginContact.View mView;
    private LoginApi mLoginApi;

    public LoginPresenter(LoginContact.View view) {
        mLoginApi = Api.getService(LoginApi.class);
        mView = view;
    }

    @Override
    public void login(String userName, String password) {
        mLoginApi.login(userName, password)
                .compose(NetUtils.handleResponse(LoginPojo.class))
                .subscribe(new NetObserver<LoginPojo>(mView, true) {

                    @Override
                    public void onSucceed(LoginPojo data) {
                        String message = data.getMessage();
                        JLog.i(message);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        JLog.e(e.toString());
                    }

                });
    }

}