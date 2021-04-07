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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tinder.databinding.BirthdayFragmentBinding;
import com.example.tinder.databinding.GenderFragmentBinding;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class GenderFragment extends Fragment {
    private int currentValue = 0;
    private GenderFragmentBinding binding;
    private MainViewModel viewModel;

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

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        System.out.println("GENDER:   " + R.id.destination_gender_fragment);
        System.out.println("CURRENT:  " + viewModel.getCurrentFragment());
        if (viewModel.getCurrentFragment() != R.id.destination_gender_fragment) {
            skipToSchoolFragment();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        System.out.println("ON VIEW CREATED:  " + viewModel.getCurrentFragment());

        LinearProgressIndicator progressIndicator = getActivity().findViewById(R.id.progress_indicator);
        progressIndicator.setProgressCompat(60, true);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        currentValue = viewModel.getGender();
        if (currentValue == 1) {
            clickWomanButton();
            setContinueClickable();
        } else if (currentValue == -1) {
            clickManButton();
            setContinueClickable();
        }
        binding.showGenderCheckbox.setChecked(viewModel.isShowGender());
        binding.showGenderCheckbox.setOnCheckedChangeListener(new CheckboxChange());

        binding.genderContinueButton.setOnClickListener(v -> navigateToSchoolFragment());
        binding.genderContinueButton.setClickable(false);

        binding.genderBackButton.setOnClickListener(v -> backToBirthdayFragment());

        binding.womanButton.setOnClickListener(v -> setWomanButtonClickedState());
        binding.manButton.setOnClickListener(v -> setManButtonClickedState());
    }

    private void setWomanButtonClickedState() {
        unclickManButton();

        if (currentValue == 1) {
            unclickWomanButton();
            currentValue = 0;
            setContinueUnclickable();
        } else {
            clickWomanButton();
            currentValue = 1;
            setContinueClickable();
        }

        viewModel.setGender(currentValue);
    }

    private void setManButtonClickedState() {
        unclickWomanButton();

        if (currentValue == -1) {
            unclickManButton();
            currentValue = 0;
            setContinueUnclickable();
        } else {
            clickManButton();
            currentValue = -1;
            setContinueClickable();
        }

        viewModel.setGender(currentValue);
    }

    private void clickManButton() {
        binding.manButton.setBackgroundColor(getResources().getColor(R.color.darker_grey, requireActivity().getTheme()));
        binding.manButton.setTextColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
    }

    private void unclickManButton() {
        binding.manButton.setBackgroundColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
        binding.manButton.setTextColor(getResources().getColor(R.color.darker_grey, requireActivity().getTheme()));
    }

    private void clickWomanButton() {
        binding.womanButton.setBackgroundColor(getResources().getColor(R.color.darker_grey, requireActivity().getTheme()));
        binding.womanButton.setTextColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
    }

    private void unclickWomanButton() {
        binding.womanButton.setBackgroundColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
        binding.womanButton.setTextColor(getResources().getColor(R.color.darker_grey, requireActivity().getTheme()));
    }

    private void setContinueClickable() {
        binding.genderContinueButton.setBackgroundColor(getResources().getColor(R.color.red_orange, requireActivity().getTheme()));
        binding.genderContinueButton.setTextColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
        binding.genderContinueButton.setClickable(true);
    }

    private void setContinueUnclickable() {
        binding.genderContinueButton.setBackgroundColor(getResources().getColor(R.color.light_grey, requireActivity().getTheme()));
        binding.genderContinueButton.setTextColor(getResources().getColor(R.color.dark_grey, requireActivity().getTheme()));
        binding.genderContinueButton.setClickable(false);
    }

    private class CheckboxChange implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            viewModel.setShowGender(isChecked);
            setContinueClickable();
        }
    }

    private void navigateToSchoolFragment() {
        viewModel.setGender(currentValue);
        viewModel.setShowGender(binding.showGenderCheckbox.isChecked());
        viewModel.setCurrentFragment(R.id.destination_school_fragment);
        NavHostFragment.findNavController(this).navigate(R.id.destination_school_fragment);
    }

    private void backToBirthdayFragment() {
        viewModel.setCurrentFragment(R.id.destination_birthday_fragment);
        NavHostFragment.findNavController(this).navigate(R.id.action_gender_fragment_pop);
    }

    private void skipToSchoolFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.destination_school_fragment);
    }
}
