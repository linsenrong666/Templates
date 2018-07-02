package com.linsr.common.utils;

import android.content.Context;

import net.grandcentrix.tray.TrayPreferences;


/**
 * 工具类
 * 支持跨进程的share preferences
 */
public class PrefsUtils {

    private static final int VERSION = 1;
    private static volatile Preferences mPrefs;

    private static class Preferences extends TrayPreferences {

        public Preferences(Context context) {
            super(context, context.getPackageName(), VERSION);
        }
    }

    private PrefsUtils(Context context) {
    }

    private static Preferences getPrefs(Context context) {
        if (mPrefs == null) {
            synchronized (PrefsUtils.class) {
                if (mPrefs == null) {
                    mPrefs = new Preferences(context);
                }
            }
        }
        return mPrefs;
    }


    /**
     * 设置可被多个进程共享的Boolean值
     */
    public static void putSharedBoolean(Context context, String key, boolean value) {
        Preferences prefs = getPrefs(context);
        prefs.put(key,value);
    }

    /**
     * 设置可被多个进程共享的Int值
     */
    public static void putSharedInt(Context context, String key, int value) {
        Preferences prefs = getPrefs(context);
        prefs.put(key,value);
    }

    /**
     * 设置可被多个进程共享的Long值
     */
    public static void putSharedLong(Context context, String key, long value) {
        Preferences prefs = getPrefs(context);
        prefs.put(key,value);
    }

    /**
     * 设置可被多个进程共享的String值
     */
    public static void putSharedString(Context context, String key, String value) {
        Preferences prefs = getPrefs(context);
        prefs.put(key,value);
    }

    /**
     * 获取可被多个进程共享的Boolean值,缺省值为false
     */
    public static boolean getSharedBoolean(Context context, String key) {
        return getSharedBoolean(context, key, false);
    }

    /**
     * 获取可被多个进程共享的Boolean值,若key不存在,则返回defaultValue
     */
    public static boolean getSharedBoolean(Context context, String key, boolean defaultValue) {
        Preferences prefs = getPrefs(context);
        return prefs.getBoolean(key, defaultValue);
    }

    /**
     * 获取可被多个进程共享的Int值,若key不存在,则返回0
     */
    public static int getSharedInt(Context context, String key) {
        return getSharedInt(context, key, 0);
    }

    /**
     * 获取可被多个进程共享的Int值,若key不存在,则返回defaultValue
     */
    public static int getSharedInt(Context context, String key, int defaultValue) {
        Preferences prefs = getPrefs(context);
        return prefs.getInt(key, defaultValue);
    }

    /**
     * 获取可被多个进程共享的Long值,若key不存在,则返回0
     */
    public static long getSharedLong(Context context, String key) {
        return getSharedLong(context, key, 0);
    }

    /**
     * 获取可被多个进程共享的Long值,若key不存在,则返回defaultValue
     */
    public static long getSharedLong(Context context, String key, long defaultValue) {
        Preferences prefs = getPrefs(context);
        return prefs.getLong(key, defaultValue);
    }

    /**
     * 获取可被多个进程共享的Int值,若key不存在,则返回null
     */
    public static String getSharedString(Context context, String key) {
        return getSharedString(context, key, null);
    }

    /**
     * 获取可被多个进程共享的Int值,若key不存在,则返回defaultValue
     */
    public static String getSharedString(Context context, String key, String defaultValue) {
       Preferences prefs = getPrefs(context);
        return prefs.getString(key, defaultValue);
    }

    public static void remove(Context context, String key) {
        Preferences prefs = getPrefs(context);
        if (key != null) {
            prefs.remove(key);
        }
    }

    /**
     * 清除配置文件
     */
    public static void clear(Context context) {
        Preferences prefs = getPrefs(context);
        prefs.clear();
    }

}