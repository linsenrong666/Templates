package com.linsr.main.fragments;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.gui.divider.GridDivider;
import com.linsr.common.gui.divider.RecycleViewDivider;
import com.linsr.common.gui.widgets.recyclerview.HeaderAndFooterWrapper;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.AuctionAdapter;
import com.linsr.main.adapters.AuctionHeaderAdapter;
import com.linsr.main.model.AuctionPojo;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/9/14 下午2:10
 */
@Route(path = MainModule.Fragment.AUCTION)
public class AuctionFragment extends RefreshFragment {

    private AuctionAdapter mAdapter;
    private RecyclerView mHeaderRecyclerView;
    private AuctionHeaderAdapter mHeaderAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new AuctionAdapter(mActivity);
        mAdapter.addData(Mock.getList(10, AuctionPojo.class));
        RecyclerView.Adapter adapter = initHeader(mAdapter);


        recyclerView.addItemDecoration(new RecycleViewDivider(mActivity,
                LinearLayoutManager.VERTICAL,
                (int) mContext.getResources().getDimension(R.dimen.mini),
                mContext.getResources().getColor(R.color.background)));

        RecyclerViewHelper.initDefault(mActivity, recyclerView, adapter);
    }

    private RecyclerView.Adapter initHeader(AuctionAdapter adapter) {
        HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(adapter);
        View headerView = LayoutInflater.from(mActivity).inflate(R.layout.main_header_auction,
                mContentLayout, false);
        mHeaderRecyclerView = headerView.findViewById(R.id.auction_header_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHeaderRecyclerView.setLayoutManager(linearLayoutManager);
        mHeaderRecyclerView.setHasFixedSize(true);
        mHeaderAdapter = new AuctionHeaderAdapter(mActivity);
        mHeaderRecyclerView.setAdapter(mHeaderAdapter);
        mHeaderAdapter.setOnItemClickListener(
                new BaseRecyclerAdapter.OnItemClickListener<AuctionPojo>() {
                    @Override
                    public void onItemClick(BaseViewHolder<AuctionPojo> holder, int position,
                                            int itemType, AuctionPojo data) {
                        Router.startActivity(MainModule.Activity.AUCTION_DETAILS);
                    }
                });
        mHeaderAdapter.addData(Mock.getList(10, AuctionPojo.class));
        wrapper.addHeaderView(headerView);
        return wrapper;
    }

}