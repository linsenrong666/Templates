package com.linsr.common.widgets;

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
    }

    private TextView mDownButton;
    private TextView mUpButton;
    private TextView mResultTextView;
    private int mCount;

    private int mMinCount = 0;
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
        LayoutInflater.from(context).inflate(R.layout.common_widget_cart_count, (ViewGroup) getRootView(), true);
        mDownButton = findViewById(R.id.cart_count_down_btn);
        mDownButton.setOnClickListener(this);
        mUpButton = findViewById(R.id.cart_count_up_btn);
        mUpButton.setOnClickListener(this);

        mResultTextView = findViewById(R.id.cart_count_result_tv);
    }

    public int getReusltCount() {
        return mCount;
    }

    public void setResultCount(int count) {
        mCount = count;
        mResultTextView.setText(String.valueOf(mCount));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.cart_count_down_btn) {
            onDown();
        } else if (i == R.id.cart_count_up_btn) {
            onUp();
        }
        mResultTextView.setText(String.valueOf(mCount));
    }

    private void onDown() {
        if (mCount > mMinCount) {
            mCount--;
        } else if (mCount == mMinCount) {
            mCount = mMinCount;
        } else {

        }
        if (mOnCountChangedListener != null) {
            mOnCountChangedListener.onChanged(mCount);
        }
    }

    private void onUp() {
        if (mCount < mMaxCount) {
            mCount++;
        } else {

        }
        if (mOnCountChangedListener != null) {
            mOnCountChangedListener.onChanged(mCount);
        }
    }
}
