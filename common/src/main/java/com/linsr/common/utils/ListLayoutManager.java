package com.linsr.common.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linsenrong on 15/3/11.
 */
public abstract class ListLayoutManager<T, V extends ListLayoutManager.ViewHolder> {

    public static abstract class ViewHolder {
        private View mView;

        public ViewHolder(View view) {
            mView = view;
        }

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private final LinearLayout mLinearLayout;
    private final Context mContext;

    public ListLayoutManager(Context context,
                             LinearLayout linearLayout) {
        mContext = context;
        mLinearLayout = linearLayout;
    }

    public void createViews(List<T> dataSource, int listLayoutId,
                            OnItemClickListener listener) {
        if (dataSource != null && dataSource.size() > 0) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            List<View> views = new ArrayList<>();

            for (int idx = 0; idx < dataSource.size(); idx++) {
                View view = layoutInflater.inflate(listLayoutId, null);
                onBindDataAndView(onCreateViewHolder(view), dataSource.get(idx));
                views.add(view);
            }

            setViews(views, listener);
        } else {
            mLinearLayout.setVisibility(View.GONE);
        }
    }

    private void setViews(List<View> views, final OnItemClickListener onItemClickListener) {
        mLinearLayout.removeAllViews();
        if (views != null && views.size() > 0) {
            for (int idx = 0; idx < views.size(); idx++) {
                final int currentPos = idx;
                final View currentView = views.get(idx);
                currentView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(currentView, currentPos);
                        }
                    }
                });
                mLinearLayout.addView(currentView);
            }
        }
    }

    protected abstract V onCreateViewHolder(View view);

    protected abstract void onBindDataAndView(V viewHolder, T data);
}
