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
import com.linsr.main.adapters.holder.RecommendHolder;
import com.linsr.main.adapters.holder.SalesHolder;
import com.linsr.main.model.FindPojo;

/**
 * Description
 *
 * @author Linsr 2018/7/12 下午4:48
 */
public class MallAdapter extends BaseRecyclerAdapter<FindPojo> {

    public MallAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<FindPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;

        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getFloorType();
    }
}
