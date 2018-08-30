package com.linsr.main.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.main.R;
import com.linsr.main.model.ShopWindowPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/30 下午6:10
 */
public class ShopWindowAdapter extends BaseRecyclerAdapter<ShopWindowPojo> {

    public ShopWindowAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<ShopWindowPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_item_shop_window_item, parent, false);
        return new Holder(mContext, view);
    }

    class Holder extends BaseViewHolder<ShopWindowPojo> {

        private TextView name;

        Holder(Context context, View itemView) {
            super(context, itemView);
            name = itemView.findViewById(R.id.item_shop_window_name_tv);
        }

        @Override
        public void convert(int position, ShopWindowPojo data, int itemType) {
            name.setText(data.getText());
        }
    }
}
