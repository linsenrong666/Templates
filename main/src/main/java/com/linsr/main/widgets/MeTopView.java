package com.linsr.main.widgets;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.linsr.common.gui.widgets.CircleImageView;
import com.linsr.common.utils.ImageUtils;
import com.linsr.common.utils.ViewUtils;
import com.linsr.main.R;
import com.linsr.main.model.UserPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/2 下午4:48
 */
public class MeTopView extends FrameLayout {

    private TextView mUserName;
    private TextView mUserNo;
    private TextView mBalance;
    private TextView mCommission;
    private TextView mWaitCommission;
    private TextView mDividend;
    private TextView mPacket;
    private ImageView mUserProfile;

    public MeTopView(@NonNull Context context) {
        this(context, null, 0);
    }

    public MeTopView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MeTopView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.main_widgets_me_top,
                (ViewGroup) getRootView(), false);
        mUserName = view.findViewById(R.id.me_top_user_name_tv);
        mUserProfile = view.findViewById(R.id.me_top_profile_iv);
        mBalance = view.findViewById(R.id.me_top_balance_tv);
        mCommission = view.findViewById(R.id.me_top_commission_tv);
        mWaitCommission = view.findViewById(R.id.me_top_wait_commission_tv);
        mDividend = view.findViewById(R.id.me_top_dividend_tv);
        mPacket = view.findViewById(R.id.me_top_packet_tv);


        mUserNo = view.findViewById(R.id.me_top_user_no_tv);
        addView(view);
    }

    public void fillData(UserPojo pojo) {
        if (pojo == null) {
            return;
        }
        ViewUtils.setText(mDividend, pojo.getTotal_dividend());
        ViewUtils.setText(mPacket, pojo.getTotal_redpacket());

        UserPojo.UserInfoBean bean = pojo.getUser_info();
        if (bean == null) {
            return;
        }
        ViewUtils.setText(mUserName, bean.getNickname());
        ViewUtils.setText(mUserNo, bean.getUsername());
        ViewUtils.setText(mBalance, bean.getUser_money());
        ViewUtils.setText(mCommission, bean.getPay_points());
        ViewUtils.setText(mWaitCommission, bean.getFrozen_money());
        ImageUtils.loadCircle(getContext(), bean.getUserface(), mUserProfile);
    }

}
