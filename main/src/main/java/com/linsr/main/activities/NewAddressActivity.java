package com.linsr.main.activities;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.gui.widgets.common_label_container;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.main.data.remote.UserRequest;
import com.linsr.main.model.AddressPojo;
//import com.yiguo.adressselectorlib.AddressSelector;
//import com.yiguo.adressselectorlib.CityInterface;
//import com.yiguo.adressselectorlib.OnItemClickListener;

/**
 * Description
 *
 * @author Linsr 2018/8/21 上午10:29
 */
@Route(path = MainModule.Activity.ADD_ADDRESS)
public class NewAddressActivity extends ActivityEx implements MainModule.Activity.AddAddressParams {

    private common_label_container mName, mPhone, mStreet, mCode;
//    private AddressSelector mAddressSelector;
    private String mAddressId;
    private CheckBox mCheckBox;

    @Override
    protected void init(Intent intent) {
        super.init(intent);
        if (intent != null) {
            mAddressId = intent.getStringExtra(ACT_ADDRESSI_ID);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_add_address;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_title_new_address);
        findView();
    }

    private void findView() {
        mCheckBox = findViewById(R.id.new_address_default_cb);
        mName = findViewById(R.id.new_address_name);
        mPhone = findViewById(R.id.new_address_phone);
        mStreet = findViewById(R.id.new_address_street);
        mCode = findViewById(R.id.new_address_code);
//        mAddressSelector = findViewById(R.id.new_address_selector);
//        mAddressSelector.setTabAmount(3);
//        mAddressSelector.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void itemClick(AddressSelector addressSelector, CityInterface cityInterface, int i) {
//
//            }
//        });
//        mAddressSelector.setOnTabSelectedListener(new AddressSelector.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(AddressSelector addressSelector, AddressSelector.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(AddressSelector addressSelector, AddressSelector.Tab tab) {
//
//            }
//        });
        findViewById(R.id.new_address_save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void confirm() {
        String name = mName.getEditContentText();
        String phone = mPhone.getEditContentText();
        String street = mStreet.getEditContentText();
        String code = mCode.getEditContentText();
        boolean checked = mCheckBox.isChecked();
        AddressPojo pojo = new AddressPojo();

        UserRequest.editAddress(this, pojo,
                new NetObserver<BizPojo>(this, true, true) {
                    @Override
                    public void onSucceed(BizPojo data) {

                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }
}
