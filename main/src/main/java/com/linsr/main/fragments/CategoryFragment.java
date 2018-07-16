package com.linsr.main.fragments;

import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.base.adapter.FragmentPagerAdapterEx;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.ActivityUtils;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.RecyclerViewHepler;
import com.linsr.common.widgets.NoScrollViewPager;
import com.linsr.main.R;
import com.linsr.main.adapters.CategoryMenuAdapter;
import com.linsr.main.contacts.HomeContact;
import com.linsr.main.model.CategoryMenuPojo;
import com.linsr.main.utils.Mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.CATEGORY)
public class CategoryFragment extends FragmentEx  {

    private RecyclerView mRecyclerView;
    private CategoryMenuAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_category;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.category_recycler_view);
        mAdapter = new CategoryMenuAdapter(mActivity);
        RecyclerViewHepler.initDefault(mActivity, mRecyclerView, mAdapter);

        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<CategoryMenuPojo>() {
            @Override
            public void onItemClick(BaseViewHolder<CategoryMenuPojo> holder, int position, int itemType, CategoryMenuPojo data) {
                replaceFragment(CategoryDetailsFragment.newInstance(data.getTitle()));
                mAdapter.setSelectedPosition(position);
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        // 把碎片添加到碎片中
        transaction.replace(R.id.category_fragment_container, fragment);
        transaction.commit();
    }

    @Override
    protected void loadData() {
        JLog.e("====loadData====");
        mAdapter.addData(Mock.getMenuList(20));
        int selectedPosition = 0;
        mAdapter.setSelectedPosition(selectedPosition);
        replaceFragment(CategoryDetailsFragment.newInstance("item-"));
    }

}