package com.linsr.common.biz;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.linsr.common.base.BaseActivity;
import com.linsr.common.base.BasePresenter;
import com.linsr.common.base.BaseRepository;
import com.linsr.common.base.BaseView;

/**
 * 和业务相关的activity基类
 *
 * @author Linsr 2018/6/16 下午2:42
 */
public abstract class ActivityEx extends BaseActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOrientation();
    }

    /**
     * 设置屏幕方向
     */
    protected void setOrientation() {
        //默认设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }



}
