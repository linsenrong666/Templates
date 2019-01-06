package com.linsr.main.logic.contacts;

import com.linsr.common.biz.IPresenter;
import com.linsr.common.biz.IView;
import com.linsr.main.model.HomePojo;
import com.linsr.main.model.bean.IsbestBean;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午5:23
 */
public interface HomeContact {

    interface View extends IView {
        void mainListSucceed(List<HomePojo.HomeListBean> list);

        void mainListFailed();

        void loadRecommendForYou(List<IsbestBean> list);
    }

    interface Presenter {
        void mainList(boolean show);

    }
}
