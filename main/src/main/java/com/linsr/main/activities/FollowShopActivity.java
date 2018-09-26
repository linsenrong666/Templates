package com.linsr.main.activities;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.adapters.FollowShopAdapter;
import com.linsr.main.model.FollowShopPojo;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/9/26 下午6:03
 */
@Route(path = MainModule.Activity.FOLLOW_SHOP)
public class FollowShopActivity extends RefreshActivity {

    private FollowShopAdapter mAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new FollowShopAdapter(this);
        mAdapter.addData(Mock.getList(10, FollowShopPojo.class));
        RecyclerViewHelper.initDefault(this, recyclerView, mAdapter);
    }

    @Override
    protected void requestData() {
        mAdapter.addData(Mock.getList(10, FollowShopPojo.class));
    }
}
