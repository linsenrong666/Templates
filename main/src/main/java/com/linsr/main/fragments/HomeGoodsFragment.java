package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.biz.EventKey;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.HomeGoodsAdapter;
import com.linsr.main.model.HomePojo;
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
@Route(path = MainModule.Fragment.HOME_GOODS)
public class HomeGoodsFragment extends FragmentEx {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private HomeGoodsAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_home_goods;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mRefreshLayout = findViewById(R.id.main_refresh_layout);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                List<HomePojo> goodsList = Mock.getGoodsList(20);
                mAdapter.addData(goodsList);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mAdapter.clear();
                List<HomePojo> goodsList = Mock.getGoodsList(20);
                mAdapter.addData(goodsList);
            }
        });

        mRecyclerView = findViewById(R.id.main_recycler_view);
        mAdapter = new HomeGoodsAdapter(mActivity);
        RecyclerViewHelper.initGridLayout(mActivity, 2, mRecyclerView, mAdapter);


        mAdapter.setOnGoodsClickListener(new HomeGoodsAdapter.OnGoodsClickListener() {
            @Override
            public void onAdd(int position) {
                JLog.e(TAG, "on add position :" + position);
                mContentsManager.notifyContentUpdateSuccess(EventKey.ADD_GOODS_CART);
            }
        });
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<HomePojo>() {
            @Override
            public void onItemClick(BaseViewHolder<HomePojo> holder, int position, int itemType, HomePojo data) {
                Router.startActivity(MainModule.Activity.PRODUCT_DETAILS);
            }
        });
    }

    @Override
    protected void loadData() {
        List<HomePojo> goodsList = Mock.getGoodsList(20);
        mAdapter.addData(goodsList);
    }
}
