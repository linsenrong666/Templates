package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.exception.ApiException;
import com.linsr.main.data.remote.GoodsRequest;
import com.linsr.main.data.remote.IndexRequest;
import com.linsr.main.logic.contacts.ProductDetailsContact;
import com.linsr.main.model.ProductDetailsPojo;

/**
 * Description
 *
 * @author Linsr 2018/12/31 下午8:00
 */
public class ProductDetailsPresenter extends PresenterEx<ProductDetailsContact.View> implements
        ProductDetailsContact.Presenter {

    public ProductDetailsPresenter(ProductDetailsContact.View IView) {
        super(IView);
    }

    @Override
    public void getGoodsInfo(String goodsId) {
        GoodsRequest.goodsInfo(getLifecycleOwner(), goodsId,
                new NetObserver<ProductDetailsPojo>(mView, true, true) {
                    @Override
                    public void onSucceed(ProductDetailsPojo data) {
                        if (data == null) {
                            onFailed(new ApiException(""));
                            return;
                        }
                        mView.loadGoodsInfo(data.getGoods());
                        mView.loadPictures(data.getPictures());
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        mView.showError("");
                    }
                });
    }
}
