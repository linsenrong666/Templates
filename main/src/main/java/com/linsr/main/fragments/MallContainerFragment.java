package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.FragmentPagerAdapterEx;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.main.adapters.FollowShopAdapter;
import com.linsr.main.widgets.MainSearchTitleLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 购物中心
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.MALL_CONTAINER)
public class MallContainerFragment extends FragmentEx {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView mLocationTextView;
    private MainSearchTitleLayout mSearchTitleLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_mall;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mTabLayout = findViewById(R.id.mall_container_tab_layout);
        mViewPager = findViewById(R.id.mall_container_view_pager);
        initSearchLayout();

    }

    private void initFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(Router.findFragment(MainModule.Fragment.MALL_HOME));
        list.add(Router.findFragment(MainModule.Fragment.AUCTION));
        list.add(Router.findFragment(MainModule.Fragment.GROUP_SHOPPING));
        list.add(Router.findFragment(MainModule.Fragment.ORDER_LIST));
        FragmentPagerAdapterEx adapter = new FragmentPagerAdapterEx(getChildFragmentManager(), list);
        List<String> titles = Arrays.asList(getResources().getStringArray(R.array.main_mall_index_list));
        adapter.setTitles(titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initSearchLayout() {
        mSearchTitleLayout= findViewById(R.id.mall_container_search_layout);
        mSearchTitleLayout.setOnEventListener(new MainSearchTitleLayout.OnEventListener() {
            @Override
            public void onSearchClick(String text) {

            }

            @Override
            public void onEditClick() {
                Router.startActivity(MainModule.Activity.SEARCH);
            }

            @Override
            public void onLeftImageClick() {

            }

            @Override
            public void onRightImageClick() {

            }
        });
    }

    @Override
    protected void loadData() {
        initFragments();
    }

}
