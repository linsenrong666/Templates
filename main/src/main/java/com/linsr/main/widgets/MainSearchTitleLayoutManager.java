package com.linsr.main.widgets;

import android.app.Activity;

import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;

/**
 * Description
 *
 * @author Linsr 2018/9/18 下午3:58
 */
public class MainSearchTitleLayoutManager implements MainSearchTitleLayout.OnEventListener {

    public static final int PAGE_TYPE_DEFAULT = 0;
    public static final int PAGE_TYPE_HOME = 1;

    private Activity mActivity;
    private MainSearchTitleLayout mSearchTitleLayout;
    private int mPageType;

    public void setUp(Activity activity, MainSearchTitleLayout searchTitleLayout, int pageType) {
        mActivity = activity;
        mPageType = pageType;
        mSearchTitleLayout = searchTitleLayout;
        mSearchTitleLayout.setOnEventListener(this);
    }

    public void setUp(Activity activity, MainSearchTitleLayout searchTitleLayout) {
        this.setUp(activity, searchTitleLayout, PAGE_TYPE_DEFAULT);
    }

    @Override
    public void onSearchClick(String text) {
        switch (mPageType) {
            default:
                Router.startActivity(MainModule.Activity.SEARCH_RESULT);
                break;
        }
    }

    @Override
    public void onEditClick() {
        switch (mPageType) {
            default:
                Router.startActivity(MainModule.Activity.SEARCH);
                break;
        }
    }

    @Override
    public void onLeftImageClick() {
        switch (mPageType) {
            case PAGE_TYPE_HOME:
                break;
            default:
                mActivity.onBackPressed();
                break;
        }
    }

    @Override
    public void onRightImageClick() {

    }

}