package com.linsr.main.adapters.holder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.adapters.MenuItemAdapter;
import com.linsr.main.model.HomePojo;
import com.linsr.main.utils.Mock;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/15 下午2:59
 */
public class MenuHolder extends BaseViewHolder<HomePojo.HomeListBean> {

    private RecyclerView mRecyclerView;
    private int mSpanCount;
    private MenuItemAdapter mMenuItemAdapter;

    public MenuHolder(Context context, View itemView, int spanCount) {
        super(context, itemView);
        mRecyclerView = (RecyclerView) itemView;
        mSpanCount = spanCount;
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, mSpanCount));
        mMenuItemAdapter = new MenuItemAdapter(mContext);
        mRecyclerView.setAdapter(mMenuItemAdapter);
    }

    @Override
    public void convert(int position, HomePojo.HomeListBean data, int itemType) {
        mMenuItemAdapter.clear();
        List<HomePojo.HomeListBean.IconDataBean> iconData = data.getIconData();
        if (iconData != null && iconData.size() > 0) {
            mMenuItemAdapter.addData(iconData);
        }

    }
}
