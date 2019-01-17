package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.exception.ApiException;
import com.linsr.main.app.Constants;
import com.linsr.main.data.IndexApi;
import com.linsr.main.data.remote.IndexRequest;
import com.linsr.main.logic.contacts.HomeContact;
import com.linsr.main.model.HomePojo;
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
                new NetObserver<HomePojo>(mView, showLoading, true) {
                    @Override
                    public void onSucceed(HomePojo data) {
                        if (data != null && data.getHome_list() != null) {
                            List<HomePojo.HomeListBean> home_list = data.getHome_list();
                            mView.mainListSucceed(home_list);

                            for (HomePojo.HomeListBean bean : home_list) {
                                if (bean.getFloorType() == Constants.FloorType.RECOMMEND_FOR_YOU) {
                                    List<IsbestBean> recData = bean.getRecData();
                                    mView.loadRecommendForYou(recData);
                                }
                                if (bean.getFloorType() == Constants.FloorType.STORE_INFO) {
                                    if (bean.getStoreInfoData() != null) {
                                        mView.loadShopInfo(bean.getStoreInfoData());
                                    }
                                }
                            }

                        } else {
                            onFailed(new ApiException(""));
                        }

                    }

                    @Override
                    public void onFailed(Throwable e) {
                        mView.mainListFailed();
                    }
                });
    }
}
