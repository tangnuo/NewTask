package com.example.caowj.newtask.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;

/**
 * 仿饿了么搜索栏动画效果<br/>
 * 注意主题样式。
 */
public class ElemSearchActivity extends BaseActivity {
    RelativeLayout tvSearchRlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tvSearchRlt = (RelativeLayout) findViewById(R.id.tv_search_rlt);
        tvSearchRlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ElemSearchActivity.this, ElemSearchResultActivity.class);
                int location[] = new int[2];
                tvSearchRlt.getLocationOnScreen(location);
                intent.putExtra("x", location[0]);
                intent.putExtra("y", location[1]);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_elem_search;
    }

    @Override
    protected void memoryRecovery() {

    }
}
