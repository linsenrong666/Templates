package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.adapters.OrderAdapter;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Description
 *
 * @author Linsr 2018/8/31 下午4:59
 */
@Route(path = MainModule.Fragment.ORDER_LIST)
public class OrderListFragment extends RefreshFragment {

    private OrderAdapter mAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new OrderAdapter(mActivity);
        RecyclerViewHelper.initDefault(mActivity, recyclerView, mAdapter);
        mAdapter.addData(Mock.getOrderList(1));
    }

    @Override
    protected void onRefreshEx(RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh();
    }

    @Override
    protected void onLoadMoreEx(RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
    }

}
