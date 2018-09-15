package com.linsr.main.activities;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/9/14 下午3:02
 */
@Route(path = MainModule.Activity.AUCTION_DETAILS)
public class AuctionDetailsActivity extends ActivityEx {

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_auction_details;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_auction_channel);
    }
}
