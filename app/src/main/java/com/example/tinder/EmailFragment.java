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

import com.example.tinder.databinding.EmailFragmentBinding;

import com.google.android.material.progressindicator.LinearProgressIndicator;

public class EmailFragment extends Fragment {
    private String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private EmailFragmentBinding binding;
    private MainViewModel viewModel;

    public EmailFragment() {
        // Required empty public constructor
    }

    public static EmailFragment newInstance() {
        return new EmailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = EmailFragmentBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        System.out.println("EMAIL:    " + R.id.destination_email_fragment);
        System.out.println("CURRENT:  " + viewModel.getCurrentFragment());
        if (viewModel.getCurrentFragment() != R.id.destination_email_fragment) {
            skipToNameFragment();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.setCurrentFragment(viewModel.getCurrentFragment());

        System.out.println("ON VIEW CREATED:  " + viewModel.getCurrentFragment());

        LinearProgressIndicator progressIndicator = getActivity().findViewById(R.id.progress_indicator);
        progressIndicator.setProgressCompat(0, true);

        binding.emailField.setText(viewModel.getEmail());

        binding.emailContinueButton.setOnClickListener(v -> navigateToNameFragment());
        binding.emailContinueButton.setClickable(false);

        binding.emailField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = binding.emailField.getText().toString();
                viewModel.setEmail(text);

                boolean validEmail = text.matches(EMAIL_REGEX);

                if (validEmail) {
                    binding.emailContinueButton.setBackgroundColor(getResources().getColor(R.color.red_orange, getActivity().getTheme()));
                    binding.emailContinueButton.setTextColor(getResources().getColor(R.color.white, getActivity().getTheme()));
                } else {
                    binding.emailContinueButton.setBackgroundColor(getResources().getColor(R.color.light_grey, getActivity().getTheme()));
                    binding.emailContinueButton.setTextColor(getResources().getColor(R.color.dark_grey, getActivity().getTheme()));
                }

                binding.emailContinueButton.setClickable(validEmail);
            }
        });
    }

    private void navigateToNameFragment() {
        viewModel.setEmail(binding.emailField.getText().toString());
        viewModel.setCurrentFragment(R.id.destination_name_fragment);
        NavHostFragment.findNavController(this).navigate(R.id.destination_name_fragment);
    }

    private void skipToNameFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.destination_name_fragment);
    }
}
