package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.main.data.remote.CartRequest;
import com.linsr.main.logic.contacts.CartContact;
import com.linsr.main.model.CartListPojo;

/**
 * Description
 *
 * @author Linsr 2019/1/12 下午9:56
 */
public class CartPresenter extends PresenterEx<CartContact.View> implements CartContact.Presenter {

    public CartPresenter(CartContact.View IView) {
        super(IView);
    }

    @Override
    public void cartList() {
        CartRequest.cartList(getLifecycleOwner(),
                new NetObserver<CartListPojo>(mView, true, true) {
                    @Override
                    public void onSucceed(CartListPojo data) {
                        if (data != null) {
                            if (data.getRecommended() != null) {
                                mView.recommend4U(data.getRecommended());
                            }
                        }
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }
}
