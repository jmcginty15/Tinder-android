package com.example.tinder.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.tinder.data.dao.UserDao;
import com.example.tinder.data.database.Database;
import com.example.tinder.data.entities.UserEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<UserEntity>> usersList;

    private final Executor executor;

    public UserRepository(Application application, ExecutorService executor) {
        Database database = Database.getDatabase(application);
        this.executor = executor;
        userDao = database.userDao();
        usersList = userDao.getUsers();
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return usersList;
    }

    public void addUser(UserEntity userEntity) {
        executor.execute(() -> userDao.insertUser(userEntity));
    }
}
