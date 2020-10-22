package com.kedacom.module_main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.kedacom.module_common.bean.main.UserInfo;
import com.kedacom.module_lib.utils.LogUtil;
import com.kedacom.module_main.R;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/22 13:13
 */
public class MainAdapter extends RecyclerView.Adapter {
    //    private List<String> mList = new ArrayList<>();
    private SparseArray<String> sparseArray;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public MainAdapter(Context mContext, SparseArray<String> sparseArray1) {
        this.mContext = mContext;
        sparseArray = sparseArray1 == null ? new SparseArray<String>() : sparseArray1;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FunctionViewHolder(mLayoutInflater.inflate(R.layout.item_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String routePath = sparseArray.get(position);
        FunctionViewHolder fHolder = (FunctionViewHolder) holder;
        fHolder.tvTitle.setText(routePath);
        if (position % 3 == 0) {
            fHolder.itemView.setBackgroundColor(Color.parseColor("#fa8072"));
        } else {
            fHolder.itemView.setBackgroundColor(Color.parseColor("#354825"));
        }

        fHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserInfo userInfo = new UserInfo(12, "caowj", "留园");

                if (position == 0) {
                    ARouter.getInstance().build(routePath)
                            .withString("name", "曹维健")
                            .withLong("age", 25)
                            .withParcelable("userInfo", userInfo)
                            .navigation();
                } else {
                    //简单写法：
//                    ARouter.getInstance().build(routePath).navigation();

                    //监听过程：
                    ARouter.getInstance().build(routePath).navigation(mContext, new NavCallback() {

                        @Override
                        public void onFound(Postcard postcard) {
                            LogUtil.myD("onFound: 找到了 ");
                        }

                        @Override
                        public void onLost(Postcard postcard) {
                            LogUtil.myD("onLost: 找不到了 ");
                        }

                        @Override
                        public void onArrival(Postcard postcard) {
                            LogUtil.myD("onArrival: 跳转完了 ");
                        }

                        @Override
                        public void onInterrupt(Postcard postcard) {
                            LogUtil.myD("onInterrupt: 被拦截了 ");
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return sparseArray.size();
    }

    class FunctionViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public FunctionViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.textView);
        }
    }
}