package com.linsr.books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.linsr.books.widgets.OnChildScrollListener;
import com.linsr.books.widgets.OnGroupScrollListener;
import com.linsr.books.widgets.SideGroupLayout;
import com.linsr.books.widgets.SideTopScrollView;

/**
 * Description
 *
 * @author Linsr 2018/6/15 下午5:45
 */
public class BookActivity extends AppCompatActivity implements OnGroupScrollListener, OnChildScrollListener {

    private SideGroupLayout mHoverLayout;
    private SideTopScrollView mSideTopScrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity_books);
        initView();
    }

    private void initView() {
        mHoverLayout = (SideGroupLayout) findViewById(R.id.hoverlayout);
        mSideTopScrollView = (SideTopScrollView) findViewById(R.id.sidescrollview);
        mHoverLayout.setOnGroupScrollListener(this);
        mSideTopScrollView.setOnChildScrollListener(this);
    }

    @Override
    public boolean isChildScroll() {
        return mHoverLayout != null && mHoverLayout.isScrollToEnd();
    }

    @Override
    public boolean isGroupScroll() {
        return mSideTopScrollView != null && mSideTopScrollView.isScrollToTop();
    }

    @Override
    public void onScrollChanged(int left, int top) {
    }
}
