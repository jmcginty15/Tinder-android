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

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BirthdayFragment extends Fragment {
    String DATE_PATTERN = "MM/dd/yyyy";
    TextInputEditText birthdayInput;

    public BirthdayFragment() {
        // Required empty public constructor
    }

    public static BirthdayFragment newInstance() {
        return new BirthdayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.birthday_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        birthdayInput = view.findViewById(R.id.birthday_field);
        birthdayInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                MainActivity mainActivity = (MainActivity) getActivity();
                String text = birthdayInput.getText().toString();
                boolean validBirthday;

                SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
                dateFormat.setLenient(false);

                try {
                    dateFormat.parse(text);
                    validBirthday = true;
                } catch (ParseException e) {
                    validBirthday = false;
                }

                mainActivity.setBirthday(validBirthday, text);
            }
        });
    }
}
