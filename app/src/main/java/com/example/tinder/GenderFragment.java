package com.example.tinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.fragment.app.Fragment;

import com.google.android.material.checkbox.MaterialCheckBox;

public class GenderFragment extends Fragment {
    Button womanButton;
    Button manButton;
    MaterialCheckBox showGenderCheckbox;
    int currentValue = 0;

    public GenderFragment() {
        // Required empty public constructor
    }

    public static GenderFragment newInstance() {
        return new GenderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.gender_fragment, container, false);

        womanButton = rootView.findViewById(R.id.woman_button);
        manButton = rootView.findViewById(R.id.man_button);
        showGenderCheckbox = rootView.findViewById(R.id.show_gender_checkbox);

        womanButton.setOnClickListener(v -> setWomanButtonClickedState());
        manButton.setOnClickListener(v -> setManButtonClickedState());

        return rootView;
    }

    @Override
    public void onDestroyView() {
        MainActivity mainActivity = (MainActivity) getActivity();
        super.onDestroyView();
        mainActivity.setShowGender(showGenderCheckbox.isChecked());
    }

    private void setWomanButtonClickedState() {
        MainActivity mainActivity = (MainActivity) getActivity();

        manButton.setBackgroundColor(getResources().getColor(R.color.white, mainActivity.getTheme()));
        manButton.setTextColor(getResources().getColor(R.color.darker_grey, mainActivity.getTheme()));

        if (currentValue == 1) {
            womanButton.setBackgroundColor(getResources().getColor(R.color.white, mainActivity.getTheme()));
            womanButton.setTextColor(getResources().getColor(R.color.darker_grey, mainActivity.getTheme()));
            currentValue = 0;
        } else {
            womanButton.setBackgroundColor(getResources().getColor(R.color.darker_grey, mainActivity.getTheme()));
            womanButton.setTextColor(getResources().getColor(R.color.white, mainActivity.getTheme()));
            currentValue = 1;
        }

        updateMainActivity();
    }

    private void setManButtonClickedState() {
        MainActivity mainActivity = (MainActivity) getActivity();

        womanButton.setBackgroundColor(getResources().getColor(R.color.white, mainActivity.getTheme()));
        womanButton.setTextColor(getResources().getColor(R.color.darker_grey, mainActivity.getTheme()));

        if (currentValue == -1) {
            manButton.setBackgroundColor(getResources().getColor(R.color.white, mainActivity.getTheme()));
            manButton.setTextColor(getResources().getColor(R.color.darker_grey, mainActivity.getTheme()));
            currentValue = 0;
        } else {
            manButton.setBackgroundColor(getResources().getColor(R.color.darker_grey, mainActivity.getTheme()));
            manButton.setTextColor(getResources().getColor(R.color.white, mainActivity.getTheme()));
            currentValue = -1;
        }

        updateMainActivity();
    }

    private void updateMainActivity() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.setGender(currentValue, showGenderCheckbox.isChecked());
    }
}
