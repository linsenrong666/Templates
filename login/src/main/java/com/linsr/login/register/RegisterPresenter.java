package com.linsr.login.register;

import android.text.TextUtils;

import com.linsr.common.biz.config.UserInfoKey;
import com.linsr.common.model.BizPojo;
import com.linsr.common.model.ResponsePojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.NetUtils;
import com.linsr.common.utils.NumberUtils;
import com.linsr.common.utils.PrefsUtils;
import com.linsr.common.utils.ToastUtils;
import com.linsr.login.base.BaseLoginPresenter;
import com.linsr.login.data.model.dto.RegisterDto;
import com.linsr.login.data.model.response.RegisterPojo;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午4:46
 */
public class RegisterPresenter extends BaseLoginPresenter<RegisterContact.View>
        implements RegisterContact.Presenter {

    private String mRandomNumber;

    RegisterPresenter(RegisterContact.View view) {
        super(view);
    }

    @Override
    public void sendCode(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.show("请输入手机号码");
            return;
        }
        mRandomNumber = NumberUtils.getRandomNumberStr();
        mLoginApi.sendCode(mobile, mRandomNumber)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(this.<BizPojo>bindLifecycle())
                .subscribe(new NetObserver<BizPojo>(mView, true, true) {

                    @Override
                    public void onSucceed(BizPojo data) {
                        ToastUtils.show("验证码已发送,请注意查收");
                        mView.startCountDown();
                    }

                    @Override
                    public void onFailed(Throwable e) {
                    }

                });
    }

    @Override
    public void register(RegisterDto dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        String code = dto.getCode();
        String recommendCode = dto.getRecode();
        String phone = dto.getMobile();
        if (TextUtils.isEmpty(mRandomNumber)) {
            ToastUtils.show("请先获取短信验证码");
            return;
        }
        mLoginApi.register(username, password, phone, code, recommendCode, mRandomNumber)
                .compose(NetUtils.handleResponse(RegisterPojo.class))
                .retryWhen(NetUtils.retry())
                .as(this.<RegisterPojo>bindLifecycle())
                .subscribe(new NetObserver<RegisterPojo>(mView, true, true) {
                    @Override
                    public void onSucceed(RegisterPojo data) {
                        String userId = data.getUser_id();
                        String token = data.getToken();
                        PrefsUtils.putSharedString(mApplication, UserInfoKey.TOKEN, token);
                        PrefsUtils.putSharedString(mApplication, UserInfoKey.USER_ID, userId);
                        mView.registerSucceed();
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }

}
