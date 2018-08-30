package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.model.BasePojo;
import com.linsr.main.R;
import com.linsr.main.adapters.holder.BannerHolder;
import com.linsr.main.adapters.holder.MenuHolder;
import com.linsr.main.adapters.holder.NullHolder;
import com.linsr.main.adapters.holder.RecommendHolder;
import com.linsr.main.adapters.holder.SalesHolder;
import com.linsr.main.app.Constants;
import com.linsr.main.model.HomePojo;

/**
 * Description
 *
 * @author Linsr 2018/8/29 下午4:44
 */
public class HomeAdapter extends BaseRecyclerAdapter {

    public HomeAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case Constants.FloorType.BANNER:
                view = mInflater.inflate(R.layout.main_item_banner, parent, false);
                return new BannerHolder(mContext, view);
            case Constants.FloorType.MENU:
                view = mInflater.inflate(R.layout.main_item_menu_container, parent, false);
                return new MenuHolder(mContext, view, 4);
            case Constants.FloorType.RECOMMEND_GOODS:
                view = mInflater.inflate(R.layout.main_item_recommend_floor,parent,false);
                return  new RecommendHolder(mContext,view);
            default:
                view = mInflater.inflate(R.layout.main_item_null, parent, false);
                return new NullHolder(mContext, view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        HomePojo homePojo = (HomePojo) mList.get(position);
        return homePojo.getFloorType();
    }
}
