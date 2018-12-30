package com.linsr.main.data.remote;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import com.linsr.common.biz.ApplicationEx;
import com.linsr.common.biz.config.UserInfoKey;
import com.linsr.common.model.BizPojo;
import com.linsr.common.model.ResponsePojo;
import com.linsr.common.net.Api;
import com.linsr.common.net.NetUtils;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.utils.NumberUtils;
import com.linsr.common.utils.PrefsUtils;
import com.linsr.main.data.IndexApi;
import com.linsr.main.model.CategoryMenuPojo;
import com.linsr.main.model.ChildCategoryPojo;
import com.linsr.main.model.RecommendPojo;

/**
 * Description
 *
 * @author Linsr 2018/12/23 下午3:21
 */
public class IndexRequest {

    public static void mainList(LifecycleOwner lifecycleOwner, NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        String userId = PrefsUtils.getSharedString(ApplicationEx.getInstance(), UserInfoKey.USER_ID);
        api.mainList(userId)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    public static void search(LifecycleOwner lifecycleOwner, String keyword,
                              int pageIndex, int pageSize, NetObserver<BizPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.search(keyword, pageIndex, pageSize)
                .compose(NetUtils.handleResponse(BizPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<BizPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);

    }


    /**
     * 获取推荐商品
     */
    public static void recommendGoodsList(LifecycleOwner lifecycleOwner,
                                          NetObserver<RecommendPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.recommendGoodsList(NumberUtils.getRandomNumberStr())
                .compose(NetUtils.handleResponse(RecommendPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<RecommendPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    /**
     * 分类列表
     */
    public static void categoryList(LifecycleOwner lifecycleOwner,
                                    NetObserver<CategoryMenuPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.categoryList()
                .compose(NetUtils.handleResponse(CategoryMenuPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<CategoryMenuPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

    /**
     * 子分类页面
     */
    public static void childCategoryList(LifecycleOwner lifecycleOwner,
                                         String fid,
                                         String sid,
                                         NetObserver<ChildCategoryPojo> observer) {
        IndexApi api = Api.getService(IndexApi.class);
        api.childCategoryList(fid, sid)
                .compose(NetUtils.handleResponse(ChildCategoryPojo.class))
                .retryWhen(NetUtils.retry())
                .as(NetUtils.<ChildCategoryPojo>bindLifecycle(lifecycleOwner))
                .subscribe(observer);
    }

}
