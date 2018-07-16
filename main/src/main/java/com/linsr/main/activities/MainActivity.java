package com.linsr.main.activities;

import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.FragmentPagerAdapterEx;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.biz.EventKey;
import com.linsr.common.router.RouterCenter;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.BottomNavigationViewHelper;
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;
import com.linsr.common.widgets.NoScrollViewPager;
import com.linsr.main.R;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

@Route(path = "/main/main")
public class MainActivity extends ActivityEx implements ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    private NoScrollViewPager mViewPager;
    private Badge mBadge;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void init() {
        super.init();
        registerOnContentUpdateListener(new AbstractOnContentUpdateListener() {
            @Override
            public void onContentUpdated(List<Object[]> values) {
                int i = mBadge.getBadgeNumber();
                i = i + 1;
                mBadge.setBadgeNumber(i);
            }

            @Override
            public boolean isActive() {
                return mIsActive;
            }

            @Override
            public String getKey() {
                return EventKey.ADD_GOODS_CART;
            }
        });
    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.main_view_pager);

        BottomNavigationView mBottomNavigationView = findViewById(R.id.main_nav_bar);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) mBottomNavigationView.getChildAt(0);
        View mCartView = menuView.getChildAt(3);
        mBadge = new QBadgeView(this).bindTarget(mCartView);

        initFragment();
    }

    private void initFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(RouterCenter.findFragment(MainModule.Fragment.HOME));
        list.add(RouterCenter.findFragment(MainModule.Fragment.CATEGORY));
        list.add(RouterCenter.findFragment(MainModule.Fragment.FIND));
        list.add(RouterCenter.findFragment(MainModule.Fragment.CART));
        list.add(RouterCenter.findFragment(MainModule.Fragment.ME));
        FragmentPagerAdapterEx mPagerAdapterEx = new FragmentPagerAdapterEx(getSupportFragmentManager(), list);
        mViewPager.setAdapter(mPagerAdapterEx);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(list.size());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.item_home) {
            mViewPager.setCurrentItem(0);
            return true;
        } else if (i == R.id.item_category) {
            mViewPager.setCurrentItem(1);
            return true;
        } else if (i == R.id.item_find) {
            mViewPager.setCurrentItem(2);
            return true;
        } else if (i == R.id.item_cart) {
            mViewPager.setCurrentItem(3);
            return true;
        } else if (i == R.id.item_me) {
            mViewPager.setCurrentItem(4);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
