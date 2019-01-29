package com.linsr.main.logic.presenter;

import com.linsr.common.biz.PresenterEx;
import com.linsr.main.logic.contacts.AddressContact;

/**
 * Description
 *
 * @author Linsr 2019/1/28 下午11:51
 */
public class AddressPresenter extends PresenterEx<AddressContact.View>
        implements AddressContact.Presenter {

    public AddressPresenter(AddressContact.View IView) {
        super(IView);
    }
}
