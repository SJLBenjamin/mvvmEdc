package com.endoc.mvvmedc.data.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.endoc.mvvmedc.data.room.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
   public long insert(User user);

    @Delete
    public void delete(User user);

    @Update
    public void update(User user);

    @Query("SELECT * FROM user")
   public LiveData<List<User>>  getUserList();


    @Query("SELECT * FROM user WHERE id=:id")
    public User getUserById(String id);

    //执行语句的操作用query执行
    @Query("DELETE  FROM user")
    public int deleteAll();

}
