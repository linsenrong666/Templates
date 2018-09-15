package com.linsr.common.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

/**
 * Description
 *
 * @author Linsr 2018/9/14 下午4:59
 */
public abstract class SimpleAdapter<T> extends BaseRecyclerAdapter<T> {

    protected abstract int getLayoutId();

    protected abstract void bindData(BaseViewHolder<T> holder, int position,
                                     T data, int itemType);

    public SimpleAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(getLayoutId(), parent, false));
    }

    class Holder extends BaseViewHolder<T> {

        public Holder(Context context, View itemView) {
            super(context, itemView);
        }

        @Override
        public void convert(int position, T data, int itemType) {
            bindData(this, position, data, itemType);
        }
    }

}