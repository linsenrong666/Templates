package com.linsr.main.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.MenuItemPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/30 下午3:22
 */
public class MenuItemAdapter extends BaseRecyclerAdapter<MenuItemPojo> {

    public MenuItemAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<MenuItemPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_menu_icon, parent, false));
    }

    class Holder extends BaseViewHolder<MenuItemPojo> {
        TextView tv;

        Holder(Context context, View itemView) {
            super(context, itemView);
            tv = (TextView) itemView;
        }

        @Override
        public void convert(int position, MenuItemPojo data, int itemType) {
            tv.setText(data.getText());
            Drawable drawable = mContext.getResources().getDrawable(data.getIcon());
            tv.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        }
    }

}
