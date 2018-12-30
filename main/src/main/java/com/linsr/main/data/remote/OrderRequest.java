package com.linsr.main.data.remote;

import android.arch.lifecycle.LifecycleOwner;

import com.linsr.common.biz.ApplicationEx;
import com.linsr.common.biz.IView;
import com.linsr.common.biz.config.UserInfoKey;
import com.linsr.common.model.ResponsePojo;
import com.linsr.common.net.Api;
import com.linsr.common.net.NetUtils;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.utils.PrefsUtils;
import com.linsr.main.data.UserApi;
import com.linsr.main.model.OrderPojo;

/**
 * Description
 *
 * @author Linsr 2018/12/15 下午9:02
 */
public class OrderRequest {

    public static void orderList(LifecycleOwner lifecycleOwner,
                                 NetObserver<OrderPojo> observer) {
        UserApi userApi = Api.getService(UserApi.class);
        String userId = PrefsUtils.getSharedString(ApplicationEx.getInstance(), UserInfoKey.USER_ID);
        userApi.orderList(userId).compose(NetUtils.handleResponse(OrderPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<OrderPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }
}
