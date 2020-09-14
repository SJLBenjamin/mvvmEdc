package com.endoc.mvvmedc.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {
    //全局变量,是否在主界面,不在登录注册页面
    MutableLiveData<Boolean>  isShow =new MutableLiveData<Boolean>();
}
