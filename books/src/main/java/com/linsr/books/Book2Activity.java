package com.linsr.books;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.BaseActivity;
import com.linsr.common.utils.JLog;

/**
 * Description
 *
 * @author Linsr 2018/6/18 下午2:25
 */
@Route(path = "/books/books2")
public class Book2Activity extends BaseActivity {

    private TextView title;
    private TextView title2;
    private View content;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity_books2);
        title = (TextView) findViewById(R.id.title);
        title2 = (TextView) findViewById(R.id.title_2);
        content = findViewById(R.id.content);

        ScrollView scrollView = (ScrollView) findViewById(R.id.nestscroll);
        scrollView.setOnScrollChangeListener(new ScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (scrollY > content.getHeight()) {
//                    title.setVisibility(View.VISIBLE);
//                } else {
//                    title.setVisibility(View.GONE);
//                }
                scrollY = Math.abs(scrollY);
//                JLog.i("title2.getTop():" + title2.getTop());
                JLog.i("scrollY:" + scrollY);
//                title2.setTranslationY(Math.max(scrollY, title2.getTop()));
                if (scrollY >= content.getHeight()) {
                    title2.setTranslationY(scrollY - content.getHeight());
                }
            }
        });


//        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.nestscroll);
//        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                title2.setTranslationY(scrollY);
//            }
//        });

    }

    static class myScroll extends ScrollView {
        public myScroll(Context context) {
            super(context);
        }

        public myScroll(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public myScroll(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onScrollChanged(int l, int t, int oldl, int oldt) {
            super.onScrollChanged(l, t, oldl, oldt);
        }
    }
}
