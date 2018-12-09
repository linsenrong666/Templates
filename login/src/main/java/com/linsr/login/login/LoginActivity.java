package com.linsr.login.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.biz.IPresenter;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.LoginModule;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.ToastUtils;
import com.linsr.login.R;

/**
 *
 * @author Linsr
 */
@Route(path = LoginModule.Activity.LOGIN)
public class LoginActivity extends ActivityEx<LoginPresenter> implements LoginContact.View {

    private EditText mAccountEditText;
    private EditText mPasswordEditText;

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_login;
    }

    @Override
    protected LoginPresenter bindPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleView(R.string.login);
        mAccountEditText = findViewById(R.id.login_account_et);
        mPasswordEditText = findViewById(R.id.login_password_et);

        TextView registerTextView = findViewById(R.id.login_register_tv);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(LoginModule.Activity.REGISTER);
            }
        });

        TextView mForgotTextView = findViewById(R.id.login_forgot_password_tv);
        mForgotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(LoginModule.Activity.FIND_PASSWORD);
            }
        });

        Button mConfirmButton = findViewById(R.id.login_confirm_btn);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = mAccountEditText.getText().toString();
                String password = mPasswordEditText.getText().toString();

                mPresenter.login(userName, password);
            }
        });
    }


    @Override
    public void onLoginSucceed() {

    }

    @Override
    public void onLoginFailure(String msg) {
        ToastUtils.show(msg);
    }

}