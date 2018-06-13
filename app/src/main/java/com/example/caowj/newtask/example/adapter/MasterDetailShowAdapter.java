package com.example.caowj.newtask.example.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.caowj.newtask.R;
import com.example.caowj.newtask.module1.viewHolder.CommonVH;
import com.kedacom.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 大师介绍
 * Created by Pan S.Q
 * on 2016/12/30.
 */

public class MasterDetailShowAdapter extends RecyclerView.Adapter<CommonVH.ImageTextViewHolder> {

    private List<String> masterProductList;
    private Activity mActivity;
    private String TAG = this.getClass().getSimpleName();

    public MasterDetailShowAdapter(Activity mActivity, List<String> masterProductList) {
        this.mActivity = mActivity;
        this.masterProductList = masterProductList == null ? new ArrayList<String>() : masterProductList;
    }

    public void setMasterProductList(List<String> masterProductList) {
        this.masterProductList = masterProductList;
        notifyDataSetChanged();
    }

    /**
     * 内存回收
     */
    public void memoryRecovery() {
        Glide.get(mActivity).clearMemory();
        mActivity = null;
    }

    @Override
    public CommonVH.ImageTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommonVH.ImageTextViewHolder(mActivity.getLayoutInflater().inflate(R.layout.item_image_text, parent, false));
    }

    @Override
    public void onBindViewHolder(final CommonVH.ImageTextViewHolder findHolder, int position) {
        if (findHolder instanceof CommonVH.ImageTextViewHolder) {
            String content = masterProductList.get(position);
            if (content == null) {
                return;
            }
            if (content.toLowerCase().startsWith("http://")) {
                //属于图片
                findHolder.tvContent.setVisibility(View.GONE);
                findHolder.ivItemImage.setVisibility(View.VISIBLE);
//                ImageUtil.setImageByScreen(mActivity, findHolder.ivItemImage, content);
            } else {
                //属于文本
                findHolder.ivItemImage.setVisibility(View.GONE);
                findHolder.tvContent.setVisibility(View.VISIBLE);
                findHolder.tvContent.setText(content);
            }
        } else {
            LogUtil.myD("未知的ViewHolder");
        }
    }

    @Override
    public int getItemCount() {
        return masterProductList.size();
    }

}
