package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.gui.widgets.recyclerview.EmptyWrapper;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.CategoryDetailsAdapter;
import com.linsr.main.model.CategoryMenuPojo;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/12 上午10:49
 */
@Route(path = MainModule.Fragment.CATEGORY_DETAILS)
public class CategoryDetailsFragment extends FragmentEx implements
        MainModule.Activity.ChildCategoryParams,
        MainModule.Fragment.CategoryDetailsParams {

    private CategoryMenuPojo.CatListsBean mCatListsBean;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_category_details;
    }

    @Override
    protected void initArguments(Bundle arguments) {
        mCatListsBean = (CategoryMenuPojo.CatListsBean) arguments.getSerializable(CATEGORY_BEAN);
    }

    @Override
    protected void initView() {
        if (mCatListsBean == null) {
            return;
        }
        RecyclerView mRecyclerView = findViewById(R.id.category_details_recycler_view);
        CategoryDetailsAdapter mAdapter = new CategoryDetailsAdapter(mActivity);
        mAdapter.addData(mCatListsBean.getKids());
        EmptyWrapper wrapper = new EmptyWrapper(mAdapter);
        RecyclerViewHelper.initGridLayout(mActivity, 3, mRecyclerView, wrapper);

        mAdapter.setOnItemClickListener(
                new BaseRecyclerAdapter.OnItemClickListener<CategoryMenuPojo.CatListsBean.KidsBean>() {
                    @Override
                    public void onItemClick(BaseViewHolder<CategoryMenuPojo.CatListsBean.KidsBean> holder,
                                            int position, int itemType,
                                            CategoryMenuPojo.CatListsBean.KidsBean data) {
                        Params params = new Params();
                        params.add(ENTER_POSITION, position);
                        params.add(FIRST_CATEGORY_ID, mCatListsBean.getCat_id());
                        params.add(SECOND_CATEGORY_ID, data.getCat_id());
                        Router.startActivity(MainModule.Activity.CHILD_CATEGORY, params);
                    }
                });
    }

}