package com.linsr.main.activities;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.url.MainModule;
import com.linsr.main.R;

/**
 * Description
 *
 * @author Linsr 2018/9/1 下午5:47
 */
@Route(path = MainModule.Activity.COMMENT_RESULT)
public class CommentResultActivity extends ActivityEx {
    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_comment_result;
    }

    @Override
    protected void initView() {
        initTitleViewWithRightText(R.string.main_thank_comment, R.string.finish, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        TextView tv = findViewById(R.id.result_text_tv);

    }
}
