package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.TabLayoutHelper;
import com.linsr.main.R;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.HOME)
public class HomeFragment extends FragmentEx {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.home_view_pager);
        mTabLayout = findViewById(R.id.home_tab_layout);
        Map<String, Fragment> map = new LinkedHashMap<>();
        map.put("AAA", Router.findFragment(MainModule.Fragment.HOME_GOODS));
        map.put("BBB", Router.findFragment(MainModule.Fragment.HOME_GOODS));
        map.put("CCC", Router.findFragment(MainModule.Fragment.HOME_GOODS));
        map.put("DDD", Router.findFragment(MainModule.Fragment.HOME_GOODS));
        map.put("EEE", Router.findFragment(MainModule.Fragment.HOME_GOODS));
        map.put("FFF", Router.findFragment(MainModule.Fragment.HOME_GOODS));
        map.put("GGG", Router.findFragment(MainModule.Fragment.HOME_GOODS));
        TabLayoutHelper.initPagerFragment(mContext, mViewPager, mTabLayout, getChildFragmentManager(), map);
        mViewPager.setOffscreenPageLimit(map.size());
    }

    @Override
    protected void loadData() {

    }

}
