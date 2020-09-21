package com.endoc.mvvmedc.data.adpter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import com.endoc.mvvmedc.R;
import com.orhanobut.logger.Logger;

/**
 * BindingAdapter是不让在Activity中或者fragment中去更新ui,虽然dataBinding将数据和ui直接绑定了,更新的数据只是url,需要去加载后才能显示真正的图片,所以需要在Adapter中更新
 */
public class MonitorBleBindingAdapter {


  /*此方法用来更新搜索的ui,不知道为什么只有第一次调用
  @BindingAdapter(value = {"searchStatus"},requireAll = true)
    public static void showSearchText(TextView textView,boolean searchStatus){
        Logger.d("搜索状态改变");
        if(searchStatus){
            textView.setText(textView.getContext().getText(R.string.stop_search));
        }else {
            textView.setText(textView.getContext().getText(R.string.start_search));
        }
    }*/


    @BindingAdapter(value = {"connectStatus","deviceName"},requireAll = false)
    public static void showConnectStatus(TextView textView,boolean connectStatus,String deviceName){
        Logger.d("连接状态改变");
        if(connectStatus){
            textView.setText(textView.getContext().getText(R.string.connect_device)+deviceName);
        }else {
            textView.setText(textView.getContext().getText(R.string.unbound_device_jump));
        }
    }

}
