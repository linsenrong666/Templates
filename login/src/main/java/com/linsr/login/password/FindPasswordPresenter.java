package com.linsr.login.password;

import android.text.TextUtils;

import com.linsr.common.model.BizPojo;
import com.linsr.common.net.NetUtils;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.utils.NumberUtils;
import com.linsr.common.utils.ToastUtils;
import com.linsr.login.base.BaseLoginPresenter;
import com.linsr.login.data.model.response.CodePojo;
import com.linsr.login.data.model.response.LoginPojo;

/**
 * Description
 *
 * @author Linsr 2018/12/15 下午6:34
 */
public class FindPasswordPresenter extends BaseLoginPresenter<FindPasswordContact.View>
        implements FindPasswordContact.Presenter {

    private String mKeyCode;

    FindPasswordPresenter(FindPasswordContact.View IView) {
        super(IView);
    }


    @Override
    public void sendCode(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.show("请输入手机号码");
            return;
        }
        mKeyCode = NumberUtils.getRandomNumberStr();
        mLoginApi.sendForgetCode(mobile, mKeyCode)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(this.<BizPojo>bindLifecycle())
                .subscribe(new NetObserver<BizPojo>(mView, true, true) {
                    @Override
                    public void onSucceed(BizPojo data) {
                        mView.startCountDown();
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }

    @Override
    public void resetPassword(String mobile, String code, String password) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.show("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.show("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show("请输入新密码");
            return;
        }
        if (TextUtils.isEmpty(mKeyCode)) {
            ToastUtils.show("请先获取验证码");
            return;
        }
        mLoginApi.resetPassword(mobile, code, password, mKeyCode)
                .compose(NetUtils.handleResponse(CodePojo.class))
                .retryWhen(NetUtils.retry())
                .as(this.<CodePojo>bindLifecycle())
                .subscribe(new NetObserver<CodePojo>(mView, true, true) {
                    @Override
                    public void onSucceed(CodePojo data) {
                        mView.resetSucceed();
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }
}
