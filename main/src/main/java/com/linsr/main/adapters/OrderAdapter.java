package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.utils.ImageUtils;
import com.linsr.common.utils.ViewUtils;
import com.linsr.main.R;
import com.linsr.main.model.OrderPojo;
import com.linsr.main.model.bean.OrderListBean;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/31 下午5:17
 */
public class OrderAdapter extends BaseRecyclerAdapter<OrderListBean> {

    public interface OnOrderItemClickListener {
        void onPayBtnClick(int position, OrderListBean data);

        void onFirstBtnClick(OrderListBean data);

        void onSecondBtnClick(OrderListBean data);
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
    public BaseViewHolder<OrderListBean> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_order, parent, false));
    }

    class Holder extends BaseViewHolder<OrderListBean> {
        private TextView shopName;
        private TextView statusText;
        private TextView total;
        private TextView freight;
        private LinearLayout goodsContainer;
        private TextView mButton1, mButton2, mButton3;
        private ViewGroup root;

        public Holder(Context context, View itemView) {
            super(context, itemView);
            root = itemView.findViewById(R.id.item_order_root);
            goodsContainer = itemView.findViewById(R.id.item_order_container);
            shopName = itemView.findViewById(R.id.item_order_shop_name_tv);
            statusText = itemView.findViewById(R.id.item_order_status_tv);
            total = itemView.findViewById(R.id.item_order_goods_total_tv);
            freight = itemView.findViewById(R.id.item_order_goods_freight_tv);
            mButton3 = itemView.findViewById(R.id.item_order_btn_3);
            mButton2 = itemView.findViewById(R.id.item_order_btn_2);
            mButton1 = itemView.findViewById(R.id.item_order_btn_1);
        }

        @Override
        public void convert(final int position, final OrderListBean data, int itemType) {

            ViewUtils.setText(statusText, data.getDesc());
            ViewUtils.setText(total, "共" + data.getTotal_number() + "件商品，总金额 ￥" + data.getTotal_fee());
            ViewUtils.setText(freight, getString(R.string.main_order_freight_text, data.getShipping_fee()));

            addGoods(data.getGoods_info());
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

        private void addGoods(List<OrderListBean.GoodsInfoBean> goods_info) {
            goodsContainer.removeAllViews();
            if (goods_info == null) {
                return;
            }
            for (OrderListBean.GoodsInfoBean bean : goods_info) {
                View view = mInflater.inflate(R.layout.main_item_order_goods, root, false);
                TextView goodsName = view.findViewById(R.id.item_order_goods_name_tv);
                ViewUtils.setText(goodsName, bean.getGoods_name());
                TextView goodsNumber = view.findViewById(R.id.item_order_goods_count_tv);
                ViewUtils.setText(goodsNumber, "X " + bean.getGoods_number());
                TextView goodsPrice = view.findViewById(R.id.item_order_goods_price_tv);
                ViewUtils.setText(goodsPrice, bean.getGoods_price());
                ImageView goodsProfile = view.findViewById(R.id.item_order_goods_pic_iv);
                ImageUtils.load(mContext, bean.getGoods_img(), goodsProfile);
                goodsContainer.addView(view);
            }
        }
    }
}
