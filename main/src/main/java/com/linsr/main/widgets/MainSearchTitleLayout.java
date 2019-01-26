package com.linsr.main.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.linsr.common.utils.DisplayUtils;
import com.linsr.common.utils.SoftKeyboardUtils;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/9/18 下午2:33
 */
public class MainSearchTitleLayout extends FrameLayout {

    public interface OnEventListener {
        void onSearchClick(String text);

        void onEditClick();

        void onLeftImageClick();

        void onRightImageClick();
    }

    private ImageView mLeftImageView;
    private TextView mLeftTextView;
    private EditText mEditText;
    private ImageView mRightImageView;

    private boolean mEnableEdit;
    private OnEventListener mOnEventListener;

    public void setOnEventListener(OnEventListener onEventListener) {
        mOnEventListener = onEventListener;
    }

    public MainSearchTitleLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public MainSearchTitleLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainSearchTitleLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.main_layout_search, (ViewGroup) getRootView(), false);
        mLeftImageView = view.findViewById(R.id.layout_search_left_img);
        mLeftTextView = view.findViewById(R.id.layout_search_left_tv);
        mEditText = view.findViewById(R.id.layout_search_et);
        mRightImageView = view.findViewById(R.id.layout_search_right_img);
        initAttrs(context, attrs);
        setListener();

        addView(view);
    }

    private void setListener() {
        if (mEnableEdit) {
            mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        SoftKeyboardUtils.hideSoftKeyboard(v);
                        if (mOnEventListener != null) {
                            mOnEventListener.onSearchClick(mEditText.getText().toString());
                        }
                        return true;
                    }
                    return false;
                }
            });
        } else {
            mEditText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnEventListener != null) {
                        mOnEventListener.onEditClick();
                    }
                }
            });
        }
        mLeftImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEventListener != null) {
                    mOnEventListener.onLeftImageClick();
                }
            }
        });
        mRightImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnEventListener != null) {
                    mOnEventListener.onRightImageClick();
                }
            }
        });
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MainSearchTitleLayout);
        //左边显示
        boolean showLeftImage = a.getBoolean(R.styleable.MainSearchTitleLayout_mainSearchShowLeftImage, true);
        boolean showLeftText = a.getBoolean(R.styleable.MainSearchTitleLayout_mainSearchShowLeftText, false);
        mLeftImageView.setVisibility(showLeftImage ? VISIBLE : GONE);
        mLeftTextView.setVisibility(showLeftText ? VISIBLE : GONE);
        //左边icon
        Drawable leftDrawable = a.getDrawable(R.styleable.MainSearchTitleLayout_mainSearchLeftImage);
        if (leftDrawable != null) {
            mLeftImageView.setImageDrawable(leftDrawable);
        }
        //左边文案
        if (showLeftText) {
            String leftText = a.getString(R.styleable.MainSearchTitleLayout_mainSearchLeftText);
            mLeftTextView.setText(leftText);
            Drawable leftTextDrawable = a.getDrawable(R.styleable.MainSearchTitleLayout_mainSearchLeftTextDrawable);
            if (leftTextDrawable != null) {
                mLeftTextView.setCompoundDrawablesWithIntrinsicBounds(leftTextDrawable, null, null, null);
                mLeftTextView.setCompoundDrawablePadding(DisplayUtils.dp2px(getContext(), 4));
            }
        }
        //是否允许编辑
        mEnableEdit = a.getBoolean(R.styleable.MainSearchTitleLayout_mainSearchEnableEdit, false);
        mEditText.setFocusable(mEnableEdit);
        mEditText.setFocusableInTouchMode(mEnableEdit);
        //右边图片
        Drawable rightDrawable = a.getDrawable(R.styleable.MainSearchTitleLayout_mainSearchRightImage);
        if (rightDrawable != null) {
            mRightImageView.setImageDrawable(rightDrawable);
        }
        boolean showRightImage = a.getBoolean(R.styleable.MainSearchTitleLayout_mainSearchShowRightImage, true);
        mRightImageView.setVisibility(showRightImage ? VISIBLE : GONE);
        a.recycle();
    }

    public void setEnableEdit(boolean enableEdit) {
        mEnableEdit = enableEdit;
        mEditText.setFocusable(mEnableEdit);
        mEditText.setFocusableInTouchMode(mEnableEdit);
        setListener();
    }

    public void setEditTextContent(CharSequence sequence) {
        mEditText.setText(sequence);
    }
}