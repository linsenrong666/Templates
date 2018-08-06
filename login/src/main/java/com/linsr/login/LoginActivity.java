package com.linsr.login;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.LoginModule;

/**
 *
 * @author Linsr
 */
@Route(path = LoginModule.Activity.LOGIN)
public class LoginActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.login_activity_login;
    }

    @Override
    protected void initView() {

    }

}
