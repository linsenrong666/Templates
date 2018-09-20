package com.linsr.main.activities;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.gui.widgets.recyclerview.EmptyWrapper;
import com.linsr.common.gui.widgets.recyclerview.HeaderAndFooterWrapper;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.common.utils.PageLoadHelper;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.AuctionAdapter;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.adapters.RecommendGoodsAdapter;
import com.linsr.main.fragments.RefreshFragment;
import com.linsr.main.model.AuctionPojo;
import com.linsr.main.model.RecommendPojo;
import com.linsr.main.utils.Mock;
import com.linsr.main.widgets.MainSearchTitleLayout;
import com.linsr.main.widgets.MainSearchTitleLayoutManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/21 下午2:47
 */
@Route(path = MainModule.Activity.SEARCH_RESULT)
public class SearchResultActivity extends ActivityEx {

    private AuctionAdapter mAdapter;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mSearchRecyclerView;
    private RecyclerView mRecommendRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_search_result;
    }

    @Override
    protected void initView() {
        mRefreshLayout = findViewById(R.id.main_refresh_layout);
        mSearchRecyclerView = findViewById(R.id.main_recycler_view);
        mSearchRecyclerView.setNestedScrollingEnabled(false);
        mRecommendRecyclerView = findViewById(R.id.search_result_recommend_rv);
        mRecommendRecyclerView.setNestedScrollingEnabled(false);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mAdapter.addData(Mock.getList(10, AuctionPojo.class));
                PageLoadHelper.onCompleted(mRefreshLayout);
            }
        });
        initRecyclerView();
    }

    @Override
    protected void initTopLayout(FrameLayout topLayout) {
        topLayout.setVisibility(View.VISIBLE);
        MainSearchTitleLayout mainSearchTitleLayout = new MainSearchTitleLayout(this);
        MainSearchTitleLayoutManager mainSearchTitleLayoutManager = new MainSearchTitleLayoutManager();
        mainSearchTitleLayoutManager.setUp(this, mainSearchTitleLayout);
        topLayout.addView(mainSearchTitleLayout);
    }

    protected void initRecyclerView() {
        mAdapter = new AuctionAdapter(this);
        //empty
        EmptyWrapper emptyWrapper = new EmptyWrapper(mAdapter);
        emptyWrapper.setEmptyView(R.layout.main_layout_empty_search_result);
        RecyclerViewHelper.initDefault(this,mSearchRecyclerView,emptyWrapper);

        //footer
        RecommendAdapter adapter = new RecommendAdapter(this);
        List<RecommendPojo> goodsList2 = Mock.getRecommendList(9);
        adapter.addData(goodsList2);
        RecyclerViewHelper.initGridLayout(this, 3, mRecommendRecyclerView, adapter);
    }
}
