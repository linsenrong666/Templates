package com.linsr.common.net.failure;

import com.linsr.common.net.exception.ApiException;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午8:26
 */
public class ApiFailureHandler extends AbsFailedHandler {


    @Override
    public boolean handleFailure(Throwable e) {
        if (e instanceof ApiException) {

            return false;
        } else {
            nextHandle(e);
        }
        return false;
    }
}
