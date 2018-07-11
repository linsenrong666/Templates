package com.linsr.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.linsr.common.base.BaseActivity;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.biz.EventKey;
import com.linsr.common.router.RouterCenter;
import com.linsr.common.router.url.BookModule;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.router.url.PersonalModule;
import com.linsr.common.utils.ActivityUtils;
import com.linsr.common.utils.BottomNavigationViewHelper;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;
import com.linsr.main.fragments.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(path = "/main/main")
public class MainActivity extends ActivityEx implements BottomNavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView mBottomNavigationView;
    private FrameLayout mFragmentContainer;

    private Fragment mCurrentFragment;
    private Fragment mHomeFragment;
    private Fragment mCategoryFragment;
    private Fragment mPersonalFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void initView() {
        mBottomNavigationView = findViewById(R.id.main_nav_bar);
        mFragmentContainer = findViewById(R.id.main_fragment_container);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        if (mHomeFragment == null) {
            mHomeFragment = RouterCenter.findFragment(MainModule.Fragment.HOME);
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mHomeFragment,
                R.id.main_fragment_container);
        mCurrentFragment = mHomeFragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_home:
                addOrShowFragment(mHomeFragment, MainModule.Fragment.HOME);
                return true;
            case R.id.item_category:
                addOrShowFragment(mCategoryFragment, MainModule.Fragment.CATEGORY);
                return true;
            case R.id.item_find:
                return true;
            case R.id.item_cart:
                return true;
            case R.id.item_me:
                addOrShowFragment(mPersonalFragment, PersonalModule.Fragment.ME);
                return true;
            default:
                return false;
        }
    }

    private void addOrShowFragment(Fragment fragment, String path) {
        if (fragment == null) {
            fragment = RouterCenter.findFragment(path);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mCurrentFragment = ActivityUtils.addOrShowFragment(mCurrentFragment,
                transaction, fragment, R.id.main_fragment_container);
    }
}
