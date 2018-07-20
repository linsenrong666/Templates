package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.base.adapter.BindView;
import com.linsr.common.utils.ImageUtils;
import com.linsr.main.R;
import com.linsr.main.model.HomePojo;

/**
 * Description
 *
 * @author Linsr 2018/7/13 上午11:21
 */
public class GoodsAdapter extends BaseRecyclerAdapter<HomePojo> {

    public interface OnGoodsClickListener {
        void onAdd(int position);
    }

    public GoodsAdapter(Context context) {
        super(context);
    }

    private OnGoodsClickListener mOnGoodsClickListener;

    public void setOnGoodsClickListener(OnGoodsClickListener onGoodsClickListener) {
        mOnGoodsClickListener = onGoodsClickListener;
    }

    @NonNull
    @Override
    public BaseViewHolder<HomePojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_item_goods, parent, false);
        return new Holder(mContext, view);
    }

    private class Holder extends BaseViewHolder<HomePojo> {

        private TextView title;
        private TextView desc;
        private ImageView img;
        private TextView add;

        Holder(Context context, View itemView) {
            super(context, itemView);
            title = findViewById(R.id.item_goods_title_tv);
            desc = findViewById(R.id.item_goods_desc_tv);
            img = findViewById(R.id.item_goods_img_iv);
            add = findViewById(R.id.item_goods_add_tv);
        }

        @Override
        protected void convert(final int position, HomePojo data, int itemType) {
            title.setText(data.getTitle());
            desc.setText(data.getDesc());
//            ImageUtils.load(mContext, data.getUrl(), img);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnGoodsClickListener != null) {
                        mOnGoodsClickListener.onAdd(position);
                    }
                }
            });
        }
    }
}
