package com.linsr.main.fragments;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.main.widgets.MeTopView;
import com.linsr.main.widgets.meitem.MainMeItemView;
import com.linsr.main.widgets.meitem.MeItemPojo;

/**
 * Description
 *
 * @author Linsr 2018/7/11 上午10:26
 */
@Route(path = MainModule.Fragment.ME)
public class MeFragment extends FragmentEx {

    private MeTopView mMeTopView;
    private MainMeItemView mMainMeItemView;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_me;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mMeTopView = findViewById(R.id.me_top_view);
        mMeTopView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(MainModule.Activity.PAY_RESULT);
            }
        });

        mMainMeItemView = findViewById(R.id.me_order_item);
        mMainMeItemView.setOnMeItemClickListener(new MainMeItemView.OnMeItemClickListener() {
            @Override
            public void onRightViewClick() {
                Router.startActivity(MainModule.Activity.ORDER_MAIN);
            }

            @Override
            public void onItemClick(int position, MeItemPojo data) {

            }
        });
    }
}
