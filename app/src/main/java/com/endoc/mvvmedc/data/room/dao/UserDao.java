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
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>>  getUserList();

    @Query("SELECT * FROM user WHERE id=:id")
    User getUserById(int id);

}
