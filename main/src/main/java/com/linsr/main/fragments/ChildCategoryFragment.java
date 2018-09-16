package com.linsr.main.fragments;


import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.model.RecommendPojo;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/8/14 上午11:11
 */
@Route(path = MainModule.Fragment.CHILD_CATEGORY)
public class ChildCategoryFragment extends RefreshFragment {

    private RecommendAdapter mAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new RecommendAdapter(mContext);
        mAdapter.addData(Mock.getRecommendList(21));
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<RecommendPojo>() {

            @Override
            public void onItemClick(BaseViewHolder<RecommendPojo> holder, int position,
                                    int itemType, RecommendPojo data) {
                Router.startActivity(MainModule.Activity.PRODUCT_DETAILS);
            }
        });
        RecyclerViewHelper.initGridLayout(mContext, 2, mRecyclerView, mAdapter);
    }

}
