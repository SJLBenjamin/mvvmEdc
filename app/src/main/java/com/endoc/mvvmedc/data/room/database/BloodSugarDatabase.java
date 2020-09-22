package com.endoc.mvvmedc.data.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.endoc.mvvmedc.data.room.dao.UserDao;
import com.endoc.mvvmedc.data.room.entity.User;

@Database(entities = {User.class},version =  1,exportSchema = false)
public abstract class BloodSugarDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "my_db";

    private static BloodSugarDatabase databaseInstance;


    /**
     * 此处可封装动态建库
     *
     * @param context 数据库
     * @return
     */
    public static synchronized BloodSugarDatabase getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(), BloodSugarDatabase.class, DATABASE_NAME).build();
        }
        return databaseInstance;
    }

    //指定Dao函数,操作数据库
    public abstract UserDao UserDao();
}
