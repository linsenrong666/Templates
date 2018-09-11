package com.linsr.main.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;

/**
 * 商品详情页
 *
 * @author Linsr 2018/8/6 下午3:10
 */
@Route(path = MainModule.Activity.PRODUCT_DETAILS)
public class ProductDetailsActivity extends ActivityEx {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_product_details;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_goods_details);
        findView();
        initViewPager();
    }

    private void initViewPager() {

    }

    private void findView() {
        mViewPager = findViewById(R.id.product_details_view_pager);
        mTabLayout = findViewById(R.id.product_details_tab_layout);
    }

}
