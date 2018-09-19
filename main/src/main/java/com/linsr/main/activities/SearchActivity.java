package com.linsr.main.activities;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.main.widgets.MainSearchTitleLayout;
import com.linsr.main.widgets.MainSearchTitleLayoutManager;

/**
 * Description
 *
 * @author Linsr 2018/8/21 下午2:47
 */
@Route(path = MainModule.Activity.SEARCH)
public class SearchActivity extends ActivityEx {

    private MainSearchTitleLayout mSearchTitleLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_search;
    }

    @Override
    protected boolean showTitleView() {
        return false;
    }

    @Override
    protected void initView() {
        mSearchTitleLayout = findViewById(R.id.search_title_layout);
        MainSearchTitleLayoutManager mainSearchTitleLayoutManager = new MainSearchTitleLayoutManager();
        mainSearchTitleLayoutManager.setUp(this, mSearchTitleLayout);
    }

}
