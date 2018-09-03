package com.linsr.main.activities;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/9/2 下午4:51
 */
@Route(path = MainModule.Activity.AFTER_SALES_DETAILS)
public class AfterSalesDetailsActivity extends ActivityEx {
    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_after_sales_details;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_after_sales);
    }
}
