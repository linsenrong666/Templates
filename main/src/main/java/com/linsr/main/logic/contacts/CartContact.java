package com.linsr.main.logic.contacts;

import com.linsr.common.biz.IView;
import com.linsr.main.adapters.cart.TreePojo;
import com.linsr.main.model.CartListPojo;
import com.linsr.main.model.CartShopPojo;
import com.linsr.main.model.bean.IsbestBean;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2019/1/12 下午9:56
 */
public interface CartContact {
    interface View extends IView {

        void loadCartList( List<TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean>> cartList);

        void recommend4U(List<IsbestBean> list);

    }

    interface Presenter {
        void cartList(boolean showLoading);

        void dropGoods();

    }
}
