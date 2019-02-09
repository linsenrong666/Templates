package com.linsr.main.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.utils.ViewUtils;
import com.linsr.main.R;
import com.linsr.main.model.HomePojo;

/**
 * Description
 *
 * @author Linsr 2018/8/30 下午3:22
 */
public class MenuItemAdapter extends BaseRecyclerAdapter<HomePojo.HomeListBean.IconDataBean> {

    public MenuItemAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<HomePojo.HomeListBean.IconDataBean> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_menu_icon, parent, false));
    }

    class Holder extends BaseViewHolder<HomePojo.HomeListBean.IconDataBean> {
        TextView tv;

        Holder(Context context, View itemView) {
            super(context, itemView);
            tv = (TextView) itemView;
        }

        @Override
        public void convert(int position, HomePojo.HomeListBean.IconDataBean data, int itemType) {
            String a = data.getA();
            int resId;
            String text;
            switch (a) {
                case "index":
                    resId = R.mipmap.ic_index_1;
                    text = "注册网店";
                    break;
                default:
                    resId = R.mipmap.placeholders_icon;
                    text = "暂不开放";
                    break;
            }
            ViewUtils.drawableTop(mContext, tv, resId);
            ViewUtils.setText(tv, text);
        }
    }

}
