package com.linsr.common.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.linsr.common.BuildConfig;
import com.linsr.common.utils.JLog;

/**
 * application基类
 *
 * @author Linsr 2018/6/16 上午11:10
 */
public class BaseApplication extends Application {

    private static BaseApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        //初始化日志工具
        JLog.init(isDebug());
        //初始化路由
        initRouter();
    }

    private void initRouter() {
        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(mApplication); // 尽可能早，推荐在Application中初始化
    }

    public boolean isDebug() {
        return true;
    }

    public static BaseApplication getInstance() {
        return mApplication;
    }
}
