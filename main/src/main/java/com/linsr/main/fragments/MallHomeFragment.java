package com.linsr.main.fragments;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.adapters.HomeAdapter;
import com.linsr.main.model.HomePojo;
import com.linsr.main.utils.Mock;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/9/4 下午5:01
 */
@Route(path = MainModule.Fragment.MALL_HOME)
public class MallHomeFragment extends RefreshFragment {

    private HomeAdapter mAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new HomeAdapter(mActivity);
        List<HomePojo> findList = Mock.getFindList(10);
        RecyclerViewHelper.initGridLayout(mActivity, 6, recyclerView, mAdapter);
        mAdapter.addData(findList);
    }

}
