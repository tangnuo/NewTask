package com.example.caowj.newtask.module1.ItemViewBinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.module1.entity.bean.ScrollNotification;
import com.example.caowj.newtask.widget.NotificationTextView;
import com.kedacom.utils.JudgmentDataUtil;
import com.kedacom.utils.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * @Author : caowj
 * @Date : 2018/3/15
 */
public class ScrollNotificationListViewBinder extends ItemViewBinder<ScrollNotificationList, ScrollNotificationListViewBinder.NotificationViewHolder> {

    private Context mContext;

    public ScrollNotificationListViewBinder(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    protected NotificationViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.choice_notification_item_layout, parent, false);
        return new NotificationViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotificationViewHolder holder, @NonNull ScrollNotificationList scrollNotificationList) {

        List<ScrollNotification> scrollDataList = scrollNotificationList.getData();

        if (JudgmentDataUtil.hasCollectionData(scrollDataList)) {
            NotificationTextView mNotificationTextView = holder.notificationTextView;
            mNotificationTextView.setScrollData(true, scrollDataList);
            mNotificationTextView.setOnItemClickListener(new NotificationTextView.OnItemClickListener() {
                @Override
                public void onItemClick(int position, int notificationID) {
                    LogUtil.myD("当前位置：" + position + "\t当前ID:" + notificationID);
                }
            });
        } else {
            LogUtil.myD("暂无通知数据");
        }
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.notificationTextView)
        NotificationTextView notificationTextView;

        public NotificationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
