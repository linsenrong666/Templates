package com.linsr.login.login;

import android.text.TextUtils;

import com.linsr.common.biz.config.AppConfig;
import com.linsr.common.biz.config.UserInfoKey;
import com.linsr.common.model.ResponsePojo;
import com.linsr.common.net.Api;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.NetUtils;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.PrefsUtils;
import com.linsr.common.utils.ToastUtils;
import com.linsr.login.base.BaseLoginPresenter;
import com.linsr.login.data.LoginApi;
import com.linsr.login.data.model.response.LoginPojo;

import org.reactivestreams.Subscription;

import io.reactivex.Observable;

/**
 * Description
 *
 * @author Linsr 2018/8/8 上午9:52
 */
public class LoginPresenter extends BaseLoginPresenter<LoginContact.View> implements LoginContact.Presenter {

    LoginPresenter(LoginContact.View view) {
        super(view);
    }

    @Override
    public void login(String userName, String password) {
        if (TextUtils.isEmpty(userName)) {
            ToastUtils.show("用户名/手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show("密码不能为空");
            return;
        }
        mLoginApi.login(userName, password)
                .compose(NetUtils.handleResponse(LoginPojo.class))
                .retryWhen(NetUtils.retry())
                .as(this.<LoginPojo>bindLifecycle())
                .subscribe(new NetObserver<LoginPojo>(mView, true) {

                    @Override
                    public void onSucceed(LoginPojo data) {
                        String userId = data.getUser_id();
                        String token = data.getToken();
                        AppConfig.getInstance().login(token, userId);
                        mView.onLoginSucceed(data);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        JLog.e(e.toString());
                        mView.onLoginFailure(e.getMessage());
                    }
                });
    }
}