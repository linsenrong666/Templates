package com.linsr.main.activities;

import android.content.Intent;
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
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.BottomNavigationViewHelper;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;
import com.linsr.common.gui.widgets.NoScrollViewPager;
import com.linsr.main.R;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

@Route(path = MainModule.Activity.MAIN)
public class MainActivity extends ActivityEx implements ViewPager.OnPageChangeListener,
        BottomNavigationView.OnNavigationItemSelectedListener, MainModule.Activity.MainParams {

    private BottomNavigationView mBottomNavigationView;
    private NoScrollViewPager mViewPager;
    private Badge mBadge;
    private List<Fragment> mFragmentList;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            int select = intent.getIntExtra(SELECT_PAGE, -1);
            switch (select) {
                case HOME_PAGE:
                    toHomePage();
                    break;
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void init(Intent intent) {
        super.init(intent);
        registerOnContentUpdateListener(new AbstractOnContentUpdateListener() {
            @Override
            public void onContentUpdated(List<Object[]> values) {
                int i = mBadge.getBadgeNumber();
                i = i + 1;
                mBadge.setBadgeNumber(i);
            }

            @Override
            public boolean isActive() {
                return true;
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
        mBottomNavigationView = findViewById(R.id.main_nav_bar);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        BottomNavigationMenuView menuView =
                (BottomNavigationMenuView) mBottomNavigationView.getChildAt(0);
        View mCartView = menuView.getChildAt(2);
        mBadge = new QBadgeView(this).bindTarget(mCartView);
        mBadge.setBadgeBackgroundColor(getResources().getColor(R.color.main_color));
        initFragment();

        //默认选择home页
        toHomePage();
    }

    private void initFragment() {
        mFragmentList = new ArrayList<>();
        mFragmentList.add(Router.findFragment(MainModule.Fragment.HOME));
        mFragmentList.add(Router.findFragment(MainModule.Fragment.CATEGORY));
//        mFragmentList.add(Router.findFragment(MainModule.Fragment.MALL_CONTAINER));暂时注释
        mFragmentList.add(Router.findFragment(MainModule.Fragment.CART));
        mFragmentList.add(Router.findFragment(MainModule.Fragment.ME));
        FragmentPagerAdapterEx mPagerAdapterEx =
                new FragmentPagerAdapterEx(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(mPagerAdapterEx);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(mFragmentList.size());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.item_home) {
            mTitleView.setVisibility(View.GONE);
            mViewPager.setCurrentItem(0);
            return true;
        } else if (i == R.id.item_category) {
            mTitleView.setVisibility(View.GONE);
            mViewPager.setCurrentItem(1);
            return true;
        }
//        else if (i == R.id.item_shop_center) {
//            mTitleView.setVisibility(View.GONE);
//            mViewPager.setCurrentItem(2);
//            return true;
//        }
        else if (i == R.id.item_cart) {
            mTitleView.setVisibility(View.VISIBLE);
            mTitleView.setTitleText(getString(R.string.main_cart));
            mViewPager.setCurrentItem(2);
            return true;
        } else if (i == R.id.item_me) {
            mTitleView.setVisibility(View.VISIBLE);
            mTitleView.setTitleText(getString(R.string.main_me_center));
            mViewPager.setCurrentItem(3);
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

    public void toHomePage() {
        if (mBottomNavigationView != null) {
            mBottomNavigationView.setSelectedItemId(R.id.item_home);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int position = mViewPager.getCurrentItem();
        if (mFragmentList != null && mFragmentList.size() > position) {
            mFragmentList.get(position).onActivityResult(requestCode, resultCode, data);
        } else {
            JLog.e(TAG, "error onActivityResult. mFragmentList:"
                    + mFragmentList + ",cur position:" + position);
        }
    }
}
