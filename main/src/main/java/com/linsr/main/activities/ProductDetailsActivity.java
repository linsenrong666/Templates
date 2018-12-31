package com.linsr.main.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.FragmentPagerAdapterEx;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.gui.widgets.FlipperView;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.CommonModule;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.main.adapters.BannerPagerAdapter;
import com.linsr.main.adapters.GoodsBannerAdapter;
import com.linsr.main.logic.contacts.ProductDetailsContact;
import com.linsr.main.logic.presenter.ProductDetailsPresenter;
import com.linsr.main.model.ProductDetailsPojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 商品详情页
 *
 * @author Linsr 2018/8/6 下午3:10
 */
@Route(path = MainModule.Activity.PRODUCT_DETAILS)
public class ProductDetailsActivity extends ActivityEx<ProductDetailsPresenter> implements
        ProductDetailsContact.View, MainModule.Activity.ProductDetailsParams {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private FlipperView mFlipperView;

    private GoodsBannerAdapter mBannerPagerAdapter;

    private String mGoodsId;

    @Override
    protected void init(Intent intent) {
        super.init(intent);
        if (intent != null) {
            mGoodsId = intent.getStringExtra(GOODS_ID);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_product_details;
    }

    @Override
    protected ProductDetailsPresenter bindPresenter() {
        return new ProductDetailsPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_goods_details);
        findView();
        initViewPager();
        initFlipperView();
        requestData();
    }

    private void initFlipperView() {

    }

    private void initViewPager() {
        String[] titles = getResources().getStringArray(R.array.main_product_details_titles);
        List<Fragment> list = new ArrayList<>();
        Params params = new Params();
        params.put(CommonModule.Fragment.WebViewParams.URL, "https://www.baidu.com");
        list.add(Router.findFragment(CommonModule.Fragment.WEB_VIEW, params));
        list.add(Router.findFragment(CommonModule.Fragment.WEB_VIEW, params));
        list.add(Router.findFragment(CommonModule.Fragment.WEB_VIEW, params));
        FragmentPagerAdapterEx fragmentPagerAdapterEx = new FragmentPagerAdapterEx(getSupportFragmentManager(), list);
        fragmentPagerAdapterEx.setTitles(Arrays.asList(titles));
        mViewPager.setOffscreenPageLimit(list.size());

        mViewPager.setAdapter(fragmentPagerAdapterEx);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void findView() {
        mViewPager = findViewById(R.id.product_details_view_pager);
        mTabLayout = findViewById(R.id.product_details_tab_layout);
        mFlipperView = findViewById(R.id.product_details_flipper_view);
    }

    private void requestData() {
        mPresenter.getGoodsInfo(mGoodsId);
    }

    @Override
    public void loadGoodsInfo(ProductDetailsPojo pojo) {

    }

    @Override
    public void loadPictures(List<ProductDetailsPojo.PicturesBean> list) {
        mBannerPagerAdapter = new GoodsBannerAdapter(this, list);
        mFlipperView.setPageAdapter(mBannerPagerAdapter);
    }
}
