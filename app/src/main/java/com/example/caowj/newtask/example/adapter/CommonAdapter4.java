package com.example.caowj.newtask.example.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.bean.Person;
import com.example.caowj.newtask.example.viewHolder.IBaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * 基于BRVAH的通用Adapter
 *
 * @deprecated 使用ButterKnife自定义viewholder
 * package: com.example.caowj.newtask.example.adapter
 * author: Administrator
 * date: 2017/9/14 15:06
 */
public class CommonAdapter4 extends BaseQuickAdapter<Person, CommonAdapter4.MyViewHolder> {

    public CommonAdapter4(int item_list, List<Person> personList) {
        super(item_list, personList);
    }

    @Override
    protected void convert(MyViewHolder helper, Person item) {
//        helper.setText(R.id.tv_item, item.getName());
        helper.updateView(item);
    }

    static class MyViewHolder extends IBaseViewHolder {
        @BindView(R.id.tv_item)
        TextView tvItem;

        public MyViewHolder(View view) {
            super(view);
        }


        @Override
        public <T> void updateView(T dateItem) {
            if (dateItem instanceof Person) {
                Person person = (Person) dateItem;
                tvItem.setText(person.getName());
            }
        }
    }
}
