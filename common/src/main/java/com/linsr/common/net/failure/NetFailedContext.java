package com.linsr.common.net.failure;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午8:34
 */
public class NetFailedContext {

    public NetFailedContext(Throwable throwable) {
        ApiFailureHandler apiFailureHandler = new ApiFailureHandler();
        NetErrorHandler errorHandler = new NetErrorHandler();
        apiFailureHandler.setNext(errorHandler);

        apiFailureHandler.handleFailure(throwable);
    }
}
