package com.example.caowj.newtask.mvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.mvp.constants.TypeConstants;
import com.example.caowj.newtask.mvp.entity.bean.PaiPinBean;
import com.example.caowj.newtask.mvp.viewHolder.CommonVH;
import com.example.caowj.newtask.mvp.viewHolder.FixedPriceProductVH;
import com.example.caowj.newtask.utils.business.ProductsDetailUtil;
import com.kedacom.utils.JudgmentDataUtil;

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
    private List<PaiPinBean> paiPinInfoList;


    public CollectionAdapter(Context context, List<PaiPinBean> paiPinInfoList) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.paiPinInfoList = paiPinInfoList == null ? new ArrayList<PaiPinBean>() : paiPinInfoList;
    }

    public void setPaiPinInfoList(List<PaiPinBean> paiPinInfoList) {
        this.paiPinInfoList = paiPinInfoList == null ? new ArrayList<PaiPinBean>() : paiPinInfoList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (JudgmentDataUtil.hasCollectionData(paiPinInfoList)) {
            return TypeConstants.TYPE_ITEM_FIRST;
        } else {
            return TypeConstants.TYPE_ITEM_NONE;
        }
    }

    @Override
    public int getItemCount() {
        if (JudgmentDataUtil.hasCollectionData(paiPinInfoList)) {
            return paiPinInfoList.size();
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TypeConstants.TYPE_ITEM_FIRST) {
            return new FixedPriceProductVH(layoutInflater.inflate(R.layout.yawu_collection_of_products_item_layout, parent, false));
        } else {
            return new CommonVH.NoneDataViewHolder(layoutInflater.inflate(R.layout.item_mine_comment_none, parent, false));
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (JudgmentDataUtil.hasCollectionData(paiPinInfoList)) {
            ProductsDetailUtil.FixedPriceProductsViewHolder((FixedPriceProductVH) holder, context, paiPinInfoList, position, true, "#DFDFDF");
        } else {
            CommonVH.NoneDataViewHolder noneDataViewHolder = (CommonVH.NoneDataViewHolder) holder;
//                noneDataViewHolder.ivHint.setImageResource(R.drawable.icon_no_order_flag);
            noneDataViewHolder.tvHint.setText("暂无活动数据哦");
        }
    }

}
