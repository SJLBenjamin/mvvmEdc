package com.endoc.mvvmedc.share;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableByte;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableShort;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.endoc.mvvmedc.bean.TitleStatus;

public class MainActivityViewModel extends ViewModel {
    //全局变量,是否在主界面,不在登录注册页面
    public MutableLiveData<Boolean>  isShow =new MutableLiveData<Boolean>();
   /* public boolean isShowTitle;//标题
    public boolean isBack;//返回键按钮
    public boolean isSave;//保存按钮
    public boolean isMessage;//消息图片*/
    public ObservableInt isConstraintLayoutStatus =new ObservableInt();//是否去填充状态栏
    public ObservableInt isShowTitle = new ObservableInt();//标题
    public ObservableInt isBack = new ObservableInt();//返回键按钮
    public ObservableInt isSave = new ObservableInt();//保存按钮
    public ObservableInt isImgMessage = new ObservableInt();//消息图片
    public ObservableField<String>  titleShow = new ObservableField<String>();//显示的内容

}
