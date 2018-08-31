package com.linsr.common.base.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午4:21
 */
public class FragmentPagerAdapterEx extends FragmentPagerAdapter {

    private List<Fragment> mList;
    private List<String> mTitles;

    public FragmentPagerAdapterEx(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mList = list;
    }

    public void setTitles(List<String> titles) {
        mTitles = titles;
    }

    public FragmentPagerAdapterEx(FragmentManager fm) {
        super(fm);
        mList = new ArrayList<>();
    }

    public void addData(List<Fragment> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null) {
            return mTitles.get(position);
        }
        return super.getPageTitle(position);
    }
}
