package com.linsr.main.adapters.cart;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.CartGoodsPojo;
import com.linsr.main.model.CartShopPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午6:38
 */
public  class ShopHolder extends BaseViewHolder<TreePojo<CartShopPojo, CartGoodsPojo>> {

    private TextView name;

    ShopHolder(Context context, View itemView) {
        super(context, itemView);
        name = findViewById(R.id.cart_shop_name_tv);
    }

    @Override
    public void convert(int position,
                        TreePojo<CartShopPojo, CartGoodsPojo> data,
                        int itemType) {
        name.setText(data.getParentPojo().getName());
    }
}