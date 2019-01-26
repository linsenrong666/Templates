package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.exception.ApiException;
import com.linsr.main.data.remote.CartRequest;
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
                new NetObserver<ProductDetailsPojo>(mView, false, true) {
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

    @Override
    public void addCart(String[] spec, int specCount, String goodsId, int number) {
        CartRequest.addToCart(getLifecycleOwner(), spec, specCount, goodsId, number,
                new NetObserver<BizPojo>(mView, true, true) {
                    @Override
                    public void onSucceed(BizPojo data) {
                        mView.onAddCartSuccess();
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }
}
