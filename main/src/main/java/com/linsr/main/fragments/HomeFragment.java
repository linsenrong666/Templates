package com.linsr.main.fragments;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.EventKey;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.RecyclerViewHepler;
import com.linsr.main.R;
import com.linsr.main.adapters.GoodsAdapter;
import com.linsr.main.contacts.HomeContact;
import com.linsr.main.model.HomePojo;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.HOME)
public class HomeFragment extends FragmentEx implements HomeContact.View {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private GoodsAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.main_recycler_view);
        mRefreshLayout = findViewById(R.id.main_refresh_layout);
        mAdapter = new GoodsAdapter(mActivity);
        RecyclerViewHepler.initDefault(mActivity, mRecyclerView, mAdapter);
        mAdapter.setOnGoodsClickListener(new GoodsAdapter.OnGoodsClickListener() {
            @Override
            public void onAdd(int position) {
                JLog.e(TAG, "on add position :" + position);
                mContentsManager.notifyContentUpdateSuccess(EventKey.ADD_GOODS_CART);
            }
        });
    }

    @Override
    protected void loadData() {
        super.loadData();
        List<HomePojo> goodsList = Mock.getGoodsList(20);
        mAdapter.addData(goodsList);
    }
}
