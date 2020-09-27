package com.endoc.mvvmedc.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.endoc.mvvmedc.data.room.dao.UserDao;
import com.endoc.mvvmedc.data.room.database.BloodSugarDatabase;
import com.endoc.mvvmedc.data.room.entity.User;

import java.util.List;

/**
 * 数据库的操作
 */
public class RoomRepository {
    private  UserDao mUserDao;

    public RoomRepository(Context mContext){
        if(mUserDao==null){
            mUserDao = BloodSugarDatabase.getInstance(mContext).UserDao();
        }
    }

    public LiveData<List<User>>  getUserList(){
       return mUserDao.getUserList();
    }

    public long saveUser(User user){
      return   mUserDao.insert(user);
    }

    public void deleteUser(User user){
        mUserDao.delete(user);
    }
    public long deleteUserAll(){
       return mUserDao.deleteAll();
    }

    public User getUserById(String id){
       return mUserDao.getUserById(id);
    }

}
