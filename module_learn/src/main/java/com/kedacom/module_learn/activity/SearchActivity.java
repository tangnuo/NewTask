package com.kedacom.module_learn.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.kedacom.module_learn.R;

/**
 * AutoCompleteTextView会实现自动匹配
 * https://www.runoob.com/w3cnote/android-tutorial-autocompletetextview.html
 * 实时模糊搜索
 * https://www.cnblogs.com/leeluyao/p/4900199.html
 */
public class SearchActivity extends AppCompatActivity {
    private static final String[] data = new String[]{
            "小猪猪", "小狗狗", "小鸡鸡", "小猫猫", "小咪咪"
    };
    private AutoCompleteTextView atv_content;
    private MultiAutoCompleteTextView matv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        atv_content = findViewById(R.id.atv_content);
        matv_content = findViewById(R.id.matv_content);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchActivity.
                this, android.R.layout.simple_dropdown_item_1line, data);
        atv_content.setAdapter(adapter);

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, data);
        matv_content.setAdapter(adapter);
        matv_content.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }
}