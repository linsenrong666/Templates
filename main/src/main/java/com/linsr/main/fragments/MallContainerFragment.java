package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.FragmentPagerAdapterEx;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.MallAdapter;
import com.linsr.main.model.FindPojo;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

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
        initFragments();
    }

    private void initFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(Router.findFragment(MainModule.Fragment.MALL_HOME));
        list.add(Router.findFragment(MainModule.Fragment.ORDER_LIST));
        list.add(Router.findFragment(MainModule.Fragment.ORDER_LIST));
        list.add(Router.findFragment(MainModule.Fragment.ORDER_LIST));
        FragmentPagerAdapterEx adapter = new FragmentPagerAdapterEx(getChildFragmentManager(), list);
        List<String> titles = Arrays.asList(getResources().getStringArray(R.array.main_mall_index_list));
        adapter.setTitles(titles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initSearchLayout() {
        findViewById(R.id.layout_search_left_img).setVisibility(View.GONE);
        mLocationTextView = findViewById(R.id.layout_search_left_tv);
        mLocationTextView.setVisibility(View.VISIBLE);
        mLocationTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageView rightImageView = findViewById(R.id.layout_search_right_img);
        rightImageView.setImageResource(R.mipmap.ic_share);
        rightImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void loadData() {

    }

}
