package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.linsr.common.biz.FragmentEx;
import com.linsr.common.net.NetConstants;
import com.linsr.main.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Description
 *
 * @author Linsr 2018/8/31 下午5:10
 */
public abstract class RefreshFragment extends FragmentEx {

    protected RecyclerView mRecyclerView;
    protected SmartRefreshLayout mRefreshLayout;
    private FrameLayout mTopLayout;
    protected int mPageIndex = NetConstants.DEFAULT_PAGE_INDEX;
    protected int mPageSize = NetConstants.DEFAULT_PAGE_SIZE;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_refresh;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mTopLayout = findViewById(R.id.main_refresh_top_layout);
        initTopLayout(mTopLayout);
        mRecyclerView = findViewById(R.id.main_recycler_view);
        initRecyclerView(mRecyclerView);
        mRefreshLayout = findViewById(R.id.main_refresh_layout);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                onRefreshEx(refreshLayout);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                onLoadMoreEx(refreshLayout);
            }
        });
    }

    protected void initTopLayout(FrameLayout topLayout) {
    }

    protected abstract void initRecyclerView(RecyclerView recyclerView);

    protected abstract void requestData(RefreshLayout refreshLayout);

    protected void onRefreshEx(RefreshLayout refreshLayout) {
        mPageIndex = NetConstants.DEFAULT_PAGE_INDEX;
        requestData(refreshLayout);
    }

    protected void onLoadMoreEx(RefreshLayout refreshLayout) {
        requestData(refreshLayout);
    }


}
