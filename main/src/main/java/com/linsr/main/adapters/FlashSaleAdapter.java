package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.FlashSalePojo;

/**
 * Description
 *
 * @author Linsr 2018/9/6 下午3:39
 */
public class FlashSaleAdapter extends BaseRecyclerAdapter<FlashSalePojo> {

    public FlashSaleAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<FlashSalePojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_item_flash_sale_item, parent, false);
        return new Holder(mContext, view);
    }

    class Holder extends BaseViewHolder<FlashSalePojo> {

        public Holder(Context context, View itemView) {
            super(context, itemView);
        }

        @Override
        public void convert(int position, FlashSalePojo data, int itemType) {

        }
    }
}
