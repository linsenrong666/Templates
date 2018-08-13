package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.model.CartPojo;

/**
 * Description
 *
 * @author Linsr 2018/7/18 下午3:20
 */
public class CartAdapter extends BaseRecyclerAdapter<CartPojo> {


    public CartAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<CartPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }





}
