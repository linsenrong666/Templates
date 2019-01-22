package com.linsr.main.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.FragmentPagerAdapterEx;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.model.ResponsePojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.exception.ApiException;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.main.data.remote.IndexRequest;
import com.linsr.main.model.CategoryMenuPojo;
import com.linsr.main.model.ChildCategoryPojo;
import com.linsr.main.widgets.MainSearchTitleLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/14 上午10:36
 */
@Route(path = MainModule.Activity.CHILD_CATEGORY)
public class ChildCategoryActivity extends ActivityEx implements
        MainModule.Activity.ChildCategoryParams, MainModule.Fragment.ChildCategoryParams {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private MainSearchTitleLayout mSearchTitleLayout;

    private static final int MAX_CACHE_FRAGMENT_COUNT = 10;
    private int mCurrentPosition;
    private String mFid;
    private String mSid;

    @Override
    protected void init(Intent intent) {
        super.init(intent);
        if (intent != null) {
            mCurrentPosition = intent.getIntExtra(ENTER_POSITION, 0);
            mFid = intent.getStringExtra(FIRST_CATEGORY_ID);
            mSid = intent.getStringExtra(SECOND_CATEGORY_ID);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_child_category;
    }

    @Override
    protected void initView() {
        mTabLayout = findViewById(R.id.child_category_tab_layout);
        mViewPager = findViewById(R.id.child_category_view_pager);
        mSearchTitleLayout = findViewById(R.id.child_category_search_layout);
        mSearchTitleLayout.setOnEventListener(new MainSearchTitleLayout.OnEventListener() {
            @Override
            public void onSearchClick(String text) {

            }

            @Override
            public void onEditClick() {
                Router.startActivity(MainModule.Activity.SEARCH);
            }

            @Override
            public void onLeftImageClick() {
                back();
            }

            @Override
            public void onRightImageClick() {

            }
        });
        requestData();
    }

    @Override
    protected boolean showTitleView() {
        return false;
    }

    public void requestData() {
        IndexRequest.childCategoryList(this, mFid, mSid,
                new NetObserver<ChildCategoryPojo>(this, true, true) {
                    @Override
                    public void onSucceed(ChildCategoryPojo data) {
                        if (data == null) {
                            onFailed(new ApiException(""));
                            return;
                        }
                        List<ChildCategoryPojo.CatListBean> cat_list = data.getCat_list();
                        onSuccess(cat_list);
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        showNoData();
                    }
                });
    }

    private void onSuccess(List<ChildCategoryPojo.CatListBean> catListBeans) {
        List<String> titles = new ArrayList<>();
        List<Fragment> list = new ArrayList<>();
        for (ChildCategoryPojo.CatListBean pojo : catListBeans) {
            titles.add(pojo.getCat_name());
            Params params = new Params();
            params.add(FID, mFid).add(SID, pojo.getCat_id());
            list.add(Router.findFragment(MainModule.Fragment.CHILD_CATEGORY, params));
        }
        FragmentPagerAdapterEx adapterEx = new FragmentPagerAdapterEx(getSupportFragmentManager(), list);
        adapterEx.setTitles(titles);
        mViewPager.setAdapter(adapterEx);

        if (catListBeans.size() < MAX_CACHE_FRAGMENT_COUNT) {
            mViewPager.setOffscreenPageLimit(catListBeans.size());
        } else {
            mViewPager.setOffscreenPageLimit(MAX_CACHE_FRAGMENT_COUNT);
        }

        if (mCurrentPosition <= catListBeans.size()) {
            mViewPager.setCurrentItem(mCurrentPosition);
        }
        mTabLayout.setupWithViewPager(mViewPager);
    }

}
