package com.linsr.common.base;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Fragment 基类
 *
 * @author Linsr 2018/6/16 上午11:18
 */
public abstract class BaseFragment extends Fragment implements EasyPermissions.PermissionCallbacks {

    private Activity mActivity;
    protected String TAG;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        TAG = getClass().getSimpleName();
        mActivity = getActivity();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}
