package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.main.adapters.cart.TreePojo;
import com.linsr.main.data.remote.CartRequest;
import com.linsr.main.logic.contacts.CartContact;
import com.linsr.main.model.CartListPojo;
import com.linsr.main.model.CartShopPojo;

import java.util.ArrayList;
import java.util.List;

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
    public void cartList(boolean showLoading) {
        CartRequest.cartList(getLifecycleOwner(),
                new NetObserver<CartListPojo>(mView, showLoading, true) {
                    @Override
                    public void onSucceed(CartListPojo data) {
                        if (data != null) {
                            if (data.getRecommended() != null) {
                                mView.recommend4U(data.getRecommended());
                            }
                            List<TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean>> treePojos = convertCartListPojo(data);
                            if (treePojos != null) {
                                mView.loadCartList(treePojos);
                            }
                        }
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }

    @Override
    public void dropGoods() {

    }

    private List<TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean>> convertCartListPojo
            (CartListPojo data) {
        if (data == null) {
            return null;
        }
        List<CartListPojo.GoodsListBean> goods_list = data.getGoods_list();
        if (goods_list == null) {
            return null;
        }
        List<TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean>> result = new ArrayList<>();
        for (CartListPojo.GoodsListBean bean : goods_list) {
            TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean> treePojo = new TreePojo<>();
            CartShopPojo cartShopPojo = new CartShopPojo(bean.getSuppliers_name());
            treePojo.setParentPojo(cartShopPojo);
            treePojo.setChildPojo(bean.getList());
            result.add(treePojo);
        }
        return result;
    }

}
