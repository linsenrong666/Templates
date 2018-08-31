package com.linsr.main.widgets.meitem;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/21 下午4:20
 */
public class MainMeItemView extends FrameLayout {

    public interface OnMeItemClickListener {
        void onRightViewClick();

        void onItemClick(int position, MeItemPojo data);
    }

    private RecyclerView mRecyclerView;
    private Adapter mAdapter;
    private TextView mLeftTextView;
    private TextView mRightTextView;

    private int mSpanCount;
    private OnMeItemClickListener mOnMeItemClickListener;
    private String[] mTitleStrings;
    private Drawable[] mIcons;
    private List<MeItemPojo> mList;

    public void setOnMeItemClickListener(OnMeItemClickListener onMeItemClickListener) {
        mOnMeItemClickListener = onMeItemClickListener;
    }

    public void setData(List<MeItemPojo> list) {
        if (mList == null) {
            mList = new ArrayList<>();
        }
        mList.clear();
        mList.addAll(list);
        mAdapter.addData(mList);
    }

    public MainMeItemView(@NonNull Context context) {
        this(context, null, 0);
    }

    public MainMeItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainMeItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.main_widgets_me_item,
                (ViewGroup) getRootView(), false);
        findView(view);
        initAttrs(context, attrs);
        initRecyclerView(context);
        initListener();
        addView(view);
    }

    private void initListener() {
        mRightTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnMeItemClickListener != null) {
                    mOnMeItemClickListener.onRightViewClick();
                }
            }
        });
    }

    private void initRecyclerView(Context context) {
        mAdapter = new Adapter(context);
        mAdapter.addData(mList);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MeItemPojo>() {
            @Override
            public void onItemClick(BaseViewHolder<MeItemPojo> holder, int position, int itemType,
                                    MeItemPojo data) {
                if (mOnMeItemClickListener != null) {
                    mOnMeItemClickListener.onItemClick(position, data);
                }
            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);
        RecyclerViewHelper.initGridLayout(context, mSpanCount, mRecyclerView, mAdapter);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MainMeItemView);
        initData(context, a);
        setText(a, mLeftTextView, R.styleable.MainMeItemView_mainLeftText);
        setText(a, mRightTextView, R.styleable.MainMeItemView_mainRightText);

        boolean showRightView = a.getBoolean(R.styleable.MainMeItemView_mainShowRightView, true);
        mRightTextView.setVisibility(showRightView ? VISIBLE : GONE);

        mSpanCount = a.getInt(R.styleable.MainMeItemView_mainSpanCount, 4);
        if (mSpanCount < 1) {
            mSpanCount = 1;
        }

        a.recycle();
    }

    private void initData(Context context, TypedArray a) {
        mList = new ArrayList<>();
        int titleListId = a.getResourceId(R.styleable.MainMeItemView_mainTitleList, -1);
        if (titleListId != -1) {
            mTitleStrings = context.getResources().getStringArray(titleListId);
        }
        int iconListId = a.getResourceId(R.styleable.MainMeItemView_mainIconList, -1);
        if (iconListId != -1) {
            TypedArray typedArray = context.getResources().obtainTypedArray(iconListId);
            mIcons = new Drawable[typedArray.length()];
            for (int i = 0; i < typedArray.length(); i++) {
                mIcons[i] = typedArray.getDrawable(i);
            }
            typedArray.recycle();
        }
        if (mTitleStrings != null && mTitleStrings.length > 0) {
            int i = 0;
            for (String title : mTitleStrings) {
                MeItemPojo pojo = new MeItemPojo();
                pojo.setTitle(title);
                if (mIcons != null && mIcons[i] != null) {
                    pojo.setIcon(mIcons[i]);
                }
                mList.add(pojo);
                i++;
            }
        }
    }

    private void setText(TypedArray a, TextView tv, int resId) {
        String text = a.getString(resId);
        if (!TextUtils.isEmpty(text)) {
            tv.setText(text);
        }
    }

    private void findView(View view) {
        mLeftTextView = view.findViewById(R.id.me_item_left_tv);
        mRightTextView = view.findViewById(R.id.me_item_right_tv);
        mRecyclerView = view.findViewById(R.id.me_item_recycler_view);
    }

    private class Adapter extends BaseRecyclerAdapter<MeItemPojo> {

        Adapter(Context context) {
            super(context);
        }

        @NonNull
        @Override
        public BaseViewHolder<MeItemPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView textView = new TextView(mContext);
            textView.setGravity(Gravity.CENTER);
            return new Holder(mContext, textView);
        }

        class Holder extends BaseViewHolder<MeItemPojo> {
            TextView content;

            Holder(Context context, View itemView) {
                super(context, itemView);
                content = (TextView) itemView;
            }

            @Override
            public void convert(int position, final MeItemPojo data, int itemType) {
                content.setText(data.getTitle());
                if (data.getUrl() != null) {
                    Glide.with(mContext).load(data.getUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            BitmapDrawable drawable = new BitmapDrawable(mContext.getResources(), resource);
                            loadDrawable(drawable);
                        }
                    });
                } else if (data.getIcon() != null) {
                    loadDrawable(data.getIcon());
                }
            }

            private void loadDrawable(Drawable drawable) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                content.setCompoundDrawables(null, drawable, null, null);
                content.setCompoundDrawablePadding(DisplayUtils.dp2px(mContext, 8));
            }
        }
    }

}
