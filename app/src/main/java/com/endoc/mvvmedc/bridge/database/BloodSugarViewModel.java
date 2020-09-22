package com.endoc.mvvmedc.bridge.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.endoc.mvvmedc.data.room.database.BloodSugarDatabase;
import com.endoc.mvvmedc.data.room.entity.User;

import java.util.List;

public class BloodSugarViewModel extends AndroidViewModel {
    private BloodSugarDatabase bloodSugarDatabase;
    private LiveData<List<User>> userListLiveData;
    public BloodSugarViewModel(@NonNull Application application) {
        super(application);
        bloodSugarDatabase = BloodSugarDatabase.getInstance(application);
        userListLiveData =bloodSugarDatabase.UserDao().getUserList();
    }

    public LiveData<List<User>> getUserListLiveData(){
        return userListLiveData;
    }

}
