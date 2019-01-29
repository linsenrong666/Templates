package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.common.model.BizPojo;
import com.linsr.common.net.callback.NetObserver;
import com.linsr.common.net.exception.ApiException;
import com.linsr.main.data.remote.UserRequest;
import com.linsr.main.logic.contacts.MeContact;
import com.linsr.main.model.OrderPojo;
import com.linsr.main.model.UserPojo;

/**
 * Description
 *
 * @author Linsr 2019/1/6 下午9:01
 */
public class MePresenter extends PresenterEx<MeContact.View> implements MeContact.Presenter {

    public MePresenter(MeContact.View IView) {
        super(IView);
    }

    @Override
    public void userCenter() {
        UserRequest.userCenter(getLifecycleOwner(),
                new NetObserver<UserPojo>(mView, true, true) {
                    @Override
                    public void onSucceed(UserPojo data) {
                        if (data != null) {
                            mView.fillMeTopData(data);
                            mView.fillRecName(data.getUser_info().getRec_nickname());
                        } else {
                            onFailed(new ApiException(""));
                        }
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }

    @Override
    public void logout() {
        UserRequest.logout(getLifecycleOwner(),
                new NetObserver<BizPojo>(mView, true, true) {
                    @Override
                    public void onSucceed(BizPojo data) {
                        mView.logout();
                    }

                    @Override
                    public void onFailed(Throwable e) {

                    }
                });
    }
}
