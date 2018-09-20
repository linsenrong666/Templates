package com.linsr.main.adapters.cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.gui.widgets.CartCountView;
import com.linsr.common.utils.JLog;
import com.linsr.main.R;
import com.linsr.main.model.CartGoodsPojo;
import com.linsr.main.model.CartShopPojo;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/18 下午3:20
 */
public class CartAdapter extends BaseRecyclerAdapter<TreePojo<CartShopPojo, CartGoodsPojo>> {

    public CartAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        if (viewType == ItemStatus.VIEW_TYPE_PARENT) {
            return new ShopHolder(mContext, mInflater.inflate(R.layout.main_item_cart_shop, parent, false));
        } else if (viewType == ItemStatus.VIEW_TYPE_CHILD) {
            return new GoodsHolder(mContext, mInflater.inflate(R.layout.main_item_cart_goods,
                    parent, false));
        }
        return new ShopHolder(mContext, mInflater.inflate(R.layout.main_item_cart_shop,
                parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return ItemStatus.getItemStatusByPosition(mList, position).getViewType();
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        int itemCount = 0;
        for (int i = 0; i < mList.size(); i++) {
            itemCount++; // 每个一级标题项+1
            itemCount += mList.get(i).getChildPojo().size();
        }
        return itemCount;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<TreePojo<CartShopPojo, CartGoodsPojo>> holder, int position) {
        ItemStatus itemStatus = ItemStatus.getItemStatusByPosition(mList, position); // 获取列表项状态
        final TreePojo<CartShopPojo, CartGoodsPojo> data = mList.get(itemStatus.getParentIndex());
        holder.convert(position, data, getItemViewType(position));
    }

    class ShopHolder extends BaseViewHolder<TreePojo<CartShopPojo, CartGoodsPojo>> {
        private CheckBox mCheckBox;
        private boolean onBind;

        ShopHolder(Context context, View itemView) {
            super(context, itemView);
            mCheckBox = findViewById(R.id.cart_shop_check_box);
        }

        @Override
        public void convert(final int position,
                            final TreePojo<CartShopPojo, CartGoodsPojo> data,
                            int itemType) {
            final CartShopPojo parentPojo = data.getParentPojo();
            final List<CartGoodsPojo> childPojo = data.getChildPojo();
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
                    for (CartGoodsPojo pojo : childPojo) {
                        pojo.setChecked(isChecked);
                    }
                    notifyItemRangeChanged(position, rangeCount);
                }
            });
        }
    }

    class GoodsHolder extends BaseViewHolder<TreePojo<CartShopPojo, CartGoodsPojo>> {

        private CheckBox mCheckBox;
        private CartCountView mCartCountView;
        private boolean onBind;

        GoodsHolder(Context context, View itemView) {
            super(context, itemView);
            mCartCountView = findViewById(R.id.cart_goods_count_ccv);
            mCheckBox = findViewById(R.id.cart_goods_check_box);
        }

        @Override
        public void convert(int position, final TreePojo<CartShopPojo, CartGoodsPojo> data, final int itemType) {
            final ItemStatus itemStatus = ItemStatus.getItemStatusByPosition(mList, position); // 获取列表项状态
            final CartGoodsPojo pojo = data.getChildPojo().get(itemStatus.getChildIndex());

            mCartCountView.setResultCount(pojo.getCount());

            onBind = true;
            mCheckBox.setChecked(pojo.isChecked());
            onBind = false;

            mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (onBind) {
                        return;
                    }
                    pojo.setChecked(isChecked);

                    //check status
                    List<CartGoodsPojo> list = data.getChildPojo();
                    CartShopPojo parentPojo = data.getParentPojo();
                    int checkedCount = 0;
                    for (CartGoodsPojo cartGoodsPojo : list) {
                        if (!cartGoodsPojo.isChecked()) {
                            parentPojo.setChecked(false);
                            notifyItemRangeChanged(itemStatus.getParentPosition(), 1);
                            break;
                        }
                        checkedCount++;
                    }
                    if (checkedCount == list.size()) {
                        parentPojo.setChecked(true);
                        notifyItemRangeChanged(itemStatus.getParentPosition(), 1);
                    }

                    JLog.i("parentPosition:" + itemStatus.getParentPosition()
                            + ",childPosition:" + itemStatus.getChildPosition());

                }
            });
            mCartCountView.setOnCountChangedListener(new CartCountView.OnCountChangedListener() {
                @Override
                public void onChanged(int count) {
                    pojo.setCount(count);
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
    }

    public void allToggleChecked(boolean isChecked) {
        ensureDataNotNull();
        for (TreePojo<CartShopPojo, CartGoodsPojo> treePojo : mList) {
            CartShopPojo parentPojo = treePojo.getParentPojo();
            parentPojo.setChecked(isChecked);
            List<CartGoodsPojo> childPojo = treePojo.getChildPojo();
            for (CartGoodsPojo cartGoodsPojo : childPojo) {
                cartGoodsPojo.setChecked(isChecked);
            }
        }
        notifyDataSetChanged();
    }

}
