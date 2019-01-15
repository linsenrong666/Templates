package com.linsr.main.data.remote;

import android.arch.lifecycle.LifecycleOwner;

import com.linsr.common.model.BizPojo;
import com.linsr.common.net.Api;
import com.linsr.common.net.NetUtils;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.main.data.IndexApi;
import com.linsr.main.model.ProductDetailsPojo;

/**
 * Description
 *
 * @author Linsr 2019/1/15 下午9:59
 */
public class GoodsRequest {

    /**
     * 商品详情
     */
    public static void goodsInfo(LifecycleOwner lifecycleOwner, String goodsId,
                                 NetObserver<ProductDetailsPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.goodsInfo(goodsId)
                .compose(NetUtils.handleResponse(ProductDetailsPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<ProductDetailsPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    /**
     * 修改购买数量
     */
    public static void modifyNumber(LifecycleOwner lifecycleOwner,
                                    String goodsId,
                                    String[] array,
                                    int number,
                                    NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.modifyNumber(goodsId, array, number)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }
}
