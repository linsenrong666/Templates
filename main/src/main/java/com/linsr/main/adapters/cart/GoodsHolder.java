package com.linsr.main.adapters.cart;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.gui.widgets.CartCountView;
import com.linsr.common.utils.ImageUtils;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.ViewUtils;
import com.linsr.main.R;
import com.linsr.main.model.CartListPojo;
import com.linsr.main.model.CartShopPojo;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午6:38
 */
public class GoodsHolder extends BaseViewHolder<TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean>> {

    public interface OnGoodsClickListener {
        void onItemClick(CartListPojo.GoodsListBean.ListBean data);
    }

    private LinearLayout mMainLayout;
    private CheckBox mCheckBox;
    private CartCountView mCartCountView;
    private boolean onBind;
    private ImageView mGoodsProfile;
    private TextView mGoodsName;
    private TextView mGoodsPrice;
    private OnGoodsClickListener mOnGoodsClickListener;
    private CartAdapter.CartListener mCartListener;
    private CartAdapter mCartAdapter;

    public void setOnGoodsClickListener(OnGoodsClickListener onGoodsClickListener) {
        mOnGoodsClickListener = onGoodsClickListener;
    }

    GoodsHolder(Context context, CartAdapter adapter, CartAdapter.CartListener cartListener, View itemView) {
        super(context, itemView);
        mCartListener = cartListener;
        mCartAdapter = adapter;
        mMainLayout = findViewById(R.id.cart_goods_main_layout);
        mCartCountView = findViewById(R.id.cart_goods_count_ccv);
        mCheckBox = findViewById(R.id.cart_goods_check_box);
        mGoodsProfile = findViewById(R.id.cart_goods_profile_iv);
        mGoodsName = findViewById(R.id.cart_goods_name_tv);
        mGoodsPrice = findViewById(R.id.cart_goods_price_tv);
    }

    @Override
    public void convert(int position, final TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean> data, final int itemType) {
        final ItemStatus itemStatus =
                ItemStatus.getItemStatusByPosition(mCartAdapter.getData(), position); // 获取列表项状态
        final CartListPojo.GoodsListBean.ListBean pojo =
                data.getChildPojo().get(itemStatus.getChildIndex());

        setInfo(pojo);

        onBind = true;
        mCheckBox.setChecked(pojo.isChecked());
        onBind = false;

        mGoodsProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnGoodsClickListener != null) {
                    mOnGoodsClickListener.onItemClick(pojo);
                }
            }
        });
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (onBind) {
                    return;
                }
                pojo.setChecked(isChecked);

                //check status
                List<CartListPojo.GoodsListBean.ListBean> list = data.getChildPojo();
                CartShopPojo parentPojo = data.getParentPojo();
                int checkedCount = 0;
                for (CartListPojo.GoodsListBean.ListBean cartGoodsPojo : list) {
                    if (!cartGoodsPojo.isChecked()) {
                        parentPojo.setChecked(false);
                        mCartAdapter.notifyItemRangeChanged(itemStatus.getParentPosition(), 1);
                        break;
                    }
                    checkedCount++;
                }
                if (checkedCount == list.size()) {
                    parentPojo.setChecked(true);
                    mCartAdapter.notifyItemRangeChanged(itemStatus.getParentPosition(), 1);
                }
                if (mCartListener != null) {
                    mCartListener.onDataChangeForBalance();
                }
                JLog.i("parentPosition:" + itemStatus.getParentPosition()
                        + ",childPosition:" + itemStatus.getChildPosition());

            }
        });
        mCartCountView.setOnCountChangedListener(new CartCountView.OnCountChangedListener() {
            @Override
            public void onChanged(int count) {
                pojo.setGoods_number(String.valueOf(count));
                if (mCartListener != null) {
                    mCartListener.onDataChangeForBalance();
                }
            }

            @Override
            public void onMinCount(int min) {
                JLog.i("==onMinCount");
            }

            @Override
            public void onMaxCount(int max) {
                JLog.i("==onMaxCount");
            }
        });
    }

    public void setInfo(CartListPojo.GoodsListBean.ListBean info) {
        ImageUtils.load(mContext, info.getGoods_img(), mGoodsProfile);
        ViewUtils.setText(mGoodsName, info.getGoods_name());
        ViewUtils.setText(mGoodsPrice, info.getGoods_price());
        if (!TextUtils.isEmpty(info.getGoods_number())) {
            try {
                int number = Integer.parseInt(info.getGoods_number());
                mCartCountView.setResultCount(number);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }
}
