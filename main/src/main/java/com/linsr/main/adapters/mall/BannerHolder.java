package com.linsr.main.adapters.mall;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.FindPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/15 下午2:46
 */
public class BannerHolder extends BaseViewHolder<FindPojo> {

    private TextView name;

    public BannerHolder(Context context, View itemView) {
        super(context, itemView);
        name = findViewById(R.id.item_find_title_tv);
    }

    @Override
    public void convert(int position, FindPojo data, int itemType) {
        name.setText("["+position+"]this is Banner");
    }

}