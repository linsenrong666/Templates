package com.linsr.main.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.FragmentPagerAdapterEx;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.gui.dialogs.DialogFactory;
import com.linsr.common.gui.widgets.FlipperView;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.CommonModule;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.ToastUtils;
import com.linsr.common.utils.ViewUtils;
import com.linsr.main.R;
import com.linsr.main.adapters.GoodsBannerAdapter;
import com.linsr.main.app.Constants;
import com.linsr.main.dialogs.AddCartDialog;
import com.linsr.main.logic.UIHelper;
import com.linsr.main.logic.contacts.ProductDetailsContact;
import com.linsr.main.logic.presenter.ProductDetailsPresenter;
import com.linsr.main.model.ProductDetailsPojo;
import com.linsr.main.utils.PriceUtils;
import com.linsr.main.widgets.ProductDetailsBottomBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.linsr.common.router.url.MainModule.Activity.MainParams.CART_PAGE;
import static com.linsr.common.router.url.MainModule.Activity.MainParams.HOME_PAGE;

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
    private TextView mGoodsNameTextView;
    private TextView mGoodsDescTextView;
    private TextView mPriceTextView;
    private TextView mIntegralPriceTextView;
    private TextView mBrokeragePriceTextView;
    private ProductDetailsBottomBar mBottomBar;

    private AddCartDialog mAddCartDialog;

    private String mGoodsId;
    private ProductDetailsPojo.GoodsBean mGoodsBean;
    private List mSpec;

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
//        initViewPager();暂时不用
        setListener();
        requestData();
    }

    private void setListener() {
        mBottomBar.setOnCollectClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.collect(mGoodsId);
            }
        });
        mBottomBar.setOnShopClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.toMainActivity(HOME_PAGE);
            }
        });
        mBottomBar.setAddCartClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddCartDialog == null) {
                    mAddCartDialog = AddCartDialog.newInstance(mGoodsBean, mSpec);
                    mAddCartDialog.setOnAddCartClickListener(new AddCartDialog.OnAddCartClickListener() {
                        @Override
                        public void onConfirm(int number) {
                            mPresenter.addCart(null, 0, mGoodsId, number);
                        }
                    });
                }
                DialogFactory.getInstance().showDialog(getFragmentManager(), "", mAddCartDialog);
            }
        });
        mBottomBar.setOnBuyClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.buy(null, 0, mGoodsId, 1);
            }
        });
        mBottomBar.setOnCartClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.toMainActivity(CART_PAGE);
            }
        });
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
        mGoodsNameTextView = findViewById(R.id.product_details_goods_name_tv);
        mGoodsDescTextView = findViewById(R.id.product_details_goods_desc_tv);
        mPriceTextView = findViewById(R.id.product_details_price_tv);
        mIntegralPriceTextView = findViewById(R.id.product_details_integral_tv);
        mBrokeragePriceTextView = findViewById(R.id.product_details_brokerage_tv);
        mBottomBar = findViewById(R.id.product_details_bottom_bar);
    }

    private void requestData() {
        mPresenter.getGoodsInfo(mGoodsId);
    }

    @Override
    public void loadGoodsInfo(ProductDetailsPojo.GoodsBean pojo) {
        mGoodsBean = pojo;

        mGoodsNameTextView.setText(pojo.getGoods_name());
        mGoodsDescTextView.setText(pojo.getGoods_desc_bubaohan());
        ViewUtils.setText(mPriceTextView, PriceUtils.origStyle(pojo.getShop_price(), pojo.getMarket_price()));
        ViewUtils.setText(mIntegralPriceTextView, "积分:" + pojo.getGive_integral());
        ViewUtils.setText(mBrokeragePriceTextView, "佣金:" + pojo.getGoods_integral());
    }

    @Override
    public void loadPictures(List<ProductDetailsPojo.PicturesBean> list) {
        GoodsBannerAdapter mBannerPagerAdapter = new GoodsBannerAdapter(this, list);
        mFlipperView.setPageAdapter(mBannerPagerAdapter);
    }

    @Override
    public void loadSpec(List spec) {
        mSpec = spec;
    }

    @Override
    public void onAddCartSuccess() {
        ToastUtils.show("已加入购物车");
        DialogFactory.getInstance().dismissDialog(mAddCartDialog);
        mContentsManager.notifyContentUpdateSuccess(Constants.Event.UPDATE_CART_LIST);
    }

    @Override
    public void onBuySuccess() {
        mContentsManager.notifyContentUpdateSuccess(Constants.Event.UPDATE_CART_LIST);
        UIHelper.toMainActivity(CART_PAGE);
    }

}
