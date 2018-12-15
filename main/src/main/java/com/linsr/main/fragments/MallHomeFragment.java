package com.linsr.main.fragments;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.adapters.HomeAdapter;
import com.linsr.main.app.Constants;
import com.linsr.main.model.HomePojo;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/9/4 下午5:01
 */
@Route(path = MainModule.Fragment.MALL_HOME)
public class MallHomeFragment extends RefreshFragment {

    private HomeAdapter mAdapter;

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        mAdapter = new HomeAdapter(mActivity);
        List<HomePojo> findList = Mock.getFindList(10);
        RecyclerViewHelper.initGridLayout(mActivity, 6, recyclerView, mAdapter);
        mAdapter.addData(findList);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder holder, int position, int itemType, Object data) {
                if (itemType == Constants.FloorType.FLASH_SALE) {
                    Router.startActivity(MainModule.Activity.FLASH_SALE);
                }
                if (itemType == Constants.FloorType.DAILY_NEW) {
                    Router.startActivity(MainModule.Activity.DAILY_NEW);
                }
            }
        });
    }

    @Override
    protected void requestData(RefreshLayout refreshLayout) {

    }

}
