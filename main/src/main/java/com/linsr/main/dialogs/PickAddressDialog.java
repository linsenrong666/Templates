package com.linsr.main.dialogs;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.linsr.common.utils.JLog;
import com.linsr.main.R;
import com.ywp.addresspickerlib.AddressPickerView;

/**
 * Description
 *
 * @author Linsr 2019/1/26 下午3:11
 */
public class PickAddressDialog extends DialogFragment {

    public interface OnAddressPickListener {
        void onPick(String province, String city, String district);
    }

    public static PickAddressDialog newInstance() {

        Bundle args = new Bundle();

        PickAddressDialog fragment = new PickAddressDialog();
        fragment.setArguments(args);
        return fragment;
    }

    private OnAddressPickListener mOnAddressPickListener;

    public void setOnAddressPickListener(OnAddressPickListener onAddressPickListener) {
        mOnAddressPickListener = onAddressPickListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        View view = inflater.inflate(R.layout.main_dialog_pick_address, container, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        AddressPickerView mPicker = view.findViewById(R.id.dialog_pick_address_view);
        mPicker.setOnAddressPickerSure(new AddressPickerView.OnAddressPickerSureListener() {

            @Override
            public void onSureClick(String address, String provinceCode, String cityCode, String districtCode) {
                if (mOnAddressPickListener != null && !TextUtils.isEmpty(address)) {
                    try {
                        String[] split = address.split(" ");
                        mOnAddressPickListener.onPick(split[0], split[1], split[2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return view;
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
