package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.linsr.common.biz.FragmentEx;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.CategoryDetailsAdapter;
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
    private TextView mTitleTextView;

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

        mTitleTextView = findViewById(R.id.category_details_title_tv);
        mTitleTextView.setText(mCategoryId);

        mAdapter.addData(Mock.getMenuList(50));
    }
}
