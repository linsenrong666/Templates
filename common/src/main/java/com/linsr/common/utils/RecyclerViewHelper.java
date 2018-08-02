package com.linsr.common.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.linsr.common.widgets.recyclerview.EmptyWrapper;
import com.linsr.common.widgets.recyclerview.HeaderAndFooterWrapper;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午2:35
 */
public class RecyclerViewHelper {

    public static void initDefault(Context context,
                                   RecyclerView recyclerView,
                                   RecyclerView.Adapter adapter) {
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
