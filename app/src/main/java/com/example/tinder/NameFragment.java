package com.example.tinder;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

public class NameFragment extends Fragment {
    TextInputEditText nameInput;

    public NameFragment() {
        // Required empty public constructor
    }

    public static NameFragment newInstance() {
        return new NameFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.name_fragment, container, false);

        nameInput = rootView.findViewById(R.id.name_field);
        nameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity mainActivity = (MainActivity) getActivity();
                String text = nameInput.getText().toString();
                boolean validName = text != null && text != "";
                mainActivity.setName(validName, text);
            }
        });


        return rootView;
    }
}