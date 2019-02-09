package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.utils.ImageUtils;
import com.linsr.common.utils.ViewUtils;
import com.linsr.main.R;
import com.linsr.main.model.HomePojo;
import com.linsr.main.model.bean.IsbestBean;

/**
 * Description
 *
 * @author Linsr 2018/7/13 上午11:21
 */
public class RecommendGoodsAdapter extends BaseRecyclerAdapter<IsbestBean> {

    public interface OnGoodsClickListener {
        void onAdd(String goodsId);
    }

    public RecommendGoodsAdapter(Context context) {
        super(context);
    }

    private OnGoodsClickListener mOnGoodsClickListener;

    public void setOnGoodsClickListener(OnGoodsClickListener onGoodsClickListener) {
        mOnGoodsClickListener = onGoodsClickListener;
    }

    @NonNull
    @Override
    public BaseViewHolder<IsbestBean> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_item_home_goods, parent, false);
        return new Holder(mContext, view);
    }

    private class Holder extends BaseViewHolder<IsbestBean> {

        private TextView title;
        private TextView desc;
        private ImageView img;
        private ImageView add;

        Holder(Context context, View itemView) {
            super(context, itemView);
            title = findViewById(R.id.item_goods_title_tv);
            desc = findViewById(R.id.item_goods_desc_tv);
            img = findViewById(R.id.item_goods_img_iv);
            add = findViewById(R.id.item_goods_add_iv);
        }

        @Override
        public void convert(final int position, final IsbestBean data, int itemType) {
            ViewUtils.setText(title, data.getGoods_name());
            ImageUtils.load(mContext, data.getGoods_img(), img);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnGoodsClickListener != null) {
                        mOnGoodsClickListener.onAdd(data.getGoods_id());
                    }
                }
            });
        }
    }
}
