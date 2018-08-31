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
import com.linsr.main.adapters.holder.ShopWindowHolder;
import com.linsr.main.app.Constants;
import com.linsr.main.model.HomePojo;

/**
 * Description
 *
 * @author Linsr 2018/8/29 下午4:44
 */
public class HomeAdapter extends BaseRecyclerAdapter {

    private RecommendHolder.OnRecommendHolderListener mOnRecommendHolderListener;

    public void setOnRecommendHolderListener(
            RecommendHolder.OnRecommendHolderListener onRecommendHolderListener) {
        mOnRecommendHolderListener = onRecommendHolderListener;
    }

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
                return newRecommendHolder(parent);
            case Constants.FloorType.SHOP_WINDOW:
                view = mInflater.inflate(R.layout.main_item_shop_window, parent, false);
                return new ShopWindowHolder(mContext, view);
            default:
                view = mInflater.inflate(R.layout.main_item_null, parent, false);
                return new NullHolder(mContext, view);
        }
    }

    private RecommendHolder newRecommendHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.main_item_recommend_floor, parent, false);
        RecommendHolder recommendHolder = new RecommendHolder(mContext, view);
        recommendHolder.setOnRecommendHolderListener(mOnRecommendHolderListener);
        return recommendHolder;
    }

    @Override
    public int getItemViewType(int position) {
        HomePojo homePojo = (HomePojo) mList.get(position);
        return homePojo.getFloorType();
    }
}
