package com.endoc.mvvmedc.bridge.request;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.endoc.mvvmedc.bridge.state.FragmentMonitorViewModel;
import com.endoc.mvvmedc.data.repository.BleOperation;

import java.util.ArrayList;
import java.util.List;

import cn.com.heaton.blelibrary.ble.model.BleDevice;

/**
 * 蓝牙的操作全部由这个类去实现,Fragment/Activity中持有此viewModel,此viewModel和蓝牙库交互,Fragment/Activity不直接和蓝牙库交互
 */
public class BleRequestViewModel extends ViewModel {
    MutableLiveData<List<BleDevice>> deviceMutableLiveData;
   public  MutableLiveData<BleDevice> connectDevice;


    public   MutableLiveData<List<BleDevice>> getDeviceMutableLiveData(){
        if(deviceMutableLiveData==null){
            deviceMutableLiveData = new MutableLiveData<List<BleDevice>>();
            deviceMutableLiveData.setValue(new ArrayList<BleDevice>());//必须初始化,否则默认是MutableLiveData中没有元素,为0
        }
        return deviceMutableLiveData;
    }

    public   MutableLiveData<BleDevice> getConnectDeviceMutableLiveData(){
        if(connectDevice==null){
            connectDevice = new MutableLiveData<BleDevice>();
        }
        return connectDevice;
    }

    public void requestStartScan(){
        BleOperation.startSearch(getDeviceMutableLiveData());
    }

    public void requestStopScan(){
        BleOperation.stopStop();
    }

    public void connectDevice(BleDevice bleDevice){
        BleOperation.startConnect(bleDevice,connectDevice);
    }

    public void disconnectDevice(){

    }


}
