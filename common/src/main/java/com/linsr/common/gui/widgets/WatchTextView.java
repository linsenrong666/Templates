package com.linsr.common.gui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linsr.common.R;

/**
 * Description
 *
 * @author Linsr 2018/9/15 下午2:04
 */
public class WatchTextView extends LinearLayout {

    private TextView mWatchLabel;
    private TextView mHourTextView, mMinTextView, mSecTextView;

    public WatchTextView(Context context) {
        this(context, null, 0);
    }

    public WatchTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WatchTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.common_widget_watch_text_view,
                (ViewGroup) getRootView(), true);
        mWatchLabel = findViewById(R.id.watch_label_tv);
        mHourTextView = findViewById(R.id.watch_hour_tv);
        mMinTextView = findViewById(R.id.watch_min_tv);
        mSecTextView = findViewById(R.id.watch_sec_tv);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WatchTextView);
        boolean isShowLabel = a.getBoolean(R.styleable.WatchTextView_watchShowLabel, false);
        if (isShowLabel) {
            mWatchLabel.setVisibility(VISIBLE);
            String labelText = a.getString(R.styleable.WatchTextView_watchLabelText);
            mWatchLabel.setText(labelText);
        } else {
            mWatchLabel.setVisibility(GONE);
        }

        a.recycle();
    }


}
