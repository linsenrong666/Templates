package com.linsr.login.password;

import com.linsr.common.biz.IView;

/**
 * Description
 *
 * @author Linsr 2018/12/15 下午6:33
 */
public interface FindPasswordContact {

    interface View extends IView {
        void startCountDown();

        void stopCountDown();

        void resetSucceed();

    }

    interface Presenter {
        void sendCode(String mobile);

        void resetPassword(String mobile, String code, String password);
    }

}
