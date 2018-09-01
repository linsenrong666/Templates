package com.linsr.main.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.linsr.common.base.adapter.BaseRecyclerAdapter;
import com.linsr.common.base.adapter.BaseViewHolder;
import com.linsr.common.gui.widgets.StarBar;
import com.linsr.common.utils.JLog;
import com.linsr.main.R;
import com.linsr.main.model.CommentPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author Linsr 2018/9/1 下午12:46
 */
public class CommentAdapter extends BaseRecyclerAdapter<CommentPojo> {

    private SparseArray<TempPojo> mCache;

    public CommentAdapter(Context context) {
        super(context);
        mCache = new SparseArray<>();
    }

    @NonNull
    @Override
    public BaseViewHolder<CommentPojo> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mContext, mInflater.inflate(R.layout.main_item_comment, parent, false));
    }

    class TempPojo {
        String content;
        float score;

        @Override
        public String toString() {
            return "TempPojo{" +
                    "content='" + content + '\'' +
                    ", score=" + score +
                    '}';
        }
    }

    class Holder extends BaseViewHolder<CommentPojo> {
        private EditText editText;
        private StarBar mStarBar;

        Holder(Context context, View itemView) {
            super(context, itemView);
            editText = findViewById(R.id.item_comment_content_et);
            mStarBar = findViewById(R.id.item_comment_star_bar);
        }

        @Override
        public void convert(int position_, CommentPojo data, int itemType) {
            setIsRecyclable(false);
            final TempPojo pojo;
            if (mCache.get(getAdapterPosition()) != null) {
                pojo = mCache.get(getAdapterPosition());
            } else {
                pojo = new TempPojo();
                mCache.put(getAdapterPosition(), pojo);
            }

            mStarBar.setIntegerMark(StarBar.MarkType.HALF);
            mStarBar.setStarMark(pojo.score);
            editText.setText(pojo.content);

            mStarBar.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
                @Override
                public void onStarChange(float mark) {
                    pojo.score = mark;
                    mCache.put(getAdapterPosition(), pojo);
                }
            });

            //about set content
            if (editText.getTag() != null && (boolean) editText.getTag()) {
                return;
            }
            TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    pojo.content = s.toString();
                    mCache.put(getAdapterPosition(), pojo);
                }
            };
            editText.addTextChangedListener(watcher);
            editText.setTag(true);
        }

        private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        v.performClick();
                        break;
                }
                return false;
            }
        };
    }

}
