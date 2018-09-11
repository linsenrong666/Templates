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

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/29 下午6:10
 */
public class BannerPagerAdapter extends PagerAdapter {

    private static final String TAG = "BannerPagerAdapter";

    private List<String> mStrings;
    private int[] mRedIds;
    private List<View> mViews;
    private Context mContext;
    private LayoutInflater mInflater;

    public BannerPagerAdapter(Context context,
                              List<String> strings) {
        mContext = context;
        mStrings = strings;
        mInflater = LayoutInflater.from(mContext);

        mViews = new ArrayList<>();
        for (String url : strings) {
            ImageView view = (ImageView) mInflater.inflate(R.layout.main_item_banner_pager_item, null);
            ImageUtils.load(mContext, url, view);
            mViews.add(view);
        }
    }

    public BannerPagerAdapter(Context context,
                              int[] strings) {
        mContext = context;
        mRedIds = strings;
        mInflater = LayoutInflater.from(mContext);

        mViews = new ArrayList<>();
        for (int url : strings) {
            ImageView view = (ImageView) mInflater.inflate(R.layout.main_item_banner_pager_item,
                    null);
            view.setImageResource(url);
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
        return mRedIds == null ? 0 : mRedIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
