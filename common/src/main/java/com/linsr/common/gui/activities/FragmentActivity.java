package com.linsr.common.gui.activities;

import android.content.Intent;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.R;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.CommonModule;
import com.linsr.common.utils.ActivityUtils;
import com.linsr.common.utils.JLog;

/**
 * Description
 *
 * @author Linsr 2018/9/4 下午5:03
 */
@Route(path = CommonModule.Activity.FRAGMENT_CONTAINER)
public class FragmentActivity extends ActivityEx implements CommonModule.Activity.FragmentContainerParams {

    private int mTitleTextResId;
    private boolean mHideTitleLayout;
    private String mFragmentPath;
    private Params mBundle;

    @Override
    protected int getLayoutId() {
        return R.layout.common_activity_fragment_container;
    }

    @Override
    protected void init(Intent intent) {
        super.init(intent);
        if (intent != null) {
            mTitleTextResId = intent.getIntExtra(TITLE_TEXT, -1);
            mHideTitleLayout = intent.getBooleanExtra(HIDE_TITLE_LAYOUT, false);
            mFragmentPath = intent.getStringExtra(FRAGMENT_PATH);
            mBundle = intent.getParcelableExtra(PARAMS);
        }
    }

    @Override
    protected boolean showTitleView() {
        return !mHideTitleLayout;
    }

    @Override
    protected void initView() {
        if (!mHideTitleLayout && mTitleTextResId != -1) {
            initTitleView(mTitleTextResId);
        }

        try {
            if (!TextUtils.isEmpty(mFragmentPath)) {
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                        Router.findFragment(mFragmentPath, mBundle),
                        R.id.fragment_container);
            } else {
                JLog.e(TAG, "error: fragment path can't be null.");
            }
        } catch (Exception e) {
            JLog.e(TAG, "error: add fragment exception.");
        }
    }

}
