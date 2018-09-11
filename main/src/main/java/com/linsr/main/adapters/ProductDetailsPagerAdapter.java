package com.linsr.main.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.List;

/**
 * Description
 *
 * @author Linsr 2018/9/11 上午10:28
 */
public class ProductDetailsPagerAdapter extends PagerAdapter {

    private String[] mTitles;
    private List<String> mList;
    private Context mContext;

    public ProductDetailsPagerAdapter(Context context, String[] titles, List<String> list) {
        mContext = context;
        mTitles = titles;
        mList = list;

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        WebView webView = new WebView(mContext);
        webView.loadUrl(mList.get(position));
        container.addView(webView);
        return webView;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null && mTitles.length > position) {
            return mTitles[position];
        }
        return super.getPageTitle(position);
    }

}
