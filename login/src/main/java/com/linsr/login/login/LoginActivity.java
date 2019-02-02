package com.linsr.login.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.LoginModule;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.ToastUtils;
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;
import com.linsr.login.R;
import com.linsr.login.base.LoginConstants;
import com.linsr.login.data.model.response.LoginPojo;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.List;

/**
 *
 * @author Linsr
 */
@Route(path = LoginModule.Activity.LOGIN)
public class LoginActivity extends ActivityEx<LoginPresenter> implements LoginContact.View {

    private EditText mAccountEditText;
    private EditText mPasswordEditText;
    private IWXAPI wxAPI;

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
        findViewById(R.id.login_wechat_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = String.valueOf(System.currentTimeMillis());
                wxAPI.sendReq(req);
            }
        });

        //wxLogin
        wxAPI = WXAPIFactory.createWXAPI(this, LoginConstants.WECHAT_APP_ID, false);
        wxAPI.registerApp(LoginConstants.WECHAT_APP_ID);
        registerOnContentUpdateListener(new AbstractOnContentUpdateListener() {
            @Override
            public void onContentUpdated(List<Object[]> values) {
                if (values != null && values.size() > 0) {
                    String code = (String) values.get(0)[0];
                    mPresenter.wxLogin(code);
                }
            }

            @Override
            public boolean isActive() {
                return mIsActive;
            }

            @Override
            public String getKey() {
                return LoginConstants.LOGIN_EVENT_WX_LOGIN_CODE;
            }
        });
    }

    @Override
    public void onLoginSucceed(LoginPojo pojo) {
        if (pojo != null) {
            Router.startActivity(MainModule.Activity.MAIN);
            finish();
        }
    }

    @Override
    public void onWXLoginSucceed(LoginPojo pojo) {
        if (pojo != null) {
            Router.startActivity(MainModule.Activity.MAIN);
            finish();
        }
    }

    @Override
    public void onLoginFailure(String msg) {
        ToastUtils.show(msg);
    }

}