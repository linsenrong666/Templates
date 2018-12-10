package com.linsr.login.register;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.gui.widgets.countdown.CountDownButton;
import com.linsr.common.gui.widgets.countdown.CountDownHandler;
import com.linsr.common.router.url.LoginModule;
import com.linsr.common.utils.NumberUtils;
import com.linsr.common.utils.ObjectUtils;
import com.linsr.common.utils.ToastUtils;
import com.linsr.common.utils.ViewUtils;
import com.linsr.login.R;
import com.linsr.login.data.model.dto.RegisterDto;

/**
 * Description
 *
 * @author Linsr 2018/8/7 上午11:15
 */
@Route(path = LoginModule.Activity.REGISTER)
public class RegisterActivity extends ActivityEx<RegisterPresenter> implements RegisterContact.View {

    private EditText mUserNameEditText;
    private EditText mPwdEditText;
    private EditText mRePwdEditText;
    private EditText mCodeEditText;
    private EditText mPhoneEditText;
    private EditText mRecommendEditText;
    private CountDownButton mCodeButton;
    private Button mRegisterButton;


    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_register;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.register);
        findView();
        setListener();
    }

    @Override
    protected RegisterPresenter bindPresenter() {
        return new RegisterPresenter(this);
    }

    private void setListener() {
        mCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.sendCode(ViewUtils.getEditTextContent(mPhoneEditText));
            }
        });
        mCodeButton.setOnCountDownListener(new CountDownHandler.OnCountDownListener() {
            @Override
            public void onCountDown(long time) {

            }

            @Override
            public void onStop() {

            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterDto dto = createDto();
                if (dto != null) {
                    mPresenter.register(dto);
                }
            }
        });
    }

    private RegisterDto createDto() {
        String username = ViewUtils.getEditTextContent(mUserNameEditText);
        if (TextUtils.isEmpty(username)) {
            ToastUtils.show("请输入用户名");
            return null;
        }
        String password = ViewUtils.getEditTextContent(mPwdEditText);
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show("请输入密码");
            return null;
        }
        String rePassword = ViewUtils.getEditTextContent(mRePwdEditText);
        if (TextUtils.isEmpty(rePassword)) {
            ToastUtils.show("请输入确认密码");
            return null;
        }
        if (!TextUtils.equals(password, rePassword)) {
            ToastUtils.show("两次输入密码不一致");
            return null;
        }
        String mobile = ViewUtils.getEditTextContent(mPhoneEditText);
        if (TextUtils.isEmpty(mobile)) {
            ToastUtils.show("请输入手机号码");
            return null;
        }
        String code = ViewUtils.getEditTextContent(mCodeEditText);
        if (TextUtils.isEmpty(code)) {
            ToastUtils.show("请输入验证码");
            return null;
        }
        String recommendCode = ViewUtils.getEditTextContent(mRecommendEditText);
        String key = NumberUtils.getRandomNumberStr();
        return new RegisterDto(username, password, mobile, code, recommendCode, key);
    }


    private void findView() {
        mUserNameEditText = findViewById(R.id.register_user_name_et);
        mPwdEditText = findViewById(R.id.register_password_et);
        mRePwdEditText = findViewById(R.id.register_re_password_et);
        mCodeEditText = findViewById(R.id.register_code_et);
        mPhoneEditText = findViewById(R.id.register_phone_et);
        mRecommendEditText = findViewById(R.id.register_recommend_code_et);
        mCodeButton = findViewById(R.id.register_code_btn);
        mRegisterButton = findViewById(R.id.register_confirm_btn);
    }

    @Override
    public void startCountDown() {
        mCodeButton.start();
    }

    @Override
    public void stopCountDown() {
        mCodeButton.stop();
    }

}