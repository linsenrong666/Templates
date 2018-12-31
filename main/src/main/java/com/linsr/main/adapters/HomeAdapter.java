package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.gui.widgets.recyclerview.HeaderAndFooterWrapper;
import com.linsr.main.R;
import com.linsr.main.adapters.holder.ActivityEnterHolder;
import com.linsr.main.adapters.holder.BannerHolder;
import com.linsr.main.adapters.holder.DailyNewHolder;
import com.linsr.main.adapters.holder.FlashSaleHolder;
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

    private ShopWindowHolder.OnShopWindowItemClickListener mOnShopWindowItemClickListener;

    public void setOnShopWindowItemClickListener(ShopWindowHolder.OnShopWindowItemClickListener onShopWindowItemClickListener) {
        mOnShopWindowItemClickListener = onShopWindowItemClickListener;
    }

    public HomeAdapter(Context context) {
        super(context);
    }

    private int mMenuSpanCount = 4;

    public void setMenuSpanCount(int menuSpanCount) {
        mMenuSpanCount = menuSpanCount;
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
                return new MenuHolder(mContext, view, mMenuSpanCount);
            case Constants.FloorType.RECOMMEND_GOODS:
                return newRecommendHolder(parent);
            case Constants.FloorType.SHOP_WINDOW:
                return newShopWindowHolder(parent);
            case Constants.FloorType.ACTIVITY_ENTER:
                view = mInflater.inflate(R.layout.main_item_activity_enter, parent, false);
                return new ActivityEnterHolder(mContext, view);
            case Constants.FloorType.FLASH_SALE:
                view = mInflater.inflate(R.layout.main_item_flash_sale, parent, false);
                return new FlashSaleHolder(mContext, view);
            case Constants.FloorType.DAILY_NEW:
                view = mInflater.inflate(R.layout.main_item_daily_new, parent, false);
                return new DailyNewHolder(mContext, view);
            default:
                view = mInflater.inflate(R.layout.main_item_null, parent, false);
                return new NullHolder(mContext, view);
        }
    }

    private ShopWindowHolder newShopWindowHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.main_item_shop_window, parent, false);
        ShopWindowHolder shopWindowHolder = new ShopWindowHolder(mContext, view);
        shopWindowHolder.setOnShopWindowItemClickListener(mOnShopWindowItemClickListener);
        return shopWindowHolder;
    }

    private RecommendHolder newRecommendHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.main_item_recommend_floor, parent, false);
        RecommendHolder recommendHolder = new RecommendHolder(mContext, view);
        recommendHolder.setOnRecommendHolderListener(mOnRecommendHolderListener);
        return recommendHolder;
    }

    @Override
    public int getItemViewType(int position) {
        HomePojo.HomeListBean homePojo = (HomePojo.HomeListBean) mList.get(position);
        return homePojo.getFloorType();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int headerCount = 0;
                    if (recyclerView.getAdapter() instanceof HeaderAndFooterWrapper) {
                        headerCount = ((HeaderAndFooterWrapper) (recyclerView.getAdapter())).getHeadersCount();
                    }
                    if (position >= headerCount) {
                        position = position - headerCount;
                    }
                    int viewType = getItemViewType(position);
                    int count = gridManager.getSpanCount();
                    switch (viewType) {
                        case Constants.FloorType.FLASH_SALE:
                        case Constants.FloorType.DAILY_NEW:
                            return count / 2;
                        case Constants.FloorType.ACTIVITY_ENTER:
                            return count / 3;
                        default:
                            return count;
                    }
                }
            });
        }
    }

}
