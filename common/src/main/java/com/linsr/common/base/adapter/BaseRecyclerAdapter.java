package com.linsr.common.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linsr.common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * recycler adapter 基类
 *
 * @author Linsr 2018/6/6 下午1:43
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {


    public interface OnItemClickListener<T> {

        /**
         * 单击事件
         * @param holder view对象
         * @param position 位置
         * @param itemType view type
         * @param data 数据
         */
        void onItemClick(BaseViewHolder<T> holder, int position, int itemType, T data);
    }

    public interface OnLongClickListener<T> {

        /**
         * 单击事件
         * @param itemView view对象
         * @param position 位置
         * @param itemType view type
         * @param data 数据
         * @return true if the callback consumed the long click, false otherwise.
         */
        boolean onLongClick(View itemView, int position, int itemType, T data);
    }

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected String TAG;

    private OnItemClickListener<T> mOnItemClickListener;
    private OnLongClickListener<T> mOnLongClickListener;

    protected List<T> mList;

    public BaseRecyclerAdapter(Context context) {
        this(context, null);
    }

    public BaseRecyclerAdapter(Context context, List<T> list) {
        TAG = getClass().getCanonicalName();
        mList = list;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull final BaseViewHolder<T> holder, final int position) {
        final int itemViewType = getItemViewType(position);
        final T item = mList.get(position);
        holder.convert(position, item, itemViewType);

        setOnItemClickListener(holder, itemViewType, item);
        setOnLongClickListener(holder, itemViewType, item);
    }

    protected void setOnItemClickListener(final BaseViewHolder<T> holder,
                                          final int itemViewType,
                                          final T item) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder, holder.getAdapterPosition(),
                            itemViewType, item);
                }
            }
        });
    }

    protected void setOnLongClickListener(final BaseViewHolder<T> holder,
                                          final int itemViewType,
                                          final T item) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnLongClickListener != null) {
                    return mOnLongClickListener.onLongClick(holder.itemView,
                            holder.getAdapterPosition(), itemViewType, item);
                }
                return false;
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener<T> onLongClickListener) {
        mOnLongClickListener = onLongClickListener;
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 : mList.size();
    }

    public void addData(T data) {
        ensureDataNotNull();
        mList.add(data);
        notifyDataSetChanged();
    }

    public void addData(List<T> data) {
        ensureDataNotNull();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public void clear() {
        ensureDataNotNull();
        mList.clear();
        notifyDataSetChanged();
    }

    private void ensureDataNotNull() {
        if (null == mList) {
            mList = new ArrayList<>();
        }
    }

}
