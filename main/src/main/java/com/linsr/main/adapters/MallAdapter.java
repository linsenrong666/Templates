package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.adapters.holder.BannerHolder;
import com.linsr.main.adapters.holder.MenuHolder;
import com.linsr.main.adapters.holder.NullHolder;
import com.linsr.main.adapters.holder.RecommendHolder;
import com.linsr.main.adapters.holder.SalesHolder;
import com.linsr.main.model.FindPojo;

/**
 * Description
 *
 * @author Linsr 2018/7/12 下午4:48
 */
public class MallAdapter extends BaseRecyclerAdapter {

    public MallAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new NullHolder(mContext, mInflater.inflate(R.layout.main_item_null, parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        if (mList == null || mList.size() == 0) {
            return super.getItemViewType(position);
        }
        FindPojo item = (FindPojo) mList.get(position);
        return item.getFloorType();
    }
}
