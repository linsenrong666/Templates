package com.linsr.main.activities;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.PageLoadHelper;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.FollowShopAdapter;
import com.linsr.main.model.FollowShopPojo;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/9/26 下午6:03
 */
@Route(path = MainModule.Activity.FOLLOW_SHOP)
public class FollowShopActivity extends RefreshActivity {

    private FollowShopAdapter mAdapter;

    @Override
    protected void initData() {

    }

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        initTitleView(R.string.main_follow_shop);

        mAdapter = new FollowShopAdapter(this);
        mAdapter.setOnEventListener(new FollowShopAdapter.OnEventListener() {
            @Override
            public void onCancel(int position, FollowShopPojo data) {
                mAdapter.remove(position);
            }
        });
        RecyclerViewHelper.initDefault(this, recyclerView, mAdapter, true);
    }

    @Override
    protected void requestData(RefreshLayout refreshLayout) {
        PageLoadHelper.onCompleted(refreshLayout);

        List<FollowShopPojo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FollowShopPojo pojo = new FollowShopPojo();
            pojo.setName("item" + i);
            list.add(pojo);
        }
        mAdapter.addData(list);
    }
}
