package com.endoc.mvvmedc.base;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.endoc.mvvmedc.App;
import com.endoc.mvvmedc.share.MainActivityViewModel;
import com.orhanobut.logger.Logger;

public abstract class BaseFragment extends Fragment {

    AppCompatActivity mActivity;
    public MainActivityViewModel mainActivityViewModel;//全局共享的viewModel

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
        mainActivityViewModel = getAppViewModelProvider().get(MainActivityViewModel.class);
        Logger.d("BaseFragment==="+mainActivityViewModel);
    }



    //让BaseFragment拿到绑定了Application的ViewModelProvider对象
    public ViewModelProvider getAppViewModelProvider(){
        return ((App) mActivity.getApplication()).getAppViewModelProvider(mActivity);
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
        mainActivityViewModel.isBack.set(back);
        mainActivityViewModel.isConstraintLayoutStatus.set(constraintLayoutStatus);
        mainActivityViewModel.isImgMessage.set(isImgMessage);
        mainActivityViewModel.isSave.set(save);
        mainActivityViewModel.titleShow.set(titleShow);
    }
}
