package com.linsr.login.register;

import android.text.TextUtils;

import com.linsr.common.net.ApiException;
import com.linsr.common.net.NetObserver;
import com.linsr.common.net.NetUtils;
import com.linsr.common.utils.NumberUtils;
import com.linsr.common.utils.ToastUtils;
import com.linsr.login.base.BaseLoginPresenter;
import com.linsr.login.data.model.response.CodePojo;
import com.linsr.login.data.model.dto.RegisterDto;
import com.linsr.login.data.model.response.RegisterPojo;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午4:46
 */
public class RegisterPresenter extends BaseLoginPresenter<RegisterContact.View>
        implements RegisterContact.Presenter {

    RegisterPresenter(RegisterContact.View view) {
        super(view);
    }

    @Override
    public void sendCode(String mobile) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.show("请输入手机号码");
            return;
        }
        mLoginApi.sendCode(mobile, NumberUtils.getRandomNumberStr())
                .compose(NetUtils.handleResponse(CodePojo.class))
                .subscribe(new NetObserver<CodePojo>(mIView, true) {

                    @Override
                    public void onSucceed(CodePojo data) {
                        mIView.startCountDown();
                    }

                    @Override
                    public void onFailed(ApiException e) {

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
        String key = NumberUtils.getRandomNumberStr();

        mLoginApi.register(username, password, phone, code, recommendCode, key)
                .compose(NetUtils.handleResponse(RegisterPojo.class))
                .retryWhen(NetUtils.retry())
                .subscribe(new NetObserver<RegisterPojo>(mIView, true) {
                    @Override
                    public void onSucceed(RegisterPojo data) {

                    }

                    @Override
                    public void onFailed(ApiException e) {

                    }
                });
    }

}
