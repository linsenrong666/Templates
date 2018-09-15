package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.gui.widgets.CategoryTitle;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.RecommendAdapter;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

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
        RecyclerViewHelper.initGridLayout(mContext, 2, mRecyclerView, mAdapter);
    }

}
