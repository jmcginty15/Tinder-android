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
import androidx.navigation.fragment.NavHostFragment;

import com.example.tinder.databinding.EmailFragmentBinding;

public class EmailFragment extends Fragment {
    private String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    private EmailFragmentBinding binding;

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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
                MainActivity mainActivity = (MainActivity) getActivity();
                String text = binding.emailField.getText().toString();
                boolean validEmail = text.matches(EMAIL_REGEX);

                if (validEmail) {
                    binding.emailContinueButton.setBackgroundColor(getResources().getColor(R.color.red_orange, mainActivity.getTheme()));
                    binding.emailContinueButton.setTextColor(getResources().getColor(R.color.white, mainActivity.getTheme()));
                } else {
                    binding.emailContinueButton.setBackgroundColor(getResources().getColor(R.color.light_grey, mainActivity.getTheme()));
                    binding.emailContinueButton.setTextColor(getResources().getColor(R.color.dark_grey, mainActivity.getTheme()));
                }

                binding.emailContinueButton.setClickable(validEmail);
            }
        });
    }

    private void navigateToNameFragment() {
        Bundle emailFragmentArgs = new NameFragmentArgs.Builder()
                .setEmail(binding.emailField.getText().toString()).build().toBundle();
        NavHostFragment.findNavController(this).navigate(R.id.destination_name_fragment, emailFragmentArgs);
    }
}
