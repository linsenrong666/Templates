package com.linsr.common.gui.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.linsr.common.R;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.common.utils.JLog;

import java.lang.ref.WeakReference;

/**
 * Description
 *
 * @author Linsr 2018/8/6 下午3:16
 */
public class FlipperView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private Context mContext;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    private LinearLayout mDotContainer;

    private int mDotDrawableResId;
    private int mDotDrawableSelectedResId;

    private int mDotPadding;
    private int mDuration = 3000;
    private boolean mIsRunning;
    private Handler mHandler;

    private static class InnerHandler extends Handler {
        private WeakReference<PagerAdapter> mPagerAdapter;
        private WeakReference<ViewPager> mViewPager;
        private int mDuration;

        public InnerHandler(PagerAdapter pagerAdapter, ViewPager viewPager, int duration) {
            mPagerAdapter = new WeakReference<>(pagerAdapter);
            mViewPager = new WeakReference<>(viewPager);
            mDuration = duration;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (check()) {
                        return;
                    }
                    mViewPager.get().setCurrentItem(0);
                    break;
                case 1:
                    if (check()) {
                        return;
                    }
                    int cur = mViewPager.get().getCurrentItem();
                    int page = cur + 1;
                    if (page >= mPagerAdapter.get().getCount()) {
                        page = 0;
                    }
                    mViewPager.get().setCurrentItem(page);
                    sendEmptyMessageDelayed(1, mDuration);
                    break;
            }
        }

        private boolean check() {
            return mPagerAdapter.get() == null || mViewPager.get() == null;
        }
    }

    public FlipperView(@NonNull Context context) {
        this(context, null, 0);
    }

    public FlipperView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlipperView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.common_widget_flipper_view,
                (ViewGroup) getRootView(), false);
        findView(view);
        initAttrs(attrs);
        addView(view);
    }

    private void initAttrs(AttributeSet attrs) {
        mDotDrawableResId = R.drawable.common_icon_dot_n;
        mDotDrawableSelectedResId = R.drawable.common_icon_dot_p;
        mDotPadding = 2;
    }

    private void findView(View view) {
        mViewPager = view.findViewById(R.id.flipper_vp);
        mDotContainer = view.findViewById(R.id.flipper_dot_container);
    }

    public void setPageAdapter(PagerAdapter pageAdapter) {
        mViewPager.setAdapter(pageAdapter);
        mPagerAdapter = pageAdapter;
        if (mPagerAdapter != null) {
            initDot();
            mViewPager.addOnPageChangeListener(this);
        }
    }

    private void initDot() {
        if (mPagerAdapter == null || mDotDrawableResId == 0) {
            return;
        }
        mDotContainer.removeAllViews();
        int count = mPagerAdapter.getCount();
        for (int i = 0; i < count; i++) {
            ImageButton iv = new ImageButton(mContext);
            iv.setImageResource(mDotDrawableResId);
            iv.setPadding(dp(mDotPadding), dp(mDotPadding), dp(mDotPadding), dp(mDotPadding));
            mDotContainer.addView(iv);
        }
        onPageSelected(0);
    }

    private void dotOnSelected(int position) {
        int childCount = mDotContainer.getChildCount();
        if (childCount == 0 || childCount < position) {
            JLog.e("error : dot on selected failed.childCount :" + childCount + ",position:" + position);
            return;
        }
        for (int i = 0; i < childCount; i++) {
            ImageView iv = (ImageView) mDotContainer.getChildAt(i);
            iv.setImageResource(mDotDrawableResId);
        }
        ((ImageView) mDotContainer.getChildAt(position)).setImageResource(mDotDrawableSelectedResId);
    }

    private int dp(int px) {
        return DisplayUtils.dp2px(mContext, px);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        dotOnSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public synchronized void start() {
        if (mHandler == null) {
            mHandler = new InnerHandler(mPagerAdapter, mViewPager, mDuration);
        }
        if (!mIsRunning) {
            mHandler.sendEmptyMessageDelayed(1, mDuration);
            mIsRunning = true;
        }
    }

    public void stop() {
        mIsRunning = false;
        mHandler.sendEmptyMessage(0);
    }
}
