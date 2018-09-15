package com.linsr.common.base.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linsr.common.R;

import java.lang.reflect.Field;

/**
 * Description
 *
 * @author Linsr 2018/6/11 下午6:06
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected final Context mContext;
    private final SparseArray<View> mCacheViews;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mCacheViews = new SparseArray<>();
    }

    public static <T> BaseViewHolder<T> createViewHolder(Context context, View itemView) {
        return new BaseViewHolder<T>(context, itemView) {
            @Override
            public void convert(int position, Object data, int itemType) {
            }
        };
    }

    public static <T> BaseViewHolder<T> createViewHolder(Context context, ViewGroup parent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        return new BaseViewHolder<T>(context, itemView) {
            @Override
            public void convert(int position, Object data, int itemType) {
            }
        };
    }

    /**
     * 绑定数据
     * @param position 位置
     * @param data 数据
     * @param itemType 类型
     */
    public abstract void convert(int position, T data, int itemType);

    public   <T extends View> T findViewById(int viewId) {
        View view = mCacheViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mCacheViews.put(viewId, view);
        }
        return (T) view;
    }

    protected String getString(int resId, Object... args) {
        return mContext.getString(resId, args);
    }

    protected int getColor(int color) {
        return mContext.getResources().getColor(color);
    }
}
