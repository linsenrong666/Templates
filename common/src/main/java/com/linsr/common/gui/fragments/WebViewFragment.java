package com.linsr.common.gui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.R;
import com.linsr.common.biz.FragmentEx;
import com.linsr.common.gui.widgets.WebViewEx;
import com.linsr.common.router.url.CommonModule;

/**
 * Description
 *
 * @author Linsr 2018/9/11 下午2:52
 */
@Route(path = CommonModule.Fragment.WEB_VIEW)
public class WebViewFragment extends FragmentEx implements CommonModule.Fragment.WebViewParams {

    private String mUrl;
    private WebViewEx mWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.common_fragment_web_view;
    }

    @Override
    protected void initArguments(Bundle arguments) {
        if (arguments != null) {
            mUrl = arguments.getString(URL);
        }
    }

    @Override
    protected void initView() {
        mWebView = findViewById(R.id.web_view);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });
        if (!TextUtils.isEmpty(mUrl)) {
            mWebView.loadUrl(mUrl);
        }
    }
}
