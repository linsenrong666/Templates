package com.linsr.main.fragments;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.exception.ApiException;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.PageLoadHelper;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.data.remote.IndexRequest;
import com.linsr.main.model.ChildCategoryPojo;
import com.linsr.main.model.RecommendPojo;
import com.linsr.main.model.bean.IsbestBean;
import com.linsr.main.utils.Mock;
import com.linsr.main.utils.ProductDetailsHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.squareup.haha.perflib.Main;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/14 上午11:11
 */
@Route(path = MainModule.Fragment.CHILD_CATEGORY)
public class ChildCategoryFragment extends RefreshFragment implements
        MainModule.Fragment.ChildCategoryParams, MainModule.Activity.ProductDetailsParams {

    private RecommendAdapter mAdapter;

    private String mFid;
    private String mSid;

    @Override
    protected void initArguments(Bundle arguments) {
        super.initArguments(arguments);
        if (arguments != null) {
            mFid = arguments.getString(FID);
            mSid = arguments.getString(SID);
        }
    }

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new RecommendAdapter(mContext);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<IsbestBean>() {

            @Override
            public void onItemClick(BaseViewHolder<IsbestBean> holder, int position,
                                    int itemType, IsbestBean data) {
                Router.startActivity(MainModule.Activity.PRODUCT_DETAILS,
                        ProductDetailsHelper.createParams(data.getGoods_id()));
            }
        });
        RecyclerViewHelper.initGridLayout(mContext, 2, mRecyclerView, mAdapter);
    }

    @Override
    protected void loadData() {
        request(true);
    }

    @Override
    protected void requestData(final RefreshLayout refreshLayout) {
        request(false);
    }

    private void request(boolean showLoading) {
        IndexRequest.childCategoryList(this, mFid, mSid,
                new NetObserver<ChildCategoryPojo>(this, showLoading, true) {
                    @Override
                    public void onSucceed(ChildCategoryPojo data) {
                        if (data != null && data.getCat_list_goods() != null) {
                            List<IsbestBean> cat_list_goods = data.getCat_list_goods();
                            mAdapter.addData(cat_list_goods);
                        } else {
                            onFailed(new ApiException(""));
                        }
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        PageLoadHelper.onCompleted(mRefreshLayout);
                    }
                });
    }

}
