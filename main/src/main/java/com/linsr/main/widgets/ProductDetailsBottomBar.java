package com.linsr.main.widgets;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.linsr.common.gui.widgets.CartCountView;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/8/22 下午4:40
 */
public class ProductDetailsBottomBar extends FrameLayout {

    private TextView mAddCartTextView;
    private View mContentLayout;
    private CartCountView mCartCountView;
    private TextView mShopTextView;
    private TextView mCollectTextView;

    public ProductDetailsBottomBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.main_widgets_product_details_bottom_bar,
                (ViewGroup) getRootView(), false);
        mAddCartTextView = view.findViewById(R.id.widgets_product_details_add_cart_tv);
        mContentLayout = view.findViewById(R.id.widgets_product_details_bar_content_layout);
        mContentLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hideContent();
            }
        });
        mAddCartTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mContentLayout.getVisibility() == GONE) {
                    mContentLayout.setVisibility(VISIBLE);
                } else {
                    hideContent();
                }
            }
        });
        mCartCountView = view.findViewById(R.id.widgets_product_details_cart_count_view);
        mCartCountView.setResultCount(1);
        mShopTextView = view.findViewById(R.id.widgets_product_details_shop_tv);
        mCollectTextView = view.findViewById(R.id.widgets_product_details_collect_tv);
        addView(view);
    }

    public void setOnShopClick(OnClickListener listener) {
        mShopTextView.setOnClickListener(listener);
    }

    public void setOnCollectClick(OnClickListener listener) {
        mCollectTextView.setOnClickListener(listener);
    }

    private void hideContent() {
        mContentLayout.setVisibility(GONE);
    }

    public ProductDetailsBottomBar(@NonNull Context context) {
        this(context, null, 0);
    }

    public ProductDetailsBottomBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

}
