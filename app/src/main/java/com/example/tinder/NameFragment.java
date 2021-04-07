package com.example.tinder;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tinder.databinding.NameFragmentBinding;
import com.google.android.material.progressindicator.LinearProgressIndicator;

public class NameFragment extends Fragment {
    private NameFragmentBinding binding;
    private MainViewModel viewModel;

    public NameFragment() {
    }

    public static NameFragment newInstance() {
        return new NameFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = NameFragmentBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        System.out.println("NAME:     " + R.id.destination_name_fragment);
        System.out.println("CURRENT:  " + viewModel.getCurrentFragment());
        if (viewModel.getCurrentFragment() != R.id.destination_name_fragment) {
            skipToBirthdayFragment();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.setCurrentFragment(viewModel.getCurrentFragment());

        System.out.println("ON VIEW CREATED:  " + viewModel.getCurrentFragment());

        LinearProgressIndicator progressIndicator = getActivity().findViewById(R.id.progress_indicator);
        progressIndicator.setProgressCompat(20, true);

        binding.nameField.setText(viewModel.getName());

        binding.nameContinueButton.setOnClickListener(v -> navigateToBirthdayFragment());
        binding.nameContinueButton.setClickable(false);

        binding.nameBackButton.setOnClickListener(v -> backToEmailFragment());

        binding.nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = binding.nameField.getText().toString();
                viewModel.setName(text);

                boolean validName = !text.isEmpty() && !text.isEmpty();

                if (validName) {
                    binding.nameContinueButton.setBackgroundColor(getResources().getColor(R.color.red_orange, requireActivity().getTheme()));
                    binding.nameContinueButton.setTextColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
                } else {
                    binding.nameContinueButton.setBackgroundColor(getResources().getColor(R.color.light_grey, requireActivity().getTheme()));
                    binding.nameContinueButton.setTextColor(getResources().getColor(R.color.dark_grey, requireActivity().getTheme()));
                }

                binding.nameContinueButton.setClickable(validName);
            }
        });
    }

    private void navigateToBirthdayFragment() {
        viewModel.setName(binding.nameField.getText().toString());
        viewModel.setCurrentFragment(R.id.destination_birthday_fragment);
        NavHostFragment.findNavController(this).navigate(R.id.destination_birthday_fragment);
    }

    private void backToEmailFragment() {
        viewModel.setCurrentFragment(R.id.destination_email_fragment);
        NavHostFragment.findNavController(this).navigate(R.id.action_name_fragment_pop);
    }

    private void skipToBirthdayFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.destination_birthday_fragment);
    }
}
