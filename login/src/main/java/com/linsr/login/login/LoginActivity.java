package com.linsr.login.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.LoginModule;
import com.linsr.login.R;

/**
 *
 * @author Linsr
 */
@Route(path = LoginModule.Activity.LOGIN)
public class LoginActivity extends ActivityEx {

    private EditText mAccountEditText;
    private EditText mPasswordEditText;
    private Button mConfirmButton;
    private TextView mForgotTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_login;
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
        mForgotTextView = findViewById(R.id.login_forgot_password_tv);
        mForgotTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(LoginModule.Activity.FIND_PASSWORD);
            }
        });
        mConfirmButton = findViewById(R.id.login_confirm_btn);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

}
