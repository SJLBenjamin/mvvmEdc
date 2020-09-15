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
    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return new ViewModelStore();
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

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化log打印
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
