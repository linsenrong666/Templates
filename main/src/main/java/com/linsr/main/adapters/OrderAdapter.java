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

    public interface OnOrderItemClickListener {
        void onPayBtnClick(int position, OrderPojo data);

        void onFirstBtnClick(OrderPojo data);

        void onSecondBtnClick(OrderPojo data);
    }

    private OnOrderItemClickListener mOnOrderItemClickListener;

    public void setOnOrderItemClickListener(OnOrderItemClickListener onOrderItemClickListener) {
        mOnOrderItemClickListener = onOrderItemClickListener;
    }

    public OrderAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<OrderPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_order, parent, false));
    }

    class Holder extends BaseViewHolder<OrderPojo> {
        private ImageView pic;
        private TextView name;
        private TextView price;
        private TextView count;
        private TextView total;
        private TextView freight;
        private TextView mButton1, mButton2, mButton3;

        public Holder(Context context, View itemView) {
            super(context, itemView);
            mButton3 = itemView.findViewById(R.id.item_order_btn_3);
            mButton2 = itemView.findViewById(R.id.item_order_btn_2);
            mButton1 = itemView.findViewById(R.id.item_order_btn_1);
        }

        @Override
        public void convert(final int position, final OrderPojo data, int itemType) {
            mButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnOrderItemClickListener != null) {
                        mOnOrderItemClickListener.onFirstBtnClick(data);
                    }
                }
            });
            mButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnOrderItemClickListener != null) {
                        mOnOrderItemClickListener.onSecondBtnClick(data);
                    }
                }
            });
            mButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnOrderItemClickListener != null) {
                        mOnOrderItemClickListener.onPayBtnClick(position, data);
                    }
                }
            });
        }
    }
}
