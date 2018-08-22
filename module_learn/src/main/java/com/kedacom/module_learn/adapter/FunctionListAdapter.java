package com.kedacom.module_learn.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Dec ：
 * @Author : Caowj
 * @Date : 2018/8/22 14:28
 */
public class FunctionListAdapter extends RecyclerView.Adapter {
    private SparseArray<Class> sparseArray;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public FunctionListAdapter(Context mContext, SparseArray<Class> sparseArray1) {
        this.mContext = mContext;
        sparseArray = sparseArray1 == null ? new SparseArray<Class>() : sparseArray1;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FunctionViewHolder(mLayoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Class className = sparseArray.get(position);
        FunctionViewHolder fHolder = (FunctionViewHolder) holder;
        fHolder.tvTitle.setText(className.getSimpleName());
        if (position % 3 == 0) {
            fHolder.itemView.setBackgroundColor(Color.parseColor("#fa8072"));
        } else {

        }

        fHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, className);
//                mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sparseArray.size();
    }

    public void setSparseArray(SparseArray<Class> sparseArray) {
        this.sparseArray = sparseArray;
    }

    class FunctionViewHolder extends RecyclerView.ViewHolder {
        @BindView(android.R.id.text1)
        TextView tvTitle;

        public FunctionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


//    class FunctionViewHolder extends RecyclerView.ViewHolder {
//        TextView tvTitle;
//
//        public FunctionViewHolder(View view) {
//            super(view);
//            tvTitle = view.findViewById(android.R.id.text1);
//        }
//    }
}