package com.linsr.main.adapters.holder;

import android.content.Context;
import android.view.View;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.utils.JLog;
import com.linsr.common.gui.widgets.FlipperView;
import com.linsr.main.R;
import com.linsr.main.adapters.BannerPagerAdapter;
import com.linsr.main.model.HomePojo;
import com.linsr.main.utils.Mock;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/15 下午2:46
 */
public class BannerHolder extends BaseViewHolder<HomePojo.HomeListBean> {

    private FlipperView mFlipperView;

    public BannerHolder(Context context, View itemView) {
        super(context, itemView);
        mFlipperView = itemView.findViewById(R.id.item_banner_flipper_view);
    }

    @Override
    public void convert(int position, HomePojo.HomeListBean data, int itemType) {
        BannerPagerAdapter pagerAdapter = new BannerPagerAdapter(mContext, data.getBannerData());
        mFlipperView.setPageAdapter(pagerAdapter);
        mFlipperView.start();
    }


}