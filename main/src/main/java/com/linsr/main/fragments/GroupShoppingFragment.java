package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.GroupShoppingAdapter;
import com.linsr.main.model.GroupShoppingPojo;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/9/14 下午4:54
 */
@Route(path = MainModule.Fragment.GROUP_SHOPPING)
public class GroupShoppingFragment extends RefreshFragment {

    private GroupShoppingAdapter mAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new GroupShoppingAdapter(mActivity);
        mAdapter.addData(Mock.getList(10, GroupShoppingPojo.class));
        RecyclerViewHelper.initDefault(mActivity,recyclerView,mAdapter,true);
    }
}
