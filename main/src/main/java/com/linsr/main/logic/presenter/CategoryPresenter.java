package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.exception.ApiException;
import com.linsr.common.utils.ToastUtils;
import com.linsr.main.data.remote.IndexRequest;
import com.linsr.main.logic.contacts.CategoryContact;
import com.linsr.main.model.CategoryMenuPojo;

/**
 * Description
 *
 * @author Linsr 2018/12/30 下午8:35
 */
public class CategoryPresenter extends PresenterEx<CategoryContact.View>
        implements CategoryContact.Presenter {

    public CategoryPresenter(CategoryContact.View IView) {
        super(IView);
    }


    @Override
    public void getCategoryList() {
        IndexRequest.categoryList(getLifecycleOwner(), new NetObserver<CategoryMenuPojo>(mView) {

            @Override
            public void onSucceed(CategoryMenuPojo data) {
                if (data != null && data.getCat_lists() != null) {
                    mView.onCategoryListSucceed(data.getCat_lists());
                } else {
                    onFailed(new ApiException("数据获取失败"));
                }
            }

            @Override
            public void onFailed(Throwable e) {
                ToastUtils.show(e.getMessage());
            }
        });
    }
}
