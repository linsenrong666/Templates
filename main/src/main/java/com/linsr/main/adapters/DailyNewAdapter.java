package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.adapters.holder.DailyNewHolder;
import com.linsr.main.model.DailyNewPojo;

/**
 * Description
 *
 * @author Linsr 2018/9/6 下午6:38
 */
public class DailyNewAdapter extends BaseRecyclerAdapter<DailyNewPojo> {
    public DailyNewAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<DailyNewPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_item_flash_sale_item, parent, false);
        return new Holder(mContext,view );
    }

    class Holder extends BaseViewHolder<DailyNewPojo> {

        private TextView buyButton;

        public Holder(Context context, View itemView) {
            super(context, itemView);
            ProgressBar progressBar = itemView.findViewById(R.id.item_flash_sale_progress);
            progressBar.setVisibility(View.GONE);
            buyButton = findViewById(R.id.item_flash_sale_buy_btn);
            buyButton.setText(R.string.main_buy_now);
        }

        @Override
        public void convert(int position, DailyNewPojo data, int itemType) {

        }
    }
}
