package com.linsr.common.net.failure;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午8:29
 */
public class NetErrorHandler extends AbsFailedHandler {

    @Override
    public boolean handleFailure(Throwable e) {
        return false;
    }
}
