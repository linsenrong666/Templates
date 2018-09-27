package com.linsr.login.password;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.LoginModule;
import com.linsr.common.gui.widgets.countdown.CountDownButton;
import com.linsr.login.R;

/**
 * Description
 *
 * @author Linsr 2018/8/7 上午11:22
 */
@Route(path = LoginModule.Activity.FIND_PASSWORD)
public class FindPasswordActivity extends ActivityEx {
    private CountDownButton mCountDownButton;

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_find_password;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.forgot_password);
        mCountDownButton = findViewById(R.id.find_pwd_countdown_btn);
        mCountDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownButton.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCountDownButton.stop();
    }
}
