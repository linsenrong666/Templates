package com.linsr.main.activities;

import android.support.v7.widget.RecyclerView;

import com.linsr.common.biz.ActivityEx;
import com.linsr.common.net.NetConstants;
import com.linsr.common.utils.PageLoadHelper;
import com.linsr.main.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Description
 *
 * @author Linsr 2018/9/2 下午3:57
 */
public abstract class RefreshActivity extends ActivityEx {

    protected SmartRefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected int mPageIndex = NetConstants.DEFAULT_PAGE_INDEX;
    protected int mPageSize = NetConstants.DEFAULT_PAGE_SIZE;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_refresh;
    }

    @Override
    protected void initView() {
        mRefreshLayout = findViewById(R.id.main_refresh_layout);
        mRecyclerView = findViewById(R.id.main_recycler_view);
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
        initRecyclerView(mRecyclerView);
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
