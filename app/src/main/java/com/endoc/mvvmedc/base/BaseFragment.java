package com.endoc.mvvmedc.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentController;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.endoc.mvvmedc.App;
import com.endoc.mvvmedc.share.MainActivityViewModel;
import com.endoc.mvvmedc.share.ShareViewModel;
import com.orhanobut.logger.Logger;

public abstract class BaseFragment extends Fragment {

   public AppCompatActivity mActivity;
    public ShareViewModel shareViewModel;//全局共享的viewModel

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
        //Logger.d("BaseFragment getAppViewModelProvider()==="+getAppViewModelProvider());

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shareViewModel = getAppViewModelProvider().get(ShareViewModel.class);
        Logger.d("BaseFragment==="+shareViewModel);
        //Logger.d("BaseFragment getAppViewModelProvider=="+getAppViewModelProvider());//不一样
    }




    //让BaseFragment拿到绑定了Application的ViewModelProvider对象
    public ViewModelProvider getAppViewModelProvider(){
       // Logger.d("BaseFragment getApplication==="+((App) mActivity.getApplication()));
        return ((App) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity);
    }


    // 同学们，只是提示而已
    public void showLongToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    // 同学们，只是提示而已
    public void showShortToast(String text) {
        Toast.makeText(mActivity.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
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
    public  void initTitleView(int back,int constraintLayoutStatus,int isImgMessage,int save,String titleShow){
        shareViewModel.isBack.set(back);
        shareViewModel.isConstraintLayoutStatus.set(constraintLayoutStatus);
        shareViewModel.isImgMessage.set(isImgMessage);
        shareViewModel.isSave.set(save);
        shareViewModel.titleShow.set(titleShow);
    }


    public NavController getNavController(){
        return NavHostFragment.findNavController(this);
    }
}
