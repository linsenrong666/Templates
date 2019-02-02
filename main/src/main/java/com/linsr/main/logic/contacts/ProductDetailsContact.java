package com.linsr.main.logic.contacts;

import com.linsr.common.biz.IView;
import com.linsr.main.model.ProductDetailsPojo;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/12/31 下午8:00
 */
public interface ProductDetailsContact {

    interface View extends IView {
        void loadGoodsInfo(ProductDetailsPojo.GoodsBean pojo);

        void loadPictures(List<ProductDetailsPojo.PicturesBean> list);

        void loadSpec(List spec);

        void onAddCartSuccess();
        void onBuySuccess();
    }

    interface Presenter {
        void getGoodsInfo(String goodsId);

        void addCart(String[] spec, int specCount, String goodsId, int number);

        void collect(String goodId);

        void buy(String[] spec, int specCount, String goodId, int number);
    }
}
