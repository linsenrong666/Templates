package com.linsr.main.logic.contacts;

import com.linsr.common.biz.IView;
import com.linsr.main.model.SearchResultPojo;
import com.linsr.main.model.bean.IsbestBean;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/12/23 下午4:33
 */
public interface SearchResultContact {
    interface View extends IView {
        void searchSucceed(List<IsbestBean> list);

        void searchFailed();

        void searchCompleted();
    }

    interface Presenter {

        void search(String keyword, int pageIndex, int pageSize,boolean showLoading);

    }

}
