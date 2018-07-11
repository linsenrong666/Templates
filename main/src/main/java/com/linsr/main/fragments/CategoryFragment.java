package com.linsr.main.fragments;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;
import com.linsr.main.contacts.HomeContact;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.CATEGORY)
public class CategoryFragment extends FragmentEx implements HomeContact.View {

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        findViewById(R.id.text).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

}
