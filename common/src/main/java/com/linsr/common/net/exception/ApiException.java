package com.linsr.common.net.exception;

/**
 * Description
 * @author Linsr
 */
public class ApiException extends Exception {
    private String mErrorMsg;

    public ApiException(String msg) {
        mErrorMsg = msg;
    }

    @Override
    public String getMessage() {
        return mErrorMsg;
    }

}