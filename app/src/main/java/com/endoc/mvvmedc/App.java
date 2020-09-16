package com.endoc.mvvmedc;

import android.app.Activity;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class App  extends Application implements ViewModelStoreOwner {
    ViewModelProvider.Factory mFactory;

    //必须保证这个是同一个对象,否则就
    private ViewModelStore mAppViewModelStore;
    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        //此方法提供的返回值必须要是同一个对象,否则就会导致创建的viewModel不一致,此对象用来存储ViewModel对象,如果每次创建了新的ViewModelStore,那么viewModel每次都为null,所以都会创建
        return mAppViewModelStore;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化log打印
        Logger.addLogAdapter(new AndroidLogAdapter());
        mAppViewModelStore = new ViewModelStore();
    }

    /**
     * @param activity
     * @return 提供ViewModel所需要的ViewModelStoreOwner对象,view的生命周期跟随ViewModelStoreOwner,此处ViewModelStoreOwner是Application的子类,所以生命周期为整个app的生命周期
     */
    public ViewModelProvider getAppViewModelProvider(Activity activity) {
        return new ViewModelProvider((App) activity.getApplicationContext(),
                ((App) activity.getApplicationContext()).getAppFactory(activity));
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = checkApplication(activity);
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(application);
        }
        return mFactory;
    }

    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }




}
