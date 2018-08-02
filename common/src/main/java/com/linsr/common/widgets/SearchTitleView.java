package com.linsr.common.widgets;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.linsr.common.R;

/**
 * Description
 *
 * @author Linsr 2018/8/2 下午5:15
 */
public class SearchTitleView extends FrameLayout {

    public SearchTitleView(@NonNull Context context) {
        this(context, null, 0);
    }

    public SearchTitleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchTitleView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.common_widget_search_title,
                (ViewGroup) getRootView(), false);

        addView(view);
    }
}
