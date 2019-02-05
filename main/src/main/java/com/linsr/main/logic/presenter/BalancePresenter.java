package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.main.data.remote.CartRequest;
import com.linsr.main.logic.contacts.BalanceContact;

/**
 * Description
 *
 * @author Linsr 2019/2/5 下午3:23
 */
public class BalancePresenter extends PresenterEx<BalanceContact.View>
        implements BalanceContact.Presenter {

    public BalancePresenter(BalanceContact.View IView) {
        super(IView);

    }

    @Override
    public void checkOut() {
        CartRequest.settleList(getLifecycleOwner(),
                new NetObserver<BizPojo>(mView, true, true) {
                    @Override
                    public void onSucceed(BizPojo data) {

                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }
}
