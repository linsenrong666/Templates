package com.linsr.main.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.FragmentPagerAdapterEx;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.main.model.CategoryMenuPojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/14 上午10:36
 */
@Route(path = MainModule.Activity.CHILD_CATEGORY)
public class ChildCategoryActivity extends ActivityEx implements MainModule.Activity.ChildCategoryParams {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<CategoryMenuPojo> mList;
    private static final int MAX_CACHE_FRAGMENT_COUNT = 10;
    private int mCurrentPosition;

    @Override
    protected void init(Intent intent) {
        super.init(intent);
        if (intent != null) {
            mList = (List<CategoryMenuPojo>) intent.getSerializableExtra(MENU_LIST);
            mCurrentPosition = intent.getIntExtra(ENTER_POSITION, 0);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_child_category;
    }

    @Override
    protected void initView() {
        mTabLayout = findViewById(R.id.child_category_tab_layout);
        mViewPager = findViewById(R.id.child_category_view_pager);
        if (mList != null) {
            List<String> titles = new ArrayList<>();
            List<Fragment> list = new ArrayList<>();
            for (CategoryMenuPojo pojo : mList) {
                titles.add(pojo.getTitle());
                list.add(Router.findFragment(MainModule.Fragment.CHILD_CATEGORY));
            }
            FragmentPagerAdapterEx adapterEx = new FragmentPagerAdapterEx(getSupportFragmentManager(), list);
            adapterEx.setTitles(titles);
            mViewPager.setAdapter(adapterEx);
            if (mList.size() < MAX_CACHE_FRAGMENT_COUNT) {
                mViewPager.setOffscreenPageLimit(mList.size());
            } else {
                mViewPager.setOffscreenPageLimit(MAX_CACHE_FRAGMENT_COUNT);
            }
            if (mCurrentPosition <= mList.size()) {
                mViewPager.setCurrentItem(mCurrentPosition);
            }
            mTabLayout.setupWithViewPager(mViewPager);
        }
    }

    @Override
    protected boolean showTitleView() {
        return false;
    }
}
