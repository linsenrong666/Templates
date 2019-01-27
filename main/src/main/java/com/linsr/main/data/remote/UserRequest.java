package com.linsr.main.data.remote;

import android.arch.lifecycle.LifecycleOwner;

import com.linsr.common.biz.ApplicationEx;
import com.linsr.common.biz.config.UserInfoKey;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.Api;
import com.linsr.common.net.NetUtils;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.utils.PrefsUtils;
import com.linsr.main.data.IndexApi;
import com.linsr.main.model.CollectPojo;
import com.linsr.main.model.OrderPojo;
import com.linsr.main.model.UserPojo;

/**
 * Description
 *
 * @author Linsr 2018/12/15 下午9:02
 */
public class UserRequest {

    public static void userCenter(LifecycleOwner lifecycleOwner,
                                  NetObserver<UserPojo> observer) {
        IndexApi userApi = Api.getService(IndexApi.class);
        String userId = PrefsUtils.getSharedString(ApplicationEx.getInstance(), UserInfoKey.USER_ID);
        userApi.userCenter(userId).compose(NetUtils.handleResponse(UserPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<UserPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void collectList(LifecycleOwner lifecycleOwner,
                                   int pageIndex, int pageSize,
                                   NetObserver<CollectPojo> observer) {
        IndexApi userApi = Api.getService(IndexApi.class);
        userApi.collectList(pageIndex, pageSize).compose(NetUtils.handleResponse(CollectPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<CollectPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }
}
