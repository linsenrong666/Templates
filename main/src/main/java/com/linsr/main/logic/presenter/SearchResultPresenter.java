package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.main.data.remote.IndexRequest;
import com.linsr.main.logic.contacts.SearchResultContact;

/**
 * Description
 *
 * @author Linsr 2018/12/23 下午4:35
 */
public class SearchResultPresenter extends PresenterEx<SearchResultContact.View> implements
        SearchResultContact.Presenter {

    public SearchResultPresenter(SearchResultContact.View IView) {
        super(IView);
    }

    @Override
    public void search(String keyword, int pageIndex, int pageSize, boolean showLoading) {
        IndexRequest.search(keyword, pageIndex, pageSize,
                new NetObserver<BizPojo>(mView, showLoading, true) {
                    @Override
                    public void onSucceed(BizPojo data) {
                        mView.searchSucceed();
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        mView.searchFailed();
                    }
                });
    }

}
