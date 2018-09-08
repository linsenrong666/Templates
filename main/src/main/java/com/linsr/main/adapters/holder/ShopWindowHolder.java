package com.linsr.main.adapters.holder;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.adapters.ShopWindowAdapter;
import com.linsr.main.model.ShopWindowPojo;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/8/15 下午2:59
 */
public class ShopWindowHolder extends BaseViewHolder {

    public interface OnShopWindowItemClickListener {
        void onItemClick();
    }

    private RecyclerView mRecyclerView;
    private OnShopWindowItemClickListener mOnShopWindowItemClickListener;

    public void setOnShopWindowItemClickListener(OnShopWindowItemClickListener onShopWindowItemClickListener) {
        mOnShopWindowItemClickListener = onShopWindowItemClickListener;
    }

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
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<ShopWindowPojo>() {
            @Override
            public void onItemClick(BaseViewHolder<ShopWindowPojo> holder,
                                    int position, int itemType, ShopWindowPojo data) {
                if (mOnShopWindowItemClickListener != null) {
                    mOnShopWindowItemClickListener.onItemClick();
                }
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

}