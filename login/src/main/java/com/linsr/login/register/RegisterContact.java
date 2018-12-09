package com.linsr.login.register;

import com.linsr.common.biz.IPresenter;
import com.linsr.common.biz.IView;
import com.linsr.login.data.model.dto.RegisterDto;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午4:42
 */
public interface RegisterContact {

    interface View extends IView {
        void startCountDown();
    }

    interface Presenter {
        void sendCode(String mobile);

        void register(RegisterDto dto);
    }
}
