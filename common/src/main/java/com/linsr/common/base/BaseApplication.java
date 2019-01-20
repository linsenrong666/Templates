package com.linsr.common.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.linsr.common.utils.JLog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.List;

/**
 * application基类
 *
 * @author Linsr 2018/6/16 上午11:10
 */
public class BaseApplication extends Application {

    protected String TAG;

    private static BaseApplication mApplication;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this); // Enable MultiDex.
    }

    @Override
    public void onCreate() {
        TAG = getClass().getSimpleName();
        JLog.i(TAG, "\n\n\n######################################################################################" +
                "启动完成######################################################################################");
        isInMainProcesses();
        super.onCreate();
        mApplication = this;
        //初始化日志工具
        JLog.init(isDebug());
        //初始化路由
        initRouter();
        //初始化内存泄漏分析工具
        initLeakCanary();
        //注册生命周期监听
        registerActivityLifecycleCallbacks(AppLife.getInstance());
    }

    private RefWatcher mRefWatcher;

    private void initLeakCanary() {
        mRefWatcher = LeakCanary.install(this);
    }

    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    /**
     * Application 是否在主进程
     * @return 是否在主进程
     */
    protected boolean isInMainProcesses() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();

        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            JLog.i(TAG, "my.pid -> " + myPid + ",mainProcessName -> " + mainProcessName);
            JLog.i(TAG, "info.pid -> " + info.pid + ",info.processName -> " + info.processName);

            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
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
