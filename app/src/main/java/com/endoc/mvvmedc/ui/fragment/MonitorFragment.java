package com.endoc.mvvmedc.ui.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.base.BaseFragment;
import com.endoc.mvvmedc.bridge.state.FragmentMonitorViewModel;
import com.endoc.mvvmedc.databinding.FragmentMonitorBinding;
import com.endoc.mvvmedc.share.MainActivityViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonitorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonitorFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentMonitorBinding mFragmentMonitorBinding;

    public MonitorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonitorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonitorFragment newInstance(String param1, String param2) {
        MonitorFragment fragment = new MonitorFragment();
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
        initTitleView(View.GONE,View.GONE,View.GONE,View.GONE,"");

        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_monitor, container, false);
        mFragmentMonitorBinding = DataBindingUtil.bind(inflate);
       getAppViewModelProvider().get(FragmentMonitorViewModel.class);
        return inflate;

    }


    /**
     * 点击事件
     */
    public  class Click{
        //点击了闹钟
        public void alarm(){

        }
        //点击了消息
        public void message(){

        }
        //点击了设备控制
        public void connectDevice(){

        }

        //点击了搜索
        public void search(){

        }


    }

}
