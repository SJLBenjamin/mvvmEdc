package com.endoc.mvvmedc.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentController;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.endoc.mvvmedc.App;
import com.endoc.mvvmedc.share.MainActivityViewModel;
import com.endoc.mvvmedc.share.ShareViewModel;
import com.orhanobut.logger.Logger;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 实例化对象不会是BaseFragment,所以可以在对应的实例化类中重写对应的方法
 */
public  class BaseFragment extends Fragment  implements EasyPermissions.PermissionCallbacks {

   public AppCompatActivity mActivity;
    public ShareViewModel shareViewModel;//全局共享的viewModel

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
        //Logger.d("BaseFragment getAppViewModelProvider()==="+getAppViewModelProvider());

    }

    /**
     * @param s 左上角内容(点击事件)发生改变的子类想处理事件,重写此方法即可
     */
    public void backChanged(Boolean s){}

    /**
     * @param s 右上角内容(点击事件)发生改变的子类想处理事件,重写此方法即可,声明泛型,可复用性高
     */
    public <T> void rightChanged(T s){}

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shareViewModel = getAppViewModelProvider().get(ShareViewModel.class);

        shareViewModel.backClick.observe(mActivity, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                backChanged(s);
            }
        });

        //设置右上角内容改变监听者,当内容改变时候,去调用rightChanged方法,子类就可以去判断这个右上角内容和自己页面是否一致,如果一致就表明此事件属于本页面,应该去处理
        shareViewModel.RightClick.observe(mActivity, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean s) {
                //将当前的右上角内容传递下去,所有的判断都是通过shareViewModel变化
                rightChanged(shareViewModel.rightTitleShow.get());
            }
        });

    }

    //让BaseFragment拿到绑定了Application的ViewModelProvider对象
    public ViewModelProvider getAppViewModelProvider(){
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
    public  void initTitleView(int back,int constraintLayoutStatus,int isImgMessage,int save,String titleShow,String rightShow,int isBottomShow){
        shareViewModel.isBack.set(back);
        shareViewModel.isConstraintLayoutStatus.set(constraintLayoutStatus);
        shareViewModel.isImgMessage.set(isImgMessage);
        shareViewModel.isSave.set(save);
        shareViewModel.titleShow.set(titleShow);
        shareViewModel.rightTitleShow.set(rightShow);
        shareViewModel.isBottomShow.set(isBottomShow);
    }


    public NavController getNavController(){
        //not associated with a fragment manager可能会出此问题
        return NavHostFragment.findNavController(this);
    }



    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onResume() {
        super.onResume();
        //得到Fragment的根布局并使该布局可以获得焦点
        getView().setFocusableInTouchMode(true);
        //得到Fragment的根布局并且使其获得焦点
        getView().requestFocus();
        //对该根布局View注册KeyListener的监听
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    Logger.d("KEYCODE_BACK");
                    //getParentFragmentManager().popBackStack();
                    return false;
                }
            return false;
            }
        });
    }

    public void right() {

    }
}
