package com.endoc.mvvmedc.bridge.request;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.endoc.mvvmedc.data.repository.BleOperation;

import java.util.List;

import cn.com.heaton.blelibrary.ble.model.BleDevice;

public class BleRequestViewModel extends ViewModel {
    MutableLiveData<List<BleDevice>> deviceMutableLiveData;
    public   MutableLiveData<List<BleDevice>> getDeviceMutableLiveData(){
        if(deviceMutableLiveData==null){
            deviceMutableLiveData = new MutableLiveData<List<BleDevice>>();
        }
        return deviceMutableLiveData;
    }

    public void requestStartScan(){
        BleOperation.startSearch(deviceMutableLiveData);
    }

}
