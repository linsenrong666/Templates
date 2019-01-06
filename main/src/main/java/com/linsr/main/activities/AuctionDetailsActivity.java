package com.linsr.main.activities;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.gui.widgets.FlipperView;
import com.linsr.common.gui.widgets.recyclerview.HeaderAndFooterWrapper;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.AuctionRecordAdapter;
import com.linsr.main.adapters.BannerPagerAdapter;
import com.linsr.main.model.AuctionRecordPojo;
import com.linsr.main.utils.Mock;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/9/14 下午3:02
 */
@Route(path = MainModule.Activity.AUCTION_DETAILS)
public class AuctionDetailsActivity extends ActivityEx {

    private static final int INIT_COUNT = 5;
    private static final int MAX_COUNT = 10;

    private RecyclerView mRecyclerView;
    private AuctionRecordAdapter mAdapter;
    private FlipperView mFlipperView;


    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_auction_details;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_auction_channel);
        mFlipperView = findViewById(R.id.auction_details_flipper_view);
        mRecyclerView = findViewById(R.id.auction_details_recycler_view);

        initFlipperView();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mAdapter = new AuctionRecordAdapter(this);
        final List<AuctionRecordPojo> list = Mock.getList(10, AuctionRecordPojo.class);
        mAdapter.addData(list, INIT_COUNT);

        final HeaderAndFooterWrapper wrapper = new HeaderAndFooterWrapper(mAdapter);
        TextView textView = (TextView) LayoutInflater.from(this)
                .inflate(R.layout.main_layout_text_view, getRootContent(), false);
        textView.setText(getString(R.string.main_auction_show_last_record));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter.getItemCount() > INIT_COUNT) {
                    mAdapter.addData(list, INIT_COUNT);
                } else {
                    mAdapter.addData(list, MAX_COUNT);
                }
            }
        });
        wrapper.addFootView(textView);

        RecyclerViewHelper.initNestScrollView(this, mRecyclerView, wrapper, true);
    }

    private void initFlipperView() {
//        int[] res = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner3};
//        BannerPagerAdapter pagerAdapter = new BannerPagerAdapter(this, res);
//        mFlipperView.setPageAdapter(pagerAdapter);
    }

}
