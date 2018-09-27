package com.linsr.main.activities;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.RecommendGoodsAdapter;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Description
 *
 * @author Linsr 2018/9/27 下午1:17
 */
@Route(path = MainModule.Activity.MY_COLLECT)
public class CollectActivity extends RefreshActivity {

    private RecommendGoodsAdapter mAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        initTitleView(R.string.main_my_collect);
        mAdapter = new RecommendGoodsAdapter(this);
        mAdapter.addData(Mock.getGoodsList(10));
        RecyclerViewHelper.initGridLayout(this, 2, recyclerView, mAdapter);
    }

    @Override
    protected void requestData(RefreshLayout refreshLayout) {

    }

}
