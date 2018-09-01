package com.linsr.main.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.CommentAdapter;
import com.linsr.main.model.CommentPojo;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/9/1 下午12:44
 */
@Route(path = MainModule.Activity.COMMENT)
public class CommentActivity extends ActivityEx {

    private RecyclerView mRecyclerView;
    private CommentAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_comment;
    }

    @Override
    protected void initView() {
        initTitleViewWithRightText(R.string.main_publish_comment,
                R.string.main_publish,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        initRecyclerView();
        initData();
    }

    private void initData() {
        mAdapter.addData(Mock.getList(10, CommentPojo.class));
    }

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.comment_recycler_view);
        mAdapter = new CommentAdapter(this);
        RecyclerViewHelper.initDefault(this, mRecyclerView, mAdapter);
    }
}
