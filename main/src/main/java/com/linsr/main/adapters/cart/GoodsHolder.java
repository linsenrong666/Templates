package com.linsr.main.adapters.cart;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.widgets.CartCountView;
import com.linsr.main.R;
import com.linsr.main.model.CartGoodsPojo;
import com.linsr.main.model.CartShopPojo;

import java.util.Map;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午6:38
 */
public class GoodsHolder extends BaseViewHolder<TreePojo<CartShopPojo, CartGoodsPojo>> {

    private CheckBox mCheckBox;
    private CartCountView mCartCountView;
    private Map<String, CartAdapter.TempPojo> mCache;

    GoodsHolder(Context context, View itemView, Map<String, CartAdapter.TempPojo> cache) {
        super(context, itemView);
        mCache = cache;
        mCartCountView = findViewById(R.id.cart_goods_count_ccv);
        mCheckBox = findViewById(R.id.cart_goods_check_box);
    }

    @Override
    public void convert(int position, TreePojo<CartShopPojo, CartGoodsPojo> data, int itemType) {
        final String key = position + "";
        final CartAdapter.TempPojo tempPojo;
        if (mCache.get(key) != null) {
            tempPojo = mCache.get(key);
        } else {
            tempPojo = new CartAdapter.TempPojo();
            mCache.put(key, tempPojo);
        }

        mCheckBox.setChecked(tempPojo.selected);
        mCartCountView.setResultCount(tempPojo.count);

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tempPojo.selected = isChecked;
                mCache.put(key, tempPojo);
            }
        });
        mCartCountView.setOnCountChangedListener(new CartCountView.OnCountChangedListener() {
            @Override
            public void onChanged(int count) {
                tempPojo.count = count;
                mCache.put(key, tempPojo);
            }
        });
    }
}
