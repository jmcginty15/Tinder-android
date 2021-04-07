package com.example.tinder;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.progressindicator.LinearProgressIndicator;

public class MainActivity extends FragmentActivity {

    private MainViewModel viewModel;

    private int currentFragment = 0;
    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        System.out.println("RESTARTING");
//        System.out.println("EMAIL:    " + R.id.destination_email_fragment);
//        System.out.println("NAME:     " + R.id.destination_name_fragment);
//        System.out.println("BIRTHDAY: " + R.id.destination_birthday_fragment);
//        System.out.println("GENDER:   " + R.id.destination_gender_fragment);
//        System.out.println("SCHOOL:   " + R.id.destination_school_fragment);
//        System.out.println("CARD:     " + R.id.destination_card_fragment);
//        System.out.println("CURRENT:  " + viewModel.getCurrentFragment());
        Navigation.findNavController(this, R.id.nav_host_fragment).setGraph(R.navigation.nav_graph);
    }
}
