package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.AfterSalesPojo;

/**
 * Description
 *
 * @author Linsr 2018/9/2 下午4:18
 */
public class AfterSalesAdapter extends BaseRecyclerAdapter<AfterSalesPojo> {

    public interface OnAfterSalesListener {
        void onBtnClick(AfterSalesPojo pojo);
    }

    private OnAfterSalesListener mOnAfterSalesListener;

    public void setOnAfterSalesListener(OnAfterSalesListener onAfterSalesListener) {
        mOnAfterSalesListener = onAfterSalesListener;
    }

    public AfterSalesAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<AfterSalesPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_after_sales, parent, false));
    }

    class Holder extends BaseViewHolder<AfterSalesPojo> {
        private TextView btn;

        public Holder(Context context, View itemView) {
            super(context, itemView);
            btn = findViewById(R.id.item_after_sales_btn);
        }

        @Override
        public void convert(int position, final AfterSalesPojo data, int itemType) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnAfterSalesListener != null) {
                        mOnAfterSalesListener.onBtnClick(data);
                    }
                }
            });
        }
    }

}
