package com.endoc.mvvmedc.data.adpter;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class CommonBindingAdapter {
/**
 * @param view 对应的view
 * @param molImage  显示箭头的图标,此处写成bindingAdapter绑定,模拟访问网络
 */
@BindingAdapter(value = {"molImage"},requireAll = false)
public static  void showMolJianTou(ImageView view,int molImage){
            view.setImageDrawable(view.getContext().getResources().getDrawable(molImage));
        }
}
