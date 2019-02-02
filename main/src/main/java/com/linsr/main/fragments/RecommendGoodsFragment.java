package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.PageLoadHelper;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.adapters.RecommendGoodsAdapter;
import com.linsr.main.data.remote.IndexRequest;
import com.linsr.main.model.RecommendPojo;
import com.linsr.main.model.bean.IsbestBean;
import com.linsr.main.widgets.SortIndexView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;


/**
 * 为你推荐
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.RECOMMEND_GOODS)
public class RecommendGoodsFragment extends RefreshFragment
        implements MainModule.Activity.ProductDetailsParams {

    private RecommendGoodsAdapter mAdapter;

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initTopLayout(FrameLayout topLayout) {
        topLayout.setVisibility(View.VISIBLE);
        SortIndexView view = new SortIndexView(mContext);
        topLayout.addView(view);
    }

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new RecommendGoodsAdapter(mActivity);
        RecyclerViewHelper.initGridLayout(mActivity, 2, mRecyclerView, mAdapter);

        mAdapter.setOnGoodsClickListener(new RecommendGoodsAdapter.OnGoodsClickListener() {
            @Override
            public void onAdd(int position) {
            }
        });
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<IsbestBean>() {
            @Override
            public void onItemClick(BaseViewHolder<IsbestBean> holder, int position, int itemType,
                                    IsbestBean data) {
                Params params = new Params();
                params.add(GOODS_ID, data.getGoods_id());
                Router.startActivity(MainModule.Activity.PRODUCT_DETAILS);
            }
        });
    }

    @Override
    protected void requestData(RefreshLayout refreshLayout) {
        request(false);
    }

    @Override
    protected void loadData() {
        request(true);
    }

    private void request(boolean showLoading) {
        IndexRequest.recommendGoodsList(this, mPageIndex, mPageSize,
                new NetObserver<RecommendPojo>(this, showLoading, true) {
                    @Override
                    public void onSucceed(RecommendPojo data) {
                        List<IsbestBean> isbest = data.getIsbest();
                        mPageIndex = PageLoadHelper.onSuccess(mPageIndex, mAdapter,
                                isbest, RecommendGoodsFragment.this);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        mPageIndex = PageLoadHelper.onFailure(mPageIndex, RecommendGoodsFragment.this);
                    }
                });
    }
}
