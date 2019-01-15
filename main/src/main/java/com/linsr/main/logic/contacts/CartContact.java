package com.linsr.main.logic.contacts;

import com.linsr.common.biz.IView;
import com.linsr.main.model.bean.IsbestBean;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2019/1/12 下午9:56
 */
public interface CartContact {
    interface View extends IView {

        void recommend4U(List<IsbestBean> list);

    }

    interface Presenter {
        void cartList();
    }
}
