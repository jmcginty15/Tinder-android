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

        Navigation.findNavController(this, R.id.nav_host_fragment).setGraph(R.navigation.nav_graph);

        if (savedInstanceState != null) {
            currentFragment = savedInstanceState.getInt(STATE_FRAGMENT);
        }
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_FRAGMENT, currentFragment);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void setCurrentFragment(int newCurrentFragment) {
        this.currentFragment = newCurrentFragment;
    }
}
