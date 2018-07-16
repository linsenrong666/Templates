package com.linsr.common.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.linsr.common.R;

import java.lang.reflect.Field;

/**
 * Description
 *
 * @author Linsr 2018/6/11 下午6:06
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected View mView;
    protected Context mContext;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        mView = itemView;
        mContext = context;
        initView();
    }

    /**
     * 绑定数据
     * @param position 位置
     * @param data 数据
     * @param itemType 类型
     */
    protected abstract void convert(int position, T data, int itemType);

    protected View findViewById(int resId) {
        return mView.findViewById(resId);
    }

    private void initView() {
        Field[] fields = this.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                BindView viewInject = field.getAnnotation(BindView.class);
                if (viewInject != null) {
                    int id = viewInject.id();
                    field.setAccessible(true);
                    try {
                        field.set(this, mView.findViewById(id));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected String getString(int resId, Object... args) {
        return mContext.getString(resId, args);
    }

    protected int getColor(int color) {
        return mContext.getResources().getColor(color);
    }
}
