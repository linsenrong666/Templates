package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

/**
 * Description
 *
 * @author Linsr 2018/8/14 上午11:11
 */
@Route(path = MainModule.Fragment.CHILD_CATEGORY)
public class ChildCategoryFragment extends FragmentEx {

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecommendAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_child_category;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.main_recycler_view);
        mAdapter = new RecommendAdapter(mContext);
        mAdapter.addData(Mock.getRecommendList(21));
        RecyclerViewHelper.initGridLayout(mContext, 2, mRecyclerView, mAdapter);

        mRefreshLayout = findViewById(R.id.main_refresh_layout);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                mAdapter.addData(Mock.getRecommendList(5));
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                mAdapter.clear();
                mAdapter.addData(Mock.getRecommendList(10));
            }
        });
    }
}
