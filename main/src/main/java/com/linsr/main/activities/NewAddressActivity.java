package com.linsr.main.activities;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/8/21 上午10:29
 */
@Route(path = MainModule.Activity.ADD_ADDRESS)
public class NewAddressActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_add_address;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_title_new_address);

    }
}
