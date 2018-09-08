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
import com.linsr.main.R;
import com.linsr.main.model.RecommendPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/13 下午5:24
 */
public class RecommendAdapter extends BaseRecyclerAdapter<RecommendPojo> {

    public RecommendAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<RecommendPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_recommend_goods,
                parent, false));
    }

    class Holder extends BaseViewHolder<RecommendPojo> {

        private TextView name;
        private TextView price;
        private ImageView img;

        Holder(Context context, View itemView) {
            super(context, itemView);
            name = findViewById(R.id.recommend_goods_name_tv);
            price = findViewById(R.id.recommend_goods_price_tv);
            img = findViewById(R.id.recommend_goods_img_iv);
        }

        @Override
        public void convert(int position, RecommendPojo data, int itemType) {
            name.setText(data.getName());
            price.setText(data.getPrice());
        }
    }
}
