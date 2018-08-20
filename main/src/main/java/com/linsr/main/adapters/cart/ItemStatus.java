package com.linsr.main.adapters.cart;

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
    private int mChildIndex = -1; // 二级列表索引

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

}
