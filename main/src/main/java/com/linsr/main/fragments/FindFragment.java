package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.RecyclerViewHepler;
import com.linsr.main.R;
import com.linsr.main.adapters.FindAdapter;
import com.linsr.main.contacts.HomeContact;
import com.linsr.main.model.FindPojo;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.FIND)
public class FindFragment extends FragmentEx {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private FindAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_find;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.find_recycler_view);
        mRefreshLayout = findViewById(R.id.find_refresh_layout);
        mRefreshLayout.setOnRefreshListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
            }
        });
        mAdapter = new FindAdapter(mActivity);
        RecyclerViewHepler.initDefault(mActivity, mRecyclerView, mAdapter);

    }

    @Override
    protected void loadData() {
        super.loadData();
        List<FindPojo> findList = Mock.getFindList(20);
        mAdapter.addData(findList);
    }
}
