package com.linsr.main.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.gui.widgets.recyclerview.EmptyWrapper;
import com.linsr.common.net.NetConstants;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.PageLoadHelper;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.AuctionAdapter;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.adapters.RecommendGoodsAdapter;
import com.linsr.main.logic.contacts.SearchResultContact;
import com.linsr.main.logic.presenter.SearchResultPresenter;
import com.linsr.main.model.SearchResultPojo;
import com.linsr.main.model.bean.IsbestBean;
import com.linsr.main.utils.ProductDetailsHelper;
import com.linsr.main.widgets.MainSearchTitleLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/21 下午2:47
 */
@Route(path = MainModule.Activity.SEARCH_RESULT)
public class SearchResultActivity extends ActivityEx<SearchResultPresenter> implements
        SearchResultContact.View, MainModule.Activity.SearchResultParams {

    private RecommendAdapter mAdapter;
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mSearchRecyclerView;
    private RecyclerView mRecommendRecyclerView;

    private String mKeyword;
    private int mPageIndex;
    private int mPageSize = NetConstants.DEFAULT_PAGE_SIZE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        super.onCreate(savedInstanceState);
    }

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
        TextView tv = findViewById(R.id.search_result_recommend_title).findViewById(R.id.layout_home_item_title_tv);
        tv.setText(R.string.main_recommend_for_you);

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
        mAdapter = new RecommendAdapter(this);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<IsbestBean>() {
            @Override
            public void onItemClick(BaseViewHolder<IsbestBean> holder, int position, int itemType, IsbestBean data) {
                ProductDetailsHelper.startActivity(data.getGoods_id());
            }
        });
        //empty
        EmptyWrapper emptyWrapper = new EmptyWrapper(mAdapter);
        emptyWrapper.setEmptyView(R.layout.main_layout_empty_search_result);
        RecyclerViewHelper.initGridLayout(this, 2, mSearchRecyclerView, emptyWrapper);
    }

    private void requestData(boolean showLoading) {
        mPresenter.search(mKeyword, mPageIndex, mPageSize, showLoading);
    }

    @Override
    public void searchSucceed(List<IsbestBean> list) {
        mPageIndex = PageLoadHelper.onSuccess(mPageIndex, mAdapter, list, this);
    }

    @Override
    public void searchFailed() {
        mPageIndex = PageLoadHelper.onFailure(mPageIndex, this);
    }

    @Override
    public void searchCompleted() {
        PageLoadHelper.onCompleted(mRefreshLayout);
    }

}
