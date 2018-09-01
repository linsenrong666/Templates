package com.linsr.common.utils;

import android.text.TextUtils;
import android.widget.Toast;

import com.linsr.common.base.BaseApplication;

/**
 * Description
 *
 * @author Linsr 2018/8/8 下午2:55
 */
public class ToastUtils {

    private static Toast toast;

    public static void show(int resId) {
        show(BaseApplication.getInstance().getResources().getText(resId), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration) {
        show(BaseApplication.getInstance().getResources().getText(resId), duration);
    }

    public static void show(CharSequence text) {
        show(text, Toast.LENGTH_SHORT);
    }

    public static void show(CharSequence text, int duration) {
        text = TextUtils.isEmpty(text == null ? "" : text.toString()) ? "请检查您的网络！" : text;
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance(), text, duration);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void show(int resId, Object... args) {
        show(String.format(BaseApplication.getInstance().getResources().getString(resId), args),
                Toast.LENGTH_SHORT);
    }

    public static void show(String format, Object... args) {
        show(String.format(format, args), Toast.LENGTH_SHORT);
    }

    public static void show(int resId, int duration, Object... args) {
        show(String.format(BaseApplication.getInstance().getResources().getString(resId), args),
                duration);
    }

    public static void show(String format, int duration, Object... args) {
        show(String.format(format, args), duration);
    }


}