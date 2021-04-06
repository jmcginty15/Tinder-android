package com.example.tinder;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.google.android.material.progressindicator.LinearProgressIndicator;

public class MainActivity extends FragmentActivity {
    private LinearProgressIndicator progressIndicator;
    private Button button;
    private boolean continueActive = false;
    private int currentFragment = 0;
    static final String STATE_FRAGMENT = "state_of_fragment";

    private String email;
    private String name;
    private String birthday;
    private int gender;
    private boolean showGender;
    private String school;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Navigation.findNavController(this, R.id.nav_host_fragment).setGraph(R.navigation.nav_graph);

        progressIndicator = findViewById(R.id.progress_indicator);

        if (savedInstanceState != null) {
            currentFragment = savedInstanceState.getInt(STATE_FRAGMENT);
        }

        displayNextFragment();
    }

    private void displayNextFragment() {
        setContinueActive(false);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (currentFragment == 0) {
            EmailFragment emailFragment = EmailFragment.newInstance();
//            fragmentTransaction.add(R.id.fragment_container, emailFragment).addToBackStack(null).commit();
        } else if (currentFragment == 1) {
            NameFragment nameFragment = NameFragment.newInstance();
//            fragmentTransaction.add(R.id.fragment_container, nameFragment).addToBackStack(null).commit();
            progressIndicator.setProgressCompat(20, true);
        } else if (currentFragment == 2) {
            BirthdayFragment birthdayFragment = BirthdayFragment.newInstance();
//            fragmentTransaction.add(R.id.fragment_container, birthdayFragment).addToBackStack(null).commit();
            progressIndicator.setProgressCompat(40, true);
        } else if (currentFragment == 3) {
            GenderFragment genderFragment = GenderFragment.newInstance();
//            fragmentTransaction.add(R.id.fragment_container, genderFragment).addToBackStack(null).commit();
            progressIndicator.setProgressCompat(60, true);
        } else if (currentFragment == 4) {
            SchoolFragment schoolFragment = SchoolFragment.newInstance();
//            fragmentTransaction.add(R.id.fragment_container, schoolFragment).addToBackStack(null).commit();
            progressIndicator.setProgressCompat(80, true);
        } else {
            CardFragment cardFragment = CardFragment.newInstance();
//            fragmentTransaction.add(R.id.fragment_container, cardFragment).addToBackStack(null).commit();
            progressIndicator.setProgressCompat(100, true);
        }
    }

    private void closeCurrentFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (currentFragment == 0) {
//            EmailFragment emailFragment = (EmailFragment) fragmentManager.findFragmentById(R.id.fragment_container);
//            if (emailFragment != null) {
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.remove(emailFragment).commit();
//            }
        } else if (currentFragment == 1) {
//            NameFragment nameFragment = (NameFragment) fragmentManager.findFragmentById(R.id.fragment_container);
//            if (nameFragment != null) {
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.remove(nameFragment).commit();
//            }
        } else if (currentFragment == 2) {
//            BirthdayFragment birthdayFragment = (BirthdayFragment) fragmentManager.findFragmentById(R.id.fragment_container);
//            if (birthdayFragment != null) {
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.remove(birthdayFragment).commit();
//            }
        } else if (currentFragment == 3) {
//            GenderFragment genderFragment = (GenderFragment) fragmentManager.findFragmentById(R.id.fragment_container);
//            if (genderFragment != null) {
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.remove(genderFragment).commit();
//            }
        } else if (currentFragment == 4) {
//            SchoolFragment schoolFragment = (SchoolFragment) fragmentManager.findFragmentById(R.id.fragment_container);
//            if (schoolFragment != null) {
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.remove(schoolFragment).commit();
//            }
        }
    }

    private void advanceToNextFragment() {
        if (continueActive) {
            closeCurrentFragment();
            currentFragment++;
            displayNextFragment();
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_FRAGMENT, currentFragment);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void setEmail(boolean valid, String newEmail) {
        setContinueActive(valid);
        email = newEmail;
    }

    public void setName(boolean valid, String newName) {
        setContinueActive(valid);
        name = newName;
    }

    public void setBirthday(boolean valid, String newBirthday) {
        setContinueActive(valid);
        birthday = newBirthday;
    }

    public void setGender(int newGender, boolean newShowGender) {
        if (newGender == 0) {
            setContinueActive(false);
        } else {
            setContinueActive(true);
            gender = newGender;
            showGender = newShowGender;
        }
    }

    public void setShowGender(boolean newShowGender) {
        showGender = newShowGender;
    }

    public void setSchool(boolean valid, String newSchool) {
        setContinueActive(valid);
        school = newSchool;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getGender() {
        return gender;
    }

    public boolean getShowGender() {
        return showGender;
    }

    public String getSchool() {
        return school;
    }

    public void setContinueActive (boolean valid) {
        if (valid && !continueActive) {
            button.setBackgroundColor(getResources().getColor(R.color.red_orange, getTheme()));
            button.setTextColor(getResources().getColor(R.color.white, getTheme()));
        } else if (!valid && continueActive) {
            button.setBackgroundColor(getResources().getColor(R.color.light_grey, getTheme()));
            button.setTextColor(getResources().getColor(R.color.dark_grey, getTheme()));
        }
        continueActive = valid;
    }
}
