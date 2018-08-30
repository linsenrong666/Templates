package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.MallAdapter;
import com.linsr.main.model.FindPojo;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * 购物中心
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.MALL)
public class MallFragment extends FragmentEx {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private MallAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_mall;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.mall_recycler_view);
        mRefreshLayout = findViewById(R.id.mall_refresh_layout);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                mAdapter.clear();
                List<FindPojo> findList = Mock.getFindList(10);
                mAdapter.addData(findList);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                List<FindPojo> findList = Mock.getFindList(5);
                mAdapter.addData(findList);
                refreshLayout.finishLoadMore();
            }
        });
        mAdapter = new MallAdapter(mActivity);
        RecyclerViewHelper.initDefault(mActivity, mRecyclerView, mAdapter);

    }

    @Override
    protected void loadData() {
        super.loadData();
        List<FindPojo> findList = Mock.getFindList(5);
        mAdapter.addData(findList);
    }
}
