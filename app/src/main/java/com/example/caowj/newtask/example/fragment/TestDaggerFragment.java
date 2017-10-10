package com.example.caowj.newtask.example.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.caowj.newtask.R;
import com.example.caowj.newtask.example.activity.TestDaggerActivity3;
import com.example.caowj.newtask.example.mDagger.component.TestDaggerFragmentComponent;
import com.example.caowj.newtask.example.presenter.TestDaggerPresenter;
import com.example.caowj.newtask.utils.ToastUtil;

import javax.inject.Inject;

public class TestDaggerFragment extends Fragment implements TestDaggerPresenter.IUserView {

    @Inject
    TestDaggerPresenter mainPresenter;
    @Inject
    ToastUtil toastUtil;


    private TestDaggerFragmentComponent fragmentComponent;

    public TestDaggerFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() instanceof TestDaggerActivity3) {
            fragmentComponent = ((TestDaggerActivity3) getActivity()).getMainComponent().mainFragmentComponent();
            fragmentComponent.inject(this);
            mainPresenter.setUserView(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_dagger, container, false);

        view.findViewById(R.id.get_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainPresenter.getUser();
            }
        });
        view.findViewById(R.id.show_toast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toastUtil.showToast("依赖注入获取到的toast");
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setUserName(String name) {
        ((TextView) getView().findViewById(R.id.user_info)).setText(name);
    }

}
