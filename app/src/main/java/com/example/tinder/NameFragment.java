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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setCurrentFragment(2);

        LinearProgressIndicator progressIndicator = getActivity().findViewById(R.id.progress_indicator);
        progressIndicator.setProgressCompat(20, true);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
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

    private void updateViewModel() {

    }

    private void navigateToBirthdayFragment() {
        viewModel.setName(binding.nameField.getText().toString());
        NavHostFragment.findNavController(this).navigate(R.id.destination_birthday_fragment);
    }

    private void backToEmailFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_name_fragment_pop);
    }
}
