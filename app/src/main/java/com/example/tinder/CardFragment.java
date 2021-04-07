package com.example.tinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tinder.databinding.CardFragmentBinding;
import com.example.tinder.databinding.SchoolFragmentBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardFragment extends Fragment {
    private MainViewModel viewModel;

    private CardFragmentBinding binding;
    public CardFragment() {
        // Required empty public constructor
    }

    public static CardFragment newInstance() {
        return new CardFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = CardFragmentBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        System.out.println("ON VIEW CREATED:  " + viewModel.getCurrentFragment());

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        LinearProgressIndicator progressIndicator = getActivity().findViewById(R.id.progress_indicator);
        progressIndicator.setProgressCompat(100, true);

        final String email = viewModel.getEmail();
        final String name = viewModel.getName();
        final String birthday = viewModel.getBirthday();
        final int gender = viewModel.getGender();
        final boolean showGender = viewModel.isShowGender();
        final String school = viewModel.getSchool();

        binding.email.setText(email);
        binding.name.setText(name);
        binding.age.setText(parseAge(birthday));
        if (showGender) { binding.gender.setText(parseGender(gender)); }
        else { binding.gender.setText("Gender hidden"); }
        binding.school.setText(school);

        binding.cardBackButton.setOnClickListener(v -> backToSchoolFragment());
        binding.cardContinueButton.setOnClickListener(v -> backToStart());
    }

    private String parseAge(String birthdayString) {
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

        int age = (int) Math.floor(y);

        return Integer.toString(age);
    }

    private String parseGender(int val) {
        if (val == 1) { return "Woman"; }
        else if (val == -1 ) { return "Man"; }
        else { return "You done goofed"; }
    }

    private void backToSchoolFragment() {
        viewModel.setCurrentFragment(R.id.destination_school_fragment);
        NavHostFragment.findNavController(this).navigate(R.id.action_card_fragment_pop);
    }

    private void backToStart() {
        viewModel.resetAll();
        NavHostFragment.findNavController(this).navigate(R.id.destination_email_fragment);
    }
}
