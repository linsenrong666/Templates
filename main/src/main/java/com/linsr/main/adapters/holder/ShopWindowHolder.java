package com.linsr.main.adapters.holder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.utils.ImageUtils;
import com.linsr.common.utils.JLog;
import com.linsr.main.R;
import com.linsr.main.adapters.HomeAdapter;
import com.linsr.main.adapters.ShopWindowAdapter;
import com.linsr.main.model.HomePojo;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/15 下午2:59
 */
public class ShopWindowHolder extends BaseViewHolder<HomePojo.HomeListBean> {

    public interface OnShopWindowItemClickListener {
        void onItemClick(HomePojo.HomeListBean.YimaStreeDataBean.GsBean data);

        void onBackgroundClick();
    }

    private ImageView mBackgroundImageView;
    private RecyclerView mRecyclerView;
    private TextView mTitleTextView;
    private View mYimaTitle;
    private OnShopWindowItemClickListener mOnShopWindowItemClickListener;

    private HomeAdapter mHomeAdapter;

    public void setHomeAdapter(HomeAdapter homeAdapter) {
        mHomeAdapter = homeAdapter;
    }

    public void setOnShopWindowItemClickListener(OnShopWindowItemClickListener onShopWindowItemClickListener) {
        mOnShopWindowItemClickListener = onShopWindowItemClickListener;
    }

    public ShopWindowHolder(Context context, View itemView) {
        super(context, itemView);
        mRecyclerView = itemView.findViewById(R.id.item_shop_window_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mBackgroundImageView = findViewById(R.id.item_shop_window_bg_tv);
        mYimaTitle = findViewById(R.id.item_shop_window_title_layout);
        mTitleTextView = findViewById(R.id.layout_home_item_title_tv);
        mTitleTextView.setText("姨妈街");
    }

    @Override
    public void convert(int position, HomePojo.HomeListBean data, int itemType) {
        showTitleIfNeed(position);
        if (data.getYimaStreeData() != null) {
            HomePojo.HomeListBean.YimaStreeDataBean.CatBean cat = data.getYimaStreeData().getCat();
            ImageUtils.load(mContext, cat.getCat_img(), mBackgroundImageView);

            mBackgroundImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnShopWindowItemClickListener != null) {
                        mOnShopWindowItemClickListener.onBackgroundClick();
                    }
                }
            });
            List<HomePojo.HomeListBean.YimaStreeDataBean.GsBean> gs = data.getYimaStreeData().getGs();
            if (gs != null && gs.size() > 0) {
                ShopWindowAdapter adapter = new ShopWindowAdapter(mContext);
                adapter.addData(gs);
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<HomePojo.HomeListBean.YimaStreeDataBean.GsBean>() {
                    @Override
                    public void onItemClick(BaseViewHolder<HomePojo.HomeListBean.YimaStreeDataBean.GsBean> holder,
                                            int position, int itemType, HomePojo.HomeListBean.YimaStreeDataBean.GsBean data) {
                        if (mOnShopWindowItemClickListener != null) {
                            mOnShopWindowItemClickListener.onItemClick(data);
                        }
                    }
                });
                mRecyclerView.setAdapter(adapter);
            }

        }
    }

    private void showTitleIfNeed(int position) {
        if (mHomeAdapter == null) {
            return;
        }
        if (mHomeAdapter.getShowYimaTitlePosition() == position
                || mHomeAdapter.getShowYimaTitlePosition() == -1) {
            mHomeAdapter.setShowYimaTitlePosition(position);
            mYimaTitle.setVisibility(View.VISIBLE);
        } else {
            mYimaTitle.setVisibility(View.GONE);
        }
    }

}