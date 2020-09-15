package com.endoc.mvvmedc.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.endoc.mvvmedc.App;
import com.endoc.mvvmedc.share.MainActivityViewModel;
import com.endoc.mvvmedc.utils.BarUtils;
import com.orhanobut.logger.Logger;


public  class BaseActivity extends AppCompatActivity {

   public MainActivityViewModel mainActivityViewModel;//全局通讯的

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //已经修改了主题颜色,主题名称没改,还是appTheme,设置不要标题栏的时候必须修改主题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        BarUtils.setStatusBarLightMode(this, true);
        mainActivityViewModel = getAppViewModelProvider().get(MainActivityViewModel.class);
        Logger.d("BaseActivity==="+mainActivityViewModel);
        //initTitleView(mainActivityViewModel);
    }

    // 2020 用法 ViewModelProvider
    protected ViewModelProvider getAppViewModelProvider() {
        return ((App) getApplicationContext()).getAppViewModelProvider(this);
    }

    protected ViewModelProvider getActivityViewModelProvider(AppCompatActivity activity) {
        return new ViewModelProvider(activity, activity.getDefaultViewModelProviderFactory());
    }

    public void showLongToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 初始化状态栏
     *
     *  public ObservableInt isConstraintLayoutStatus =new ObservableInt();//是否去填充状态栏
     *     public ObservableInt isShowTitle = new ObservableInt();//标题
     *     public ObservableInt isBack = new ObservableInt();//返回键按钮
     *     public ObservableInt isSave = new ObservableInt();//保存按钮
     *     public ObservableInt isMessage = new ObservableInt();//消息图片
     *     public ObservableField<String>  titleShow = new ObservableField<String>();//显示的内容
     */
   public  void initTitleView(int back,int constraintLayoutStatus,int message,int save,String titleShow){
       mainActivityViewModel.isBack.set(back);
       mainActivityViewModel.isConstraintLayoutStatus.set(constraintLayoutStatus);
       mainActivityViewModel.isImgMessage.set(message);
       mainActivityViewModel.isSave.set(save);
       mainActivityViewModel.titleShow.set(titleShow);
   }
}
