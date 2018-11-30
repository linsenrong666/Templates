package com.linsr.login.login;

import com.linsr.common.base.mvp.IView;

/**
 * Description
 *
 * @author Linsr 2018/8/7 下午4:54
 */
public interface LoginContact {

    interface View extends IView {
        void onLoginSucceed();

        void onLoginFailure();
    }

    interface Presenter {
        void login(String userName, String password);
    }

}
