package com.linsr.main.adapters;

import android.content.Context;
import android.view.View;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.base.adapter.SimpleAdapter;
import com.linsr.main.R;
import com.linsr.main.model.AuctionRecordPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/9/15 下午4:26
 */
public class AuctionRecordAdapter extends SimpleAdapter<AuctionRecordPojo> {

    public AuctionRecordAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_item_auction_record;
    }

    @Override
    protected void bindData(BaseViewHolder<AuctionRecordPojo> holder, int position,
                            AuctionRecordPojo data, int itemType) {

    }

    public void addData(List<AuctionRecordPojo> data, int visibleCount) {
        ensureDataNotNull();
        if (visibleCount <= 0 || visibleCount > data.size()) {
            return;
        }
        mList.clear();
        for (int i = 0; i < visibleCount; i++) {
            mList.add(data.get(i));
        }
        notifyDataSetChanged();
    }

}
