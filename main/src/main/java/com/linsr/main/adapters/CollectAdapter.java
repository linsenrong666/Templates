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
import com.linsr.main.model.CollectPojo;
import com.linsr.main.utils.PriceUtils;

/**
 * Description
 *
 * @author Linsr 2019/1/27 下午5:27
 */
public class CollectAdapter extends BaseRecyclerAdapter<CollectPojo.ListBean> {

    public CollectAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<CollectPojo.ListBean> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_collect, parent, false));
    }

    class Holder extends BaseViewHolder<CollectPojo.ListBean> {
        private ImageView img;
        private TextView name;
        private TextView price;
        private TextView count;

        public Holder(Context context, View itemView) {
            super(context, itemView);
            name = itemView.findViewById(R.id.item_collect_name_tv);
            price = itemView.findViewById(R.id.item_collect_price_tv);
            count = itemView.findViewById(R.id.item_collect_count_tv);
            img = itemView.findViewById(R.id.item_collect_img_iv);
        }

        @Override
        public void convert(int position, CollectPojo.ListBean data, int itemType) {
            ImageUtils.load(mContext, data.getGoods_img(), img);
            ViewUtils.setText(name, data.getShort_name());
            ViewUtils.setText(price, PriceUtils.format(data.getShop_price()));
            ViewUtils.setText(count, data.getCount_collect() + "人收藏");
        }
    }
}
