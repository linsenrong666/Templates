package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.CategoryMenuPojo;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午2:31
 */
public class CategoryMenuAdapter extends BaseRecyclerAdapter<CategoryMenuPojo.CatListsBean> {

    private int mSelectedPosition;

    public CategoryMenuAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<CategoryMenuPojo.CatListsBean> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_item_category_menu_item, parent, false);
        return new MenuHolder(mContext, view);
    }

    public void setSelectedPosition(int selectedPosition) {
        mSelectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    private class MenuHolder extends BaseViewHolder<CategoryMenuPojo.CatListsBean> {
        private View strip;
        private TextView title;

        MenuHolder(Context context, View itemView) {
            super(context, itemView);
            title = findViewById(R.id.item_category_title_tv);
            strip = findViewById(R.id.item_category_strip);
        }

        @Override
        public void convert(final int position, CategoryMenuPojo.CatListsBean data, int itemType) {
            title.setText(data.getCat_name());
            if (mSelectedPosition == position) {
                strip.setVisibility(View.VISIBLE);
                title.setTextColor(getColor(R.color.main_color));
                itemView.setBackgroundColor(getColor(R.color.white));
            } else {
                strip.setVisibility(View.INVISIBLE);
                title.setTextColor(getColor(R.color.text_primary));
                itemView.setBackgroundColor(getColor(R.color.common_transparent));
            }
        }
    }
}
