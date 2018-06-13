package com.example.caowj.newtask.example.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.mvp.constants.TypeConstants;
import com.kedacom.utils.JudgmentDataUtil;
import com.kedacom.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * package: com.example.caowj.newtask.example.adapter
 * author: Administrator
 * date: 2017/9/4 15:09
 */
public class TaoBaoDetailAdater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> dataList1 = new ArrayList<>();
    private List<String> dataList2 = new ArrayList<>();
    private List<String> dataList3 = new ArrayList<>();

    public TaoBaoDetailAdater(List<String> datas) {
        this.dataList1 = datas;
    }

    public void setDataList1(List<String> dataList1) {
        this.dataList1 = dataList1;
    }

    public void setDataList2(List<String> dataList2) {
        this.dataList2 = dataList2;
    }

    public void setDataList3(List<String> dataList3) {
        this.dataList3 = dataList3;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view;
        if (viewType == TypeConstants.TYPE_ITEM_FIRST) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_swipe, viewGroup, false);
            return new ViewHolder1(view);
        } else if (viewType == TypeConstants.TYPE_ITEM_SECOND) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_swipe, viewGroup, false);
            return new ViewHolder2(view);
        } else if (viewType == TypeConstants.TYPE_ITEM_THIRD) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_swipe, viewGroup, false);
            return new ViewHolder3(view);
        } else {
            return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isFirstType(position)) {
            return TypeConstants.TYPE_ITEM_FIRST;
        } else if (isSecondType(position)) {
            return TypeConstants.TYPE_ITEM_SECOND;
        } else if (isThirdType(position)) {
            return TypeConstants.TYPE_ITEM_THIRD;
        } else {
            return TypeConstants.TYPE_ITEM_NONE;
        }
    }


    public boolean isFirstType(int index) {
        boolean flag;
        if (JudgmentDataUtil.hasCollectionData(dataList1)) {
            if (index > -1 && index < dataList1.size()) {
                flag = true;
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    public boolean isSecondType(int index) {
        boolean flag;
        if (JudgmentDataUtil.hasCollectionData(dataList2)) {
            if (index >= dataList1.size() && index < dataList1.size() + dataList2.size()) {
                flag = true;
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    public boolean isThirdType(int index) {
        boolean flag;
        if (JudgmentDataUtil.hasCollectionData(dataList3)) {
            if (index >= dataList1.size() + dataList2.size() && index < dataList1.size() + dataList2.size() + dataList3.size()) {
                flag = true;
            } else {
                flag = false;
            }
        } else {
            flag = false;
        }
        return flag;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder1) {
            ViewHolder1 holder = (ViewHolder1) viewHolder;
            holder.mTextView.setText(dataList1.get(position));
        } else if (viewHolder instanceof ViewHolder2) {
            int mPosition = position - dataList1.size();
            ViewHolder2 holder = (ViewHolder2) viewHolder;
            holder.mTextView.setText(dataList2.get(mPosition));
        } else if (viewHolder instanceof ViewHolder3) {
            ViewHolder3 holder = (ViewHolder3) viewHolder;
            int mPosition = position - dataList1.size() - dataList2.size();
            holder.mTextView.setText(dataList3.get(mPosition));
        } else {
            LogUtil.myD("weizhi...");
        }

    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return dataList1.size() + dataList2.size() + dataList3.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    private static class ViewHolder1 extends RecyclerView.ViewHolder {
        private TextView mTextView;

        private ViewHolder1(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
        }
    }

    private static class ViewHolder2 extends RecyclerView.ViewHolder {
        private TextView mTextView;

        private ViewHolder2(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
        }
    }

    private static class ViewHolder3 extends RecyclerView.ViewHolder {
        private TextView mTextView;

        private ViewHolder3(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.text);
        }
    }
}
