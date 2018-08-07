package com.linsr.login.register;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.LoginModule;
import com.linsr.login.R;

/**
 * Description
 *
 * @author Linsr 2018/8/7 上午11:15
 */
@Route(path = LoginModule.Activity.REGISTER)
public class RegisterActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_register;
    }

    @Override
    protected void initView() {

    }

}