package com.linsr.main.logic.contacts;

import com.linsr.common.biz.IView;

/**
 * Description
 *
 * @author Linsr 2019/2/5 下午3:21
 */
public interface BalanceContact {
    interface View extends IView {

    }

    interface Presenter {
        void checkOut();
    }
}
