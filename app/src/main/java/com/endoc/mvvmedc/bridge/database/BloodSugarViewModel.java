package com.endoc.mvvmedc.bridge.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.endoc.mvvmedc.data.room.dao.UserDao;
import com.endoc.mvvmedc.data.room.database.BloodSugarDatabase;
import com.endoc.mvvmedc.data.room.entity.User;

import java.util.List;

public class BloodSugarViewModel extends AndroidViewModel {
    private BloodSugarDatabase bloodSugarDatabase;


    //查询数据库中的数据是否发生变化
    private LiveData<List<User>> userListLiveData;


    //用来接收用户输入的信息
    public  LiveData<User> userObservableField = new MutableLiveData<>();
    private final UserDao mUserDao;//user表


    public BloodSugarViewModel(@NonNull Application application) {
        super(application);
        bloodSugarDatabase = BloodSugarDatabase.getInstance(application);
        mUserDao = bloodSugarDatabase.UserDao();
    }

    public LiveData<List<User>> getUserListLiveData(){
        if(userListLiveData ==null){
              userListLiveData = mUserDao.getUserList();
        }
          return   userListLiveData;
    }

    public void saveUser(User user){
        mUserDao.insert(user);
    }

}
