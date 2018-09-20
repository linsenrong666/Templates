package com.linsr.main.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/8/13 下午4:29
 */
public class BalanceBar extends FrameLayout {

    public interface OnCartBottomBarListener {
        void onBalanceClick();

        void onAllChecked(boolean isChecked);
    }

    private TextView mBalanceButton;
    private TextView mAmount;
    private CheckBox mCheckBox;
    private OnCartBottomBarListener mOnCartBottomBarListener;

    public void setOnCartBottomBarListener(OnCartBottomBarListener onCartBottomBarListener) {
        mOnCartBottomBarListener = onCartBottomBarListener;
    }

    public BalanceBar(Context context) {
        this(context, null, 0);
    }

    public BalanceBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BalanceBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.main_widgets_balance_bar,
                (ViewGroup) getRootView(), true);

        mBalanceButton = findViewById(R.id.balance_bar_btn);
        mBalanceButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCartBottomBarListener != null) {
                    mOnCartBottomBarListener.onBalanceClick();
                }
            }
        });
        mAmount = findViewById(R.id.balance_bar_amount_tv);
        mCheckBox = findViewById(R.id.balance_bar_check_box);
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mOnCartBottomBarListener != null) {
                    mOnCartBottomBarListener.onAllChecked(isChecked);
                }
            }
        });
    }

    public void setAmount(String amount) {
        String text = getContext().getString(R.string.main_balance_count, amount);
        mAmount.setText(text);
    }

}