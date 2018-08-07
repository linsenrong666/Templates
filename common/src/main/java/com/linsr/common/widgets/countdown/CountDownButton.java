package com.linsr.common.widgets.countdown;

import android.content.Context;
import android.util.AttributeSet;

import com.linsr.common.utils.JLog;

/**
 * Description
 *
 * @author Linsr 2018/8/7 下午2:25
 */
public class CountDownButton extends android.support.v7.widget.AppCompatButton {

    public final Object mLock = new Object();

    public CountDownHandler mHandler;

    public CountDownButton(Context context) {
        this(context, null, 0);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final long interval = 1000;
        final long initTime = 60 * interval;
        mHandler = new CountDownHandler(this, initTime, interval);
        setText("获取验证码");
        setOnCountDownListener();
    }

    public void start() {
        synchronized (mLock) {
            if (mHandler.getCurMode() == CountDownHandler.STOP) {
                mHandler.sendEmptyMessage(CountDownHandler.RUNNING);
            } else {
                JLog.e("倒计时已经启动了");
            }
        }
    }

    public void stop() {
        mHandler.sendEmptyMessage(CountDownHandler.STOP);
    }

    private CountDownHandler.OnCountDownListener mProxy;

    private void setOnCountDownListener() {
        mHandler.setOnCountDownListener(new CountDownHandler.OnCountDownListener() {
            @Override
            public void onCountDown(long time) {
                setText("重新发送(" + time / 1000 + ")");
                if (mProxy != null) {
                    mProxy.onCountDown(time);
                }
            }

            @Override
            public void onStop() {
                setText("获取验证码");
                if (mProxy != null) {
                    mProxy.onStop();
                }
            }
        });
    }

    public void setOnCountDownListener(CountDownHandler.OnCountDownListener listener) {
        mProxy = listener;
    }

}
