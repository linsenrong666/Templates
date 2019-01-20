package com.linsr.main.adapters.cart;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.CartListPojo;
import com.linsr.main.model.CartShopPojo;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午6:38
 */
public class ShopHolder extends BaseViewHolder<TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean>> {
    private CheckBox mCheckBox;
    private boolean onBind;
    private CartAdapter.CartListener mCartListener;
    private CartAdapter mCartAdapter;

    ShopHolder(Context context, CartAdapter adapter, CartAdapter.CartListener cartListener, View itemView) {
        super(context, itemView);
        mCartAdapter = adapter;
        mCartListener = cartListener;
        mCheckBox = findViewById(R.id.cart_shop_check_box);
    }

    @Override
    public void convert(final int position,
                        final TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean> data,
                        int itemType) {
        final CartShopPojo parentPojo = data.getParentPojo();
        final List<CartListPojo.GoodsListBean.ListBean> childPojo = data.getChildPojo();
        final int rangeCount = 1 + childPojo.size();

        mCheckBox.setText(data.getParentPojo().getName());
        onBind = true;
        mCheckBox.setChecked(parentPojo.isChecked());
        onBind = false;
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onBind) {
                    return;
                }
                parentPojo.setChecked(isChecked);
                for (CartListPojo.GoodsListBean.ListBean pojo : childPojo) {
                    pojo.setChecked(isChecked);
                }
                mCartAdapter.notifyItemRangeChanged(position, rangeCount);
                if (mCartListener != null) {
                    mCartListener.onDataChangeForBalance();
                }
            }
        });
    }
}