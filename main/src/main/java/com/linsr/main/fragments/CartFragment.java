package com.linsr.main.fragments;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

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
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;
import com.linsr.main.R;
import com.linsr.main.activities.MainActivity;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.adapters.cart.CartAdapter;
import com.linsr.main.adapters.cart.CartBalanceTO;
import com.linsr.main.adapters.cart.GoodsHolder;
import com.linsr.main.adapters.cart.TreePojo;
import com.linsr.main.app.Constants;
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

    }

    @Override
    protected void initView() {
        initTitle();
        findView();
        initCartAdapter();
        initRefreshLayout();
        initBottomBar();
        register();

        mPresenter.cartList(true);
    }

    private void register() {
        registerOnContentUpdateListener(new AbstractOnContentUpdateListener() {
            @Override
            public void onContentUpdated(List<Object[]> values) {
                mPresenter.cartList(false);
            }

            @Override
            public boolean isActive() {
                return true;
            }

            @Override
            public String getKey() {
                return Constants.Event.UPDATE_CART_LIST;
            }
        });
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
            if (mIsBalanceMode) {
                mTitleView.setRightText("管理");
                mBalanceBar.setBalanceMode();
            } else {
                mTitleView.setRightText(getString(R.string.cancel));
                mBalanceBar.setDeleteMode();
            }
            mTitleView.setOnRightClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toggleCartMode();
                }
            });
        } else {
            mTitleView.setRightText("");
            mTitleView.setOnRightClickListener(null);
        }
    }

    private void toggleCartMode() {
        mIsBalanceMode = !mIsBalanceMode;
        mBalanceBar.resetBalance();
        mCartAdapter.toggleAllChecked(false);
        mRefreshLayout.setEnableRefresh(mIsBalanceMode);
        mCartAdapter.setHideCartCountView(!mIsBalanceMode);
        if (mIsBalanceMode) {
            mTitleView.setRightText("管理");
            mBalanceBar.setBalanceMode();
        } else {
            mTitleView.setRightText(getString(R.string.cancel));
            mBalanceBar.setDeleteMode();
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

    @Override
    protected void onVisible() {
        super.onVisible();
        if (mCartAdapter == null || mCartAdapter.getItemCount() == 0) {
            setTitleStatus(false);
        } else {
            setTitleStatus(true);
        }
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_layout_home_item_title,
                mContentLayout, false);
        TextView textView = view.findViewById(R.id.layout_home_item_title_tv);
        textView.setText(R.string.main_recommend_for_you);
        wrapper.addHeaderView(view);

        RecyclerViewHelper.initGridLayout(mContext, 3, mRecommendRecyclerView, wrapper);
    }

    private void initCartAdapter() {
        mCartAdapter = new CartAdapter(mContext);
        ((SimpleItemAnimator) mCartRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        EmptyWrapper mGoodsAdapterWrapper = new EmptyWrapper(mCartAdapter);
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_layout_empty_result,
                mContentLayout, false);
        TextView tv = view.findViewById(R.id.result_btn);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity instanceof MainActivity) {
                    ((MainActivity) mActivity).toHomePage();
                }
            }
        });
        mGoodsAdapterWrapper.setEmptyView(view);
        RecyclerViewHelper.initDefault(mContext, mCartRecyclerView, mGoodsAdapterWrapper);
        mCartAdapter.registerAdapterDataObserver(mAdapterDataObserver);
        mCartAdapter.setCartListener(new CartAdapter.CartListener() {
            @Override
            public void onDataChangeForBalance() {
                CartBalanceTO to = balance();
                mBalanceBar.setAllChecked(mCartAdapter.isAllChecked(to.getCount()));
            }

            @Override
            public void onNumberChanged(String recId, int count) {
                mPresenter.modifyGoodsCount(recId, count);
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
    public void dropGoodsSucceed() {
        ToastUtils.show("移除商品成功");
        toggleCartMode();
        mPresenter.cartList(true);
    }

    @Override
    public void modifyCartBadgeCount(int count) {
        mContentsManager.notifyContentUpdateSuccess(Constants.Event.UPDATE_CART_BADGE, count);
    }

    @Override
    public void modifyCartGoodsCount() {
        mPresenter.cartList(false);
    }

    @Override
    public void onConfirm(int mode) {
        List<CartListPojo.GoodsListBean.ListBean> deleteList = mCartAdapter.getCheckedList();
        if (mode == BalanceBar.BALANCE_MODE) {
            if (deleteList == null || deleteList.size() == 0) {
                ToastUtils.show("请选择需结算的商品");
                return;
            }
            Router.startActivity(MainModule.Activity.BALANCE);
        } else if (mode == BalanceBar.DELETE_MODE) {

            if (deleteList == null || deleteList.size() == 0) {
                ToastUtils.show("请选择需移除的商品");
                return;
            }
            if (deleteList.size() > 1) {
                ToastUtils.show("当前一次仅支持移除一种商品");
                return;
            }
            mPresenter.dropGoods(deleteList.get(0).getRec_id());
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