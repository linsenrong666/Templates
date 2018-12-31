package com.linsr.main.logic.contacts;

import com.linsr.common.biz.IPresenter;
import com.linsr.common.biz.IView;
import com.linsr.main.model.bean.IsbestBean;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午5:23
 */
public interface HomeContact {

    interface View extends IView {
        void mainListSucceed();

        void mainListFailed();
    }

    interface Presenter {
        void mainList(boolean show);

    }
}
