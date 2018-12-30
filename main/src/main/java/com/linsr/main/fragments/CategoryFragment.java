package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.CategoryMenuAdapter;
import com.linsr.main.logic.contacts.CategoryContact;
import com.linsr.main.logic.presenter.CategoryPresenter;
import com.linsr.main.model.CategoryMenuPojo;
import com.linsr.main.widgets.MainSearchTitleLayout;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.CATEGORY)
public class CategoryFragment extends FragmentEx<CategoryPresenter>
        implements CategoryContact.View, MainModule.Fragment.CategoryDetailsParams {

    private RecyclerView mRecyclerView;
    private CategoryMenuAdapter mAdapter;
    private MainSearchTitleLayout mSearchTitleLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_category;
    }

    @Override
    protected CategoryPresenter bindPresenter() {
        return new CategoryPresenter(this);
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.category_recycler_view);
        mAdapter = new CategoryMenuAdapter(mActivity);
        RecyclerViewHelper.initDefault(mActivity, mRecyclerView, mAdapter);

        mAdapter.setOnItemClickListener(
                new BaseRecyclerAdapter.OnItemClickListener<CategoryMenuPojo.CatListsBean>() {
                    @Override
                    public void onItemClick(BaseViewHolder<CategoryMenuPojo.CatListsBean> holder,
                                            int position, int itemType,
                                            CategoryMenuPojo.CatListsBean data) {
                        replaceFragment(data);
                        mAdapter.setSelectedPosition(position);
                    }
                });

        mSearchTitleLayout = findViewById(R.id.category_search_title_layout);
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

    private void replaceFragment(CategoryMenuPojo.CatListsBean catListsBean) {
        Params params = new Params();
        params.add(CATEGORY_BEAN, catListsBean);
        Fragment fragment = Router.findFragment(MainModule.Fragment.CATEGORY_DETAILS, params);
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.category_fragment_container, fragment);
        transaction.commit();
    }

    @Override
    protected void loadData() {
        mPresenter.getCategoryList();
    }

    @Override
    public void onCategoryListSucceed(List<CategoryMenuPojo.CatListsBean> list) {
        mAdapter.addData(list);
        int selectedPosition = 0;
        mAdapter.setSelectedPosition(selectedPosition);
        replaceFragment(list.get(selectedPosition));
    }

}