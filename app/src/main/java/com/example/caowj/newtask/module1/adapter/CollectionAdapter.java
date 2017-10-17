package com.example.caowj.newtask.module1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.module1.entity.PaiPinInfo;
import com.example.caowj.newtask.module1.viewHolder.FixedPriceProductVH;
import com.example.caowj.newtask.utils.JudgmentDataUtil;
import com.example.caowj.newtask.utils.LogUtil;
import com.example.caowj.newtask.utils.business.ProductsDetailUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pan S.Q
 * on 2017/5/4.
 * 雅集适配器
 */

public class CollectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = getClass().getSimpleName();

    private Context context;

    private LayoutInflater layoutInflater;
    private List<PaiPinInfo> paiPinInfoList;

    public CollectionAdapter(Context context, List<PaiPinInfo> paiPinInfoList) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.paiPinInfoList = paiPinInfoList == null ? new ArrayList<PaiPinInfo>() : paiPinInfoList;
    }


    @Override
    public int getItemCount() {
        //轮播图+专题+拍品+暂无
        return paiPinInfoList.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FixedPriceProductVH(layoutInflater.inflate(R.layout.yawu_collection_of_products_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (JudgmentDataUtil.hasCollectionData(paiPinInfoList)) {
            ProductsDetailUtil.FixedPriceProductsViewHolder((FixedPriceProductVH) holder, context, paiPinInfoList, position, true, "#DFDFDF");
        } else {
            LogUtil.d(TAG, "暂无一口价数据");
        }
    }

}
