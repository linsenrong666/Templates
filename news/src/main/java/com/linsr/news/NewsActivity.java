package com.linsr.news;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.BaseActivity;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.common.utils.JLog;

/**
 * Description
 *
 * @author Linsr 2018/6/17 下午5:24
 */
@Route(path = "/news/news")
public class NewsActivity extends BaseActivity {

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private CoordinatorLayout mCoordinatorLayout;
    private TextView mTextView;
    private AppBarLayout mAppBarLayout;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity_news);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        mTextView = (TextView) findViewById(R.id.news_title);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbarlayout);
//        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
//            @Override
//            public void onStateChanged(AppBarLayout appBarLayout, State state) {
//                if (state == State.EXPANDED) {
//                    //展开状态
//
//                } else if (state == State.COLLAPSED) {
//                    //折叠状态
//                    mTextView.setVisibility(View.VISIBLE);
//                } else {
//                    mTextView.setVisibility(View.GONE);
//                    //中间状态
//
//                }
//            }
//        });
        final float max = DisplayUtils.dp2px(this, 44);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                JLog.i(TAG,"offset:" + verticalOffset);
                verticalOffset = Math.abs(verticalOffset);

                if (verticalOffset > 0) {
                    mTextView.setVisibility(View.VISIBLE);
                }else{
                    mTextView.setVisibility(View.GONE);
                }

                if (verticalOffset > 0 && verticalOffset < max) {
                    float scale = (float) verticalOffset / max;
                    int alpha = (int) (255 * scale);
                    mTextView.getBackground().setAlpha(alpha);
                }


            }
        });

    }

    public abstract static class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

        public enum State {
            EXPANDED,
            COLLAPSED,
            IDLE
        }

        private State mCurrentState = State.IDLE;

        @Override
        public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (i == 0) {
                if (mCurrentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED);
                }
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                if (mCurrentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED);
                }
                mCurrentState = State.COLLAPSED;
            } else {
                if (mCurrentState != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE);
                }
                mCurrentState = State.IDLE;
            }
        }

        public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
    }
}
