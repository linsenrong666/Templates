package com.linsr.main.logic.contacts;

import com.linsr.common.biz.IView;

/**
 * Description
 *
 * @author Linsr 2018/12/23 下午4:33
 */
public interface SearchResultContact {
    interface View extends IView {
        void searchSucceed();

        void searchFailed();
    }

    interface Presenter {

        void search(String keyword, int pageIndex, int pageSize,boolean showLoading);

    }

}
