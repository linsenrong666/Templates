package com.linsr.common.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Description
 *
 * @author Linsr 2018/7/11 下午2:35
 */
public class RecyclerViewHepler {

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

}
