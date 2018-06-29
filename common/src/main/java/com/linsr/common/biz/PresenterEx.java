package com.linsr.common.biz;

import com.linsr.common.base.BaseRepository;
import com.linsr.common.base.BaseView;

/**
 * Description
 *
 * @author Linsr 2018/6/28 下午5:25
 */
public abstract class PresenterEx<V extends BaseView, R extends BaseRepository> {

    protected V mView;
    protected R mRepository;

    public PresenterEx(R repository, V view) {
        mView = view;
        mRepository = repository;
    }

    public void attach(V view) {
        mView = view;
    }

    public void detach() {
        mView = null;
    }

}
