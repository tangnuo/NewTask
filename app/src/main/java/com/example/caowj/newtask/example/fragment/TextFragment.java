package com.example.caowj.newtask.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caowj.newtask.R;

public class TextFragment extends Fragment {
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        textView = (TextView) rootView.findViewById(R.id.tv_title);
        String string = getArguments().getString("key");
        textView.setText(string);
        return rootView;
    }

}
