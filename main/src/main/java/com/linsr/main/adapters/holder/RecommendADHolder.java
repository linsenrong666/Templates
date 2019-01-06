package com.linsr.main.adapters.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.utils.ImageUtils;
import com.linsr.common.utils.ObjectUtils;
import com.linsr.main.R;
import com.linsr.main.app.Constants;
import com.linsr.main.model.FindPojo;
import com.linsr.main.model.HomePojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 推荐广告
 *
 * @author Linsr 2018/8/15 下午2:59
 */
public class RecommendADHolder extends BaseViewHolder<HomePojo.HomeListBean> {

    private TextView more;
    private ImageView pic1, pic2, pic3;

    public interface OnRecommendHolderListener {
        void onMoreClick(int position);
    }

    private OnRecommendHolderListener mOnRecommendHolderListener;

    public void setOnRecommendHolderListener(OnRecommendHolderListener onRecommendHolderListener) {
        mOnRecommendHolderListener = onRecommendHolderListener;
    }

    public RecommendADHolder(Context context, View itemView) {
        super(context, itemView);
        more = (TextView) findViewById(R.id.item_recommend_floor_more_tv);
        pic1 = (ImageView) findViewById(R.id.item_recommend_floor_pic_1);
        pic2 = (ImageView) findViewById(R.id.item_recommend_floor_pic_2);
        pic3 = (ImageView) findViewById(R.id.item_recommend_floor_pic_3);
    }

    @Override
    public void convert(final int position, HomePojo.HomeListBean data, int itemType) {
        List<HomePojo.HomeListBean.AdDataBean> list = new ArrayList<>();
        switch (itemType) {
            case Constants.FloorType.AD_TWO_DATA:
                list.addAll(data.getAdTwoData());
                break;
            case Constants.FloorType.AD_ONE_DATA:
                list.add(data.getAdOneData());
                break;
            case Constants.FloorType.AD_THREE_DATA:
                list.add(data.getAdThreeData());
                break;
        }
        setImage(list, 0, pic1);
        setImage(list, 1, pic2);
        setImage(list, 2, pic3);

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRecommendHolderListener != null) {
                    mOnRecommendHolderListener.onMoreClick(position);
                }
            }
        });
    }

    private void setImage(List<HomePojo.HomeListBean.AdDataBean> list, int index, ImageView iv) {
        if (list.size() > 0 && index < list.size() && list.get(index) != null) {
            ImageUtils.load(mContext, list.get(index).getAd_image(), iv);
        } else {
            iv.setVisibility(View.GONE);
        }
    }
}
