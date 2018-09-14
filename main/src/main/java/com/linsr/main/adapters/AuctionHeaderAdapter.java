package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.AuctionPojo;

/**
 * Description
 *
 * @author Linsr 2018/9/14 上午11:19
 */
public class AuctionHeaderAdapter extends BaseRecyclerAdapter<AuctionPojo> {

    public AuctionHeaderAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<AuctionPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_auction_header, parent, false));
    }

    class Holder extends BaseViewHolder<AuctionPojo> {

        public Holder(Context context, View itemView) {
            super(context, itemView);
        }

        @Override
        public void convert(int position, AuctionPojo data, int itemType) {

        }
    }
}
