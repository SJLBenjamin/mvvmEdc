package com.endoc.mvvmedc.bridge.request;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.endoc.mvvmedc.data.repository.BleOperation;

import java.util.ArrayList;
import java.util.List;

import cn.com.heaton.blelibrary.ble.model.BleDevice;

public class BleRequestViewModel extends ViewModel {
    MutableLiveData<List<BleDevice>> deviceMutableLiveData;

    public   MutableLiveData<List<BleDevice>> getDeviceMutableLiveData(){
        if(deviceMutableLiveData==null){
            deviceMutableLiveData = new MutableLiveData<List<BleDevice>>();
            deviceMutableLiveData.setValue(new ArrayList<BleDevice>());//必须初始化,否则默认是MutableLiveData中没有元素,为0
        }
        return deviceMutableLiveData;
    }

    public void requestStartScan(){
        BleOperation.startSearch(getDeviceMutableLiveData());
    }

    public void requestStopScan(){
        BleOperation.stopStop();
    }

}
