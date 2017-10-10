package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.base.BaseActivity;

/**
 * 搜索框<br/>
 * <p>
 * 源码：http://blog.csdn.net/airsaid/article/details/51087226<br/>
 * <p>
 * 属性说明：http://www.cnblogs.com/tianzhijiexian/p/4226675.html
 */
public class SearchViewActivity extends BaseActivity {
    private String[] mStrs = {"aaa", "bbb", "ccc", "airsaid"};
    private SearchView mSearchView;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search_view;
    }

    @Override
    protected void initData() {
        mSearchView = (SearchView) findViewById(R.id.searchView);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs));
        mListView.setTextFilterEnabled(true);

        mSearchView.setIconified(false);
//        mSearchView.setIconifiedByDefault(false);
        mSearchView.setSubmitButtonEnabled(true);
        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    mListView.setFilterText(newText);
                } else {
                    mListView.clearTextFilter();
                }
                return false;
            }
        });
    }

    @Override
    protected void memoryRecovery() {

    }
}
