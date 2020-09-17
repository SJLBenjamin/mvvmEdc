package com.endoc.mvvmedc.ui.adapter;

import android.bluetooth.BluetoothClass;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.endoc.mvvmedc.R;

import java.util.List;

import cn.com.heaton.blelibrary.ble.model.BleDevice;

public class DeviceListAdapter extends RecyclerView.Adapter<DeviceListAdapter.DeviceHolder> {

    List<BleDevice> mList;
    public DeviceListAdapter(List list){
        mList = list;
    }

    @NonNull
    @Override
    public DeviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_device_item, parent, false);
        return new DeviceHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHolder holder, int position) {
        holder.deviceName.setText(mList.get(position).getBleName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class DeviceHolder extends RecyclerView.ViewHolder{
        TextView deviceName;
        public DeviceHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
