package com.linsr.main.activities;

import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/8/21 下午2:47
 */
@Route(path = MainModule.Activity.SEARCH_RESULT)
public class SearchResultActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_search_result;
    }

    @Override
    protected void init(Intent intent) {
        super.init(intent);
    }

    @Override
    protected void initView() {

    }
}
