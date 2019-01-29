package com.linsr.main.adapters;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.gui.widgets.recyclerview.WrapperUtils;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.common.utils.ImageUtils;
import com.linsr.main.R;
import com.linsr.main.model.RecommendPojo;
import com.linsr.main.model.bean.IsbestBean;

/**
 * Description
 *
 * @author Linsr 2018/8/13 下午5:24
 */
public class RecommendAdapter extends BaseRecyclerAdapter<IsbestBean> {

    public RecommendAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<IsbestBean> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_recommend_goods,
                parent, false));
    }

    private GridLayoutManager mGridLayoutManager;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            mGridLayoutManager = (GridLayoutManager) manager;
        }
    }

    class Holder extends BaseViewHolder<IsbestBean> {

        private TextView name;
        private TextView price;
        private ImageView img;

        Holder(Context context, View itemView) {
            super(context, itemView);
            name = findViewById(R.id.recommend_goods_name_tv);
            price = findViewById(R.id.recommend_goods_price_tv);
            img = findViewById(R.id.recommend_goods_img_iv);

            if (mGridLayoutManager != null) {
                ViewGroup.LayoutParams params = img.getLayoutParams();
                int x = mGridLayoutManager.getWidth() / mGridLayoutManager.getSpanCount() -
                        DisplayUtils.dp2px(context, 8) * 2;
                params.width = params.height = x;
                img.setLayoutParams(params);
            }
        }

        @Override
        public void convert(int position, IsbestBean data, int itemType) {
            name.setText(data.getGoods_name());
            price.setText(data.getShop_price());
            ImageUtils.load(mContext, data.getGoods_img(), img);
        }
    }

}
