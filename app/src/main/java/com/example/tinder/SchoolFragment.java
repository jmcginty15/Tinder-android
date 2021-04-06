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

import com.example.tinder.databinding.GenderFragmentBinding;
import com.example.tinder.databinding.SchoolFragmentBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class SchoolFragment extends Fragment {
    private SchoolFragmentBinding binding;

    public SchoolFragment() {
        // Required empty public constructor
    }

    public static SchoolFragment newInstance() {
        return new SchoolFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = SchoolFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.schoolContinueButton.setOnClickListener(v -> navigateToCardFragment());
        binding.schoolContinueButton.setClickable(false);

        binding.schoolBackButton.setOnClickListener(v -> backToGenderFragment());

        binding.schoolField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = binding.schoolField.getText().toString();
                boolean validSchool = text != null && text != "";

                if (validSchool) {
                    binding.schoolContinueButton.setBackgroundColor(getResources().getColor(R.color.red_orange, requireActivity().getTheme()));
                    binding.schoolContinueButton.setTextColor(getResources().getColor(R.color.white, requireActivity().getTheme()));
                } else {
                    binding.schoolContinueButton.setBackgroundColor(getResources().getColor(R.color.light_grey, requireActivity().getTheme()));
                    binding.schoolContinueButton.setTextColor(getResources().getColor(R.color.dark_grey, requireActivity().getTheme()));
                }

                binding.schoolContinueButton.setClickable(validSchool);
            }
        });
    }

    private void navigateToCardFragment() {
        Bundle cardFragmentArgs = new CardFragmentArgs.Builder()
                .setEmail(requireArguments().get("email").toString())
                .setName(requireArguments().get("name").toString())
                .setBirthday(requireArguments().get("birthday").toString())
                .setGender(Integer.parseInt(requireArguments().get("gender").toString()))
                .setShowGender(Boolean.parseBoolean(requireArguments().get("showGender").toString()))
                .setSchool(binding.schoolField.getText().toString()).build().toBundle();
        NavHostFragment.findNavController(this).navigate(R.id.destination_card_fragment, cardFragmentArgs);
    }

    private void backToGenderFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_school_fragment_pop);
    }
}
