package com.linsr.common.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.linsr.common.R;
import com.linsr.common.gui.divider.RecycleViewDivider;
import com.linsr.common.gui.widgets.recyclerview.EmptyWrapper;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午2:35
 */
public class RecyclerViewHelper {

    public static void initNestScrollView(Context context,
                                          RecyclerView recyclerView,
                                          RecyclerView.Adapter adapter,
                                          boolean hasDivider) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setFocusable(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        if (hasDivider) {
            recyclerView.addItemDecoration(new RecycleViewDivider(context,
                    LinearLayoutManager.VERTICAL,
                    DisplayUtils.dp2px(context, 0.5f),
                    ContextCompat.getColor(context, R.color.divider)));
        }
    }

    public static void initDefault(Context context,
                                   RecyclerView recyclerView,
                                   RecyclerView.Adapter adapter) {
        initDefault(context, recyclerView, adapter, false);
    }

    public static void initDefault(Context context,
                                   RecyclerView recyclerView,
                                   RecyclerView.Adapter adapter,
                                   boolean hasDivider) {
        if (hasDivider) {
            recyclerView.addItemDecoration(new RecycleViewDivider(context,
                    LinearLayoutManager.VERTICAL, R.drawable.divider_horizontal));
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    public static void initGridLayout(Context context,
                                      int spanCount,
                                      RecyclerView recyclerView,
                                      RecyclerView.Adapter adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(context, spanCount));
        recyclerView.setAdapter(adapter);
    }

//没想好怎么封装
//    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
//    private Context mContext;
//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//
//    public RecyclerViewHelper(Context context, RecyclerView recyclerView, RecyclerView.Adapter adapter) {
//        mContext = context;
//        mRecyclerView = recyclerView;
//        mAdapter = adapter;
//    }
//
//    public RecyclerView.Adapter initWithHeader() {
//        mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(getCurAdapter());
//        initDefault(mContext, mRecyclerView, mHeaderAndFooterWrapper);
//        return mHeaderAndFooterWrapper;
//    }
//
//    public RecyclerView.Adapter setEmptyView(View v) {
//        EmptyWrapper wrapper = new EmptyWrapper(getCurAdapter());
//        wrapper.setEmptyView(v);
//        return wrapper;
//    }
//
//    public void addHeaderView(View v) {
//        if (mHeaderAndFooterWrapper == null) {
//            return;
//        }
//        mHeaderAndFooterWrapper.addHeaderView(v);
//    }
//
//    private RecyclerView.Adapter getCurAdapter() {
//        return mRecyclerView.getAdapter();
//    }

}
