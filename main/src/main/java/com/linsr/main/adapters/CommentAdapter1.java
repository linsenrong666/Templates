//package com.linsr.main.adapters;
//
//import android.content.Context;
//import android.support.annotation.NonNull;
//import android.support.v7.widget.RecyclerView;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//
//import com.linsr.common.base.adapter.BaseRecyclerAdapter;
//import com.linsr.common.base.adapter.BaseViewHolder;
//import com.linsr.common.gui.widgets.StarBar;
//import com.linsr.main.R;
//import com.linsr.main.app.Constants;
//import com.linsr.main.model.CommentPojo;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Description
// *
// * @author Linsr 2018/9/1 下午12:46
// */
//public class CommentAdapter1 extends RecyclerView.Adapter<CommentAdapter1.Holder> {
//
//    private SparseArray<TempPojo> mCache;
//
//    private Context mContext;
//    private List<CommentPojo> mList;
//
//    public CommentAdapter1(Context context) {
//        mContext = context;
//        mCache = new SparseArray<>();
//    }
//
//    public void addData(List<CommentPojo> list) {
//        if (mList == null) {
//            mList = new ArrayList<>();
//
//        }
//        mList.addAll(list);
//    }
//
//    @NonNull
//    @Override
//    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new Holder(LayoutInflater.from(mContext).inflate(R.layout.main_item_comment, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final Holder holder, int position) {
//        holder.setIsRecyclable(false);
//        final TempPojo pojo;
//        if (mCache.get(holder.getAdapterPosition()) != null) {
//            pojo = mCache.get(holder.getAdapterPosition());
//        } else {
//            pojo = new TempPojo();
//            mCache.put(holder.getAdapterPosition(), pojo);
//        }
//
//        holder.mStarBar.setIntegerMark(true);
//        holder.mStarBar.setStarMark(pojo.score);
//        holder.editText.setText(pojo.content);
//
//        holder.mStarBar.setOnStarChangeListener(new StarBar.OnStarChangeListener() {
//            @Override
//            public void onStarChange(float mark) {
//                pojo.score = mark;
//                mCache.put(holder.getAdapterPosition(), pojo);
//            }
//        });
//        //about set content
//        if (holder.editText.getTag() != null && (boolean) holder.editText.getTag()) {
//            return;
//        }
//        TextWatcher watcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                pojo.content = s.toString();
//                mCache.put(holder.getAdapterPosition(), pojo);
//            }
//        };
//        holder.editText.addTextChangedListener(watcher);
//        holder.editText.setTag(true);
//    }
//
//    @Override
//    public int getItemCount() {
//        return mList == null ? 0 : mList.size();
//    }
//
//    class TempPojo {
//        String content;
//        float score;
//
//        @Override
//        public String toString() {
//            return "TempPojo{" +
//                    "content='" + content + '\'' +
//                    ", score=" + score +
//                    '}';
//        }
//    }
//
//    class Holder extends RecyclerView.ViewHolder {
//        private EditText editText;
//        private StarBar mStarBar;
//
//        Holder(View itemView) {
//            super(itemView);
//            editText = itemView.findViewById(R.id.item_comment_content_et);
//            mStarBar = itemView.findViewById(R.id.item_comment_star_bar);
//        }
//
////        private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
////            @Override
////            public boolean onTouch(View v, MotionEvent event) {
////                switch (event.getAction()) {
////                    case MotionEvent.ACTION_DOWN:
////                        v.getParent().requestDisallowInterceptTouchEvent(true);
////                        break;
////                    case MotionEvent.ACTION_MOVE:
////                        break;
////                    case MotionEvent.ACTION_UP:
////                        v.getParent().requestDisallowInterceptTouchEvent(false);
////                        v.performClick();
////                        break;
////                }
////                return false;
////            }
////        };
//    }
//
//}
