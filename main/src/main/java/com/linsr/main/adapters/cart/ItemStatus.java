package com.linsr.main.adapters.cart;

import com.linsr.common.utils.JLog;
import com.linsr.main.model.CartGoodsPojo;
import com.linsr.main.model.CartShopPojo;

import java.util.List;

import lombok.Data;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午4:47
 */
public class ItemStatus {

    public static final int VIEW_TYPE_PARENT = 0;
    public static final int VIEW_TYPE_CHILD = 1;

    private int mViewType; // item类型：parent or child
    private int mParentIndex; // 一级列表索引
    private int mParentPosition; // 一级列表索引所在位置
    private int mChildIndex = -1; // 二级列表索引
    private int mChildPosition = -1; // 二级列表索引所在位置


    public int getParentPosition() {
        return mParentPosition;
    }

    public void setParentPosition(int parentPosition) {
        mParentPosition = parentPosition;
    }

    public int getChildPosition() {
        return mChildPosition;
    }

    public void setChildPosition(int childPosition) {
        mChildPosition = childPosition;
    }

    public int getViewType() {
        return mViewType;
    }

    public void setViewType(int viewType) {
        mViewType = viewType;
    }

    public int getParentIndex() {
        return mParentIndex;
    }

    public void setParentIndex(int parentIndex) {
        mParentIndex = parentIndex;
    }

    public int getChildIndex() {
        return mChildIndex;
    }

    public void setChildIndex(int childIndex) {
        mChildIndex = childIndex;
    }

    /**
     * 根据item的位置，获取当前Item的状态
     *
     * @param position 当前item的位置（此position的计数包含groupItem和subItem合计）
     * @return 当前Item的状态（此Item可能是parent，也可能是child）
     */
    public static <K, V> ItemStatus getItemStatusByPosition(List<TreePojo<K, V>> mList, int position) {
        ItemStatus itemStatus = new ItemStatus();
        int itemCount = 0;
        int i;
        for (i = 0; i < mList.size(); i++) {
            //position刚好等于计数时，item为groupItem
            if (itemCount == position) {
                itemStatus.setViewType(ItemStatus.VIEW_TYPE_PARENT);
                itemStatus.setParentIndex(i);
                //设定parent所在位置
                if (i > 0) {
                    int temp = (itemCount - mList.get(i - 1).getChildPojo().size());
                    itemStatus.setParentPosition(temp - 1);
                } else {
                    itemStatus.setParentPosition(0);
                }
                break;
            }
            //position大于计数时，item为groupItem(i - 1)中的某个subItem
            else if (itemCount > position) {
                itemStatus.setViewType(ItemStatus.VIEW_TYPE_CHILD);
                // 指定的position组索引
                itemStatus.setParentIndex(i - 1);
                // 计算指定的position前，统计的列表项和
                int temp = (itemCount - mList.get(i - 1).getChildPojo().size());
                // 指定的position的子项索引：即为position-之前统计的列表项和
                itemStatus.setChildIndex(position - temp);
                // 设定child所在位置
                itemStatus.setChildPosition(position);
                // 设定parent所在位置
                if (i > 0) {
                    itemStatus.setParentPosition(temp - 1);
                } else {
                    itemStatus.setParentPosition(0);
                }
                break;
            }
            itemCount++;
            itemCount += mList.get(i).getChildPojo().size();
        }
        // 轮询到最后一组时，未找到对应位置
        if (i >= mList.size()) {
            // 设置为二级标签类型
            itemStatus.setViewType(ItemStatus.VIEW_TYPE_CHILD);
            // 设置一级标签为最后一组
            itemStatus.setParentIndex(i - 1);
            itemStatus.setChildIndex(position - (itemCount - mList.get(i - 1).getChildPojo().size()));

            itemStatus.setParentPosition(itemCount - mList.get(i - 1).getChildPojo().size() - 1);
            itemStatus.setChildPosition(position);
        }
        return itemStatus;
    }

    @Override
    public String toString() {
        return "ItemStatus{" +
                "mViewType=" + mViewType +
                ", mParentIndex=" + mParentIndex +
                ", mParentPosition=" + mParentPosition +
                ", mChildIndex=" + mChildIndex +
                ", mChildPosition=" + mChildPosition +
                '}';
    }
}
