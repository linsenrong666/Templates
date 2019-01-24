package com.linsr.main.fragments;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.gui.widgets.TitleView;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.common.gui.widgets.recyclerview.EmptyWrapper;
import com.linsr.common.gui.widgets.recyclerview.HeaderAndFooterWrapper;
import com.linsr.common.utils.ToastUtils;
import com.linsr.main.R;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.adapters.cart.CartAdapter;
import com.linsr.main.adapters.cart.CartBalanceTO;
import com.linsr.main.adapters.cart.GoodsHolder;
import com.linsr.main.adapters.cart.ItemStatus;
import com.linsr.main.adapters.cart.TreePojo;
import com.linsr.main.logic.contacts.CartContact;
import com.linsr.main.logic.presenter.CartPresenter;
import com.linsr.main.model.CartListPojo;
import com.linsr.main.model.CartShopPojo;
import com.linsr.main.model.bean.IsbestBean;
import com.linsr.main.utils.ProductDetailsHelper;
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
public class CartFragment extends FragmentEx<CartPresenter> implements CartContact.View
        , BalanceBar.OnCartBottomBarListener {

    private RecyclerView mCartRecyclerView;
    private CartAdapter mCartAdapter;
    private RecommendAdapter mRecommendAdapter;
    private BalanceBar mBalanceBar;
    private SmartRefreshLayout mRefreshLayout;
    private boolean mIsBalanceMode = true;
    private TitleView mTitleView;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_cart;
    }

    @Override
    protected CartPresenter bindPresenter() {
        return new CartPresenter(this);
    }

    @Override
    protected void loadData() {
        mPresenter.cartList(true);
    }

    @Override
    protected void initView() {
        initTitle();
        findView();
        initCartAdapter();
        initRefreshLayout();
        initBottomBar();
    }

    private void initTitle() {
        if (mActivity instanceof ActivityEx) {
            mTitleView = ((ActivityEx) mActivity).getTitleView();
        }
    }

    private void setTitleStatus(boolean show) {
        if (mTitleView == null) {
            return;
        }
        if (show) {
            mTitleView.setRightText("管理");
            mTitleView.setOnRightClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIsBalanceMode = !mIsBalanceMode;
                    mBalanceBar.resetBalance();
                    mCartAdapter.toggleAllChecked(false);
                    if (mIsBalanceMode) {
                        mBalanceBar.setBalanceMode();
                    } else {
                        mBalanceBar.setDeleteMode();
                    }
                }
            });
        } else {
            mTitleView.setRightText("");
            mTitleView.setOnRightClickListener(null);
        }

    }

    private void initBottomBar() {
        mBalanceBar.setOnCartBottomBarListener(this);
    }

    private void initRefreshLayout() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPresenter.cartList(false);
            }
        });
    }

    private void findView() {
        mRefreshLayout = findViewById(R.id.cart_refresh_layout);
        mBalanceBar = findViewById(R.id.cart_bottom_layout);
        mCartRecyclerView = findViewById(R.id.cart_recycler_view);
        mCartRecyclerView.setNestedScrollingEnabled(false);
    }

    private void initRecommend() {
        if (mRecommendAdapter != null) {
            return;
        }
        RecyclerView mRecommendRecyclerView = findViewById(R.id.recommend_recycler_view);
        mRecommendRecyclerView.setVisibility(View.VISIBLE);
        mRecommendRecyclerView.setNestedScrollingEnabled(false);
        mRecommendAdapter = new RecommendAdapter(mContext);
        mRecommendAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<IsbestBean>() {
            @Override
            public void onItemClick(BaseViewHolder<IsbestBean> holder, int position, int itemType, IsbestBean data) {
                ProductDetailsHelper.startActivity(data.getGoods_id());
            }
        });
        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(mRecommendAdapter);
        wrapper.addHeaderView(LayoutInflater.from(mContext).inflate(
                R.layout.main_header_recommend, null));
        RecyclerViewHelper.initGridLayout(mContext, 3, mRecommendRecyclerView, wrapper);
    }

    private void initCartAdapter() {
        mCartAdapter = new CartAdapter(mContext);
        ((SimpleItemAnimator) mCartRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        EmptyWrapper mGoodsAdapterWrapper = new EmptyWrapper(mCartAdapter);
        mGoodsAdapterWrapper.setEmptyView(R.layout.main_layout_empty_result);
        RecyclerViewHelper.initDefault(mContext, mCartRecyclerView, mGoodsAdapterWrapper);
        mCartAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        mCartAdapter.setCartListener(new CartAdapter.CartListener() {
            @Override
            public void onDataChangeForBalance() {
                CartBalanceTO to = balance();
                mBalanceBar.setAllChecked(mCartAdapter.isAllChecked(to.getCount()));
            }
        });
        mCartAdapter.setOnGoodsClickListener(new GoodsHolder.OnGoodsClickListener() {
            @Override
            public void onItemClick(CartListPojo.GoodsListBean.ListBean data) {
                ProductDetailsHelper.startActivity(data.getGoods_id());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCartAdapter.unregisterAdapterDataObserver(mAdapterDataObserver);
    }

    private RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            JLog.i("数据变了");
            setTitleStatus(mCartAdapter.getItemCount() != 0);
            if (mCartAdapter.getItemCount() == 0) {
                mBalanceBar.setVisibility(View.GONE);
            } else {
                mBalanceBar.setVisibility(View.VISIBLE);
            }
        }
    };

    @Override
    public void loadCartList(List<TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean>> cartList) {
        mRefreshLayout.finishRefresh();
        mCartAdapter.clear();
        mCartAdapter.addData(cartList);
        mBalanceBar.resetBalance();
    }

    @Override
    public void recommend4U(List<IsbestBean> list) {
        initRecommend();
        mRecommendAdapter.clear();
        mRecommendAdapter.addData(list);
    }

    @Override
    public void onConfirm(int mode) {
        if (mode == BalanceBar.BALANCE_MODE) {
            Router.startActivity(MainModule.Activity.BALANCE);
        } else if (mode == BalanceBar.DELETE_MODE) {

        }
    }

    @Override
    public void onAllChecked(boolean isChecked) {
        mCartAdapter.toggleAllChecked(isChecked);
        balance();
    }

    private CartBalanceTO balance() {
        CartBalanceTO to = mCartAdapter.balance();
        mBalanceBar.setAmount(to.getAmount());
        mBalanceBar.setBalanceNumber(to.getNumber());
        return to;
    }

}