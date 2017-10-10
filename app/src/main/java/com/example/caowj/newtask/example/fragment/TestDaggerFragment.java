package com.example.caowj.newtask.example.fragment;

import android.content.Context;
import android.net.Uri;
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

    private OnFragmentInteractionListener mListener;

    public TestDaggerFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestDaggerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestDaggerFragment newInstance(String param1, String param2) {
        TestDaggerFragment fragment = new TestDaggerFragment();
//        Bundle args = new Bundle();

        return fragment;
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
        // Inflate the layout for this fragment
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


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }


    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setUserName(String name) {
        ((TextView) getView().findViewById(R.id.user_info)).setText(name);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
