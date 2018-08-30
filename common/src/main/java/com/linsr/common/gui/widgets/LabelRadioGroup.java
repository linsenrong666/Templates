package com.linsr.common.gui.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linsr.common.R;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.common.utils.ObjectUtils;
import com.linsr.common.utils.ViewUtils;

import java.util.Arrays;

/**
 * Description
 *
 * @author Linsr 2018/6/6 上午10:06
 */
public class LabelRadioGroup extends RelativeLayout {
    private static final String TAG = "LabelRadioGroup";

    private RelativeLayout mContainer;
    private LinearLayout mTitleContainer;
    private RadioGroup mRadioGroup;
    private View mTopDivider;
    private View mBottomDivider;
    private View mBottomInnerDivider;
    private TextView mTitleTextView;

    private String[] mStrings;
    private int mRadioButtonDrawableId;

    public LabelRadioGroup(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.common_widget_label_radio_group, this, true);
        findView();
        initView(context, attrs);
        initData();
    }

    private void initData() {
        if (ObjectUtils.isNull(mStrings)) {
            Log.e(TAG, "Error: content list can't be null !");
            return;
        }
        Log.i(TAG, Arrays.toString(mStrings));
        mRadioGroup.removeAllViews();
        for (String str : mStrings) {
            mRadioGroup.addView(createDefaultItem(str));
        }
    }

    private RadioButton createDefaultItem(String str) {
        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setText(str);
        radioButton.setGravity(Gravity.CENTER);
        radioButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);
        radioButton.setButtonDrawable(mRadioButtonDrawableId);
        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
        radioButton.setLayoutParams(layoutParams);
        ViewUtils.setMargins(radioButton, 0, 0, DisplayUtils.dp2px(getContext(), 20), 0);
        return radioButton;
    }

    private Resources getRes() {
        return getContext().getResources();
    }

    private void findView() {
        mContainer = (RelativeLayout) findViewById(R.id.label_rg_container_rl);
        mTopDivider = findViewById(R.id.label_rg_top_divider);
        mBottomDivider = findViewById(R.id.label_rg_bottom_divider);
        mBottomInnerDivider = findViewById(R.id.label_rg_bottom_inner_divider);
        mTitleTextView = (TextView) findViewById(R.id.label_rg_title_tv);
        mTitleContainer = (LinearLayout) findViewById(R.id.label_rg_title_container);
        mRadioGroup = (RadioGroup) findViewById(R.id.label_rg_content);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.common_label_radioGroup);
        //显示分割线
        boolean showTopDivider = a.getBoolean(R.styleable.common_label_radioGroup_common_show_top_divider_rg, false);
        boolean showBottomDivider = a.getBoolean(R.styleable.common_label_radioGroup_common_show_bottom_divider_rg, false);
        boolean showBottomInnerDivider = a.getBoolean(R.styleable.common_label_radioGroup_common_show_bottom_inner_divider_rg, true);
        mTopDivider.setVisibility(showTopDivider ? VISIBLE : GONE);
        mBottomDivider.setVisibility(showBottomDivider ? VISIBLE : GONE);
        mBottomInnerDivider.setVisibility(showBottomInnerDivider ? VISIBLE : GONE);
        //设置内边距
        int innerMarginLeft = (int) a.getDimension(R.styleable.common_label_radioGroup_common_inner_margin_left_rg, 0);
        int innerMarginRight = (int) a.getDimension(R.styleable.common_label_radioGroup_common_inner_margin_right_rg, 0);
        ViewUtils.setMargins(mContainer, innerMarginLeft, 0, innerMarginRight, 0);
        //设置标题文字
        String titleString = a.getString(R.styleable.common_label_radioGroup_common_label_text_rg);
        if (!TextUtils.isEmpty(titleString)) {
            mTitleTextView.setText(titleString);
        }
        //设置文字大小
        float titleTextSize = a.getDimension(R.styleable.common_label_radioGroup_common_label_text_size_rg, 13);
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, titleTextSize);
        //设置标题宽度
        int labelWidth = (int) a.getDimension(R.styleable.common_label_radioGroup_common_label_width_rg, -1);
        if (labelWidth != -1) {
            ViewGroup.LayoutParams labelLayoutParams = mTitleContainer.getLayoutParams();
            labelLayoutParams.width = labelWidth;
            mTitleContainer.setLayoutParams(labelLayoutParams);
        }
        //内容文字list
        int contentListId = a.getResourceId(R.styleable.common_label_radioGroup_common_content_list_rg, -1);
        if (contentListId != -1) {
            mStrings = context.getResources().getStringArray(contentListId);
        }
        mRadioButtonDrawableId = a.getResourceId(R.styleable.common_label_radioGroup_common_rb_drawable_id_rg,-1);
        a.recycle();
    }

    public LabelRadioGroup(@NonNull Context context) {
        this(context, null, 0);
    }

    public LabelRadioGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

}
