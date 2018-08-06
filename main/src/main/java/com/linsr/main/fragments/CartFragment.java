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
 * @author Linsr 2018/7/12 下午4:41
 */
@Route(path = MainModule.Fragment.CART)
public class CartFragment extends FragmentEx {

    private RecyclerView mRecyclerView;


    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_cart;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {

    }
}