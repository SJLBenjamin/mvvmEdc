package com.endoc.mvvmedc.data.repository;


import androidx.lifecycle.MutableLiveData;


import com.endoc.mvvmedc.bridge.request.BleRequestViewModel;
import com.endoc.mvvmedc.bridge.state.FragmentMonitorViewModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.heaton.blelibrary.ble.Ble;
import cn.com.heaton.blelibrary.ble.callback.BleConnectCallback;
import cn.com.heaton.blelibrary.ble.callback.BleScanCallback;
import cn.com.heaton.blelibrary.ble.model.BleDevice;


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
                            //同步线程不需要set,直接修改值也会生效,而异步线程必须post
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
                        //同步线程不需要set,直接修改值也会生效,而异步线程必须post
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

    //开始连接
    public static void startConnect(BleDevice bleDevice, final MutableLiveData<BleDevice> connectDevice){
        Logger.d("startConnect");
        Ble.getInstance().connect(bleDevice, new BleConnectCallback<BleDevice>() {
            @Override
            public void onConnectionChanged(BleDevice device) {
                //设置状态更新
                Logger.d("连接状态改变=="+device.isConnected());
                connectDevice.postValue(device);
            }

            @Override
            public void onConnectException(BleDevice device, int errorCode) {
                super.onConnectException(device, errorCode);
            }
        });
    }


}
