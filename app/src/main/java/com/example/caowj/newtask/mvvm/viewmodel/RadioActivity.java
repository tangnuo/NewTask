package com.example.caowj.newtask.mvvm.viewmodel;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.databinding.ActivityRadioBinding;
import com.example.caowj.newtask.BR;

/**
 * 自定义单选框
 */
public class RadioActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioViewModel radioViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRadioBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_radio);
        radioViewModel = new RadioViewModel();
        binding.setVariable(BR.radioViewModel, radioViewModel);
        binding.llIndex0.setOnClickListener(this);
        binding.llIndex1.setOnClickListener(this);
        binding.llIndex2.setOnClickListener(this);
        binding.llIndex3.setOnClickListener(this);
        binding.stateImage.setOnStateChangedListener(state -> {
            Log.e("CMW", "state---->" + state);
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_index0: {
                radioViewModel.setSelectIndex(0);
            }
            break;
            case R.id.ll_index1: {
                radioViewModel.setSelectIndex(1);
            }
            break;
            case R.id.ll_index2: {
                radioViewModel.setSelectIndex(2);
            }
            break;
            case R.id.ll_index3: {
                radioViewModel.setSelectIndex(3);
            }
            break;
            default: {
                radioViewModel.setSelectIndex(0);
            }
            break;
        }
    }
}
