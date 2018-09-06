package com.linsr.common.utils;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.linsr.common.R;
import com.linsr.common.base.BaseFragment;
import com.linsr.common.base.adapter.FragmentPagerAdapterEx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * tab layout 帮助类
 *
 * @author Linsr 2018/6/12 上午11:24
 */
public class TabLayoutHelper {

    private static final String TAG = TabLayoutHelper.class.getCanonicalName();

    public static void initPagerFragment(Context context,
                                         final ViewPager mViewPager,
                                         TabLayout mTabLayout,
                                         FragmentManager fm,
                                         List<Fragment> fragments,
                                         List<String> titleList) {
        List<TextView> textViews = new ArrayList<>();
        for (Fragment f : fragments) {
            TextView textView = new TextView(context);
            textView.setTextSize(14);
            textView.setTextColor(context.getResources().getColorStateList(R.color.color_main_selector));
            textViews.add(textView);
        }
        initPagerFragment(context,mViewPager,mTabLayout,fm,textViews,fragments,titleList);
    }

    /**
     * 默认的viewpager搭配tabLayout内容是fragment的设置
     * @param mViewPager vp
     * @param mTabLayout tl
     * @param fm fragmentManager
     * @param fragments fragment list
     * @param titleList 标题 list
     */
    public static void initPagerFragment(Context context,
                                         final ViewPager mViewPager,
                                         TabLayout mTabLayout,
                                         FragmentManager fm,
                                         List<TextView> customViews,
                                         List<Fragment> fragments,
                                         List<String> titleList) {
        if (ObjectUtils.isNull(fragments) || ObjectUtils.isNull(titleList)) {
            JLog.e(TAG, "error! fragments =" + fragments + ",titleList=" + titleList);
            return;
        }
        FragmentPagerAdapterEx pagerAdapter = new FragmentPagerAdapterEx(fm, fragments);
        mViewPager.setAdapter(pagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //隐藏下划线
        mTabLayout.setSelectedTabIndicatorHeight(0);
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            //获得每一个tab
            TabLayout.Tab tab = mTabLayout.getTabAt(i);
            if (tab != null) {
                TextView textView = customViews.get(i);
                tab.setCustomView(textView);
                if (i == 0) {
                    // 设置第一个tab的TextView是被选择的样式//第一个tab被选中
                    textView.setSelected(true);
                } else {
                    textView.setSelected(false);
                }
                //设置tab上的文字
                textView.setText(titleList.get(i));
            }
        }

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                int position = tab.getPosition();
                mViewPager.setCurrentItem(position);
                if (customView != null) {
                    customView.setSelected(true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View customView = tab.getCustomView();
                if (customView != null) {
                    customView.setSelected(false);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public static void initPagerFragment(Context context,
                                         final ViewPager mViewPager,
                                         TabLayout mTabLayout,
                                         FragmentManager fm,
                                         Map<String, Fragment> map) {
        // 获取标题和fragment
        List<String> titleList = new ArrayList<>();
        List<Fragment> fragments = new ArrayList<>();
        for (Map.Entry<String, Fragment> entry : map.entrySet()) {
            titleList.add(entry.getKey());
            fragments.add(entry.getValue());
        }
        initPagerFragment(context, mViewPager, mTabLayout, fm, fragments, titleList);
    }

}
