package com.endoc.mvvmedc.bridge.state;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.endoc.mvvmedc.data.repository.RoomRepository;
import com.endoc.mvvmedc.data.room.entity.User;

import java.util.List;

public class AddCaseFragmentViewModel extends ViewModel {
    //User数据,用来接收用户添加新用户
  public MutableLiveData<User>  userObservableField;


    public MutableLiveData<User> getUserObservableField() {
        if(userObservableField==null){
            userObservableField = new MutableLiveData<User>();
        }
        return userObservableField;
    }

    public long saveUser(Context context,User user){
        RoomRepository roomRepository = new RoomRepository(context);
      return   roomRepository.saveUser(user);
    }

    public void deleteUser(Context context,User user){
        RoomRepository roomRepository = new RoomRepository(context);
        roomRepository.deleteUser(user);
    }

    public long deleteUserAll(Context context){
        RoomRepository roomRepository = new RoomRepository(context);
     return    roomRepository.deleteUserAll();
    }

    public LiveData<List<User>> getUsers(Context context){
        RoomRepository roomRepository = new RoomRepository(context);
       return roomRepository.getUserList();
    }

    public User getUser(Context context,String id){
        RoomRepository roomRepository = new RoomRepository(context);
        return roomRepository.getUserById(id);
    }
}
