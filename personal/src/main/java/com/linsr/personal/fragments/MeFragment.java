package com.linsr.personal.fragments;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.url.PersonalModule;
import com.linsr.news.R;

/**
 * Description
 *
 * @author Linsr 2018/7/11 上午10:26
 */
@Route(path = PersonalModule.Fragment.ME)
public class MeFragment extends FragmentEx {

    @Override
    protected int getLayoutId() {
        return R.layout.personal_fragment_me;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {

    }
}
