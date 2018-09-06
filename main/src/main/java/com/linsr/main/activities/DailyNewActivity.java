package com.linsr.main.activities;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.DailyNewAdapter;
import com.linsr.main.model.DailyNewPojo;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * Description
 *
 * @author Linsr 2018/9/6 下午6:20
 */
@Route(path = MainModule.Activity.DAILY_NEW)
public class DailyNewActivity extends ActivityEx {

    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private DailyNewAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_daily_new;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_daily_new);
        mRefreshLayout = findViewById(R.id.main_refresh_layout);
        mRecyclerView = findViewById(R.id.main_recycler_view);
        mAdapter = new DailyNewAdapter(this);
        RecyclerViewHelper.initDefault(this, mRecyclerView, mAdapter);
        mAdapter.addData(Mock.getList(10, DailyNewPojo.class));
    }
}