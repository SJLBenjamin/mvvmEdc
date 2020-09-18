package com.endoc.mvvmedc.bridge.state;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableDouble;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import cn.com.heaton.blelibrary.ble.model.BleDevice;

public class FragmentMonitorViewModel extends ViewModel {
    //设备连接名称
    public   ObservableField<String>  connectDeviceName = new ObservableField<String>();
    //设备是否连接
    public  ObservableBoolean connectState = new ObservableBoolean();
    //用户名
    public ObservableField<String> userName = new ObservableField<String>();

    //当前的监测的血糖值
    public ObservableDouble monitorMol =new ObservableDouble();

    //当前箭头显示的图标
    public ObservableInt drawableID = new ObservableInt();

    //箭头是否显示
    public ObservableInt jianTouShow = new ObservableInt();

    //是否搜索
    public MutableLiveData<Boolean> search =new MutableLiveData();

    //蓝牙列表
    public ObservableArrayList<BleDevice> bleDevices = new ObservableArrayList<>();

    //public MutableLiveData<Boolean> search = new MutableLiveData<>();

    public FragmentMonitorViewModel(){
        search.setValue(true);//默认未搜索
    }

}
