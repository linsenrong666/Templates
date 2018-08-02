package com.linsr.main.widgets;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/8/2 下午4:48
 */
public class MeTopView extends FrameLayout {

    public MeTopView(@NonNull Context context) {
        this(context, null, 0);
    }

    public MeTopView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeTopView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.main_widgets_me_top,
                (ViewGroup) getRootView(), false);

        addView(view);
    }
}
