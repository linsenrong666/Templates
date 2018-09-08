package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.CategoryDetailsAdapter;
import com.linsr.main.model.CategoryMenuPojo;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/7/12 上午10:49
 */
public class CategoryDetailsFragment extends FragmentEx {

    private RecyclerView mRecyclerView;
    private CategoryDetailsAdapter mAdapter;
    private String mCategoryId;

    public static CategoryDetailsFragment newInstance(String categoryId) {

        Bundle args = new Bundle();
        args.putString("categoryId", categoryId);

        CategoryDetailsFragment fragment = new CategoryDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_category_details;
    }

    @Override
    protected void initArguments(Bundle arguments) {
        mCategoryId = arguments.getString("categoryId");
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.category_details_recycler_view);
        mAdapter = new CategoryDetailsAdapter(mActivity);
        RecyclerViewHelper.initGridLayout(mActivity, 3, mRecyclerView, mAdapter);

        mAdapter.addData(Mock.getMenuList(50));
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<CategoryMenuPojo>() {
            @Override
            public void onItemClick(BaseViewHolder<CategoryMenuPojo> holder,
                                    int position, int itemType, CategoryMenuPojo data) {
                Router.startActivity(MainModule.Activity.CHILD_CATEGORY);
            }
        });
    }
}
