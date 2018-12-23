package com.linsr.main.activities;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.main.widgets.MainSearchTitleLayout;

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
        mSearchTitleLayout.setOnEventListener(new MainSearchTitleLayout.OnEventListener() {
            @Override
            public void onSearchClick(String text) {
                Params params = new Params();
                params.add(MainModule.Activity.SearchResultParams.KEYWORD, text);
                Router.startActivity(MainModule.Activity.SEARCH_RESULT, params);
            }

            @Override
            public void onEditClick() {

            }

            @Override
            public void onLeftImageClick() {
                back();
            }

            @Override
            public void onRightImageClick() {

            }
        });
    }

}
