package com.example.caowj.newtask.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caowj.newtask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * package: com.example.caowj.newtask.example.adapter
 * author: Administrator
 * date: 2017/9/1 14:44
 */
public class FunctionListAdapter extends RecyclerView.Adapter {
    //    private List<String> mList = new ArrayList<>();
    private SparseArray<Class> sparseArray;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public FunctionListAdapter(Context mContext, SparseArray<Class> sparseArray1) {
        this.mContext = mContext;
//        this.mList = mList == null ? new ArrayList<String>() : mList;
        sparseArray = sparseArray1 == null ? new SparseArray<Class>() : sparseArray1;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FunctionViewHolder(mLayoutInflater.inflate(R.layout.item_function_list, parent, false));
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
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public FunctionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
