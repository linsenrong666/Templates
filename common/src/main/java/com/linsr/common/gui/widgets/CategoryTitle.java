package com.linsr.common.gui.widgets;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.linsr.common.R;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.gui.divider.GridDividerItemDecoration;
import com.linsr.common.utils.DisplayUtils;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/9/13 下午5:15
 */
public class CategoryTitle extends LinearLayout {

    public interface OnItemSelectedListener {
        void onItemSelected();
    }

    private TextView mTitleTextView;
    private ImageView mToggleImageView;
    private CategoryTitleAdapter mAdapter;
    private PopupWindow mPopupWindow;
    private OnItemSelectedListener mOnItemSelectedListener;

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        mOnItemSelectedListener = onItemSelectedListener;
    }

    public CategoryTitle(@NonNull Context context) {
        this(context, null, 0);
    }

    public CategoryTitle(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryTitle(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.common_widget_category_title,
                (ViewGroup) getRootView(), true);
        mTitleTextView = findViewById(R.id.category_title_tv);
        mToggleImageView = findViewById(R.id.category_title_toggle_iv);
        mToggleImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });

        RecyclerView mRecyclerView = new RecyclerView(getContext());
        int padding = (int) getResources().getDimension(R.dimen.small);
        mRecyclerView.setPadding(padding, padding, padding, padding);
        mRecyclerView.setBackgroundColor(context.getResources().getColor(R.color.white));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mRecyclerView.addItemDecoration(new GridDividerItemDecoration(
                (int) getContext().getResources().getDimension(R.dimen.mini),
                getContext().getResources().getColor(R.color.transparent)));

        mAdapter = new CategoryTitleAdapter(getContext());
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(BaseViewHolder<String> holder, int position, int itemType, String data) {
                mTitleTextView.setText(data);
                mAdapter.setSelection(position);
                toggle();
                if (mOnItemSelectedListener != null) {
                    mOnItemSelectedListener.onItemSelected();
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mPopupWindow = new PopupWindow(mRecyclerView, LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
    }

    private void toggle() {
        if (mPopupWindow == null) {
            return;
        }
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        } else {
            mPopupWindow.showAsDropDown(mToggleImageView);
        }
    }

    public void setData(List<String> list) {
        if (list != null && list.size() > 0) {
            mAdapter.addData(list);
        }
    }

    class CategoryTitleAdapter extends BaseRecyclerAdapter<String> {

        private int selected = -1;

        public void setSelection(int position) {
            this.selected = position;
            notifyDataSetChanged();
        }

        CategoryTitleAdapter(Context context) {
            super(context);
        }

        @NonNull
        @Override
        public BaseViewHolder<String> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = new TextView(mContext);
            textView.setTextColor(mContext.getResources().getColor(R.color.text_primary));
            textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_stroke_btn));
            textView.setSingleLine();
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.font_small));
            int hor = DisplayUtils.dp2px(mContext, 10);
            int ver = DisplayUtils.dp2px(mContext, 6);
            textView.setPadding(hor, ver, hor, ver);
            return new Holder(mContext, textView);
        }

        class Holder extends BaseViewHolder<String> {

            public Holder(Context context, View itemView) {
                super(context, itemView);
            }

            @Override
            public void convert(int position, String data, int itemType) {
                TextView textView = (TextView) itemView;
                textView.setText(data);
                if (selected == position) {
                    textView.setTextColor(mContext.getResources().getColor(R.color.main_color));
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_stroke_main_color_btn));
                } else {
                    textView.setTextColor(mContext.getResources().getColor(R.color.text_primary));
                    textView.setBackground(mContext.getResources().getDrawable(R.drawable.bg_stroke_btn));
                }
            }
        }
    }

}
