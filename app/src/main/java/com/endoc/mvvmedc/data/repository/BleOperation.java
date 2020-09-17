package com.endoc.mvvmedc.data.repository;



import androidx.lifecycle.MutableLiveData;


import java.util.List;

import cn.com.heaton.blelibrary.ble.Ble;
import cn.com.heaton.blelibrary.ble.callback.BleScanCallback;
import cn.com.heaton.blelibrary.ble.model.BleDevice;

public class BleOperation {

    public static void startSearch(final MutableLiveData<List<BleDevice>> deviceMutableLiveData){
        Ble.getInstance().startScan(new BleScanCallback<BleDevice>() {
            @Override
            public void onLeScan(BleDevice device, int rssi, byte[] scanRecord) {
                List<BleDevice> value = deviceMutableLiveData.getValue();
                value.add(device);
                deviceMutableLiveData.setValue(value);
            }
        });
    }

    public static void stopStop(){
        Ble.getInstance().stopScan();
    }


}
