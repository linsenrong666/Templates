package com.linsr.main.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.TabLayoutHelper;
import com.linsr.main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/9/6 上午10:24
 */
@Route(path = MainModule.Activity.FLASH_SALE)
public class FlashSaleActivity extends ActivityEx {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_flash_sale;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_flash_sales);
        mTabLayout = findViewById(R.id.flash_sale_tab_layout);
        mViewPager = findViewById(R.id.flash_sale_view_pager);

        initFragments();
    }

    private void initFragments() {
        List<String> titleList = new ArrayList<>();
        List<TextView> textViews = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String item = "16日" + i + "点";
            if (i == 0) {
                item = item + "\n抢购中";
            } else {
                item = item + "\n即将开始";
            }
            TextView textView = new TextView(this);
            textView.setGravity(Gravity.CENTER);

            textViews.add(textView);
            fragmentList.add(Router.findFragment(MainModule.Fragment.FLASH_SALE));
            titleList.add(item);
        }
        TabLayoutHelper.initPagerFragment(this, mViewPager, mTabLayout,
                getSupportFragmentManager(), textViews, fragmentList, titleList);
    }
}
