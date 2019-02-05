package com.linsr.main.data.remote;

import android.arch.lifecycle.LifecycleOwner;

import com.linsr.common.biz.config.AppConfig;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.Api;
import com.linsr.common.net.NetUtils;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.main.data.IndexApi;
import com.linsr.main.model.CartListPojo;

/**
 * Description
 *
 * @author Linsr 2019/1/12 下午9:52
 */
public class CartRequest {

    public static void addToCart(LifecycleOwner lifecycleOwner,
                                 String[] spec,
                                 int specCount,
                                 String goodsId,
                                 int number, NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.addToCart(spec, specCount, goodsId, number)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void cartList(LifecycleOwner lifecycleOwner,
                                NetObserver<CartListPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.cartList("").compose(NetUtils.handleResponse(CartListPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<CartListPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void dropCartGoods(LifecycleOwner lifecycleOwner,
                                     String rec_id, String userId,
                                     NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.dropGoods(rec_id, userId).compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }


    public static void modifyCartNumber(LifecycleOwner lifecycleOwner,
                                        String recId, int number,
                                        NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.modifyCartNumber(recId, number).compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void chooseAddress(LifecycleOwner lifecycleOwner,
                                     String addressId,
                                     NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.chooseAddress(addressId)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void settleList(LifecycleOwner lifecycleOwner,
                                  NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.settleList(AppConfig.getInstance().getUserId())
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void settleAccounts(LifecycleOwner lifecycleOwner,
                                      int payment, int shipping, String postscript,
                                      NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.settleAccounts(payment, shipping, postscript)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }


}
