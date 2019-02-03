package com.linsr.main.data.remote;

import android.arch.lifecycle.LifecycleOwner;

import com.linsr.common.biz.ApplicationEx;
import com.linsr.common.biz.config.AppConfig;
import com.linsr.common.biz.config.UserInfoKey;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.Api;
import com.linsr.common.net.NetUtils;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.utils.PrefsUtils;
import com.linsr.main.data.IndexApi;
import com.linsr.main.model.AddressPojo;
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
        String userId = AppConfig.getInstance().getUserId();
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

    public static void collect(LifecycleOwner lifecycleOwner,
                               String goodsId,
                               NetObserver<BizPojo> observer) {
        IndexApi userApi = Api.getService(IndexApi.class);
        userApi.collect(goodsId).compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void logout(LifecycleOwner lifecycleOwner,
                              NetObserver<BizPojo> observer) {
        IndexApi userApi = Api.getService(IndexApi.class);
        userApi.logout("").compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void editAddress(LifecycleOwner lifecycleOwner,
                                   AddressPojo pojo,
                                   NetObserver<BizPojo> observer) {
        IndexApi userApi = Api.getService(IndexApi.class);
        userApi.editAddress(pojo.getAddress_id(), pojo.getConsignee(), pojo.getMobile(),
                pojo.getAddress(), pojo.getZipcode(), pojo.getIs_default(), pojo.getProvince_str(),
                pojo.getCity_str(), pojo.getDistrict_str(), pojo.getUser_id())
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

}
