package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.common.gui.widgets.recyclerview.EmptyWrapper;
import com.linsr.common.gui.widgets.recyclerview.HeaderAndFooterWrapper;
import com.linsr.main.R;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.adapters.cart.CartAdapter;
import com.linsr.main.adapters.cart.TreePojo;
import com.linsr.main.model.CartGoodsPojo;
import com.linsr.main.model.CartShopPojo;
import com.linsr.main.model.RecommendPojo;
import com.linsr.main.utils.Mock;
import com.linsr.main.widgets.BalanceBar;
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
    private CartAdapter mCartAdapter;
    private RecyclerView mRecommendRecyclerView;
    private RecommendAdapter mRecommendAdapter;
    private BalanceBar mBalanceBar;

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
        initBottomBar();
    }

    private void initBottomBar() {
        mBalanceBar.setOnCartBottomBarListener(new BalanceBar.OnCartBottomBarListener() {
            @Override
            public void onBalanceClick() {
                Router.startActivity(MainModule.Activity.BALANCE);
            }

            @Override
            public void onAllChecked(boolean isChecked) {
                mCartAdapter.allToggleChecked(isChecked);
            }
        });
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                List<TreePojo<CartShopPojo, CartGoodsPojo>> cartList = Mock.getCartList(2);
                mCartAdapter.addData(cartList);
            }
        });
    }

    private void findView() {
        mRefreshLayout = findViewById(R.id.cart_refresh_layout);
        mBalanceBar = findViewById(R.id.cart_bottom_layout);
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

//        List<RecommendPojo> goodsList2 = Mock.getRecommendList(9);
//        mRecommendAdapter.addData(goodsList2);

        RecyclerViewHelper.initGridLayout(mContext, 3,
                mRecommendRecyclerView, wrapper);
    }

    EmptyWrapper mGoodsAdapterWrapper;

    private void initCartAdapter() {
        mCartAdapter = new CartAdapter(mContext);
        ((SimpleItemAnimator) mCartRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        mGoodsAdapterWrapper = new EmptyWrapper(mCartAdapter);
        mGoodsAdapterWrapper.setEmptyView(R.layout.main_layout_empty_result);
        RecyclerViewHelper.initDefault(mContext, mCartRecyclerView, mGoodsAdapterWrapper);
        mCartAdapter.registerAdapterDataObserver(mAdapterDataObserver);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCartAdapter.unregisterAdapterDataObserver(mAdapterDataObserver);
    }

    private RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            if (mCartAdapter.getItemCount() == 0) {
                mBalanceBar.setVisibility(View.GONE);
            } else {
                mBalanceBar.setVisibility(View.VISIBLE);
            }
        }
    };

}