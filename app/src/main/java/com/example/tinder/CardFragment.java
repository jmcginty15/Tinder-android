package com.example.tinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tinder.databinding.CardFragmentBinding;
import com.example.tinder.databinding.SchoolFragmentBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardFragment extends Fragment {
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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearProgressIndicator progressIndicator = getActivity().findViewById(R.id.progress_indicator);
        progressIndicator.setProgressCompat(100, true);

        final String email = requireArguments().get("email").toString();
        final String name = requireArguments().get("name").toString();
        final String birthday = requireArguments().get("birthday").toString();
        final int gender = Integer.parseInt(requireArguments().get("gender").toString());
        final boolean showGender = Boolean.parseBoolean(requireArguments().get("showGender").toString());
        final String school = requireArguments().get("school").toString();

        binding.name.setText(name);
        binding.age.setText(parseAge(birthday));
        if (showGender) { binding.gender.setText(parseGender(gender)); }
        else { binding.gender.setText("Gender hidden"); }
        binding.school.setText(school);
        binding.email.setText(email);

        binding.cardBackButton.setOnClickListener(v -> backToSchoolFragment());
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
        NavHostFragment.findNavController(this).navigate(R.id.action_card_fragment_pop);
    }
}
