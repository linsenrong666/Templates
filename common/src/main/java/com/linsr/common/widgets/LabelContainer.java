package com.linsr.common.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linsr.common.R;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.common.utils.ViewUtils;


/**
 * Description
 *
 * @author linsenrong on 2016/10/12 17:22
 */
public class LabelContainer extends FrameLayout {

    private RelativeLayout mContainer;
    private LinearLayout mTitleContainer;
    private LinearLayout mRightContainer;
    private TextView mTitleTextView;
    private TextView mContentTextView;

    private ImageView mContentImageView;
    private EditText mContentEditText;
    private View mTopDivider;
    private View mBottomDivider;
    private View mBottomInnerDivider;

    private boolean mIsShowEdit;

    private int mDefaultTextSize = 13;

    public LabelContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.common_widget_label_view, (ViewGroup) getParent(), false);
        findView(view);
        initView(context, attrs);
        addView(view);
    }

    private void findView(View view) {
        mTitleTextView = (TextView) view.findViewById(R.id.label_title_tv);
        mContentTextView = (TextView) view.findViewById(R.id.label_content_tv);
        mContentImageView = (ImageView) view.findViewById(R.id.label_content_iv);
        mContentEditText = (EditText) view.findViewById(R.id.label_content_et);
        mContainer = (RelativeLayout) view.findViewById(R.id.label_container_rl);
        mTopDivider = view.findViewById(R.id.label_top_divider);
        mBottomDivider = view.findViewById(R.id.label_bottom_divider);
        mBottomInnerDivider = view.findViewById(R.id.label_bottom_inner_divider);
        mRightContainer = (LinearLayout) view.findViewById(R.id.label_right_container);
        mTitleContainer = (LinearLayout) view.findViewById(R.id.label_title_container);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LabelContainer);

        //设置文字大小
        float titleTextSize = a.getDimension(R.styleable.LabelContainer_label_text_size, mDefaultTextSize);
        float contentEditSize = a.getDimension(R.styleable.LabelContainer_content_edit_size, mDefaultTextSize);
        float contentTextSize = a.getDimension(R.styleable.LabelContainer_content_text_size, mDefaultTextSize);
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, titleTextSize);
        mContentEditText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, contentEditSize);
        mContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, contentTextSize);
        //设置输入框输入类型
        int inputType = a.getInt(R.styleable.LabelContainer_edit_input_type, InputType.TYPE_CLASS_TEXT);
        mContentEditText.setInputType(inputType);
        //设置标题文字
        String titleString = a.getString(R.styleable.LabelContainer_label_text);
        if (!TextUtils.isEmpty(titleString)) {
            mTitleTextView.setText(titleString);
        }
        //设置标题文字颜色
        int titleColor = a.getColor(R.styleable.LabelContainer_label_text_color, -1);
        if (titleColor != -1) {
            mTitleTextView.setTextColor(titleColor);
        }
        //设置内容文字
        String contentString = a.getString(R.styleable.LabelContainer_content_text);
        if (!TextUtils.isEmpty(contentString)) {
            mContentTextView.setVisibility(VISIBLE);
            mContentTextView.setText(contentString);
        }
        //设置内容文字颜色
        int contentTextColor = a.getColor(R.styleable.LabelContainer_content_text_color, -1);
        if (contentTextColor != -1) {
            mContentTextView.setTextColor(contentTextColor);
        }
        //设置左边图片
        Drawable drawable = a.getDrawable(R.styleable.LabelContainer_content_image);
        if (drawable != null) {
            mContentImageView.setVisibility(VISIBLE);
            mContentImageView.setImageDrawable(drawable);
        }
        //设置是否显示输入框
        mIsShowEdit = a.getBoolean(R.styleable.LabelContainer_show_edit_text, false);
        String hintText = a.getString(R.styleable.LabelContainer_hint_text);
        if (mIsShowEdit) {
            mContentTextView.setVisibility(GONE);
            mContentEditText.setVisibility(VISIBLE);
            mContentEditText.setHint(hintText);
        } else {
            mContentEditText.setVisibility(GONE);
        }

        //显示分割线
        boolean showTopDivider = a.getBoolean(R.styleable.LabelContainer_show_top_divider, false);
        boolean showBottomDivider = a.getBoolean(R.styleable.LabelContainer_show_bottom_divider, false);
        boolean showBottomInnerDivider = a.getBoolean(R.styleable.LabelContainer_show_bottom_inner_divider, true);
        mTopDivider.setVisibility(showTopDivider ? VISIBLE : GONE);
        mBottomDivider.setVisibility(showBottomDivider ? VISIBLE : GONE);
        mBottomInnerDivider.setVisibility(showBottomInnerDivider ? VISIBLE : GONE);
        //设置高度
        int contentHeight = (int) a.getDimension(R.styleable.LabelContainer_content_height, -1);
        if (contentHeight != -1) {
            ViewGroup.LayoutParams layoutParams = mContainer.getLayoutParams();
            layoutParams.height = contentHeight;
            mContainer.setLayoutParams(layoutParams);
        }
        //设置内边距
        int innerMarginLeft = (int) a.getDimension(R.styleable.LabelContainer_inner_margin_left, 0);
        int innerMarginRight = (int) a.getDimension(R.styleable.LabelContainer_inner_margin_right, 0);
        ViewUtils.setMargins(mContainer, innerMarginLeft, 0, innerMarginRight, 0);
        //设置标题宽度
        int labelWidth = (int) a.getDimension(R.styleable.LabelContainer_label_width, -1);
        if (labelWidth != -1) {
            ViewGroup.LayoutParams labelLayoutParams = mTitleContainer.getLayoutParams();
            labelLayoutParams.width = labelWidth;
            mTitleContainer.setLayoutParams(labelLayoutParams);
        }

        a.recycle();
    }

    public void hideAllDivider() {
        mTopDivider.setVisibility(GONE);
        mBottomDivider.setVisibility(GONE);
        mBottomInnerDivider.setVisibility(GONE);
    }

    public void setLabelWidth(int labelWidth) {
        ViewGroup.LayoutParams labelLayoutParams = mTitleContainer.getLayoutParams();
        labelLayoutParams.width = DisplayUtils.dp2px(getContext(), labelWidth);
        mTitleContainer.setLayoutParams(labelLayoutParams);
    }

    public void setLabelTitle(String str) {
        mTitleTextView.setText(str);
    }

    /**
     * 设置标题文字样式
     * @param textSize 文字大小
     * @param textColor 文字颜色
     * @param width 宽度
     * @param  gravity 居中
     */
    public void setLabelTitleStyle(Integer textSize, Integer textColor, Integer width, Integer gravity) {
        if (textColor != null) {
            mTitleTextView.setTextColor(getContext().getResources().getColor(textColor));
        }
        if (textSize != null && textSize > 0) {
            mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        }
        if (gravity != null) {
            mTitleTextView.setGravity(gravity);
        }
        if (width != null) {
            setLabelWidth(width);
        }
    }

    /**
     * 设置text view 内容文字
     * @param str string
     */
    public void setContentText(String str) {
        if (mContentTextView.getVisibility() != VISIBLE) {
            mContentTextView.setVisibility(VISIBLE);
        }
        mContentTextView.setText(str);
    }

    public void setContentTextStyle(Integer textSize, Integer textColor, Integer gravity) {
        if (textColor != null) {
            mContentTextView.setTextColor(getContext().getResources().getColor(textColor));
        }
        if (textSize != null && textSize > 0) {
            mContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);
        }
        if (gravity != null) {
            mContentTextView.setGravity(gravity);
        }
    }

    public void setEditContentText(String str) {
        if (mContentEditText.getVisibility() != VISIBLE) {
            mContentEditText.setVisibility(VISIBLE);
        }
        mContentEditText.setText(str);
    }

    public void setContentImage(Drawable d) {
        if (mContentImageView.getVisibility() != VISIBLE) {
            mContentImageView.setVisibility(VISIBLE);
        }
        mContentImageView.setImageDrawable(d);
    }

    public void showEdit(boolean isShowEdit) {
        mIsShowEdit = isShowEdit;
        if (mIsShowEdit) {
            mContentTextView.setVisibility(GONE);
            mContentEditText.setVisibility(VISIBLE);
        } else {
            mContentEditText.setVisibility(GONE);
        }
    }

    public void addRightView(View view) {
        mRightContainer.setVisibility(VISIBLE);
        mRightContainer.addView(view);
    }

    public void setDefaultTextSize(int defaultTextSize) {
        mDefaultTextSize = defaultTextSize;
    }

    public String getEditContentText() {
        return mContentEditText.getText().toString().trim();
    }

    public EditText getContentEditText() {
        return mContentEditText;
    }

    public void setContentImageClickListener(OnClickListener listener) {
        mContentImageView.setOnClickListener(listener);
    }

    public LabelContainer(Context context) {
        this(context, null, 0);
    }

    public LabelContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

}
