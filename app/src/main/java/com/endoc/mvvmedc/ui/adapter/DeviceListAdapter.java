package com.endoc.mvvmedc.ui.adapter;

import android.bluetooth.BluetoothClass;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.databinding.ListviewDeviceItemBinding;

import java.util.List;

import cn.com.heaton.blelibrary.ble.model.BleDevice;

public abstract class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceHolder> {

    List<BleDevice> mList;
    public DeviceListAdapter(List list){
        mList = list;

    }

    @NonNull
    @Override
    public DeviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_device_item, parent, false);
        ListviewDeviceItemBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.listview_device_item, parent, false);
        return new DeviceHolder(inflate.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHolder holder, int position) {
        //holder.deviceName.setText(mList.get(position).getBleName());
        holder.bind(holder,position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public abstract void bindItem(ListviewDeviceItemBinding listviewDeviceItemBinding,DeviceHolder holder,int position);

   public class DeviceHolder extends RecyclerView.ViewHolder{



        public DeviceHolder(@NonNull View itemView) {
            super(itemView);
             //listviewDeviceItemBinding= DataBindingUtil.bind(itemView);
        }


        public void bind(DeviceHolder holder,int position){
            ListviewDeviceItemBinding listviewDeviceItemBinding = DataBindingUtil.getBinding(holder.itemView);
            listviewDeviceItemBinding.setVm(mList.get(position));
            //向外暴露数据
            bindItem(listviewDeviceItemBinding,holder,position);
            //listviewDeviceItemBinding.tvDeviceName.setOnClickListener(mOnClickListener);
        }


    }

}
