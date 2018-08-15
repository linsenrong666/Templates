package com.linsr.main.adapters.mall;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.base.adapter.BindView;
import com.linsr.main.R;
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
        switch (viewType) {
            case 0:
                view = mInflater.inflate(R.layout.main_item_find,parent,false);
                return new BannerHolder(mContext,view);
            case 1:
                view = mInflater.inflate(R.layout.main_item_find,parent,false);
                return new MenuHolder(mContext,view);
            case 2:
                view = mInflater.inflate(R.layout.main_item_find,parent,false);
                return new RecommendHolder(mContext,view);
            case 3:
                view = mInflater.inflate(R.layout.main_item_find,parent,false);
                return new SalesHolder(mContext,view);
            default:
                view = mInflater.inflate(R.layout.main_item_find,parent,false);
                return new SalesHolder(mContext,view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getFloorType();
    }
}
