package com.linsr.personal;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.RouterCenter;
import com.linsr.common.router.url.PersonalModule;
import com.linsr.common.utils.ActivityUtils;
import com.linsr.news.R;

public class MainActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.personal_activity_main;
    }

    @Override
    protected void initView() {
        Fragment fragment = RouterCenter.findFragment(PersonalModule.Fragment.ME);
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment,
                R.id.personal_main_fragment_container);
    }
}
