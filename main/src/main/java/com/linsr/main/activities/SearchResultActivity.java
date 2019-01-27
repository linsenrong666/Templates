package com.linsr.main.activities;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.gui.widgets.recyclerview.EmptyWrapper;
import com.linsr.common.net.NetConstants;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.PageLoadHelper;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.AuctionAdapter;
import com.linsr.main.logic.contacts.SearchResultContact;
import com.linsr.main.logic.presenter.SearchResultPresenter;
import com.linsr.main.model.SearchResultPojo;
import com.linsr.main.widgets.MainSearchTitleLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Description
 *
 * @author Linsr 2018/8/21 下午2:47
 */
@Route(path = MainModule.Activity.SEARCH_RESULT)
public class SearchResultActivity extends ActivityEx<SearchResultPresenter> implements
        SearchResultContact.View, MainModule.Activity.SearchResultParams {

    private AuctionAdapter mAdapter;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mSearchRecyclerView;
    private RecyclerView mRecommendRecyclerView;

    private String mKeyword;
    private int mPageIndex;
    private int mPageSize = NetConstants.DEFAULT_PAGE_SIZE;

    @Override
    protected void init(Intent intent) {
        super.init(intent);
        if (intent != null) {
            mKeyword = intent.getStringExtra(KEYWORD);
        }
    }

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
                mPageIndex = NetConstants.DEFAULT_PAGE_INDEX;
                requestData(false);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                requestData(false);
            }
        });
        initRecyclerView();
        requestData(true);
    }

    @Override
    protected SearchResultPresenter bindPresenter() {
        return new SearchResultPresenter(this);
    }

    @Override
    protected void initTopLayout(FrameLayout topLayout) {
        topLayout.setVisibility(View.VISIBLE);
        MainSearchTitleLayout mainSearchTitleLayout = new MainSearchTitleLayout(this);
        mainSearchTitleLayout.setEditTextContent(mKeyword);
        mainSearchTitleLayout.setEnableEdit(true);
        mainSearchTitleLayout.setOnEventListener(new MainSearchTitleLayout.OnEventListener() {
            @Override
            public void onSearchClick(String text) {
                mPageIndex = NetConstants.DEFAULT_PAGE_INDEX;
                mKeyword = text;
                requestData(true);
            }

            @Override
            public void onEditClick() {

            }

            @Override
            public void onLeftImageClick() {
                back();
            }

            @Override
            public void onRightImageClick() {

            }
        });
        topLayout.addView(mainSearchTitleLayout);
    }

    protected void initRecyclerView() {
        mAdapter = new AuctionAdapter(this);
        //empty
        EmptyWrapper emptyWrapper = new EmptyWrapper(mAdapter);
        emptyWrapper.setEmptyView(R.layout.main_layout_empty_search_result);
        RecyclerViewHelper.initDefault(this, mSearchRecyclerView, emptyWrapper);
    }

    private void requestData(boolean showLoading) {
        mPresenter.search(mKeyword, mPageIndex, mPageSize, showLoading);
    }

    @Override
    public void searchSucceed(SearchResultPojo pojo) {

    }

    @Override
    public void searchFailed() {
        mPageIndex = PageLoadHelper.onFailure(mPageIndex, this);
    }

}
