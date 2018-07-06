package com.linsr.books;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.base.adapter.BindView;

/**
 * Description
 *
 * @author Linsr 2018/7/6 下午3:59
 */
public class ss extends BaseRecyclerAdapter<String> {


    public ss(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    class aaa extends BaseViewHolder<String> {
        @BindView(id = R.id.sidescrollview)
        TextView mTextView;
        public aaa(Context context, View itemView) {
            super(context, itemView);
        }


        @Override
        protected void convert(int position, String data, int itemType) {

        }
    }
}
