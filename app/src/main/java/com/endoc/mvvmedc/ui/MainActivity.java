package com.endoc.mvvmedc.ui;
import android.os.Bundle;

import android.view.View;


import androidx.databinding.DataBindingUtil;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import androidx.navigation.ui.NavigationUI;

import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.base.BaseActivity;

import com.endoc.mvvmedc.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends BaseActivity {

    private BottomNavigationView mBottomNavigationView;
    private ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mActivityMainBinding.setVm(shareViewModel);//设置绑定
        mActivityMainBinding.setClick(new ClickProxy());
        initNavigationView();
//        Logger.d(shareViewModel);
    }


    private void initNavigationView() {
        //取消navigationBottom的着色,显示图片本身的颜色,ctrl+alt+f设置为全局变量
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv_main);
        mBottomNavigationView.setItemIconTintList(null);
        //设置默认选中的菜单
        mBottomNavigationView.setSelectedItemId(mBottomNavigationView.getMenu().getItem(2).getItemId());
        //设置标题
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.reportFragment, R.id.recordFragment, R.id.monitorFragment,R.id.dataFragment,R.id.myFragment)
//                .build();
        //找到hostFragment
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        //设置每个界面的标题
        //NavigationUI.setupActionBarWithNavController(this, navController,appBarConfiguration);
        //将hostFragment和bottomNavigation绑定,这样的方法绑定后fragment不会常驻内存
        NavigationUI.setupWithNavController(mBottomNavigationView, navController);
        //设置点击事件

    }

    public class  ClickProxy{
        //取消按钮
        public void back(){
            //设置返回键被点击了
            if(shareViewModel.isBack.get()==View.VISIBLE){//如果返回键可见
                shareViewModel.backClick.setValue(true);
            }
        }

        //右边文件
        public void right(){
            //如果右上角可见
            if(shareViewModel.isSave.get()==View.VISIBLE){
                //那么重新设置修改右上角的
                shareViewModel.RightClick.setValue(true);
            }
        }
    }

}
