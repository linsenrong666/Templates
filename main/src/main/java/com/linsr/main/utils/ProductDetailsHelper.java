package com.linsr.main.utils;

import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;

/**
 * Description
 *
 * @author Linsr 2019/1/12 下午8:47
 */
public class ProductDetailsHelper {

    public static void startActivity(String goodsId) {
        Router.startActivity(MainModule.Activity.PRODUCT_DETAILS,
                createParams(goodsId));
    }

    public static Params createParams(String goodsId) {
        Params params = new Params();
        params.add(MainModule.Activity.ProductDetailsParams.GOODS_ID, goodsId);
        return params;
    }
}
