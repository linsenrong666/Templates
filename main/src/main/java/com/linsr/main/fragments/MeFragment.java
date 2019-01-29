package com.linsr.main.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.AppLife;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.biz.config.AppConfig;
import com.linsr.common.gui.widgets.common_label_container;
import com.linsr.common.router.Flags;
import com.linsr.common.router.Params;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.LoginModule;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.ActivityUtils;
import com.linsr.common.utils.ViewUtils;
import com.linsr.main.R;
import com.linsr.main.logic.contacts.MeContact;
import com.linsr.main.logic.presenter.MePresenter;
import com.linsr.main.model.UserPojo;
import com.linsr.main.widgets.MeTopView;
import com.linsr.main.widgets.meitem.MainMeItemView;
import com.linsr.main.widgets.meitem.MeItemPojo;


/**
 * Description
 *
 * @author Linsr 2018/7/11 上午10:26
 */
@Route(path = MainModule.Fragment.ME)
public class MeFragment extends FragmentEx<MePresenter> implements MainModule.Activity,
        MeContact.View {

    private MeTopView mMeTopView;
    private TextView mRecNameTextView;
    private MainMeItemView mMainMeItemView;
    private common_label_container mFollowShop;
    private common_label_container mCollect;
    private common_label_container mAddressManage;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_me;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected MePresenter bindPresenter() {
        return new MePresenter(this);
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
        mRecNameTextView = findViewById(R.id.me_rec_name_tv);
        mMainMeItemView = findViewById(R.id.me_order_item);
        mMainMeItemView.setOnMeItemClickListener(new MainMeItemView.OnMeItemClickListener() {
            @Override
            public void onRightViewClick() {
                Router.startActivity(ORDER_MAIN);
            }

            @Override
            public void onItemClick(int position, MeItemPojo data) {
                if (position == 4) {
                    Router.startActivity(MainModule.Activity.AFTER_SALES);
                } else {
                    Params params = new Params();
                    params.put(MainModule.Activity.OrderMainParams.ORDER_STATUS, position + 1);
                    Router.startActivity(ORDER_MAIN, params);
                }
            }
        });

        mFollowShop = findViewById(R.id.me_follow_shop);
        mFollowShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(MainModule.Activity.FOLLOW_SHOP);
            }
        });
        mCollect = findViewById(R.id.me_collect);
        mCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(MainModule.Activity.MY_COLLECT);
            }
        });
        mAddressManage = findViewById(R.id.me_address_manage);
        mAddressManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(MainModule.Activity.ADDRESS_LIST);
            }
        });

        findViewById(R.id.me_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.logout();
            }
        });
    }

    @Override
    protected void loadData() {
        mPresenter.userCenter();
    }

    @Override
    public void fillMeTopData(UserPojo pojo) {
        mMeTopView.fillData(pojo);
    }

    @Override
    public void fillRecName(String name) {
        ViewUtils.setText(mRecNameTextView, getString(R.string.main_recommend_user_name, name));
    }

    @Override
    public void logout() {
        AppConfig.getInstance().logout();
        AppLife.getInstance().exit();
        Router.startActivity(LoginModule.Activity.LOGIN);
    }
}
