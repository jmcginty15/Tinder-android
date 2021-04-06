package com.example.tinder;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tinder.databinding.BirthdayFragmentBinding;
import com.example.tinder.databinding.NameFragmentBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthdayFragment extends Fragment {
    private String DATE_PATTERN = "MM/dd/yyyy";
    private BirthdayFragmentBinding binding;

    public BirthdayFragment() {
        // Required empty public constructor
    }

    public static BirthdayFragment newInstance() {
        return new BirthdayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = BirthdayFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.birthdayContinueButton.setOnClickListener(v -> navigateToGenderFragment());
        binding.birthdayContinueButton.setClickable(false);

        binding.birthdayBackButton.setOnClickListener(v -> backToNameFragment());

        binding.birthdayField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity mainActivity = (MainActivity) getActivity();
                String text = binding.birthdayField.getText().toString();
                boolean validBirthday;

                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
                dateFormat.setLenient(false);

                try {
                    dateFormat.parse(text);
                    validBirthday = true;
                } catch (ParseException e) {
                    validBirthday = false;
                }

                if (validBirthday) {
                    binding.birthdayContinueButton.setBackgroundColor(getResources().getColor(R.color.red_orange, mainActivity.getTheme()));
                    binding.birthdayContinueButton.setTextColor(getResources().getColor(R.color.white, mainActivity.getTheme()));
                } else {
                    binding.birthdayContinueButton.setBackgroundColor(getResources().getColor(R.color.light_grey, mainActivity.getTheme()));
                    binding.birthdayContinueButton.setTextColor(getResources().getColor(R.color.dark_grey, mainActivity.getTheme()));
                }

                binding.birthdayContinueButton.setClickable(validBirthday);
            }
        });
    }

    private void navigateToGenderFragment() {
        Bundle genderFragmentArgs = new GenderFragmentArgs.Builder()
                .setEmail(requireArguments().get("email").toString())
                .setName(requireArguments().get("name").toString())
                .setBirthday(binding.birthdayField.getText().toString()).build().toBundle();
        NavHostFragment.findNavController(this).navigate(R.id.destination_gender_fragment, genderFragmentArgs);
    }

    private void backToNameFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_birthday_fragment_pop);
    }
}
