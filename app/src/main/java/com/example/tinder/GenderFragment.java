package com.example.tinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tinder.databinding.BirthdayFragmentBinding;
import com.example.tinder.databinding.GenderFragmentBinding;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GenderFragment extends Fragment {
    private int currentValue = 0;
    private GenderFragmentBinding binding;

    public GenderFragment() {
        // Required empty public constructor
    }

    public static GenderFragment newInstance() {
        return new GenderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = GenderFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.genderContinueButton.setOnClickListener(v -> navigateToSchoolFragment());
        binding.genderContinueButton.setClickable(false);

        binding.genderBackButton.setOnClickListener(v -> backToBirthdayFragment());

        binding.womanButton.setOnClickListener(v -> setWomanButtonClickedState());
        binding.manButton.setOnClickListener(v -> setManButtonClickedState());
    }

    private void setWomanButtonClickedState() {
        binding.manButton.setBackgroundColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
        binding.manButton.setTextColor(getResources().getColor(R.color.darker_grey, requireActivity().getTheme()));

        if (currentValue == 1) {
            binding.womanButton.setBackgroundColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
            binding.womanButton.setTextColor(getResources().getColor(R.color.darker_grey, requireActivity().getTheme()));
            currentValue = 0;

            binding.genderContinueButton.setBackgroundColor(getResources().getColor(R.color.light_grey, requireActivity().getTheme()));
            binding.genderContinueButton.setTextColor(getResources().getColor(R.color.dark_grey, requireActivity().getTheme()));
            binding.genderContinueButton.setClickable(false);
        } else {
            binding.womanButton.setBackgroundColor(getResources().getColor(R.color.darker_grey, requireActivity().getTheme()));
            binding.womanButton.setTextColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
            currentValue = 1;

            binding.genderContinueButton.setBackgroundColor(getResources().getColor(R.color.red_orange, requireActivity().getTheme()));
            binding.genderContinueButton.setTextColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
            binding.genderContinueButton.setClickable(true);
        }
    }

    private void setManButtonClickedState() {
        binding.womanButton.setBackgroundColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
        binding.womanButton.setTextColor(getResources().getColor(R.color.darker_grey, requireActivity().getTheme()));

        if (currentValue == -1) {
            binding.manButton.setBackgroundColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
            binding.manButton.setTextColor(getResources().getColor(R.color.darker_grey, requireActivity().getTheme()));
            currentValue = 0;

            binding.genderContinueButton.setBackgroundColor(getResources().getColor(R.color.light_grey, requireActivity().getTheme()));
            binding.genderContinueButton.setTextColor(getResources().getColor(R.color.dark_grey, requireActivity().getTheme()));
            binding.genderContinueButton.setClickable(false);
        } else {
            binding.manButton.setBackgroundColor(getResources().getColor(R.color.darker_grey, requireActivity().getTheme()));
            binding.manButton.setTextColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
            currentValue = -1;

            binding.genderContinueButton.setBackgroundColor(getResources().getColor(R.color.red_orange, requireActivity().getTheme()));
            binding.genderContinueButton.setTextColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
            binding.genderContinueButton.setClickable(true);
        }
    }

    private void navigateToSchoolFragment() {
        Bundle schoolFragmentArgs = new SchoolFragmentArgs.Builder()
                .setEmail(requireArguments().get("email").toString())
                .setName(requireArguments().get("name").toString())
                .setBirthday(requireArguments().get("birthday").toString())
                .setGender(currentValue)
                .setShowGender(binding.showGenderCheckbox.isChecked()).build().toBundle();
        NavHostFragment.findNavController(this).navigate(R.id.destination_school_fragment, schoolFragmentArgs);
    }

    private void backToBirthdayFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_gender_fragment_pop);
    }
}
