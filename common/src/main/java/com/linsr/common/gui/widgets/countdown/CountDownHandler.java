package com.linsr.common.gui.widgets.countdown;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * Description
 *
 * @author Linsr 2018/8/7 下午3:38
 */
public class CountDownHandler extends Handler {
    static final int RUNNING = 0;
    static final int STOP = 1;

    public interface OnCountDownListener {
        void onCountDown(long time);

        void onStop();
    }

    private long mInitTime = 10 * 1000;
    private long mInterval = 1000;
    private int curMode = STOP;
    private long curTime;
    private TextView mButton;
    private OnCountDownListener mOnCountDownListener;

    CountDownHandler(TextView button, long initTime, long interval) {
        mButton = button;
        mInitTime = initTime;
        mInterval = interval;
        if (mInitTime <= 0 || mInitTime < interval) {
            throw new RuntimeException("初始时间不能小于0或者小于间隔时间");
        }
    }


    void setOnCountDownListener(OnCountDownListener onCountDownListener) {
        mOnCountDownListener = onCountDownListener;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case RUNNING:
                curMode = RUNNING;
                mButton.setClickable(false);
                mButton.setEnabled(false);
                curTime = curTime + mInterval;
                if (curTime < mInitTime) {
                    long time = mInitTime - curTime;
                    if (mOnCountDownListener != null) {
                        mOnCountDownListener.onCountDown(time);
                    }
                    sendEmptyMessageDelayed(RUNNING, mInterval);
                } else {
                    sendEmptyMessage(STOP);
                }
                break;
            case STOP:
                curMode = STOP;
                mButton.setClickable(true);
                mButton.setEnabled(true);
                curTime = 0;
                removeCallbacksAndMessages(null);
                if (mOnCountDownListener != null) {
                    mOnCountDownListener.onStop();
                }
                break;
            default:
                break;
        }
    }

    public int getCurMode() {
        return curMode;
    }
}