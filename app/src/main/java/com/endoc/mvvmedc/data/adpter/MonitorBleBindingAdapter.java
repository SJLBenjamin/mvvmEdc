package com.endoc.mvvmedc.data.adpter;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import com.endoc.mvvmedc.App;
import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.bridge.state.FragmentMonitorViewModel;
import com.endoc.mvvmedc.data.repository.BleOperation;
import com.endoc.mvvmedc.ui.MainActivity;
import com.endoc.mvvmedc.ui.fragment.MonitorFragment;

/**
 * BindingAdapter是不让在Activity中或者fragment中去更新ui,虽然dataBinding将数据和ui直接绑定了,更新的数据只是url,需要去加载后才能显示真正的图片,所以需要在Adapter中更新
 */
public class MonitorBleBindingAdapter {

    @BindingAdapter(value = {"searchStatus"})
    public static void showSearchText(TextView textView,boolean searchStatus){
        if(searchStatus){
            textView.setText(textView.getContext().getText(R.string.stop_search));
        }else {
            textView.setText(textView.getContext().getText(R.string.start_search));

            //怎么获取ViewModel中的成员,此ViewModel是Fragment已经实例化的对象
            FragmentMonitorViewModel fragmentMonitorViewModel = ((App) textView.getContext().getApplicationContext()).getAppViewModelProvider((MainActivity) textView.getContext()).get(FragmentMonitorViewModel.class);


        }
    }

}
