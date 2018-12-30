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
import com.linsr.main.model.CategoryMenuPojo;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午2:31
 */
public class CategoryDetailsAdapter extends BaseRecyclerAdapter<CategoryMenuPojo.CatListsBean.KidsBean> {

    public CategoryDetailsAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<CategoryMenuPojo.CatListsBean.KidsBean> onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_item_category_child_item, parent, false);
        return new MenuHolder(mContext, view);
    }

    private class MenuHolder extends BaseViewHolder<CategoryMenuPojo.CatListsBean.KidsBean> {

        private TextView title;
        private ImageView img;

        MenuHolder(Context context, View itemView) {
            super(context, itemView);
            title = findViewById(R.id.item_category_child_title_tv);
            img = findViewById(R.id.item_category_child_img_iv);
        }

        @Override
        public void convert(int position, CategoryMenuPojo.CatListsBean.KidsBean data, int itemType) {
            title.setText(data.getCat_name());
            ImageUtils.load(mContext, data.getCat_img(), img);
        }
    }
}
