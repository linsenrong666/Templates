package com.linsr.books;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.BaseFragment;
import com.linsr.common.biz.ApplicationEx;
import com.linsr.common.biz.EventKey;
import com.linsr.common.router.url.BookModule;
import com.linsr.common.utils.JLog;
import com.linsr.common.utils.contents.AbstractOnContentUpdateListener;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/6/16 下午3:16
 */
@Route(path = BookModule.Fragment.TEST1)
public class BooksFragment extends BaseFragment {

    String aa;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initArguments(Bundle arguments) {
        aa = arguments.getString("aa");
        registerOnContentUpdateListener(new AbstractOnContentUpdateListener() {
            @Override
            public void onContentUpdated(List<Object[]> values) {
                JLog.e("fragment+++++++++++++++++++++++:" + aa);
            }

            @Override
            public boolean isActive() {
                return mIsVisible;
            }

            @Override
            public String getKey() {
                return EventKey.aa;
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(ApplicationEx.getInstance());
        textView.setText("booksFragment," + aa);
        return textView;
    }

    @Override
    protected void loadData() {
        super.loadData();
    }

    @Override
    protected void onVisible() {
        super.onVisible();
        JLog.i(TAG, "====BooksFragment:" + aa);
    }
}
