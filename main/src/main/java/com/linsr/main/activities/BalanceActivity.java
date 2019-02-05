package com.linsr.main.activities;

import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.main.logic.contacts.BalanceContact;
import com.linsr.main.logic.presenter.BalancePresenter;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午7:04
 */
@Route(path = MainModule.Activity.BALANCE)
public class BalanceActivity extends ActivityEx<BalancePresenter> implements BalanceContact.View {

    private RelativeLayout mAddressLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_balance;
    }

    @Override
    protected BalancePresenter bindPresenter() {
        return new BalancePresenter(this);
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_confirm_message);

        mAddressLayout = findViewById(R.id.balance_address_layout);
        mAddressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(MainModule.Activity.ADDRESS_LIST);
            }
        });

        findViewById(R.id.balance_go_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(MainModule.Activity.PAY_RESULT);
            }
        });

        mPresenter.checkOut();
    }
}
