package com.linsr.login.login;

import com.linsr.common.biz.BasePresenter;
import com.linsr.common.biz.config.AppConfig;
import com.linsr.common.biz.config.UserInfoKey;
import com.linsr.common.net.Api;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.NetUtils;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.PrefsUtils;
import com.linsr.login.base.BaseLoginPresenter;
import com.linsr.login.data.LoginApi;
import com.linsr.login.data.model.response.LoginPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/8 上午9:52
 */
public class LoginPresenter extends BaseLoginPresenter<LoginContact.View> implements LoginContact.Presenter {

    private LoginApi mLoginApi;

    LoginPresenter(LoginContact.View view) {
        super(view);
        mLoginApi = Api.getService(LoginApi.class);
    }

    @Override
    public void login(String userName, String password) {
        mLoginApi.login(userName, password)
                .compose(NetUtils.handleResponse(LoginPojo.class))
                .retryWhen(NetUtils.retry())
                .subscribe(new NetObserver<LoginPojo>(mView, true) {

                    @Override
                    public void onSucceed(LoginPojo data) {
                        String userId = data.getUser_id();
                        String token = data.getToken();
                        PrefsUtils.putSharedString(mApplication, UserInfoKey.TOKEN, token);
                        PrefsUtils.putSharedString(mApplication, UserInfoKey.USER_ID, userId);
                        mView.onLoginSucceed(data);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        JLog.e(e.toString());
                        mView.onLoginFailure(e.getMessage());
                    }
                });
    }

    @Override
    public void onDestroy() {
        mView = null;
    }
}