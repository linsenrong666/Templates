package com.linsr.common.gui.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.linsr.common.R;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午3:03
 */
public class CartCountView extends LinearLayout implements View.OnClickListener {

    public interface OnCountChangedListener {

        void onChanged(int count);

        void onMinCount(int minCont);

        void onMaxCount(int maxCount);
    }

    private TextView mDownButton;
    private TextView mUpButton;
    private TextView mResultTextView;

    private int mMinCount = 1;
    private int mCount = mMinCount;
    private int mMaxCount = Integer.MAX_VALUE;

    private OnCountChangedListener mOnCountChangedListener;

    public void setOnCountChangedListener(OnCountChangedListener onCountChangedListener) {
        mOnCountChangedListener = onCountChangedListener;
    }

    public CartCountView(@NonNull Context context) {
        this(context, null, 0);
    }

    public CartCountView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CartCountView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.common_widget_cart_count,
                (ViewGroup) getRootView(), true);
        mDownButton = findViewById(R.id.cart_count_down_btn);
        mDownButton.setOnClickListener(this);
        mUpButton = findViewById(R.id.cart_count_up_btn);
        mUpButton.setOnClickListener(this);

        mResultTextView = findViewById(R.id.cart_count_result_tv);
        mResultTextView.setText(String.valueOf(mMinCount));
    }

    public void setMaxCount(int maxCount) {
        mMaxCount = maxCount;
    }

    public void setMinCount(int minCount) {
        mMinCount = minCount;
    }

    public int getResultCount() {
        return mCount;
    }

    public void setResultCount(int count) {
        if (count < mMinCount) {
            count = mMinCount;
        }
        if (count > mMaxCount) {
            count = mMaxCount;
        }
        mCount = count;
        mResultTextView.setText(String.valueOf(mCount));
        check();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cart_count_down_btn) {
            onDown();
        } else if (i == R.id.cart_count_up_btn) {
            onUp();
        }
    }

    private void check() {
        if (mCount <= mMinCount) {
            mDownButton.setEnabled(false);
        } else {
            mDownButton.setEnabled(true);
        }
        if (mCount >= mMaxCount) {
            mUpButton.setEnabled(false);
        } else {
            mUpButton.setEnabled(true);
        }
    }

    private void onDown() {
        if (mCount > mMinCount) {
            mCount--;
            if (mOnCountChangedListener != null) {
                if (mCount == mMinCount) {
                    mOnCountChangedListener.onMinCount(mCount);
                } else {
                    mOnCountChangedListener.onChanged(mCount);
                }
            }
        }
    }

    private void onUp() {
        if (mCount < mMaxCount) {
            mCount++;
            if (mOnCountChangedListener != null) {
                if (mCount == mMaxCount) {
                    mOnCountChangedListener.onMaxCount(mCount);
                } else {
                    mOnCountChangedListener.onChanged(mCount);
                }
            }
        }
    }

}