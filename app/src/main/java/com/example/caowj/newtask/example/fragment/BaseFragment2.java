package com.example.caowj.newtask.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caowj.newtask.R;
import com.kedacom.base.mvc.BaseButterKnifeFragment;

/**
 * @deprecated 仅适用于dagger2的情况 see{@link BaseButterKnifeFragment}
 */
public class BaseFragment2 extends Fragment {

    public BaseFragment2() {
        // Required empty public constructor
    }

    public static Fragment getInstance(String title) {
        TextFragment fragment = new TextFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", title);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_base, container, false);
        return rootView;
    }

}
