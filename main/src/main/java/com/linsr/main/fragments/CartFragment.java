package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.common.widgets.recyclerview.EmptyWrapper;
import com.linsr.common.widgets.recyclerview.HeaderAndFooterWrapper;
import com.linsr.main.R;
import com.linsr.main.adapters.HomeGoodsAdapter;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.model.HomePojo;
import com.linsr.main.model.RecommendPojo;
import com.linsr.main.utils.Mock;
import com.linsr.main.widgets.CartBottomBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/12 下午4:41
 */
@Route(path = MainModule.Fragment.CART)
public class CartFragment extends FragmentEx {

    private RecyclerView mCartRecyclerView;
    private HomeGoodsAdapter mHomeGoodsAdapter;
    private RecyclerView mRecommendRecyclerView;
    private RecommendAdapter mRecommendAdapter;
    private CartBottomBar mCartBottomBar;

    private SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_cart;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        findView();
        initCartAdapter();
        initRecommend();
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();

                List<HomePojo> goodsList1 = Mock.getGoodsList(1);
                mHomeGoodsAdapter.addData(goodsList1);
                mGoodsAdapterWrapper.notifyDataSetChanged();

                List<RecommendPojo> goodsList3 = Mock.getRecommendList(1);
                mRecommendAdapter.addData(goodsList3);

            }
        });
    }

    private void findView() {
        mRefreshLayout = findViewById(R.id.cart_refresh_layout);
        mCartBottomBar = findViewById(R.id.cart_bottom_layout);
        mCartRecyclerView = findViewById(R.id.cart_recycler_view);
        mCartRecyclerView.setNestedScrollingEnabled(false);
        mRecommendRecyclerView = findViewById(R.id.recommend_recycler_view);
        mRecommendRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initRecommend() {
        mRecommendAdapter = new RecommendAdapter(mContext);
        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(mRecommendAdapter);
        wrapper.addHeaderView(LayoutInflater.from(mContext).inflate(
                R.layout.main_header_recommend, null));

        List<RecommendPojo> goodsList2 = Mock.getRecommendList(1);
        mRecommendAdapter.addData(goodsList2);

        RecyclerViewHelper.initGridLayout(mContext, 3,
                mRecommendRecyclerView, wrapper);
    }

    EmptyWrapper mGoodsAdapterWrapper;

    private void initCartAdapter() {
        mHomeGoodsAdapter = new HomeGoodsAdapter(mContext);
        mGoodsAdapterWrapper = new EmptyWrapper(mHomeGoodsAdapter);
        mGoodsAdapterWrapper.setEmptyView(R.layout.main_empty_cart);
        RecyclerViewHelper.initDefault(mContext, mCartRecyclerView, mGoodsAdapterWrapper);
    }

}