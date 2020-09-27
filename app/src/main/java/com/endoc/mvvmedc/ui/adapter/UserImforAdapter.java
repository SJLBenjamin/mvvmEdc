package com.endoc.mvvmedc.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.databinding.RecycleAddCasesItemBinding;


/**
 * 此类作废
 */
public class UserImforAdapter extends RecyclerView.Adapter<UserImforAdapter.MyHolder> {

Context mContext;
     public UserImforAdapter(Context context){
        mContext =context;
     }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_add_cases_item, parent, false);
        RecycleAddCasesItemBinding recycleAddCasesItemBinding = DataBindingUtil.bind(inflate);
        return new MyHolder(recycleAddCasesItemBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public MyHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(int position){
            RecycleAddCasesItemBinding recycleAddCasesItemBinding = DataBindingUtil.getBinding(this.itemView);
            switch (position){
                case 0:
                    recycleAddCasesItemBinding.etInput.setVisibility(View.VISIBLE);
                    recycleAddCasesItemBinding.tvContent.setText(mContext.getString(R.string.name));
                    recycleAddCasesItemBinding.ivDump.setVisibility(View.GONE);
                    break;
                case 1:
                    recycleAddCasesItemBinding.etInput.setVisibility(View.VISIBLE);
                    recycleAddCasesItemBinding.tvContent.setText(mContext.getString(R.string.sex));
                    recycleAddCasesItemBinding.ivDump.setVisibility(View.GONE);
                    break;
                case 2:
                    recycleAddCasesItemBinding.etInput.setVisibility(View.VISIBLE);
                    recycleAddCasesItemBinding.tvContent.setText(mContext.getString(R.string.birthday));
                    recycleAddCasesItemBinding.ivDump.setVisibility(View.GONE);
                    break;
                case 3:
                    recycleAddCasesItemBinding.etInput.setVisibility(View.VISIBLE);
                    recycleAddCasesItemBinding.tvContent.setText(mContext.getString(R.string.phone));
                    recycleAddCasesItemBinding.ivDump.setVisibility(View.GONE);
                    break;
                case 4:
                    recycleAddCasesItemBinding.etInput.setVisibility(View.VISIBLE);
                    recycleAddCasesItemBinding.tvContent.setText(mContext.getString(R.string.id_number));
                    recycleAddCasesItemBinding.ivDump.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }

    }
}
