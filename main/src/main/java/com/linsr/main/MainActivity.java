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
import com.linsr.common.router.RouterCenter;
import com.linsr.common.router.url.BookModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;

import java.util.List;

@Route(path = "/main/main")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_main);
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
        adapter a = new adapter(getSupportFragmentManager());
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
                return "aa";
            }
        });
    }

    class adapter extends FragmentPagerAdapter {

        public adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = (Fragment) ARouter.getInstance().build("/books/booksFragment").navigation();
            return fragment;
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

}
