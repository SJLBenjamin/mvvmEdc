package com.endoc.mvvmedc.data.room.entity;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//https://blog.csdn.net/gtsong/article/details/103782437
@Entity(tableName = "user")
public class User {
    @ColumnInfo(name ="name",typeAffinity = ColumnInfo.TEXT)
    public String name;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.TEXT)
    public String id;//唯一标识

    @ColumnInfo(name = "sex",typeAffinity = ColumnInfo.TEXT)
    public String sex;//性别


    @ColumnInfo(name = "telephone",typeAffinity = ColumnInfo.TEXT)
    public String telephone;//电话号码


    @ColumnInfo(name = "birthDay")
    public long birthDay;//生日

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", sex='" + sex + '\'' +
                ", telephone='" + telephone + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
