package com.linsr.main.adapters.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.FindPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/15 下午2:59
 */
public class RecommendHolder extends BaseViewHolder {

    private TextView more;

    public interface OnRecommendHolderListener {
        void onMoreClick(int position);
    }

    private OnRecommendHolderListener mOnRecommendHolderListener;

    public void setOnRecommendHolderListener(OnRecommendHolderListener onRecommendHolderListener) {
        mOnRecommendHolderListener = onRecommendHolderListener;
    }

    public RecommendHolder(Context context, View itemView) {
        super(context, itemView);
        more = (TextView) findViewById(R.id.item_recommend_floor_more_tv);
    }

    @Override
    public void convert(final int position, Object data, int itemType) {
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecommendHolderListener != null) {
                    mOnRecommendHolderListener.onMoreClick(position);
                }
            }
        });
    }
}
