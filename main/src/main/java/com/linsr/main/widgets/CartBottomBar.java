package com.linsr.main.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/8/13 下午4:29
 */
public class CartBottomBar extends FrameLayout {

    public interface OnCartBottomBarListener {
        void onBalanceClick();
    }

    private TextView mBalanceButton;
    private OnCartBottomBarListener mOnCartBottomBarListener;

    public void setOnCartBottomBarListener(OnCartBottomBarListener onCartBottomBarListener) {
        mOnCartBottomBarListener = onCartBottomBarListener;
    }

    public CartBottomBar(Context context) {
        this(context, null, 0);
    }

    public CartBottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CartBottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.main_widgets_cart_bottom_bar,
                (ViewGroup) getRootView(), true);

        mBalanceButton = findViewById(R.id.cart_balance_btn);
        mBalanceButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCartBottomBarListener != null) {
                    mOnCartBottomBarListener.onBalanceClick();
                }
            }
        });
    }
}