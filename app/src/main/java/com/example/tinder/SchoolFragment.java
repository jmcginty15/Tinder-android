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
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tinder.databinding.GenderFragmentBinding;
import com.example.tinder.databinding.SchoolFragmentBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

public class SchoolFragment extends Fragment {
    private SchoolFragmentBinding binding;
    private MainViewModel viewModel;

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

        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setCurrentFragment(5);

        LinearProgressIndicator progressIndicator = getActivity().findViewById(R.id.progress_indicator);
        progressIndicator.setProgressCompat(80, true);

        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding.schoolField.setText(viewModel.getSchool());

        binding.schoolContinueButton.setOnClickListener(v -> navigateToCardFragment());
        binding.schoolContinueButton.setClickable(false);

        binding.schoolBackButton.setOnClickListener(v -> backToGenderFragment());
        binding.schoolSkipButton.setOnClickListener(v -> skipToCardFragment());

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
                viewModel.setSchool(text);
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
        viewModel.setSchool(binding.schoolField.getText().toString());
        NavHostFragment.findNavController(this).navigate(R.id.destination_card_fragment);
    }

    private void skipToCardFragment() {
        viewModel.setSchool("");
        NavHostFragment.findNavController(this).navigate(R.id.destination_card_fragment);
    }

    private void backToGenderFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_school_fragment_pop);
    }
}
