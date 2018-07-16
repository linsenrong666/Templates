package com.linsr.main.activities;

import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.RouterCenter;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/7/16 下午2:45
 */
public class SplashScreenActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_splash_screen;
    }

    @Override
    protected void initView() {
        getRootContent().postDelayed(new Runnable() {
            @Override
            public void run() {
                RouterCenter.startActivity(MainModule.Activity.MAIN);
            }
        }, 2000);
    }
}
