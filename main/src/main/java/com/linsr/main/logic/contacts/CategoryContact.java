package com.linsr.main.logic.contacts;

import com.linsr.common.biz.IView;
import com.linsr.main.model.CategoryMenuPojo;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/12/30 下午8:36
 */
public interface CategoryContact {

    interface View extends IView {
        void onCategoryListSucceed(List<CategoryMenuPojo.CatListsBean> list);
    }

    interface Presenter {
        void getCategoryList();
    }
}
