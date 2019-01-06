package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.linsr.common.utils.ImageUtils;
import com.linsr.common.utils.JLog;
import com.linsr.main.R;
import com.linsr.main.model.HomePojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/29 下午6:10
 */
public class BannerPagerAdapter extends PagerAdapter {

    private static final String TAG = "BannerPagerAdapter";

    private List<HomePojo.HomeListBean.BannerDataBean> mStrings;
    private List<View> mViews;
    private Context mContext;
    private LayoutInflater mInflater;

    public BannerPagerAdapter(Context context,
                              List<HomePojo.HomeListBean.BannerDataBean> strings) {
        mContext = context;
        mStrings = strings;
        mInflater = LayoutInflater.from(mContext);

        mViews = new ArrayList<>();
        for (HomePojo.HomeListBean.BannerDataBean bean : strings) {
            ImageView view = (ImageView) mInflater.inflate(R.layout.main_item_banner_pager_item,
                    null);
            ImageUtils.load(context, bean.getAd_image(), view);
            mViews.add(view);
        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        return mStrings == null ? 0 : mStrings.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
