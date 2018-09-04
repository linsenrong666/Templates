package com.linsr.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/9/4 下午5:01
 */
@Route(path = MainModule.Fragment.MALL_HOME)
public class MallHomeFragment extends RefreshFragment {

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {

    }

}
