package com.linsr.main.activities;

import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.gui.divider.GridDivider;
import com.linsr.common.gui.divider.RecycleViewDivider;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.exception.ApiException;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.PageLoadHelper;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.CollectAdapter;
import com.linsr.main.adapters.RecommendGoodsAdapter;
import com.linsr.main.data.remote.UserRequest;
import com.linsr.main.model.CollectPojo;
import com.linsr.main.utils.Mock;
import com.linsr.main.utils.ProductDetailsHelper;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

/**
 * Description
 *
 * @author Linsr 2018/9/27 下午1:17
 */
@Route(path = MainModule.Activity.MY_COLLECT)
public class CollectActivity extends RefreshActivity {

    private CollectAdapter mAdapter;

    @Override
    protected void initData() {
        request(true);
    }

    @Override
    protected void initRecyclerView(RecyclerView recyclerView) {
        initTitleView(R.string.main_my_collect);
        mAdapter = new CollectAdapter(this);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<CollectPojo.ListBean>() {
            @Override
            public void onItemClick(BaseViewHolder<CollectPojo.ListBean> holder,
                                    int position, int itemType, CollectPojo.ListBean data) {
                ProductDetailsHelper.startActivity(data.getGoods_id());
            }
        });
        RecyclerViewHelper.initGridLayout(this, 2, recyclerView, mAdapter);
    }

    @Override
    protected void requestData(RefreshLayout refreshLayout) {
        request(false);
    }

    private void request(boolean showLoading) {
        UserRequest.collectList(this, mPageIndex, mPageSize,
                new NetObserver<CollectPojo>(this, showLoading, true) {

                    @Override
                    public void onSucceed(CollectPojo data) {
                        if (data != null) {
                            mPageIndex = PageLoadHelper.onSuccess(mPageIndex, mAdapter, data.getList(), CollectActivity.this);
                        } else {
                            onFailed(new ApiException(""));
                        }
                    }

                    @Override
                    public void onFailed(Throwable e) {
                        mPageIndex = PageLoadHelper.onFailure(mPageIndex, CollectActivity.this);
                    }
                });
    }
}
