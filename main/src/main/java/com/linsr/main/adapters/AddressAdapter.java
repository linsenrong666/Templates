package com.linsr.main.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.main.R;
import com.linsr.main.model.AddressPojo;

/**
 * Description
 *
 * @author Linsr 2018/8/21 上午9:58
 */
public class AddressAdapter extends BaseRecyclerAdapter<AddressPojo> {
    public AddressAdapter(Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseViewHolder<AddressPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_address, parent, false));
    }

    class Holder extends BaseViewHolder<AddressPojo> {
        private TextView address;
        private TextView name;
        private CheckBox checkBox;
        private TextView phone;
        private ImageView edit;

        public Holder(Context context, View itemView) {
            super(context, itemView);
            address = findViewById(R.id.item_address_content_tv);
            name = findViewById(R.id.item_address_name_tv);
            phone = findViewById(R.id.item_address_phone_tv);
            checkBox = findViewById(R.id.item_address_check_box);
            edit = findViewById(R.id.item_address_edit_iv);
        }

        @Override
        public void convert(int position, AddressPojo data, int itemType) {
            address.setText(data.getAddress());
            name.setText(data.getUserName());
            phone.setText(data.getPhoneNumber());
        }
    }
}
