package com.linsr.common.net;

/**
 * Description
 * @author Linsr
 */
public class ApiException extends RuntimeException {
    private String mErrorMsg;

    public ApiException(String msg) {
        mErrorMsg = msg;
    }

    @Override
    public String getMessage() {
        return mErrorMsg;
    }

}