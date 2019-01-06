package com.linsr.main.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.common.utils.ImageUtils;
import com.linsr.common.utils.ViewUtils;
import com.linsr.main.R;
import com.linsr.main.model.HomePojo;
import com.linsr.main.model.ShopWindowPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/30 下午6:10
 */
public class ShopWindowAdapter extends
        BaseRecyclerAdapter<HomePojo.HomeListBean.YimaStreeDataBean.GsBean> {

    public ShopWindowAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<HomePojo.HomeListBean.YimaStreeDataBean.GsBean> onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_item_shop_window_item, parent, false);
        return new Holder(mContext, view);
    }

    class Holder extends BaseViewHolder<HomePojo.HomeListBean.YimaStreeDataBean.GsBean> {

        private TextView name;
        private TextView price;
        private ImageView img;

        Holder(Context context, View itemView) {
            super(context, itemView);
            name = itemView.findViewById(R.id.item_shop_window_name_tv);
            price = itemView.findViewById(R.id.item_shop_window_price_tv);
            img = itemView.findViewById(R.id.item_shop_window_img_iv);
        }

        @Override
        public void convert(int position, HomePojo.HomeListBean.YimaStreeDataBean.GsBean data, int itemType) {
            ViewUtils.setText(name, data.getShort_name());
            ViewUtils.setText(price, "$" + data.getShop_price());
            ImageUtils.load(mContext, data.getGoods_img(), img);
        }
    }
}
