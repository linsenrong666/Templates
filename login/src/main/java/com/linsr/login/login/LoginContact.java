package com.linsr.login.login;

import com.linsr.common.biz.IPresenter;
import com.linsr.common.biz.IView;
import com.linsr.login.data.model.response.LoginPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/7 下午4:54
 */
public interface LoginContact {

    interface View extends IView {
        void onLoginSucceed(LoginPojo pojo);

        void onLoginFailure(String errMsg);
    }

    interface Presenter {
        void login(String userName, String password);
    }

}
