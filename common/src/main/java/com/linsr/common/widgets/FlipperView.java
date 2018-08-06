package com.linsr.common.widgets;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.linsr.common.R;

/**
 * Description
 *
 * @author Linsr 2018/8/6 下午3:16
 */
public class FlipperView extends FrameLayout {

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    public FlipperView(@NonNull Context context) {
        this(context, null, 0);
    }

    public FlipperView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlipperView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.common_widget_flipper_view,
                (ViewGroup) getRootView(), false);
        mViewPager = view.findViewById(R.id.flipper_vp);

        addView(view);
    }

    public void setPageAdapter(PagerAdapter pageAdapter) {
        mViewPager.setAdapter(pageAdapter);
        mPagerAdapter = pageAdapter;
        if (mPagerAdapter != null) {
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }
    }

}
