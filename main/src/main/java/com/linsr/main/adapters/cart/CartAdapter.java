package com.linsr.main.adapters.cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Pair;
import android.view.ViewGroup;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.utils.JLog;
import com.linsr.main.R;
import com.linsr.main.model.CartListPojo;
import com.linsr.main.model.CartShopPojo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/7/18 下午3:20
 */
public class CartAdapter extends BaseRecyclerAdapter<TreePojo<CartShopPojo,
        CartListPojo.GoodsListBean.ListBean>> {

    public interface CartListener {
        void onDataChangeForBalance();

        void onNumberChanged(int count);
    }

    private CartListener mCartListener;
    private GoodsHolder.OnGoodsClickListener mOnGoodsClickListener;

    public void setCartListener(CartListener cartListener) {
        mCartListener = cartListener;
    }

    public void setOnGoodsClickListener(GoodsHolder.OnGoodsClickListener onGoodsClickListener) {
        mOnGoodsClickListener = onGoodsClickListener;
    }

    public CartAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean>> onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        if (viewType == ItemStatus.VIEW_TYPE_PARENT) {
            return new ShopHolder(mContext, this, mCartListener,
                    mInflater.inflate(R.layout.main_item_cart_shop, parent, false));
        } else if (viewType == ItemStatus.VIEW_TYPE_CHILD) {
            GoodsHolder goodsHolder = new GoodsHolder(mContext,
                    this, mCartListener, mInflater.inflate(R.layout.main_item_cart_goods,
                    parent, false));
            goodsHolder.setOnGoodsClickListener(mOnGoodsClickListener);
            return goodsHolder;
        }
        return new ShopHolder(mContext, this, mCartListener, mInflater.inflate(R.layout.main_item_cart_shop,
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
    public void onBindViewHolder(@NonNull BaseViewHolder<TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean>> holder, int position) {
        ItemStatus itemStatus = ItemStatus.getItemStatusByPosition(mList, position); // 获取列表项状态
        final TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean> data = mList.get(itemStatus.getParentIndex());
        holder.convert(position, data, getItemViewType(position));
    }

    public boolean isAllChecked(int checkedCount) {
        int count = 0;
        for (TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean> treePojo : mList) {
            List<CartListPojo.GoodsListBean.ListBean> childPojo = treePojo.getChildPojo();
            count = count + childPojo.size();
        }
        JLog.i("当前选中：" + checkedCount + ",总共:" + count);
        return checkedCount == count;
    }

    public void toggleAllChecked(boolean isChecked) {
        ensureDataNotNull();
        for (TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean> treePojo : mList) {
            CartShopPojo parentPojo = treePojo.getParentPojo();
            parentPojo.setChecked(isChecked);
            List<CartListPojo.GoodsListBean.ListBean> childPojo = treePojo.getChildPojo();
            for (CartListPojo.GoodsListBean.ListBean cartGoodsPojo : childPojo) {
                cartGoodsPojo.setChecked(isChecked);
            }
        }
        notifyDataSetChanged();
    }

    public synchronized CartBalanceTO balance() {
        ensureDataNotNull();
        CartBalanceTO to = new CartBalanceTO();
        //选中的项
        int count = 0;
        //商品的个数
        int number = 0;
        double total = 0;
        for (TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean> treePojo : mList) {
            List<CartListPojo.GoodsListBean.ListBean> childPojo = treePojo.getChildPojo();
            if (childPojo == null) {
                continue;
            }
            for (CartListPojo.GoodsListBean.ListBean cartGoodsPojo : childPojo) {
                boolean checked = cartGoodsPojo.isChecked();
                if (checked) {
                    count++;
                    try {
                        number = number + Integer.parseInt(cartGoodsPojo.getGoods_number());
                        total = total +
                                (Double.parseDouble(cartGoodsPojo.getGoods_price()) * number);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        to.setAmount(total);
        to.setCount(count);
        to.setNumber(number);
        return to;
    }

    public List<CartListPojo.GoodsListBean.ListBean> getCheckedList() {
        if (mList == null) {
            return null;
        }
        List<CartListPojo.GoodsListBean.ListBean> result = new ArrayList<>();
        for (TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean> treePojo : mList) {
            List<CartListPojo.GoodsListBean.ListBean> childPojo = treePojo.getChildPojo();
            if (childPojo == null) {
                continue;
            }
            for (CartListPojo.GoodsListBean.ListBean cartGoodsPojo : childPojo) {
                boolean checked = cartGoodsPojo.isChecked();
                if (checked) {
                    result.add(cartGoodsPojo);
                }
            }
        }
        return result;
    }

    public void deleteGoods(List<CartListPojo.GoodsListBean.ListBean> list) {
        for (Iterator<TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean>> iterator = mList.iterator(); iterator.hasNext(); ) {
            TreePojo<CartShopPojo, CartListPojo.GoodsListBean.ListBean> treePojo = iterator.next();
            List<CartListPojo.GoodsListBean.ListBean> childPojo = treePojo.getChildPojo();
            if (childPojo == null) {
                continue;
            }
            for (Iterator<CartListPojo.GoodsListBean.ListBean> iterator1 = childPojo.iterator(); iterator1.hasNext(); ) {
                CartListPojo.GoodsListBean.ListBean cartGoodsPojo = iterator1.next();
                if (list.contains(cartGoodsPojo)) {
                    JLog.i(cartGoodsPojo.getGoods_id());
                    iterator1.remove();
                }
            }
            if (childPojo.size() == 0) {
                iterator.remove();
            }
        }
        notifyDataSetChanged();
    }

}
