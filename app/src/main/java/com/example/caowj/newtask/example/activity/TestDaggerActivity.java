package com.example.caowj.newtask.example.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.mDragger.ApiService;
import com.example.caowj.newtask.example.mDragger.DaggerUserComponent;

import javax.inject.Inject;

public class TestDaggerActivity extends AppCompatActivity {

    @Inject
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dragger);

        //Dagger会自动创建这个类，以Dagger开头+UserComponent
        DaggerUserComponent.create().inject(this);
        apiService.register(this);
    }
}
