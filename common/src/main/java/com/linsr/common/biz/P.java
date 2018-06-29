package com.linsr.common.biz;

import com.linsr.common.base.BaseRepository;

/**
 * Description
 *
 * @author Linsr 2018/6/28 下午3:58
 */
public class P extends PresenterEx<Constact.View, BaseRepository> implements Constact.Persenter {

    public P(BaseRepository repository, Constact.View view) {
        super(repository, view);
    }

    @Override
    public void start() {

    }

}
