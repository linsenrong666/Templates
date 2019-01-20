package com.linsr.common.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.LinkedList;

/**
 * Description
 *
 * @author Linsr 2019/1/20 下午1:50
 */
public class AppLife implements Application.ActivityLifecycleCallbacks {

    private final LinkedList<Activity> mActivities = new LinkedList<>();

    private AppLife() {
    }

    private volatile static AppLife mInstance;

    public static AppLife getInstance() {
        if (mInstance == null) {
            synchronized (AppLife.class) {
                if (mInstance == null) {
                    mInstance = new AppLife();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mActivities.addFirst(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        mActivities.remove(activity);
    }

    /**
     * 清除 除了自己外其他activity
     *  @param oneself 不被移除的activity
     */
    public void removeOtherActivity(Activity oneself) {
        try {
            for (Activity activity : mActivities) {
                if (activity != null && !activity.getLocalClassName().equals(oneself.getLocalClassName())) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 退出应用时调用
     */
    public void exit() {
        for (Activity activity : mActivities) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

}
