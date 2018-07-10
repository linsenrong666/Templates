package com.linsr.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.linsr.common.base.BaseActivity;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.biz.EventKey;
import com.linsr.common.router.RouterCenter;
import com.linsr.common.router.url.BookModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Route(path = "/main/main")
public class MainActivity extends ActivityEx {


    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_main;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RouterCenter.startActivity(BookModule.Activity.MAIN);
            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/news/main").navigation();
            }
        });


        ViewPager viewPager = (ViewPager) findViewById(R.id.vp);

        List<Fragment> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("aa", "-1000");
        list.add(RouterCenter.findFragment(BookModule.Fragment.TEST1, map));

        Map<String, Object> map2 = new HashMap<>();
        map2.put("aa", "1000");
        list.add(RouterCenter.findFragment(BookModule.Fragment.TEST1, map2));

        adapter a = new adapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(a);


        registerOnContentUpdateListener(new AbstractOnContentUpdateListener() {
            @Override
            public void onContentUpdated(List<Object[]> values) {
                JLog.e("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            }

            @Override
            public boolean isActive() {
                return mIsActive;
            }

            @Override
            public String getKey() {
                return EventKey.aa;
            }
        });
    }

    class adapter extends FragmentPagerAdapter {

        private List<Fragment> mList;

        public adapter(FragmentManager fm, List<Fragment> mList) {
            super(fm);
            this.mList = mList;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }

}
