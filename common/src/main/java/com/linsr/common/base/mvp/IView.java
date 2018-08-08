package com.linsr.common.base.mvp;

/**
 * Description
 *
 * @author Linsr 2018/7/9 下午4:41
 */
public interface IView {

    void showNoData();

    void hideNoData();

    void showLoading();

    void hideLoading();

    void showError(String text);
}
