package com.linsr.main.adapters;

import android.content.Context;
import android.view.View;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.base.adapter.SimpleAdapter;
import com.linsr.main.R;
import com.linsr.main.model.FollowShopPojo;

/**
 * Description
 *
 * @author Linsr 2018/9/26 下午6:13
 */
public class FollowShopAdapter extends SimpleAdapter<FollowShopPojo> {

    public FollowShopAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_item_follow_shop;
    }

    @Override
    protected void initView(View itemView) {

    }

    @Override
    protected void bindData(BaseViewHolder<FollowShopPojo> holder, int position,
                            FollowShopPojo data, int itemType) {

    }

}
