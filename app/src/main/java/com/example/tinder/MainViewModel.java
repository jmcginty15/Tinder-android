package com.example.tinder;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavDestination;

public class MainViewModel extends ViewModel {
    private String email;
    private String name;
    private String birthday;
    private int gender;
    private boolean showGender;
    private String school;
    private int currentFragment = 0;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isShowGender() {
        return showGender;
    }

    public void setShowGender(boolean showGender) {
        this.showGender = showGender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getCurrentDestination() {
        return currentFragment;
    }

    public void setCurrentDestination(int currentFragment) {
        this.currentFragment = currentFragment;
    }
}
