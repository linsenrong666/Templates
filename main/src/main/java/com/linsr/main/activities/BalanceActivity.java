package com.linsr.main.activities;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.ImageUtils;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.ViewUtils;
import com.linsr.main.R;
import com.linsr.main.logic.contacts.BalanceContact;
import com.linsr.main.logic.presenter.BalancePresenter;
import com.linsr.main.model.CartListPojo;
import com.linsr.main.utils.PriceUtils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/8/20 下午7:04
 */
@Route(path = MainModule.Activity.BALANCE)
public class BalanceActivity extends ActivityEx<BalancePresenter> implements BalanceContact.View {

    private RelativeLayout mAddressLayout;
    private android.widget.RadioGroup mRadioGroup;
    private LinearLayout mGoodsContainer;
    private TextView mAmountTextView;

    private List<CartListPojo.GoodsListBean.ListBean> mGoodsList;
    private double mAmount;

    @Override
    protected void init(Intent intent) {
        super.init(intent);
        if (intent != null) {
            mGoodsList = (List<CartListPojo.GoodsListBean.ListBean>) intent.getSerializableExtra(
                    MainModule.Activity.BalanceParams.ACT_BALANCE_GOODS_LIST);
            mAmount = intent.getDoubleExtra(MainModule.Activity.BalanceParams.ACT_BALANCE_AMOUNT, 0.0);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_balance;
    }

    @Override
    protected BalancePresenter bindPresenter() {
        return new BalancePresenter(this);
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_confirm_message);
        findView();
        setListener();
        initGoodsList();
        initBalanceLayout();
        mPresenter.checkOut();
    }

    private void initBalanceLayout() {
        ViewUtils.setText(mAmountTextView, getString(R.string.main_balance_count, mAmount));
    }

    private void initGoodsList() {
        if (mGoodsList != null && mGoodsList.size() > 0) {
            LayoutInflater inflater = LayoutInflater.from(this);
            for (int i = 0; i < mGoodsList.size(); i++) {
                CartListPojo.GoodsListBean.ListBean bean = mGoodsList.get(i);
                View view = inflater.inflate(R.layout.main_layout_balance_goods, mContentLayout, false);
                TextView name = view.findViewById(R.id.balance_goods_name_tv);
                TextView price = view.findViewById(R.id.balance_goods_price_tv);
                TextView count = view.findViewById(R.id.balance_goods_count_tv);
                ImageView img = view.findViewById(R.id.balance_goods_pic_iv);

                ViewUtils.setText(name, bean.getGoods_name());
                ViewUtils.setText(count, "X" + bean.getGoods_number());
                ViewUtils.setText(price, PriceUtils.defaultStyle(bean.getGoods_price()));
                ImageUtils.load(this, bean.getGoods_img(), img);
                mGoodsContainer.addView(view);
            }
        }
    }

    private void setListener() {
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.balance_alipay_rb) {
                    JLog.i("alipay====");
                } else if (checkedId == R.id.balance_wxpay_rb) {
                    JLog.i("weixinpay====");
                }
            }
        });
        mAddressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(MainModule.Activity.ADDRESS_LIST);
            }
        });
        findViewById(R.id.balance_go_pay_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPay();
            }
        });
    }

    private void findView() {
        mGoodsContainer = findViewById(R.id.balance_goods_container);
        mAddressLayout = findViewById(R.id.balance_address_layout);
        mRadioGroup = findViewById(R.id.balance_pay_rg);
        mAmountTextView = findViewById(R.id.balance_amount_tv);
    }

    private void toPay() {
        Router.startActivity(MainModule.Activity.PAY_RESULT);
    }

}
