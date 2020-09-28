package com.endoc.mvvmedc.bridge.state;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.endoc.mvvmedc.data.repository.RoomRepository;
import com.endoc.mvvmedc.data.room.entity.User;

import java.util.List;

public class CaseListFragmentViewModel extends ViewModel {


    public void deleteUser(Context context,User user){
        RoomRepository roomRepository = new RoomRepository(context);
        roomRepository.deleteUser(user);
    }

    public long deleteUserAll(Context context){
        RoomRepository roomRepository = new RoomRepository(context);
        return    roomRepository.deleteUserAll();
    }

    /**
     *
     *
     * @param context 上下文
     * @return 数据库的
     */
    public LiveData<List<User>> getUsers(Context context){
        RoomRepository roomRepository = new RoomRepository(context);
        return roomRepository.getUserList();
    }



    public User getUser(Context context,String id){
        RoomRepository roomRepository = new RoomRepository(context);
        return roomRepository.getUserById(id);
    }
}
