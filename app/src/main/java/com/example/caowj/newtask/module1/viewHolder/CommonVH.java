package com.example.caowj.newtask.module1.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caowj.newtask.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * package: com.jsfengling.qipai.viewholder.search
 * author: caowj
 * date: 2017/5/15 16:46
 */
public class CommonVH {

    /**
     * 页面：item_mine_comment_none
     */
    public static class NoneDataViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_hint)
        public
        TextView tvHint;
        @BindView(R.id.iv_hint)
        public ImageView ivHint;

        public NoneDataViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    /**
     * 图文详情
     */
    public static class ImageTextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_image)
        public ImageView ivItemImage;
        @BindView(R.id.tv_content)
        public TextView tvContent;

        public ImageTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
