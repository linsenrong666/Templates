package com.linsr.common.base.adapter;

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

    public FragmentPagerAdapterEx(FragmentManager fm, List<Fragment> list) {
        super(fm);
        mList = list;
    }

    public FragmentPagerAdapterEx(FragmentManager fm) {
        super(fm);
        mList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }


}
