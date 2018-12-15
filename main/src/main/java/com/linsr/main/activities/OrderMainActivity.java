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
import com.linsr.main.app.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.linsr.common.router.url.MainModule.Activity.OrderMainParams.ORDER_STATUS;

/**
 * Description
 *
 * @author Linsr 2018/8/31 下午4:01
 */
@Route(path = MainModule.Activity.ORDER_MAIN)
public class OrderMainActivity extends ActivityEx {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private int mOrderStatus;

    @Override
    protected void init(Intent intent) {
        super.init(intent);
        if (intent != null) {
            mOrderStatus = intent.getIntExtra(ORDER_STATUS, Constants.OrderStatus.ALL_ORDER);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_order_main;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_my_order);
        findView();
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

        mViewPager.setOffscreenPageLimit(list.size());
        mViewPager.setCurrentItem(mOrderStatus);
    }

    private void findView() {
        mTabLayout = findViewById(R.id.order_main_tab_layout);
        mViewPager = findViewById(R.id.order_main_view_pager);
    }
}
