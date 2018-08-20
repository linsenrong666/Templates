package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.base.adapter.BindView;
import com.linsr.main.R;
import com.linsr.main.model.CategoryMenuPojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午2:31
 */
public class CategoryMenuAdapter extends BaseRecyclerAdapter<CategoryMenuPojo> {

    private int mSelectedPosition;

    public CategoryMenuAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<CategoryMenuPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_item_category_menu_item, parent, false);
        return new MenuHolder(mContext, view);
    }

    public void setSelectedPosition(int selectedPosition) {
        mSelectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    private class MenuHolder extends BaseViewHolder<CategoryMenuPojo> {

        private TextView title;

        MenuHolder(Context context, View itemView) {
            super(context, itemView);
            title = findViewById(R.id.item_category_title_tv);
        }

        @Override
        public void convert(final int position, CategoryMenuPojo data, int itemType) {
            title.setText(data.getTitle());
            if (mSelectedPosition == position) {
                mView.setBackgroundColor(getColor(R.color.colorAccent));
            } else {
                mView.setBackgroundColor(getColor(R.color.common_transparent));
            }
        }
    }
}
