package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.base.adapter.BindView;
import com.linsr.main.R;
import com.linsr.main.model.FindPojo;

/**
 * Description
 *
 * @author Linsr 2018/7/12 下午4:48
 */
public class FindAdapter extends BaseRecyclerAdapter<FindPojo> {

    public FindAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<FindPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_item_find, parent, false);
        return new FindHolder(mContext, view);
    }

    private class FindHolder extends BaseViewHolder<FindPojo> {

        private TextView title;
        private TextView content;

        FindHolder(Context context, View itemView) {
            super(context, itemView);
            title = findViewById(R.id.item_find_title_tv);
            content = findViewById(R.id.item_find_content_tv);
        }

        @Override
        protected void convert(int position, FindPojo data, int itemType) {
            title.setText(data.getTitle());
            content.setText(data.getContent());
        }
    }
}
