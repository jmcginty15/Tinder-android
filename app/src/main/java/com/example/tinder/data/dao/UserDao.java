package com.example.tinder.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tinder.data.entities.UserEntity;

import java.util.List;

@Dao
public abstract class UserDao {
    @Query("SELECT * FROM users")
    public abstract LiveData<List<UserEntity>> getUsers();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insertUser(UserEntity user);

    @Query("DELETE FROM users")
    public abstract void deleteUsers();
}
