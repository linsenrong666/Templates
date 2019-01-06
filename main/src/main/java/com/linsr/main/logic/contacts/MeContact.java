package com.linsr.main.logic.contacts;

import com.linsr.common.biz.IView;
import com.linsr.main.model.UserPojo;

/**
 * Description
 *
 * @author Linsr 2019/1/6 下午9:00
 */
public interface MeContact {

    interface View extends IView {
        void fillMeTopData(UserPojo pojo);
        void fillRecName(String name);
    }

    interface Presenter {
        void userCenter();
    }
}
