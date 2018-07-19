package com.linsr.common.router;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;
import java.util.Map;

/**
 * 路由工具
 *
 * @author Linsr 2018/6/29 下午2:22
 */
public class Router {

    public static void startActivityForResult(Activity context,
                                              String activity,
                                              int requestCode,
                                              Map<String, Object> params) {
        Postcard build = ARouter.getInstance().build(activity);
        if (params != null && params.size() > 0) {
            build = handleParams(params, build);
        }
        build.navigation(context, requestCode);
    }

    public static void startActivity(String activity) {
        startActivity(activity, null);
    }

    public static void startActivity(String activity, Map<String, Object> params) {
        Postcard build = ARouter.getInstance().build(activity);
        if (params != null && params.size() > 0) {
            build = handleParams(params, build);
        }
        build.navigation();
    }

    public static Fragment findFragment(String fragment, Map<String, Object> params) {
        Postcard build = ARouter.getInstance().build(fragment);
        if (params != null && params.size() > 0) {
            build = handleParams(params, build);
        }
        return (Fragment) build.navigation();
    }

    public static Fragment findFragment(String fragment) {
        return findFragment(fragment, null);
    }

    public static void inject(Object o) {
        ARouter.getInstance().inject(o);
    }

    private static Postcard handleParams(Map<String, Object> params, Postcard build) {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                build.withString(key, (String) value);
            } else if (value instanceof Float) {
                build.withFloat(key, (Float) value);
            } else if (value instanceof Double) {
                build.withDouble(key, (Double) value);
            } else if (value instanceof Integer) {
                build.withInt(key, (Integer) value);
            } else if (value instanceof Boolean) {
                build.withBoolean(key, (Boolean) value);
            } else if (value instanceof Bundle) {
                build.withBundle(key, (Bundle) value);
            } else if (value instanceof Serializable) {
                build.withSerializable(key, (Serializable) value);
            } else {
                throw new RuntimeException("参数类型不支持,请补充参数类型.");
            }
        }
        return build;
    }
}
