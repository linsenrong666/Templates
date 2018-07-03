package com.linsr.books;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.linsr.common.base.BaseActivity;
import com.linsr.common.router.url.BookModule;
import com.linsr.common.utils.PrefsUtils;

@Route(path = BookModule.Activity.MAIN)
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.books_activity_main);

        TextView tv = findViewById(R.id.books_tv);
        PrefsUtils.putSharedString(this,"booksName","i am books module！\nwelcome！");
        tv.setText(PrefsUtils.getSharedString(this,"booksName"));

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContentsManager.notifyContentUpdateSuccess("aa");
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/books/books2").navigation();
            }
        });
    }
}
