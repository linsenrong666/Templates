package com.linsr.main.activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linsr.common.biz.ActivityEx;
import com.linsr.common.router.Router;
import com.linsr.common.router.url.MainModule;
import com.linsr.common.utils.RecyclerViewHelper;
import com.linsr.main.R;
import com.linsr.main.adapters.AddressAdapter;
import com.linsr.main.utils.Mock;

/**
 * Description
 *
 * @author Linsr 2018/8/21 上午9:46
 */
@Route(path = MainModule.Activity.ADDRESS_LIST)
public class AddressActivity extends ActivityEx {

    private RecyclerView mRecyclerView;
    private AddressAdapter mAdapter;
    private Button mAddButton;

    @Override
    protected int getLayoutId() {
        return R.layout.main_activity_address_list;
    }

    @Override
    protected void initView() {
        initTitleView(R.string.main_address_manage);
        mRecyclerView = findViewById(R.id.address_recycler_view);
        mAdapter = new AddressAdapter(this);
        RecyclerViewHelper.initDefault(this, mRecyclerView, mAdapter, true);

        mAddButton = findViewById(R.id.address_add_btn);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Router.startActivity(MainModule.Activity.ADD_ADDRESS);
            }
        });

    }

}
