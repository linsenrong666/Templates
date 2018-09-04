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
 * @author Linsr 2018/9/4 下午5:03
 */
@Route(path = MainModule.Activity.FRAGMENT_CONTAINER)
public class FragmentActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_fragment_container;
    }

    @Override
    protected void initView() {
//        initTitleView(R.string.main_recommend_for_you);
//
//        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
//                Router.findFragment(MainModule.Fragment.CHILD_CATEGORY),
//                R.id.fragment_container);
    }

}
