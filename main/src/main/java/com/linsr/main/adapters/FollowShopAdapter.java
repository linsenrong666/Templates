package com.linsr.main.adapters;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.base.adapter.SimpleAdapter;
import com.linsr.common.gui.widgets.CircleImageView;
import com.linsr.main.R;
import com.linsr.main.model.FollowShopPojo;

/**
 * Description
 *
 * @author Linsr 2018/9/26 下午6:13
 */
public class FollowShopAdapter extends SimpleAdapter<FollowShopPojo> {

    public interface OnEventListener {
        void onCancel(int position, FollowShopPojo data);
    }

    private TextView mCancelTextView;
    private CircleImageView mProfileImageView;
    private TextView mNameTextView;
    private OnEventListener mOnEventListener;

    public FollowShopAdapter(Context context) {
        super(context);
    }

    public void setOnEventListener(OnEventListener onEventListener) {
        mOnEventListener = onEventListener;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_item_follow_shop;
    }

    @Override
    protected void bindData(BaseViewHolder<FollowShopPojo> holder, final int position,
                            final FollowShopPojo data, int itemType) {
        mNameTextView = holder.findViewById(R.id.item_follow_name_tv);
        mCancelTextView = holder.findViewById(R.id.item_follow_cancel_tv);

        mNameTextView.setText(data.getName());
        mCancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEventListener != null) {
                    mOnEventListener.onCancel(position, data);
                }
            }
        });
    }

}
