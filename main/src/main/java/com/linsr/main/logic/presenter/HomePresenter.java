package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.main.data.IndexApi;
import com.linsr.main.data.remote.IndexRequest;
import com.linsr.main.logic.contacts.HomeContact;
import com.linsr.main.model.RecommendPojo;
import com.linsr.main.model.bean.IsbestBean;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/12/23 下午3:25
 */
public class HomePresenter extends PresenterEx<HomeContact.View> implements HomeContact.Presenter {

    public HomePresenter(HomeContact.View IView) {
        super(IView);
    }

    @Override
    public void mainList(boolean showLoading) {
        IndexRequest.mainList(getLifecycleOwner(),
                new NetObserver<BizPojo>(mView, showLoading, true) {
                    @Override
                    public void onSucceed(BizPojo data) {
                        mView.mainListSucceed();
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        mView.mainListFailed();
                    }
                });
    }

    @Override
    public void recommendGoodsList() {
        IndexRequest.recommendGoodsList(getLifecycleOwner(),
                new NetObserver<RecommendPojo>(mView, false, false) {

                    @Override
                    public void onSucceed(RecommendPojo data) {
                        List<IsbestBean> isbest = data.getIsbest();
                        mView.goodsListSucceed(isbest);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        mView.goodsListFailed();
                    }
                });
    }
}
