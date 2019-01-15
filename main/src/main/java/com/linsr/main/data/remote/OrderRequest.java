package com.linsr.main.data.remote;

import android.arch.lifecycle.LifecycleOwner;

import com.linsr.common.model.BizPojo;
import com.linsr.common.net.Api;
import com.linsr.common.net.NetUtils;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.main.data.IndexApi;
import com.linsr.main.model.OrderPojo;

/**
 * Description
 *
 * @author Linsr 2018/12/15 下午9:02
 */
public class OrderRequest {

    public static void orderList(LifecycleOwner lifecycleOwner,
                                 int status, int pageIndex, int pageSize,
                                 NetObserver<OrderPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.orderList(status, pageIndex, pageSize)
                .compose(NetUtils.handleResponse(OrderPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<OrderPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }


    public static void queryExpress(LifecycleOwner lifecycleOwner,
                                    String no,
                                    NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.queryExpress(no)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void confirmReceived(LifecycleOwner lifecycleOwner,
                                       String orderId,
                                       NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.confirmReceived(orderId)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void refund(LifecycleOwner lifecycleOwner,
                              String orderId,
                              String reason, String node,
                              NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.refund(orderId, reason, node)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void refund(LifecycleOwner lifecycleOwner,
                              String orderId,
                              NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.cancelRefund(orderId)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void orderDetails(LifecycleOwner lifecycleOwner,
                                    String orderId,
                                    NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.orderDetails(orderId)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }


}
