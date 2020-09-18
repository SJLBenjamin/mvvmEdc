package com.endoc.mvvmedc.data.repository;


import androidx.lifecycle.MutableLiveData;


import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import cn.com.heaton.blelibrary.ble.Ble;
import cn.com.heaton.blelibrary.ble.callback.BleScanCallback;
import cn.com.heaton.blelibrary.ble.model.BleDevice;
import kotlin.jvm.Synchronized;

public class BleOperation {

    public static void startSearch(final MutableLiveData<List<BleDevice>> deviceMutableLiveData) {
        Ble.getInstance().startScan(new BleScanCallback<BleDevice>() {
            @Override
            public void onLeScan(BleDevice device, int rssi, byte[] scanRecord) {
                synchronized (BleOperation.class) {
                    if (device.getBleName() != null && device.getBleName().contains("Endoc_")) {

                        List<BleDevice> value = deviceMutableLiveData.getValue();
                        if (value.size()==0) {//如果一个都没有,那么直接添加
                            value.add(device);
                            deviceMutableLiveData.postValue(value);
                            return;
                        }
                        boolean isContain = false;
                        for (int i = 0; i < value.size(); i++) {
                            if (value.get(i).getBleName().equals(device.getBleName())) {
                                isContain = true;
                                return;
                            }
                        }
                        //如果不包含此设备
                        if (!isContain) {
                            value.add(device);
                        }
                        deviceMutableLiveData.postValue(value);
                    }
                }
            }
        });
    }

    /**
     * 停止搜索
     */
    public static void stopStop() {
        Ble.getInstance().stopScan();
    }


}
