package com.linsr.main.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.linsr.common.gui.widgets.CartCountView;
import com.linsr.common.utils.ImageUtils;
import com.linsr.common.utils.ViewUtils;
import com.linsr.main.R;
import com.linsr.main.model.ProductDetailsPojo;
import com.linsr.main.utils.PriceUtils;

/**
 * Description
 *
 * @author Linsr 2019/1/26 下午3:11
 */
public class AddCartDialog extends DialogFragment {

    public interface OnAddCartClickListener {
        void onConfirm(int number);
    }

    private ProductDetailsPojo.GoodsBean mGoodsBean;

    public static AddCartDialog newInstance(ProductDetailsPojo.GoodsBean goodsBean) {

        Bundle args = new Bundle();
        args.putSerializable("data", goodsBean);

        AddCartDialog fragment = new AddCartDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private OnAddCartClickListener mOnAddCartClickListener;

    private CartCountView mCartCountView;
    private TextView mConfirmTextView;
    private TextView mNameTextView;
    private TextView mPriceTextView;
    private TextView mMarketPriceTextView;
    private ViewGroup mSpecLayout;


    private ImageView mProfileImageView;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void setOnAddCartClickListener(OnAddCartClickListener onAddCartClickListener) {
        mOnAddCartClickListener = onAddCartClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        if (getArguments() != null) {
            mGoodsBean = (ProductDetailsPojo.GoodsBean) getArguments().getSerializable("data");
        }
        View view = inflater.inflate(R.layout.main_dialog_add_cart, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mProfileImageView = view.findViewById(R.id.dialog_product_details_img_iv);
        mSpecLayout = view.findViewById(R.id.dialog_product_details_spec_layout);
        mNameTextView = view.findViewById(R.id.dialog_product_details_name_tv);
        mPriceTextView = view.findViewById(R.id.widgets_product_details_price_tv);
        mMarketPriceTextView = view.findViewById(R.id.widgets_product_details_market_price_tv);

        mCartCountView = view.findViewById(R.id.dialog_product_details_cart_count_view);
        mCartCountView.setResultCount(1);
        mCartCountView.setOnCountChangedListener(new CartCountView.OnCountChangedListener() {
            @Override
            public void onChanged(int count) {
                mCartCountView.setResultCount(count);
            }

            @Override
            public void onMinCount(int minCont) {
                mCartCountView.setResultCount(minCont);
            }

            @Override
            public void onMaxCount(int maxCount) {
                mCartCountView.setResultCount(maxCount);
            }
        });
        mConfirmTextView = view.findViewById(R.id.dialog_product_details_confirm_btn);
        mConfirmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnAddCartClickListener != null) {
                    int number = mCartCountView.getResultCount();
                    mOnAddCartClickListener.onConfirm(number);
                }
            }
        });
        setInfo();
        return view;
    }

    public void setInfo() {
        if (mGoodsBean == null) {
            return;
        }
        ViewUtils.setText(mNameTextView, mGoodsBean.getShort_name());
        ViewUtils.setText(mPriceTextView, PriceUtils.format(mGoodsBean.getShop_price()));
        ViewUtils.setText(mMarketPriceTextView, PriceUtils.format(mGoodsBean.getMarket_price()));
        ViewUtils.strikethrough(mMarketPriceTextView);
        ImageUtils.load(mContext, mGoodsBean.getGoods_thumb(), mProfileImageView);


    }


    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.BOTTOM; // 位置
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;//宽度满屏
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;//宽度满屏
            window.setAttributes(layoutParams);
        }
    }
}
