package com.linsr.main.activities;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.ActivityUtils;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/8/14 上午10:36
 */
@Route(path = MainModule.Activity.CHILD_CATEGORY)
public class ChildCategoryActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.common_activity_fragment_container;
    }

    @Override
    protected void initView() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                Router.findFragment(MainModule.Fragment.CHILD_CATEGORY),
                R.id.fragment_container);
    }

    @Override
    protected boolean showTitleView() {
        return false;
    }
}
