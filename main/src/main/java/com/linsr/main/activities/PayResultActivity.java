package com.linsr.main.activities;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/8/22 下午3:21
 */
@Route(path = MainModule.Activity.PAY_RESULT)
public class PayResultActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_pay_result;
    }

    @Override
    protected void initView() {

    }
}
