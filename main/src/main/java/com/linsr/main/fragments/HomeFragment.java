package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.common.gui.widgets.recyclerview.HeaderAndFooterWrapper;
import com.linsr.main.R;
import com.linsr.main.adapters.HomeAdapter;
import com.linsr.main.adapters.holder.RecommendHolder;
import com.linsr.main.model.HomePojo;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.HOME)
public class HomeFragment extends FragmentEx {

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private HeaderAndFooterWrapper mWrapper;
    private HomeAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        findView();
        initAdapter();
        initHeader();
        initRefresh();
        RecyclerViewHelper.initDefault(mActivity, mRecyclerView, mWrapper);
    }


    private void findView() {
        mRefreshLayout = findViewById(R.id.home_refresh_layout);
        mRecyclerView = findViewById(R.id.home_recycler_view);
    }

    private void initAdapter() {
        mAdapter = new HomeAdapter(mActivity);
        List<HomePojo> findList = Mock.getHomeList(100);
        mAdapter.addData(findList);
        mAdapter.setOnRecommendHolderListener(new RecommendHolder.OnRecommendHolderListener() {
            @Override
            public void onMoreClick(int position) {
                JLog.i("position:" + position);
                Router.startActivity(MainModule.Activity.RECOMMEND_GOODS);
            }
        });
    }

    private void initHeader() {
        mWrapper = new HeaderAndFooterWrapper(mAdapter);
        View headerView = LayoutInflater.from(mActivity).inflate(R.layout.main_header_home_shop_info,
                mContentLayout, false);
        mWrapper.addHeaderView(headerView);
    }

    private void initRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected void loadData() {

    }

}
