package com.linsr.main.activities;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.biz.config.AppConfig;
import com.linsr.common.gui.dialogs.DialogFactory;
import com.linsr.common.gui.widgets.common_label_container;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.ToastUtils;
import com.linsr.main.R;
import com.linsr.main.data.remote.UserRequest;
import com.linsr.main.dialogs.PickAddressDialog;
import com.linsr.main.model.AddressPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/21 上午10:29
 */
@Route(path = MainModule.Activity.ADD_ADDRESS)
public class NewAddressActivity extends ActivityEx implements MainModule.Activity.AddAddressParams {

    private common_label_container mName, mPhone, mStreet, mCode, mAddress;
    private String mAddressId;
    private CheckBox mCheckBox;
    private PickAddressDialog mDialog;
    private AddressPojo mAddressPojo;

    @Override
    protected void init(Intent intent) {
        super.init(intent);
        if (intent != null) {
            mAddressId = intent.getStringExtra(ACT_ADDRESS_ID);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_add_address;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_title_new_address);
        mAddressPojo = new AddressPojo();
        findView();
    }

    private void findView() {
        mCheckBox = findViewById(R.id.new_address_default_cb);
        mName = findViewById(R.id.new_address_name);
        mPhone = findViewById(R.id.new_address_phone);
        mStreet = findViewById(R.id.new_address_street);
        mCode = findViewById(R.id.new_address_code);
        mAddress = findViewById(R.id.new_address_content);

        mAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialog == null) {
                    mDialog = PickAddressDialog.newInstance();
                    mDialog.setOnAddressPickListener(new PickAddressDialog.OnAddressPickListener() {
                        @Override
                        public void onPick(String province, String city, String district) {
                            mAddressPojo.setProvince_str(province);
                            mAddressPojo.setCity_str(city);
                            mAddressPojo.setDistrict_str(district);

                            mAddress.setContentText(province + "  " + city + "  " + district);
                            DialogFactory.getInstance().dismissDialog(mDialog);
                        }
                    });
                }
                DialogFactory.getInstance().showDialog(getFragmentManager(), mDialog);
            }
        });
        findViewById(R.id.new_address_save_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
    }

    private void confirm() {
        String name = mName.getEditContentText();
        String phone = mPhone.getEditContentText();
        String street = mStreet.getEditContentText();
        String code = mCode.getEditContentText();
        boolean checked = mCheckBox.isChecked();

        mAddressPojo.setConsignee(name);
        mAddressPojo.setMobile(phone);
        mAddressPojo.setAddress(street);
        mAddressPojo.setZipcode(code);
        mAddressPojo.setIs_default(checked ? "1" : "0");
        mAddressPojo.setUser_id(AppConfig.getInstance().getUserId());

        UserRequest.editAddress(this, mAddressPojo,
                new NetObserver<BizPojo>(this, true, true) {
                    @Override
                    public void onSucceed(BizPojo data) {
                        if (data != null) {
                            ToastUtils.show(data.getMessage());
                            finish();
                        }
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }
}
