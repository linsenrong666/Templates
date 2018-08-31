package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.OrderPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/31 下午5:17
 */
public class OrderAdapter extends BaseRecyclerAdapter<OrderPojo> {

    public OrderAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<OrderPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext,mInflater.inflate(R.layout.main_item_order,parent,false));
    }

    class Holder extends BaseViewHolder<OrderPojo> {
        private ImageView pic;
        private TextView name;
        private TextView price;
        private TextView count;
        private TextView total;
        private TextView freight;

        public Holder(Context context, View itemView) {
            super(context, itemView);
        }

        @Override
        public void convert(int position, OrderPojo data, int itemType) {

        }
    }
}
