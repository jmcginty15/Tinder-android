package com.example.tinder;

import androidx.lifecycle.ViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainViewModel extends ViewModel {
    private ArrayList<MainViewModel> userList = new ArrayList<>();
    private String email = "";
    private String name = "";
    private String birthday = "";
    private int age = 0;
    private int gender = 0;
    private boolean showGender = false;
    private String school = "";
    private int currentFragment = R.id.destination_email_fragment;

    public ArrayList<MainViewModel> getUserList() {
        return userList;
    }

    public void addUser(MainViewModel user) {
        userList.add(user);
    }

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
        this.setAge(parseAge(birthday));
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public String getGenderString() {
        if (showGender) {
            if (gender == 1) {
                return "Woman";
            } else if (gender == -1) {
                return "Man";
            } else {
                return "You done goofed";
            }
        } else {
            return "Gender hidden";
        }
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

    private int parseAge(String birthdayString) {
        int length = birthdayString.length();
        String format = length == 10 ? "MM/dd/yyyy" : "MM/dd/yy";

        Date birthday = null;
        try {
            birthday = new SimpleDateFormat(format).parse(birthdayString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date currentDate = new Date();
        long ms = currentDate.getTime() - birthday.getTime();

        double s = (double) ms / 1000.0;
        double h = s / 3600;
        double d = h / 24;
        double y = d / 365.25;

        int newAge = (int) Math.floor(y);
        return newAge;
    }
}
