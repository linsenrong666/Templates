package com.linsr.books.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Description
 *
 * @author Linsr 2018/6/18 上午10:54
 */
public class SideTopScrollView extends ScrollView {

    private OnChildScrollListener mAction;

    private boolean isScrollTop = false;

    public SideTopScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean bool = mAction != null && mAction.isChildScroll() && super.onInterceptTouchEvent(ev);
        return bool;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mAction != null && mAction.isChildScroll() && super.onTouchEvent(event);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (t == 0) {
            isScrollTop = true;
        } else {
            isScrollTop = false;
        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public boolean isScrollToTop() {
        return isScrollTop;
    }

    public void setOnChildScrollListener(OnChildScrollListener action) {
        this.mAction = action;
    }


}
