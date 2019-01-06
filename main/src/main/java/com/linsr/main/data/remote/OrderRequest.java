package com.linsr.main.data.remote;

import android.arch.lifecycle.LifecycleOwner;

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
        IndexApi userApi = Api.getService(IndexApi.class);
        userApi.orderList(status, pageIndex, pageSize).compose(NetUtils.handleResponse(OrderPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<OrderPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }
}
