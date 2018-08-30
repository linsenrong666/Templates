package com.linsr.main.adapters.holder;

import android.content.Context;
import android.view.View;

import com.linsr.common.base.adapter.BaseViewHolder;

/**
 * Description
 *
 * @author Linsr 2018/8/30 下午3:06
 */
public class NullHolder extends BaseViewHolder {

    public NullHolder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    public void convert(int position, Object data, int itemType) {

    }
}
