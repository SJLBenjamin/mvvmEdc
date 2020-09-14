package com.endoc.mvvmedc.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.endoc.mvvmedc.utils.BarUtils;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //已经修改了主题颜色,主题名称没改,还是appTheme,设置不要标题栏的时候必须修改主题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
        BarUtils.setStatusBarLightMode(this, true);
    }
}
