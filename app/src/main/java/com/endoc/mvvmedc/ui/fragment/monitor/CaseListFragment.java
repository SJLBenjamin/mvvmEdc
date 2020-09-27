package com.endoc.mvvmedc.ui.fragment.monitor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.base.BaseFragment;
import com.orhanobut.logger.Logger;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaseListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaseListFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaseListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaseListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaseListFragment newInstance(String param1, String param2) {
        CaseListFragment fragment = new CaseListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_case_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitleView(View.VISIBLE,View.VISIBLE,View.INVISIBLE,View.VISIBLE,getString(R.string.cases),getString(R.string.add_cases),View.GONE);
        view.findViewById(R.id.bt_dump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_caseListFragment_to_addCaseFragment);
            }
        });
    }

    @Override
    public void right() {
        super.right();
        Logger.d("CaseListFragment");
    }

    @Override
    public void backChanged(Boolean s) {
        super.backChanged(s);
        Logger.d("caseListFragment backClick");
    }

    @Override
    public <T> void rightChanged(T s) {
        super.rightChanged(s);
        if(s.equals(mActivity.getString(R.string.add_cases))){
            Logger.d("CaseListFragment rightChanged");
           getNavController().navigate(R.id.action_caseListFragment_to_addCaseFragment);
        }
    }

    //    /**
//     * @param s 此方法会被调用,是因为实例化对象不是BaseFragment
//     */
//    @Override
//    public void rightChanged(String s) {
//        if(s.equals(getString(R.string.add_cases))){
//            Logger.d("CaseListFragment rightChanged");
//            getNavController().navigate(R.id.addCaseFragment);
//        }
//
//    }
}
