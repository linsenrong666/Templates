package com.linsr.books;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.base.BaseFragment;
import com.linsr.common.biz.ApplicationEx;

/**
 * Description
 *
 * @author Linsr 2018/6/16 下午3:16
 */
@Route(path = "/books/booksFragment")
public class BooksFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(ApplicationEx.getInstance());
        textView.setText("booksFragment");
        return textView;
    }
}
