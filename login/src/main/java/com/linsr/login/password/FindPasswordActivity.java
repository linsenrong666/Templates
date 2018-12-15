package com.linsr.login.password;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.LoginModule;
import com.linsr.common.gui.widgets.countdown.CountDownButton;
import com.linsr.common.utils.ToastUtils;
import com.linsr.common.utils.ViewUtils;
import com.linsr.login.R;

/**
 * Description
 *
 * @author Linsr 2018/8/7 上午11:22
 */
@Route(path = LoginModule.Activity.FIND_PASSWORD)
public class FindPasswordActivity extends ActivityEx<FindPasswordPresenter>
        implements FindPasswordContact.View {

    private CountDownButton mCountDownButton;
    private EditText mPhoneEditText;
    private EditText mCodeEditText;
    private EditText mNewPasswordEditText;
    private Button mConfirmButton;

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_find_password;
    }

    @Override
    protected FindPasswordPresenter bindPresenter() {
        return new FindPasswordPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleView(R.string.forgot_password);
        findViews();
        setListener();
    }

    private void setListener() {
        mCountDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.sendCode(ViewUtils.getEditTextContent(mPhoneEditText));
            }
        });
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.resetPassword(ViewUtils.getEditTextContent(mPhoneEditText),
                        ViewUtils.getEditTextContent(mCodeEditText),
                        ViewUtils.getEditTextContent(mNewPasswordEditText));
            }
        });
    }

    private void findViews() {
        mPhoneEditText = findViewById(R.id.find_pwd_phone_et);
        mCodeEditText = findViewById(R.id.find_pwd_code_et);
        mNewPasswordEditText = findViewById(R.id.find_pwd_new_et);
        mConfirmButton = findViewById(R.id.find_pwd_confirm_btn);
        mCountDownButton = findViewById(R.id.find_pwd_countdown_btn);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCountDown();
    }

    @Override
    public void startCountDown() {
        mCountDownButton.start();
    }

    @Override
    public void stopCountDown() {
        mCountDownButton.stop();
    }

    @Override
    public void resetSucceed() {
        ToastUtils.show("重设密码成功");
        finish();
    }

}
