package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.base.adapter.SimpleAdapter;
import com.linsr.main.R;
import com.linsr.main.model.GroupShoppingPojo;

/**
 * Description
 *
 * @author Linsr 2018/9/14 下午4:56
 */
public class GroupShoppingAdapter extends SimpleAdapter<GroupShoppingPojo> {


    @Override
    protected int getLayoutId() {
        return R.layout.main_item_group_shopping;
    }

    @Override
    protected void bindData(BaseViewHolder<GroupShoppingPojo> holder, int position,
                            GroupShoppingPojo data, int itemType) {

    }
    
    public GroupShoppingAdapter(Context context) {
        super(context);
    }
}
