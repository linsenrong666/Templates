package com.linsr.main.fragments;

import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.DisplayUtils;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.FlashSaleAdapter;
import com.linsr.main.model.FlashSalePojo;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/9/6 下午3:11
 */
@Route(path = MainModule.Fragment.FLASH_SALE)
public class FlashSaleFragment extends RefreshFragment {

    private TextView mTimeTextView;
    private FlashSaleAdapter mAdapter;

    @Override
    protected void initTopLayout(FrameLayout topLayout) {
        topLayout.setVisibility(View.VISIBLE);
        mTimeTextView = new TextView(mActivity);
        mTimeTextView.setGravity(Gravity.CENTER);
        mTimeTextView.setPadding(0, DisplayUtils.dp2px(mActivity, 10), 0,
                DisplayUtils.dp2px(mActivity, 10));
        mTimeTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.font_middle));
        mTimeTextView.setText("本场还剩00：00：00");
        topLayout.addView(mTimeTextView);
    }

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new FlashSaleAdapter(mActivity);
        mAdapter.addData(Mock.getList(10, FlashSalePojo.class));
        RecyclerViewHelper.initDefault(mActivity, recyclerView, mAdapter);
    }

}
