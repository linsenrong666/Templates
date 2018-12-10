package com.linsr.common.net.failure;

/**
 * Description
 *
 * @author Linsr 2018/12/9 下午8:25
 */
public abstract class AbsFailedHandler {
    protected AbsFailedHandler next;

    public abstract boolean handleFailure(Throwable e);

    public void setNext(AbsFailedHandler next) {
        this.next = next;
    }

    protected void nextHandle(Throwable e) {
        if (next != null) {
            next.handleFailure(e);
        }
    }
}
