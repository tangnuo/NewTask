package com.example.caowj.newtask.utils.business;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.example.caowj.newtask.toutiao.util.GlideUtils;
import com.example.caowj.newtask.module1.constants.Constants;
import com.example.caowj.newtask.module1.entity.bean.PaiPinBean;
import com.example.caowj.newtask.module1.viewHolder.FixedPriceProductVH;
import com.example.caowj.newtask.utils.AlimmdnUtil;

import java.util.List;

/**
 * Created by Pan S.Q
 * on 2017/5/11.
 */

public class ProductsDetailUtil {

    private static final String TAG = ProductsDetailUtil.class.getSimpleName();

    /**
     * 一口价商品 该布局是网格布局不能用于线性布局
     *
     * @param holder
     * @param context
     * @param list
     * @param position      下标位置
     * @param isActivePrice false:采用y_price true:activePrice
     * @param color         背景色
     */
    public static void FixedPriceProductsViewHolder(FixedPriceProductVH holder, final Context context, List<PaiPinBean> list, int position, boolean isActivePrice, String color) {

        final PaiPinBean paiPinInfo = list.get(position);
        if (paiPinInfo == null) {
            return;
        }
        //商品名称
        holder.tvProductsName.setText(paiPinInfo.getPaipinName());
        //商家名称
        String shopName = paiPinInfo.getRoleUse() == 0 ? paiPinInfo.getShopName() : "启拍自营";
        holder.tvStoreName.setText("【" + shopName + "】");
        //商品售价
        holder.tvPrice.setText(Constants.RMBTag + (isActivePrice ? paiPinInfo.getActivePrice() : paiPinInfo.getY_price()));
        int width = MyAndroidUtils.getScreenWidth(context) / 2;
        GridLayoutManager.LayoutParams parentParams = (GridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
        parentParams.width = width;
        holder.itemView.setLayoutParams(parentParams);
        holder.rl_content.setBackgroundColor(Color.parseColor(color));
        holder.rl_content.setPadding(position % 2 != 0 ? 6 : 12, 0, position % 2 != 0 ? 12 : 6, 12);
        //控件的实际宽度 width - 12(左（右）paddingLeft) - 6 (左（右）paddingRight)
        int imageViewWidth = width - 12 - 6;
        //控件的实际高度 4 ： 3 比例 v2.6.3改为1:1显示
//        int imageViewHeight = imageViewWidth * 3 / 4 ;
        int imageViewHeight = imageViewWidth;//此处的高度为1:1
//        LogUtil.d(TAG,"控件宽度："+imageViewWidth+"\t控件高度："+imageViewHeight);
        ViewGroup.LayoutParams params = holder.ivPoster.getLayoutParams();
        params.width = imageViewWidth;
        params.height = imageViewHeight;
        holder.ivPoster.setLayoutParams(params);
        ViewGroup.LayoutParams borderParams = holder.border.getLayoutParams();
        borderParams.width = imageViewWidth;
        borderParams.height = imageViewHeight;
        holder.border.setLayoutParams(borderParams);
        //一口价尺寸 360*270px
        String url = paiPinInfo.getPicFixedPrice();
        GlideUtils.loadNormal(holder.ivPoster.getContext(), AlimmdnUtil.modifyImagePath(url), holder.ivPoster);
//        GlideUtils.loadStringRes(holder.ivPoster, AlimmdnUtil.modifyImagePath(url), GlideUtils.setConfigSize(url, 360, 270), null);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到商品咨询或者一口价详情
//                MyAndroidUtils.toDifferentType(context,paiPinInfo.getId(),paiPinInfo.getNumber());
            }
        });
    }

}
