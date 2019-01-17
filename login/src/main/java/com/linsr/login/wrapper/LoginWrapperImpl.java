package com.linsr.login.wrapper;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.config.AppConfig;
import com.linsr.common.router.Flags;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.LoginModule;

/**
 * Description
 *
 * @author Linsr 2019/1/17 下午9:28
 */
@Route(path = LoginModule.Service.WRAPPER)
public class LoginWrapperImpl implements LoginModule.Service.LoginWrapper {

    @Override
    public void logout() {
        AppConfig.getInstance().logout();
        Router.startActivity(LoginModule.Activity.LOGIN, Flags.clearTop());
    }

    @Override
    public void init(Context context) {

    }
}
