package com.example.caowj.newtask.module1.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.caowj.newtask.R;


/**
 * @author Dick.Pan
 * @date 2017/6/14.
 */

public class FixedPriceProductVH extends RecyclerView.ViewHolder {

    public ImageView ivPoster;//封面

    public RelativeLayout border;//描边

    public TextView tvPrice;//价格

    public TextView tvStoreName;//商家名称

    public TextView tvProductsName;//商品名称

    public RelativeLayout rl_content;//父容器

    public FixedPriceProductVH(View itemView) {
        super(itemView);

        ivPoster = (ImageView) itemView.findViewById(R.id.iv_poster);
        border = (RelativeLayout) itemView.findViewById(R.id.rl_border);
        tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
        tvStoreName = (TextView) itemView.findViewById(R.id.tv_store_name);
        tvProductsName = (TextView) itemView.findViewById(R.id.tv_products_name);
        rl_content = (RelativeLayout) itemView.findViewById(R.id.rl_content);
    }
}
