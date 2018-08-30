package com.linsr.main.adapters.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.adapters.ShopWindowAdapter;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/8/15 下午2:59
 */
public class ShopWindowHolder extends BaseViewHolder {

    private RecyclerView mRecyclerView;

    public ShopWindowHolder(Context context, View itemView) {
        super(context, itemView);
        mRecyclerView = itemView.findViewById(R.id.item_shop_window_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void convert(int position, Object data, int itemType) {
        ShopWindowAdapter adapter = new ShopWindowAdapter(mContext);
        adapter.addData(Mock.getShopWindowList(10));
        mRecyclerView.setAdapter(adapter);
    }

}