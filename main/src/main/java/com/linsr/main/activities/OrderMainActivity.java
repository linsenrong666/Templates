package com.linsr.main.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.FragmentPagerAdapterEx;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.TabLayoutHelper;
import com.linsr.main.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/31 下午4:01
 */
@Route(path = MainModule.Activity.ORDER_MAIN)
public class OrderMainActivity extends ActivityEx {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_order_main;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_my_order);
        findView();
//        initTabs();
        initFragment();
    }

    private void initFragment() {
        List<Fragment> list = new ArrayList<>();
        list.add(Router.findFragment(MainModule.Fragment.ORDER_LIST));
        list.add(Router.findFragment(MainModule.Fragment.ORDER_LIST));
        list.add(Router.findFragment(MainModule.Fragment.ORDER_LIST));
        list.add(Router.findFragment(MainModule.Fragment.ORDER_LIST));
        list.add(Router.findFragment(MainModule.Fragment.ORDER_LIST));
        FragmentPagerAdapterEx adapter = new FragmentPagerAdapterEx(getSupportFragmentManager(), list);
        List<String> titles = Arrays.asList(getResources().getStringArray(R.array.main_order_status_text_list));
        adapter.setTitles(titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initTabs() {
        String[] itemTitles = getResources().getStringArray(R.array.main_order_status_text_list);
        for (String tabTitle : itemTitles) {
            mTabLayout.addTab(mTabLayout.newTab().setText(tabTitle));
        }

    }

    private void findView() {
        mTabLayout = findViewById(R.id.order_main_tab_layout);
        mViewPager = findViewById(R.id.order_main_view_pager);
    }
}
