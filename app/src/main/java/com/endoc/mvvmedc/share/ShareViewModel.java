package com.endoc.mvvmedc.share;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

/**
 * 用来记录整个app的标题栏的显示状态
 */
public class ShareViewModel extends ViewModel {
    public ObservableInt isConstraintLayoutStatus =new ObservableInt();//是否去填充状态栏
    public ObservableInt isShowTitle = new ObservableInt();//标题
    public ObservableInt isBack = new ObservableInt();//返回键按钮
    public ObservableInt isSave = new ObservableInt();//保存按钮
    public ObservableInt isImgMessage = new ObservableInt();//消息图片
    public ObservableField<String> titleShow = new ObservableField<String>();//显示的内容
}
