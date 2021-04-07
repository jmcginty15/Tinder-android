package com.example.tinder;

import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private String email = "";
    private String name = "";
    private String birthday = "";
    private int gender = 0;
    private boolean showGender = false;
    private String school = "";
    private int currentFragment = R.id.destination_email_fragment;

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

    public int getCurrentFragment() {
        return currentFragment;
    }

    public void setCurrentFragment(int currentFragment) {
        this.currentFragment = currentFragment;
    }

    public void resetAll() {
        email = "";
        name = "";
        birthday = "";
        gender = 0;
        showGender = false;
        school = "";
        currentFragment = R.id.destination_email_fragment;
    }
}
