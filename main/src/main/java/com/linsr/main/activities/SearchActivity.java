package com.linsr.main.activities;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/8/21 下午2:47
 */
@Route(path = MainModule.Activity.SEARCH)
public class SearchActivity extends ActivityEx {
    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_search;
    }

    @Override
    protected void initView() {
        findViewById(R.id.search_title_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(MainModule.Activity.SEARCH_RESULT);
            }
        });
    }
}
