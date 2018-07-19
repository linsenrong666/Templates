package com.linsr.main.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.BaseActivity;
import com.linsr.common.biz.EventKey;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.Permissions;
import com.linsr.common.utils.RecyclerViewHepler;
import com.linsr.main.R;
import com.linsr.main.activities.CaptureActivity;
import com.linsr.main.adapters.GoodsAdapter;
import com.linsr.main.contacts.HomeContact;
import com.linsr.main.model.HomePojo;
import com.linsr.main.utils.Mock;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Description
 *
 * @author Linsr 2018/7/10 下午5:59
 */
@Route(path = MainModule.Fragment.HOME)
public class HomeFragment extends FragmentEx implements HomeContact.View {

    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mRefreshLayout;
    private GoodsAdapter mAdapter;
    private TextView mScanCodeTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_home;
    }

    @Override
    protected void initArguments(Bundle arguments) {

    }

    @Override
    protected void initView() {
        mRefreshLayout = findViewById(R.id.main_refresh_layout);

        mRecyclerView = findViewById(R.id.main_recycler_view);
        mAdapter = new GoodsAdapter(mActivity);
        RecyclerViewHepler.initDefault(mActivity, mRecyclerView, mAdapter);
        mAdapter.setOnGoodsClickListener(new GoodsAdapter.OnGoodsClickListener() {
            @Override
            public void onAdd(int position) {
                JLog.e(TAG, "on add position :" + position);
                mContentsManager.notifyContentUpdateSuccess(EventKey.ADD_GOODS_CART);
            }
        });

        mScanCodeTextView = findViewById(R.id.main_scan_code_tv);
        mScanCodeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (requestPermission()) {
                    Router.startActivityForResult(mActivity, MainModule.Activity.SCAN_CODE, 200, null);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String stringExtra = data.getStringExtra(CaptureActivity.EXTRA_RESULT);
            JLog.e(TAG, "text:"+stringExtra);
        }
    }

    @Override
    protected void loadData() {
        super.loadData();
        List<HomePojo> goodsList = Mock.getGoodsList(20);
        mAdapter.addData(goodsList);
    }

    @AfterPermissionGranted(Permissions.PERMISSION_CAMERA)
    private boolean requestPermission() {
        if (EasyPermissions.hasPermissions(mActivity, Permissions.PERMISSIONS_CAMERA)) {
            return true;
        } else {
            EasyPermissions.requestPermissions(this, "扫码需要相机权限",
                    Permissions.PERMISSION_CAMERA, Permissions.PERMISSIONS_CAMERA);
            return false;
        }
    }
}
