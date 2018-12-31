package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.CommonModule;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.common.gui.widgets.recyclerview.HeaderAndFooterWrapper;
import com.linsr.main.R;
import com.linsr.main.adapters.HomeAdapter;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.adapters.RecommendGoodsAdapter;
import com.linsr.main.adapters.holder.RecommendHolder;
import com.linsr.main.adapters.holder.ShopWindowHolder;
import com.linsr.main.logic.contacts.HomeContact;
import com.linsr.main.logic.presenter.HomePresenter;
import com.linsr.main.model.RecommendPojo;
import com.linsr.main.model.bean.IsbestBean;
import com.linsr.main.utils.Mock;
import com.linsr.main.widgets.MainSearchTitleLayout;
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
public class HomeFragment extends FragmentEx<HomePresenter> implements
        CommonModule.Activity.FragmentContainerParams, HomeContact.View {

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private HeaderAndFooterWrapper mWrapper;
    private HomeAdapter mAdapter;
    private ImageView mLeftImage;
    private MainSearchTitleLayout mSearchTitleLayout;
    private RecommendAdapter mFootAdapter;

    @Override
    protected HomePresenter bindPresenter() {
        return new HomePresenter(this);
    }

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
        initFooter();
        RecyclerViewHelper.initDefault(mActivity, mRecyclerView, mWrapper);
    }

    private void initFooter() {
        RecyclerView mFootRecyclerView = new RecyclerView(mActivity);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mFootRecyclerView.setLayoutParams(layoutParams);
        mFootAdapter = new RecommendAdapter(mActivity);
        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(mFootAdapter);
        wrapper.addHeaderView(LayoutInflater.from(mContext).inflate(R.layout.main_header_recommend, null));
        RecyclerViewHelper.initGridLayout(mContext, 3, mFootRecyclerView, wrapper);
        mWrapper.addFootView(mFootRecyclerView);
    }

    private void findView() {
        mSearchTitleLayout = findViewById(R.id.home_search_title);
        mLeftImage = findViewById(R.id.layout_search_left_img);
        mLeftImage.setImageResource(R.mipmap.ic_logo_1);
        mRefreshLayout = findViewById(R.id.home_refresh_layout);
        mRecyclerView = findViewById(R.id.home_recycler_view);

        mSearchTitleLayout.setOnEventListener(new MainSearchTitleLayout.OnEventListener() {
            @Override
            public void onSearchClick(String text) {

            }

            @Override
            public void onEditClick() {
                Router.startActivity(MainModule.Activity.SEARCH);
            }

            @Override
            public void onLeftImageClick() {

            }

            @Override
            public void onRightImageClick() {

            }
        });
    }

    private void initAdapter() {
        mAdapter = new HomeAdapter(mActivity);
        mAdapter.setMenuSpanCount(5);
        mAdapter.setOnRecommendHolderListener(new RecommendHolder.OnRecommendHolderListener() {
            @Override
            public void onMoreClick(int position) {
                Params params = new Params(MainModule.Fragment.RECOMMEND_GOODS);
                params.add(TITLE_TEXT, R.string.main_recommend_for_you);
                Router.startActivity(CommonModule.Activity.FRAGMENT_CONTAINER, params);
            }
        });
        mAdapter.setOnShopWindowItemClickListener(new ShopWindowHolder.OnShopWindowItemClickListener() {
            @Override
            public void onItemClick() {
                Router.startActivity(MainModule.Activity.PRODUCT_DETAILS);
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
                requestMain(false);
            }
        });
    }

    @Override
    protected void loadData() {
        requestMain(true);
    }

    private void requestMain(boolean showLoading) {
        mPresenter.mainList(showLoading);
    }

    @Override
    public void mainListSucceed() {
        mRefreshLayout.finishRefresh();
    }

    @Override
    public void mainListFailed() {
        mRefreshLayout.finishRefresh();
    }

}
