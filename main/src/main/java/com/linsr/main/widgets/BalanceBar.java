package com.linsr.main.widgets;

import android.content.Context;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/8/13 下午4:29
 */
public class BalanceBar extends FrameLayout {

    public static final int BALANCE_MODE = 0;
    public static final int DELETE_MODE = 1;

    public interface OnCartBottomBarListener {
        void onConfirm(int mode);

        void onAllChecked(boolean isChecked);
    }

    private TextView mBalanceButton;
    private TextView mDeleteButton;
    private TextView mAmount;
    private CheckBox mCheckBox;
    private OnCartBottomBarListener mOnCartBottomBarListener;
    private int mCurMode = BALANCE_MODE;

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
                    mOnCartBottomBarListener.onConfirm(BALANCE_MODE);
                }
            }
        });
        mAmount = findViewById(R.id.balance_bar_amount_tv);
        mCheckBox = findViewById(R.id.balance_bar_check_box);
        mCheckBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCartBottomBarListener != null) {
                    mIsChecked = !mIsChecked;
                    mOnCartBottomBarListener.onAllChecked(mIsChecked);
                }
            }
        });
        mDeleteButton = findViewById(R.id.balance_bar_delete_btn);
        mDeleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCartBottomBarListener != null) {
                    mOnCartBottomBarListener.onConfirm(DELETE_MODE);
                }
            }
        });
    }

    private boolean mIsChecked;

    public void setAmount(Double amount) {
        String text = getContext().getString(R.string.main_balance_count, String.valueOf(amount));
        mAmount.setText(text);
    }

    public void setBalanceNumber(Integer count) {
        String text = getContext().getString(R.string.main_go_balance, String.valueOf(count));
        mBalanceButton.setText(text);
    }

    public void setAllChecked(boolean isChecked) {
        this.mIsChecked = isChecked;
        mCheckBox.setChecked(isChecked);
    }

    public void resetBalance() {
        mIsChecked = false;
        mCheckBox.setChecked(false);
        setAmount(0.0);
        setBalanceNumber(0);
    }

    public void setDeleteMode() {
        mDeleteButton.setVisibility(VISIBLE);
        mBalanceButton.setVisibility(GONE);
        mAmount.setVisibility(GONE);
        mCurMode = DELETE_MODE;
    }

    public void setBalanceMode() {
        mDeleteButton.setVisibility(GONE);
        mBalanceButton.setVisibility(VISIBLE);
        mAmount.setVisibility(VISIBLE);
        mCurMode = BALANCE_MODE;
    }

}