package com.example.caowj.newtask.module1.ItemViewBinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.module1.entity.bean.AdBean;
import com.example.caowj.newtask.utils.business.MyAndroidUtils;
import com.example.caowj.newtask.widget.BannerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @Author : caowj
 * @Date : 2018/3/14
 */
public class ADInfoListViewBinder extends ItemViewBinder<ADInfoList, ADInfoListViewBinder.BannerAndSearchViewHolder> {

    private Context mContext;

    public ADInfoListViewBinder(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected BannerAndSearchViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_a_d_info, parent, false);
        return new BannerAndSearchViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull BannerAndSearchViewHolder holder, @NonNull ADInfoList adInfoList) {
        //根据是否有通知模块决定marginBottom的值
        RelativeLayout.LayoutParams parentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        parentParams.setMargins(0, 0, 0, 16);
        holder.rlParent.setLayoutParams(parentParams);

        holder.slideshowView.setVisibility(View.VISIBLE);
        //广告图片宽高比例：15:10（3:2）；边距为0dp;
        int height = MyAndroidUtils.getHeightByScale(mContext, 0, 0, 0, 15, 10);
        ViewGroup.LayoutParams params = holder.slideshowView.getLayoutParams();
        params.height = height;
        holder.slideshowView.setLayoutParams(params);

        List<AdBean> adInfos = adInfoList.getAdInfoList();
        //当前集合大于1时执行轮播效果
        boolean isAuto = adInfos.size() > 1 ? true : false;
        BannerView slideshowView = holder.slideshowView;
        slideshowView.setADInfo(adInfos, isAuto, 4000);
    }


    /**
     * 1.轮播图+搜索
     */
    static class BannerAndSearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_parent)
        RelativeLayout rlParent;
        @BindView(R.id.slideshowView)
        BannerView slideshowView;

        public BannerAndSearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
