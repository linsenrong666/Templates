//package com.linsr.main.adapters.cart;
//
//import android.content.Context;
//import android.view.View;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.TextView;
//
//import com.linsr.common.base.adapter.BaseViewHolder;
//import com.linsr.main.R;
//import com.linsr.main.model.CartGoodsPojo;
//import com.linsr.main.model.CartShopPojo;
//
//import java.util.List;
//
///**
// * Description
// *
// * @author Linsr 2018/8/20 下午6:38
// */
//public class ShopHolder extends BaseViewHolder<TreePojo<CartShopPojo, CartGoodsPojo>> {
//
//    public interface OnEventListener {
//        void onChecked(boolean isChecked);
//    }
//
//    private CheckBox mCheckBox;
//    private OnEventListener mOnEventListener;
//
//    public void setOnEventListener(OnEventListener onEventListener) {
//        mOnEventListener = onEventListener;
//    }
//
//    ShopHolder(Context context, View itemView) {
//        super(context, itemView);
//        mCheckBox = findViewById(R.id.cart_shop_check_box);
//    }
//
//    @Override
//    public void convert(int position,
//                        final TreePojo<CartShopPojo, CartGoodsPojo> data,
//                        int itemType) {
//        final List<CartGoodsPojo> childPojo = data.getChildPojo();
//        final CartShopPojo parentPojo = data.getParentPojo();
//
//        mCheckBox.setText(data.getParentPojo().getName());
//        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                parentPojo.setChecked(isChecked);
//                for (CartGoodsPojo pojo : childPojo) {
//                    pojo.setChecked(isChecked);
//                }
//            }
//        });
//    }
//}