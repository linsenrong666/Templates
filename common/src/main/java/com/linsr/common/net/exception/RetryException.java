package com.linsr.common.net.exception;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午9:03
 */
public class RetryException extends Throwable {

    private int mRetryCount;

    public RetryException(int retryCount) {
        mRetryCount = retryCount;
    }

    @Override
    public String getMessage() {
        return "重试次数已超过设置次数 = " + mRetryCount + ",不再重试";
    }

}
