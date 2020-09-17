package com.endoc.mvvmedc.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.base.BaseFragment;
import com.endoc.mvvmedc.bridge.request.BleRequestViewModel;
import com.endoc.mvvmedc.bridge.state.FragmentMonitorViewModel;
import com.endoc.mvvmedc.data.repository.BleOperation;
import com.endoc.mvvmedc.databinding.FragmentMonitorBinding;
import com.endoc.mvvmedc.share.MainActivityViewModel;
import com.endoc.mvvmedc.ui.adapter.DeviceListAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

import cn.com.heaton.blelibrary.ble.model.BleDevice;


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
    private FragmentMonitorViewModel mFragmentMonitorViewModel;
    private BleRequestViewModel mBleRequestViewModel;

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
        mFragmentMonitorViewModel = getAppViewModelProvider().get(FragmentMonitorViewModel.class);
        //蓝牙列表相关viewm
        mBleRequestViewModel = getAppViewModelProvider().get(BleRequestViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initTitleView(View.GONE,View.GONE,View.GONE,View.GONE,"");

        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_monitor, container, false);
        mFragmentMonitorBinding = DataBindingUtil.bind(inflate);
        mFragmentMonitorBinding.setVm(mFragmentMonitorViewModel);
        return inflate;
    }

    //此方法在onCreateView执行完马上执行
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mFragmentMonitorBinding.rcDeviceList.setAdapter(new DeviceListAdapter());


        //设置搜索按钮观察者,其实没必要
        mFragmentMonitorViewModel.search.observe(mActivity, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

            }
        });



        //设置蓝牙列表观察
        mBleRequestViewModel.getDeviceMutableLiveData().observe(mActivity, new Observer<List<BleDevice>>() {
            @Override
            public void onChanged(List<BleDevice> bleDevices) {
                mFragmentMonitorBinding.notifyChange();
            }
        });

        //还需要绑定点击事件
        mFragmentMonitorBinding.setClick(new Click());
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
            if(!mFragmentMonitorViewModel.search.getValue()){
                mFragmentMonitorViewModel.search.setValue(true);
                //开始搜索
                mBleRequestViewModel.requestStartScan();
            }else {
                mFragmentMonitorViewModel.search.setValue(false);
            }

        }



    }

}
