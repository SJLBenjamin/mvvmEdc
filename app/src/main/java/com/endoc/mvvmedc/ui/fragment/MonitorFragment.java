package com.endoc.mvvmedc.ui.fragment;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.base.BaseFragment;
import com.endoc.mvvmedc.bridge.request.BleRequestViewModel;
import com.endoc.mvvmedc.bridge.state.FragmentMonitorViewModel;

import com.endoc.mvvmedc.databinding.FragmentMonitorBinding;

import com.endoc.mvvmedc.ui.adapter.DeviceListAdapter;
import com.orhanobut.logger.Logger;

import java.util.List;

import cn.com.heaton.blelibrary.ble.model.BleDevice;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;


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
    private DeviceListAdapter mDeviceListAdapter;
    private List<BleDevice> value;


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

        //mBleRequestViewModel.getDeviceMutableLiveData().getValue(),此处未初始化,所以一直为null,导致List拿不到数据
        value = mBleRequestViewModel.getDeviceMutableLiveData().getValue();
        mFragmentMonitorBinding.rcDeviceList.setLayoutManager(new LinearLayoutManager(mActivity));
        mDeviceListAdapter = new DeviceListAdapter(value);
        mFragmentMonitorBinding.rcDeviceList.setAdapter(mDeviceListAdapter);
        //设置搜索按钮观察者
        mFragmentMonitorViewModel.search.observe(mActivity, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Logger.d("搜索按钮  Boolean==="+aBoolean);
                if(aBoolean){
                    startSearch();//开始搜索
                }else {
                    mBleRequestViewModel.requestStopScan();//停止搜索
                }
            }
        });

        //设置蓝牙列表观察
        mBleRequestViewModel.getDeviceMutableLiveData().observe(mActivity, new Observer<List<BleDevice>>() {
            @Override
            public void onChanged(List<BleDevice> bleDevices) {
                Logger.d("蓝牙列表数据更新  length==="+value.size());
                mDeviceListAdapter.notifyDataSetChanged();
            }
        });

        //还需要绑定点击事件
        mFragmentMonitorBinding.setClick(new Click());
    }


    /**
     * 点击事件
     */
    public  class Click {
        //点击了闹钟
        public void alarm() {

        }

        //点击了消息
        public void message() {

        }

        //点击了设备控制
        public void connectDevice() {

        }

        //点击了搜索
        public void search() {
            if (!mFragmentMonitorViewModel.search.getValue()) {
                mFragmentMonitorViewModel.search.setValue(true);
            } else {
                mFragmentMonitorViewModel.search.setValue(false);
            }
        }
    }

    public void startSearch(){
        if (EasyPermissions.hasPermissions(mActivity, BLE)) {
            //清除列表数据
           mBleRequestViewModel.getDeviceMutableLiveData().getValue().clear();
            //开始搜索
            mBleRequestViewModel.requestStartScan();
        } else {
            EasyPermissions.requestPermissions(MonitorFragment.this,
                    getResources().getString(R.string.saturday),//使用官方提供的弹窗(`AppSettingsDialog`)的时候会用到这个字符串,作为用户拒绝之后弹窗的内容
                    RC_LOCATION_GPS_PREM,
                    BLE);
        }
    }




    private static final String[] BLE ={Manifest.permission.BLUETOOTH_ADMIN,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private static final int RC_LOCATION_GPS_PREM =124;



    //https://blog.csdn.net/yang_study_first/article/details/101023919?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.channel_param
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //将结果交由EasyPermissions解决
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

    //申请失败,申请一组权限的时候,有的会失败
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        super.onPermissionsDenied(requestCode, perms);
        if(requestCode==RC_LOCATION_GPS_PREM){
            //showShortToast("权限拒绝");
            for (int i=0;i<perms.size();i++){
                Logger.d("onPermissionsDenied"+perms.get(i));
            }
        }
    }

    //申请成功
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        super.onPermissionsGranted(requestCode, perms);
        if(requestCode==RC_LOCATION_GPS_PREM){
            Logger.d("onPermissionsGranted");
          /*  //直接搜索
            startSearch();*/
            //或者通过观察者驱动,观察者驱动会多走判断权限是否拥有代码
            mFragmentMonitorViewModel.search.setValue(true);
        }
    }

}
