package com.linsr.main.activities;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.AfterSalesAdapter;
import com.linsr.main.model.AfterSalesPojo;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/9/2 下午3:35
 */
@Route(path = MainModule.Activity.AFTER_SALES)
public class AfterSalesActivity extends RefreshActivity {

    private AfterSalesAdapter mAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        initTitleView(R.string.main_after_sales);
        mAdapter = new AfterSalesAdapter(this);
        mAdapter.setOnAfterSalesListener(new AfterSalesAdapter.OnAfterSalesListener() {
            @Override
            public void onBtnClick(AfterSalesPojo pojo) {
                Router.startActivity(MainModule.Activity.AFTER_SALES_DETAILS);
            }
        });
        RecyclerViewHelper.initDefault(this, recyclerView, mAdapter);
        mAdapter.addData(Mock.getList(10, AfterSalesPojo.class));
    }

    @Override
    protected void requestData() {

    }

}
