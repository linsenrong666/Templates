package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.PageLoadHelper;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.adapters.OrderAdapter;
import com.linsr.main.data.remote.OrderRequest;
import com.linsr.main.model.OrderPojo;
import com.linsr.main.model.bean.OrderListBean;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

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
        mAdapter.setOnOrderItemClickListener(new OrderAdapter.OnOrderItemClickListener() {
            @Override
            public void onPayBtnClick(int position, OrderListBean data) {
                Router.startActivity(MainModule.Activity.COMMENT);
            }

            @Override
            public void onFirstBtnClick(OrderListBean data) {

            }

            @Override
            public void onSecondBtnClick(OrderListBean data) {
                Router.startActivity(MainModule.Activity.AFTER_SALES);
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
        OrderRequest.orderList(this,
                new NetObserver<OrderPojo>(this, showLoading, true) {
                    @Override
                    public void onSucceed(OrderPojo data) {
                        List<OrderListBean> orderList = data.getOrder_list();
                        mPageIndex = PageLoadHelper.onSuccess(mPageIndex, mAdapter, orderList,
                                OrderListFragment.this);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        mPageIndex = PageLoadHelper.onFailure(mPageIndex, OrderListFragment.this);
                    }

                    @Override
                    public void onComplete() {
                        PageLoadHelper.onCompleted(mRefreshLayout);
                    }
                });
    }
}
