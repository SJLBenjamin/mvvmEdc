package com.endoc.mvvmedc.ui;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentController;

import com.endoc.mvvmedc.R;
import com.endoc.mvvmedc.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //取消navigationBottom的着色,显示图片本身的颜色,ctrl+alt+f设置为全局变量
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bnv_main);
        mBottomNavigationView.setItemIconTintList(null);
        //设置默认选中的菜单
        mBottomNavigationView.setSelectedItemId(mBottomNavigationView.getMenu().getItem(2).getItemId());

        //设置点击事件

    }

   /* //修改底部栏图标大小
    private void adjustNavigationIcoSize(BottomNavigationView navigation,float height,float width){
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(android.support.design.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }*/
}
