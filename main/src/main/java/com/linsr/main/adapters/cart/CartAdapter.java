package com.linsr.main.adapters.cart;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.widgets.CartCountView;
import com.linsr.main.R;
import com.linsr.main.model.CartGoodsPojo;
import com.linsr.main.model.CartShopPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author Linsr 2018/7/18 下午3:20
 */
public class CartAdapter extends BaseRecyclerAdapter<TreePojo<CartShopPojo, CartGoodsPojo>> {

    private Map<String, TempPojo> mCache = new HashMap<>();

    public CartAdapter(Context context) {
        super(context);
    }


    public static class TempPojo {
        int count = 0;
        boolean selected = false;

        @Override
        public String toString() {
            return "TempPojo{" +
                    "count=" + count +
                    ", selected=" + selected +
                    '}';
        }
    }

    @NonNull
    @Override
    public BaseViewHolder<TreePojo<CartShopPojo, CartGoodsPojo>> onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        if (viewType == ItemStatus.VIEW_TYPE_PARENT) {
            return new ShopHolder(mContext, mInflater.inflate(R.layout.main_item_cart_shop,
                    parent, false));
        } else if (viewType == ItemStatus.VIEW_TYPE_CHILD) {
            return new GoodsHolder(mContext, mInflater.inflate(R.layout.main_item_cart_goods,
                    parent, false), mCache);
        }
        return new ShopHolder(mContext, mInflater.inflate(R.layout.main_item_cart_shop,
                parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return getItemStatusByPosition(position).getViewType();
    }

    /**
     * 根据item的位置，获取当前Item的状态
     *
     * @param position 当前item的位置（此position的计数包含groupItem和subItem合计）
     * @return 当前Item的状态（此Item可能是parent，也可能是child）
     */
    private ItemStatus getItemStatusByPosition(int position) {
        ItemStatus itemStatus = new ItemStatus();
        int itemCount = 0;
        int i;
        for (i = 0; i < mList.size(); i++) {
            if (itemCount == position) { //position刚好等于计数时，item为groupItem
                itemStatus.setViewType(ItemStatus.VIEW_TYPE_PARENT);
                itemStatus.setParentIndex(i);
                break;
            } else if (itemCount > position) { //position大于计数时，item为groupItem(i - 1)中的某个subItem
                itemStatus.setViewType(ItemStatus.VIEW_TYPE_CHILD);
                itemStatus.setParentIndex(i - 1); // 指定的position组索引
                // 计算指定的position前，统计的列表项和
                int temp = (itemCount - mList.get(i - 1).getChildPojo().size());
                // 指定的position的子项索引：即为position-之前统计的列表项和
                itemStatus.setChildIndex(position - temp);
                break;
            }

            itemCount++;
            itemCount += mList.get(i).getChildPojo().size();
        }
        // 轮询到最后一组时，未找到对应位置
        if (i >= mList.size()) {
            itemStatus.setViewType(ItemStatus.VIEW_TYPE_CHILD); // 设置为二级标签类型
            itemStatus.setParentIndex(i - 1); // 设置一级标签为最后一组
            itemStatus.setChildIndex(position - (itemCount - mList.get(i - 1).getChildPojo().size()));
        }
        return itemStatus;
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
        ItemStatus itemStatus = getItemStatusByPosition(position); // 获取列表项状态
        final TreePojo<CartShopPojo, CartGoodsPojo> data = mList.get(itemStatus.getParentIndex());
        holder.convert(position, data, getItemViewType(position));
    }


}
