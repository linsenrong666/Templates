package com.linsr.main.adapters.holder;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.gui.divider.GridDividerItemDecoration;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.main.R;
import com.linsr.main.adapters.MenuItemAdapter;
import com.linsr.main.model.FindPojo;
import com.linsr.main.model.MenuItemPojo;
import com.linsr.main.utils.Mock;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/15 下午2:59
 */
public class MenuHolder extends BaseViewHolder {

    private RecyclerView mRecyclerView;
    private int mSpanCount;

    public MenuHolder(Context context, View itemView, int spanCount) {
        super(context, itemView);
        mRecyclerView = (RecyclerView) itemView;
        mSpanCount = spanCount;
    }

    @Override
    public void convert(int position, Object data, int itemType) {
        MenuItemAdapter adapter = new MenuItemAdapter(mContext);
        List<MenuItemPojo> menuItemList = Mock.getMenuItemList(5);
        adapter.addData(menuItemList);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, mSpanCount));
        mRecyclerView.setAdapter(adapter);
    }
}
